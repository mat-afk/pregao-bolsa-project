package ativos;

import entities.Empresa;

public class FII extends Ativo {

    public FII(int id, String codigo, Empresa empresa, double cotacao) {
        super(id, codigo, empresa, cotacao);
    }
}
