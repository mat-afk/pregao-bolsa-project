package ordens;

import ativos.Ativo;
import entities.Investidor;

import java.time.LocalDateTime;

public abstract class Ordem {
    private Ativo ativo;
    private Investidor investidor;
    private double preco;
    private int quantidade;
    private LocalDateTime dataEmissao;

    public Ordem(Ativo ativo, Investidor investidor, double preco, int quantidade) {
        this.ativo = ativo;
        this.investidor = investidor;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataEmissao = LocalDateTime.now();
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public double getPreco() {
        return preco;
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
}
