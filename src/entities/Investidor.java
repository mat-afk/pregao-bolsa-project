package entities;

import entities.ativos.Ativo;
import org.jetbrains.annotations.NotNull;

public abstract class Investidor implements Comparable<Empresa> {

    private String nome;
    private double saldo;
    private Carteira carteira;
    private Corretora corretora;
    private Bolsa bolsa;

    public Investidor(String nome, double saldo, Corretora corretora, Bolsa bolsa) {
        this.nome = nome;
        this.saldo = saldo;
        this.carteira = new Carteira();
        this.corretora = corretora;
        this.bolsa = bolsa;
    }

    public abstract void depositar(double valor);

    public abstract void descontar(double valor);

    public abstract void solicitarCompra(Ativo acao, double preco, int quantidade);

    public abstract void solicitarVenda(Ativo acao, double preco, int quantidade);

    public abstract int getId();

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

    public abstract String getCpf();

    public abstract String getCnpj();

    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }

    public abstract int compareTo(@NotNull InvestidorFisico otherInvestidor);

    public abstract int compareTo(@NotNull Empresa otherEmpresa);
}
