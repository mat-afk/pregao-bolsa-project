package ordens;

import ativos.Ativo;
import entities.Investidor;

public class OrdemVenda extends Ordem {

    public OrdemVenda(Ativo ativo, Investidor investidor, double preco, int quantidade) {
        super(ativo, investidor, preco, quantidade);
    }
}
