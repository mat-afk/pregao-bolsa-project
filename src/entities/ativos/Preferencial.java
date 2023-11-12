package entities.ativos;

import entities.*;

public class Preferencial extends Ativo {

    public Preferencial(String simbolo, Empresa empresa, double cotacao) {
        super(simbolo, empresa, Natureza.PREFERENCIAL, cotacao);
    }

    public Preferencial(String simbolo, Empresa empresa) {
        super(simbolo, empresa, Natureza.ORDINARIA);
    }

    public Preferencial(int id, String simbolo, Empresa empresa, double cotacao) {
        super(id, simbolo, empresa, Natureza.ORDINARIA, cotacao);
    }

    public Preferencial(int id, String simbolo, Empresa empresa) {
        super(id, simbolo, empresa, Natureza.ORDINARIA);
    }

    @Override
    public String generateSimbolo() {
        final String NUMERO_DE_ACAO = "4";
        return getEmpresa().getSimbolo() + NUMERO_DE_ACAO;
    }
}
