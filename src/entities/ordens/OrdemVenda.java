package entities.ordens;

import entities.ativos.Ativo;
import entities.Investidor;

import java.time.LocalDateTime;

public class OrdemVenda extends Ordem {

    public OrdemVenda(Ativo ativo, Investidor investidor, double preco, int quantidade) {
        super(ativo, investidor, preco, quantidade);
    }

    public OrdemVenda(Ativo ativo, Investidor investidor, double preco, int quantidade, LocalDateTime data) {
        super(ativo, investidor, preco, quantidade, data);
    }
}
