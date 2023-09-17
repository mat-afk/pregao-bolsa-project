package entities;

import ativos.Ativo;
import estruturasdedados.DynamicArray;

public class Carteira {

    private DynamicArray<Ativo> ativos;

    public Carteira() {
        this.ativos = new DynamicArray<>();
    }

    public void addAtivo(Ativo ativo) {
        ativos.add(ativo);
    }

    public void removerAtivo(Ativo ativo) {
        ativos.remove(ativo);
    }

    public DynamicArray<Ativo> getAtivos() {
        return ativos;
    }

    public double calcularTotal() {
        double valorTotal = 0.0;
        for (Ativo acao : ativos) {
            valorTotal += acao.calcularValor();
        }
        return valorTotal;
    }
}
