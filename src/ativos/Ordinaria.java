package ativos;

import entities.*;

public class Ordinaria extends Ativo {

    public Ordinaria(int id, String codigo, Empresa empresa, double cotacao) {
        super(id, codigo, empresa, cotacao);
    }

    public Ordinaria(int id, String codigo, Empresa empresa) {
        super(id, codigo, empresa);
    }
}
