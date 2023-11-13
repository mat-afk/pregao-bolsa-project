package entities.ordens;

import entities.Empresa;
import entities.Investidor;
import entities.ativos.Ativo;

import java.time.LocalDateTime;

public abstract class Ordem {

    private static int count = 0;
    private int id;
    private Ativo ativo;
    private Investidor investidor;
    private double preco;
    private int quantidade;
    private TipoOrdem tipo;
    private LocalDateTime dataEmissao;

    public Ordem(int id, Ativo ativo, Investidor investidor, double preco, int quantidade, TipoOrdem tipo, LocalDateTime dataEmissao) {
        this.id = id;
        this.ativo = ativo;
        this.investidor = investidor;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataEmissao = dataEmissao;
        this.tipo = tipo;
    }

    public Ordem(int id, Ativo ativo, Investidor investidor, double preco, int quantidade, TipoOrdem tipo) {
        this(id, ativo, investidor, preco, quantidade, tipo, LocalDateTime.now());
    }

    public Ordem(Ativo ativo, Investidor investidor, double preco, int quantidade, TipoOrdem tipo, LocalDateTime dataEmissao) {
        this(++count, ativo, investidor, preco, quantidade, tipo, dataEmissao);
    }

    public Ordem(Ativo ativo, Investidor investidor, double preco, int quantidade, TipoOrdem tipo) {
        this(++count, ativo, investidor, preco, quantidade, tipo, LocalDateTime.now());
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Ordem.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public TipoOrdem getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrdem tipo) {
        this.tipo = tipo;
    }

    public String formatToSave() {
        if(investidor instanceof Empresa) {
            return String.format("%-5s%-10s%-30s%-30s%-15s%-5s%-10s%-20s",
                    getId(), getAtivo().getSimbolo(), getInvestidor().getNome(), getInvestidor().getCnpj(), getPreco(), getQuantidade(), getTipo(), getDataEmissao());
        }

        return String.format("%-5s%-10s%-30s%-30s%-15s%-5s%-10s%-20s",
                getId(), getAtivo().getSimbolo(), getInvestidor().getNome(), getInvestidor().getCpf(), getPreco(), getQuantidade(), getTipo(), getDataEmissao());
    }

    @Override
    public String toString() {
        return "Ordem{" +
                "id=" + id +
                ", ativo=" + ativo +
                ", investidor=" + investidor +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", tipo=" + tipo +
                ", dataEmissao=" + dataEmissao +
                '}';
    }
}
