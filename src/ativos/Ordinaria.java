package ativos;

import entities.*;

public class Ordinaria extends Ativo {

    public Ordinaria(int id, String nome, String codigo, Empresa empresa, double cotacao) {
        super(id, nome, codigo, empresa, cotacao);
    }

    public Ordinaria(int id, String nome, String codigo, Empresa empresa) {
        super(id, nome, codigo, empresa);
    }

    @Override
    public double calcularValor() {
        return 0;
    }
}
