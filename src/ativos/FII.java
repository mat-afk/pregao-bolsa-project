package ativos;

import entities.Empresa;

public class FII extends Ativo {

    public FII(int id, String nome, String codigo, Empresa empresa, double cotacao) {
        super(id, nome, codigo, empresa, cotacao);
    }
}
