package entities;

import entities.ativos.Ativo;
import estruturasdedados.ArvoreBinaria;
import estruturasdedados.LinkedList;

public class Carteira {

    private final LinkedList<Ativo> ativos;

    public Carteira() {
        this.ativos = new LinkedList<>();
    }

    public void addAtivo(Ativo ativo) {
        ativos.addLast(ativo);
    }

    public void removerAtivo(Ativo ativo) {
        ativos.remove(ativo);
    }

    public LinkedList<Ativo> getAtivos() {
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
            if(ativo != null && ativo == acao) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carteira{ ativos=");

        for(Ativo ativo : ativos) {
            sb.append(ativo.toString());
        }

        sb.append("}");

        return sb.toString();
    }
}
