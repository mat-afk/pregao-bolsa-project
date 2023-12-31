package entities;

import dao.InvestidorDAO;
import entities.ativos.Ativo;
import main.Validador;
import entities.ordens.*;
import org.jetbrains.annotations.NotNull;

public class InvestidorFisico extends Investidor {

    private static int count = 0;
    private final int id;
    private String cpf;

    public InvestidorFisico(int id, String nome, String cpf, double saldo, Corretora corretora, Bolsa bolsa) {
        super(nome, saldo, corretora, bolsa);
        this.id = id;
        setCpf(cpf);
    }

    public InvestidorFisico(int id, String nome, String cpf, Corretora corretora, Bolsa bolsa) {
        this(id, nome, cpf, 0.0, corretora, bolsa);
    }

    public InvestidorFisico(String nome, String cpf, double valor, Corretora corretora, Bolsa bolsa) {
        this(++count, nome, cpf, valor, corretora, bolsa);
    }

    public InvestidorFisico(String nome, String cpf, Corretora corretora, Bolsa bolsa) {
        this(nome, cpf, 0.0, corretora, bolsa);
    }

    public InvestidorFisico(String nome, String cpf, Corretora corretora) {
        this(nome, cpf, 0.0, corretora, null);
    }

    public InvestidorFisico(int id, String nome, String cpf, double saldo, Corretora corretora) {
        this(id, nome, cpf, saldo, corretora, null);
    }

    public void depositar(double valor) {
        setSaldo(getSaldo() + valor);
        InvestidorDAO.update(this);
    }

    public void descontar(double valor) {
        setSaldo(getSaldo() - valor);
        InvestidorDAO.update(this);
    }

    public void solicitarCompra(Ativo acao, double preco, int quantidade) {
        getCorretora().receberOrdem(new OrdemCompra(acao, this, preco, quantidade));
        getCarteira().addAtivo(acao);
    }

    public void solicitarVenda(Ativo acao, double preco, int quantidade) {
        InvestidorDAO.update(this);
        if(getCarteira().ativoInCarteira(acao)) {
            getCorretora().receberOrdem(new OrdemVenda(acao, this, preco, quantidade));
        }
        throw new IllegalArgumentException("Você não pode vender uma ação que você não possui.");
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getCnpj() {
        return null;
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
        return super.getSaldo();
    }

    public void setSaldo(double saldo) {
        super.setSaldo(saldo);
        InvestidorDAO.update(this);
    }

    public Carteira getCarteira() {
        return super.getCarteira();
    }

    public void setCarteira(Carteira carteira) {
        super.setCarteira(carteira);
        InvestidorDAO.update(this);
    }

    public Corretora getCorretora() {
        return super.getCorretora();
    }

    public String formatToSave() {
        return String.format("%-5s%-30s%-20s%-15s%-30s",
                getId(), getNome(), getCpf(), getSaldo(), getCorretora().getNome());
    }

    @Override
    public int compareTo(@NotNull InvestidorFisico otherInvestidor) {
        return this.getNome().compareTo(otherInvestidor.getNome());
    }

    @Override
    public int compareTo(@NotNull Empresa empresa) {
        return this.getNome().compareTo(empresa.getNome());
    }

    @Override
    public String toString() {
        return "InvestidorFisico{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
