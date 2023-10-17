package entities;
import ativos.Registro;
import database.DatabaseManager;
import estruturasdedados.Fila;
import estruturasdedados.LinkedList;
import ordens.Ordem;
import ordens.OrdemCompra;
import ordens.OrdemVenda;

public class Bolsa {

    private final String nome;
    private final LinkedList<Corretora> corretoras;
    private final LinkedList<Empresa> empresas;
    private final Fila<Ordem> ordensDeCompra;
    private final Fila<Ordem> ordensDeVenda;

    public Bolsa(String nome) {
        this.nome = nome;
        this.corretoras = new LinkedList<>();
        this.empresas = new LinkedList<>();
        this.ordensDeCompra = new Fila<>();
        this.ordensDeVenda = new Fila<>();
    }

    public void addCorretora(Corretora corretora) {
        corretoras.addLast(corretora);
        DatabaseManager.gravarCorretora(corretora);
    }

    public void addEmpresa(Empresa empresa) {
        empresas.addLast(empresa);
        DatabaseManager.gravarEmpresa(empresa);
    }

    public void processarOrdensDeCompra() {
        for(Corretora corretora : corretoras) {
            for(Ordem ordem : corretora.getOrdens()) {
                if(ordem instanceof OrdemCompra) {
                    ordensDeVenda.enqueue(ordem);
                    executarOrdens();
                }
            }
        }
    }

    public void processarOrdensDeVenda() {
        for(Corretora corretora : corretoras) {
            for(Ordem ordem : corretora.getOrdens()) {
                if(ordem instanceof OrdemVenda) {
                    ordensDeVenda.enqueue(ordem);
                    executarOrdens();
                }
            }
        }
    }

    private void executarOrdens() {
        boolean correspondenciaEncontrada = true;

        while (correspondenciaEncontrada) {
            correspondenciaEncontrada = false;

            for (Ordem compra : ordensDeCompra) {
                for (Ordem venda : ordensDeVenda) {
                    if (compra.getAtivo().equals(venda.getAtivo()) && compra.getPreco() >= venda.getPreco()) {

                        int quantidadeNegociada = Math.min(compra.getQuantidade(), venda.getQuantidade());

                        Registro r1 = new Registro(venda, compra.getPreco(), quantidadeNegociada);
                        venda.getAtivo().getHistorico().addRegistro(r1);
                        DatabaseManager.gravarRegistro(r1);

                        Registro r2 = new Registro(compra, compra.getPreco(), quantidadeNegociada);
                        compra.getAtivo().getHistorico().addRegistro(r2);
                        DatabaseManager.gravarRegistro(r2);

                        venda.getInvestidor().depositar(quantidadeNegociada * compra.getPreco());
                        compra.getInvestidor().descontar(quantidadeNegociada * compra.getPreco());

                        compra.setQuantidade(compra.getQuantidade() - quantidadeNegociada);
                        venda.setQuantidade(venda.getQuantidade() - quantidadeNegociada);

                        if (compra.getQuantidade() == 0) {
                            ordensDeCompra.dequeue();
                            compra.getInvestidor().getCarteira().addAtivo(compra.getAtivo());
                        }
                        if (venda.getQuantidade() == 0) {
                            ordensDeVenda.dequeue();
                            venda.getInvestidor().getCarteira().removerAtivo(venda.getAtivo());
                        }

                        correspondenciaEncontrada = true;
                    }
                }
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public LinkedList<Corretora> getCorretoras() {
        return corretoras;
    }

    public LinkedList<Empresa> getEmpresas() {
        return empresas;
    }

    public Fila<Ordem> getOrdensDeCompra() {
        return ordensDeCompra;
    }

    public Fila<Ordem> getOrdensDeVenda() {
        return ordensDeVenda;
    }
}
