package ordens;

import ativos.Ativo;
import entities.Investidor;

public class OrdemCompra extends Ordem {

    public OrdemCompra(Ativo ativo, Investidor investidor, double preco, int quantidade) {
        super(ativo, investidor, preco, quantidade);
    }
}
