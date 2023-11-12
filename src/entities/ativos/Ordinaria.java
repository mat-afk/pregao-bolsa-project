package entities.ativos;

import entities.*;

public class Ordinaria extends Ativo {

    public Ordinaria(Empresa empresa, double cotacao) {
        super(empresa, Natureza.ORDINARIA, cotacao);
    }

    public Ordinaria(Empresa empresa) {
        super(empresa, Natureza.ORDINARIA);
    }

    public Ordinaria(String simbolo, Empresa empresa, double cotacao) {
        super(simbolo, empresa, Natureza.ORDINARIA, cotacao);
    }

    public Ordinaria(String simbolo, Empresa empresa) {
        super(simbolo, empresa, Natureza.ORDINARIA);
    }

    public Ordinaria(int id, String simbolo, Empresa empresa, double cotacao) {
        super(id, simbolo, empresa, Natureza.ORDINARIA, cotacao);
    }

    public Ordinaria(int id, String simbolo, Empresa empresa) {
        super(id, simbolo, empresa, Natureza.ORDINARIA);
    }

    @Override
    public String generateSimbolo() {
        final String NUMERO_DE_ACAO = "3";
        return getEmpresa().getSimbolo() + NUMERO_DE_ACAO;
    }

    @Override
    public String formatToSave() {
        return String.format("%-5s%-10s%-30s%-15s%-15s",
                getId(), getSimbolo(), getEmpresa().getNome(), getCotacao(), getNatureza());
    }
}
