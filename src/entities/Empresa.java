package entities;

import dao.AtivoDAO;
import entities.ativos.Ativo;
import entities.ordens.OrdemVenda;
import main.Validador;
import org.jetbrains.annotations.NotNull;

public class Empresa extends Investidor {
    private static int count = 0;
    private final int id;
    private String cnpj;
    private String simbolo;
    private double capitalizacao;
    private String setor;

    public Empresa(int id, String nome, String cnpj, String simbolo, double capitalizacao, String setor, Corretora corretora, Bolsa bolsa) {
        super(nome, capitalizacao, corretora, bolsa);
        this.id = id;
        setCnpj(cnpj);
        this.simbolo = simbolo;
        this.capitalizacao = capitalizacao;
        this.setor = setor;
    }

    public Empresa(int id, String nome, String cnpj, String simbolo, double capitalizacao, String setor, Bolsa bolsa) {
        this(id, nome, cnpj, simbolo, capitalizacao, setor, null, bolsa);
    }

    public Empresa(String nome, String cnpj, String simbolo, double capitalizacao, String setor, Corretora corretora, Bolsa bolsa) {
        this(++count, nome, cnpj, simbolo, capitalizacao, setor, bolsa);
    }

    public Empresa(String nome, String cnpj, String simbolo, double capitalizacao, String setor, Bolsa bolsa) {
        this(++count, nome, cnpj, simbolo, capitalizacao, setor, null, bolsa);
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
        return super.getBolsa();
    }

    public void setBolsa(Bolsa bolsa) {
        super.setBolsa(bolsa);
    }

    public void setCorretora(Corretora corretora) {
        super.setCorretora(corretora);
    }

    @Override
    public String getCpf() {
        return null;
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if(Validador.validarCNPJ(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        this.cnpj = cnpj;
    }

    public Carteira getCarteira() {
        return super.getCarteira();
    }

    public String formatToSave() {
        return String.format("%-5s%-30s%-30s%-10s%-20s%-20s",
                getId(), getNome(), getCnpj(), getSimbolo(), getCapitalizacao(), getSetor());
    }

    @Override
    public int compareTo(@NotNull InvestidorFisico otherInvestidor) {
        return this.getNome().compareTo(otherInvestidor.getNome());
    }

    @Override
    public int compareTo(@NotNull Empresa otherEmpresa) {
        return this.getNome().compareTo(otherEmpresa.getNome());
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nome='" + getNome() + '\'' +
                ", simbolo='" + simbolo + '\'' +
                ", capitalizacao=" + capitalizacao +
                ", setor='" + setor + '\'' +
                "}\n";
    }
}
