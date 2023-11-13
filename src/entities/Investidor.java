package entities;

import entities.ativos.Ativo;

public abstract class Investidor {

    private String nome;
    private double saldo;
    private Carteira carteira;
    private Corretora corretora;

    public Investidor(String nome, double saldo, Corretora corretora) {
        this.nome = nome;
        this.saldo = saldo;
        this.carteira = new Carteira();
        this.corretora = corretora;
    }

    public abstract void depositar(double valor);

    public abstract void descontar(double valor);

    public abstract void solicitarCompra(Ativo acao, double preco, int quantidade);

    public abstract void solicitarVenda(Ativo acao, double preco, int quantidade);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public Corretora getCorretora() {
        return corretora;
    }

    public void setCorretora(Corretora corretora) {
        this.corretora = corretora;
    }

    public String getCpf() {
        return null;
    }

    public String getCnpj() {
        return null;
    }
}
