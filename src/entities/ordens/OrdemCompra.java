package entities.ordens;

import entities.Investidor;
import entities.ativos.Ativo;
import entities.InvestidorFisico;

import java.time.LocalDateTime;

public class OrdemCompra extends Ordem {

    public OrdemCompra(int id, Ativo ativo, Investidor investidor, double preco, int quantidade, LocalDateTime dataEmissao) {
        super(id, ativo, investidor, preco, quantidade, TipoOrdem.COMPRA, dataEmissao);
    }

    public OrdemCompra(int id, Ativo ativo, Investidor investidor, double preco, int quantidade) {
        super(id, ativo, investidor, preco, quantidade, TipoOrdem.COMPRA);
    }

    public OrdemCompra(Ativo ativo, Investidor investidor, double preco, int quantidade, LocalDateTime dataEmissao) {
        super(ativo, investidor, preco, quantidade, TipoOrdem.COMPRA, dataEmissao);
    }

    public OrdemCompra(Ativo ativo, Investidor investidor, double preco, int quantidade) {
        super(ativo, investidor, preco, quantidade, TipoOrdem.COMPRA);
    }
}
