package entities.ativos;

import entities.Empresa;

public class FII extends Ativo {

    public FII(String simbolo, Empresa empresa, double cotacao) {
        super(simbolo, empresa, Natureza.FII, cotacao);
    }

    public FII(String simbolo, Empresa empresa) {
        super(simbolo, empresa, Natureza.FII);
    }

    public FII(int id, String simbolo, Empresa empresa, double cotacao) {
        super(id, simbolo, empresa, Natureza.FII, cotacao);
    }

    public FII(int id, String simbolo, Empresa empresa) {
        super(id, simbolo, empresa, Natureza.FII);
    }

    @Override
    public String generateSimbolo() {
        final String NUMERO_DE_ACAO = "11";
        return getEmpresa().getSimbolo() + NUMERO_DE_ACAO;
    }
}
