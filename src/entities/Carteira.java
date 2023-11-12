package entities;

import entities.ativos.Ativo;
import estruturasdedados.ArvoreBinaria;

public class Carteira {

    private final ArvoreBinaria<Ativo> ativos;

    public Carteira() {
        this.ativos = new ArvoreBinaria<>(null, (o1, o2) -> (int) (o1.getCotacao() - o2.getCotacao()));
    }

    public void addAtivo(Ativo ativo) {
        ativos.add(ativo);
    }

    public void removerAtivo(Ativo ativo) {
        ativos.remover(ativo);
    }

    public ArvoreBinaria<Ativo> getAtivos() {
        return ativos;
    }

    public double calcularTotal() {
        double valorTotal = 0.0;
        for (Ativo acao : ativos) {
            valorTotal += acao.getCotacao();
        }
        return valorTotal;
    }

    public boolean ativoInCarteira(Ativo acao) {
        for(Ativo ativo : ativos) {
            if(ativo.equals(acao)) {
                return true;
            }
        }
        return false;
    }
}
