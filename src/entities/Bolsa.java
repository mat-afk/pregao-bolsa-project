package entities;
import dao.CorretoraDAO;
import dao.EmpresaDAO;
import dao.OrdemDAO;
import dao.RegistroDAO;
import estruturasdedados.Fila;
import estruturasdedados.LinkedList;
import entities.ordens.Ordem;
import entities.ordens.OrdemCompra;
import entities.ordens.OrdemVenda;

import javax.sound.midi.Soundbank;

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
        CorretoraDAO.save(corretora);
    }

    public void addEmpresa(Empresa empresa) {
        empresas.addLast(empresa);
        empresa.setBolsa(this);
        EmpresaDAO.save(empresa);
    }

    public void processarOrdensDeCompra() {
        for(Corretora corretora : corretoras) {
            for(Ordem ordem : corretora.getOrdens()) {
                if(ordem instanceof OrdemCompra) {
                    ordensDeCompra.enqueue(ordem);
                    corretora.getOrdens().dequeue();
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
                    corretora.getOrdens().dequeue();
                    executarOrdens();
                }
            }
        }
    }

    public void processarOrdens() {
        processarOrdensDeVenda();
        processarOrdensDeCompra();
    }

    private void executarOrdens() {
        boolean correspondenciaEncontrada = true;

        while (correspondenciaEncontrada) {
            correspondenciaEncontrada = false;

            for (Ordem compra : ordensDeCompra) {
                for (Ordem venda : ordensDeVenda) {
                    if(encontrarCorrespondencia(compra, venda)) {
                        int quantidadeNegociada = Math.min(compra.getQuantidade(), venda.getQuantidade());

                        Registro registro = criarRegistro(compra, venda, quantidadeNegociada);
                        atualizarCarteirasEHistoricos((OrdemCompra) compra, (OrdemVenda) venda, quantidadeNegociada, registro);

                        RegistroDAO.save(registro);

                        correspondenciaEncontrada = true;
                    }
                }
            }
        }
    }


    private boolean encontrarCorrespondencia(Ordem compra, Ordem venda) {
        return compra.getAtivo().getSimbolo().equals(venda.getAtivo().getSimbolo()) && compra.getPreco() >= venda.getPreco();
    }

    private Registro criarRegistro(Ordem compra, Ordem venda, int quantidadeNegociada) {
        return new Registro(
                compra.getAtivo(),
                (OrdemVenda) venda,
                (OrdemCompra) compra,
                compra.getPreco(),
                quantidadeNegociada
        );
    }

    private void atualizarCarteirasEHistoricos(OrdemCompra compra, OrdemVenda venda, int quantidadeNegociada, Registro registro) {
        compra.getAtivo().getHistorico().addRegistro(registro);
        venda.getAtivo().getHistorico().addRegistro(registro);

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
