package entities.ordens;

import entities.Investidor;
import entities.ativos.Ativo;
import entities.InvestidorFisico;

import java.time.LocalDateTime;

public class OrdemVenda extends Ordem {

    public OrdemVenda(int id, Ativo ativo, Investidor investidor, double preco, int quantidade, LocalDateTime dataEmissao) {
        super(id, ativo, investidor, preco, quantidade, TipoOrdem.VENDA, dataEmissao);
    }

    public OrdemVenda(int id, Ativo ativo, Investidor investidor, double preco, int quantidade) {
        super(id, ativo, investidor, preco, quantidade, TipoOrdem.VENDA);
    }

    public OrdemVenda(Ativo ativo, Investidor investidor, double preco, int quantidade, LocalDateTime dataEmissao) {
        super(ativo, investidor, preco, quantidade, TipoOrdem.VENDA, dataEmissao);
    }

    public OrdemVenda(Ativo ativo, Investidor investidor, double preco, int quantidade) {
        super(ativo, investidor, preco, quantidade, TipoOrdem.VENDA);
    }
}
