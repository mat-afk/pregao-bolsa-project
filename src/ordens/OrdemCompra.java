package ordens;

import ativos.Ativo;
import entities.Investidor;

import java.time.LocalDateTime;

public class OrdemCompra extends Ordem {

    public OrdemCompra(Ativo ativo, Investidor investidor, double preco, int quantidade) {
        super(ativo, investidor, preco, quantidade);
    }

    public OrdemCompra(Ativo ativo, Investidor investidor, double preco, int quantidade, LocalDateTime data) {
        super(ativo, investidor, preco, quantidade, data);
    }
}
