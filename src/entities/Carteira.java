package entities;

import ativos.Ativo;
import estruturasdedados.LinkedList;

public class Carteira {

    private LinkedList<Ativo> ativos;

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
            if(ativo.equals(acao)) {
                return true;
            }
        }
        return false;
    }
}
