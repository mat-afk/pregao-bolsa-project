package ativos;

import entities.*;

public class Preferencial extends Ativo {
    public Preferencial(int id, String nome, String codigo, Empresa empresa, double cotacao) {
        super(id, nome, codigo, empresa, cotacao);
    }

    public Preferencial(int id, String nome, String codigo, Empresa empresa) {
        super(id, nome, codigo, empresa);
    }

    @Override
    public double calcularValor() {
        return 0;
    }
}
