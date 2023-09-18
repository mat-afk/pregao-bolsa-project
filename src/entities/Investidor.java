package entities;

import ativos.Ativo;
import main.Validador;
import ordens.*;

public class Investidor {

    private final int id;
    private final String nome;
    private String cpf;
    private double saldo;
    private Carteira carteira;
    private final Corretora corretora;

    public Investidor(int id, String nome, String cpf, double valor, Corretora corretora) {
        this.id = id;
        this.nome = nome;
        setCpf(cpf);
        this.saldo = valor;
        this.corretora = corretora;
        this.carteira = new Carteira();
    }

    public Investidor(int id, String nome, String cpf, Corretora corretora) {
        this(id, nome, cpf, 0.0, corretora);
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void descontar(double valor) {
        saldo -= valor;
    }

    public void solicitarCompra(Ativo acao, double preco, int quantidade) {
        corretora.receberOrdem(new OrdemCompra(acao, this, preco, quantidade));
        carteira.addAtivo(acao);
    }

    public void solicitarVenda(Ativo acao, double preco, int quantidade) {
        if(carteira.ativoInCarteira(acao)) {
            corretora.receberOrdem(new OrdemVenda(acao, this, preco, quantidade));
        }
        throw new IllegalArgumentException("Você não pode vender uma ação que você não possui.");
    }

    public void solicitarVenda(Ativo acao, int quantidade) {
        solicitarVenda(acao, 0.0, quantidade);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        if(!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$") && !Validador.validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.cpf = cpf;
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
}
