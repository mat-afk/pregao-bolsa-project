package ativos;

import entities.*;

public class Preferencial extends Ativo {
    public Preferencial(int id, String codigo, Empresa empresa, double cotacao) {
        super(id, codigo, empresa, cotacao);
    }

    public Preferencial(int id, String codigo, Empresa empresa) {
        super(id, codigo, empresa);
    }
}
