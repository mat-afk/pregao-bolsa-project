package entities;

import dao.AtivoDAO;
import dao.EmpresaDAO;
import entities.ativos.Ativo;
import entities.ordens.OrdemVenda;

public class Empresa extends Investidor {
    private static int count = 0;
    private final int id;
    private String simbolo;
    private double capitalizacao;
    private String setor;
    private Bolsa bolsa;

    public Empresa(int id, String nome, String simbolo, double capitalizacao, String setor, Corretora corretora) {
        super(nome, capitalizacao, corretora);
        this.id = id;
        this.simbolo = simbolo;
        this.capitalizacao = capitalizacao;
        this.setor = setor;
    }

    public Empresa(int id, String nome, String simbolo, double capitalizacao, String setor) {
        this(id, nome, simbolo, capitalizacao, setor, null);
    }

    public Empresa(String nome, String simbolo, double capitalizacao, String setor, Corretora corretora) {
        this(++count, nome, simbolo, capitalizacao, setor);
    }

    public Empresa(String nome, String simbolo, double capitalizacao, String setor) {
        this(++count, nome, simbolo, capitalizacao, setor, null);
    }

    @Override
    public void depositar(double valor) {
        setSaldo(getSaldo() + valor);
    }

    @Override
    public void descontar(double valor) {
        setSaldo(getSaldo() - valor);
    }

    @Override
    public void solicitarCompra(Ativo acao, double preco, int quantidade) {
        throw new RuntimeException("Você não pode comprar essa ação como empresa");
    }

    public void solicitarVenda(Ativo acao, double preco, int quantidade) {
        if(getCarteira().ativoInCarteira(acao)) {
            getCorretora().receberOrdem(new OrdemVenda(acao, this, preco, quantidade));
        } else {
            throw new IllegalArgumentException("Você não pode vender uma ação que você não possui.");
        }
    }


    public void lancarAtivo(Ativo acao) {
        getCarteira().addAtivo(acao);
        AtivoDAO.save(acao);
        if(getCarteira().ativoInCarteira(acao)) {
            solicitarVenda(acao, acao.getCotacao(), 100);
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return super.getNome();
    }

    public void setNome(String nome) {
        super.setNome(nome);
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public double getCapitalizacao() {
        return capitalizacao;
    }

    public void setCapitalizacao(double capitalizacao) {
        this.capitalizacao = capitalizacao;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }

    public void setCorretora(Corretora corretora) {
        super.setCorretora(corretora);
    }

    public Carteira getCarteira() {
        return super.getCarteira();
    }

    public String formatToSave() {
        return String.format("%-5s%-30s%-10s%-20s%-20s",
                getId(), getNome(), getSimbolo(), getCapitalizacao(), getSetor());
    }
}
