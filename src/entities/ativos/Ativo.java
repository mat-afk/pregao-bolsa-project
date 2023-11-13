package entities.ativos;

import entities.Empresa;
import entities.Historico;
import main.Validador;

public abstract class Ativo {

    private static int count = 0;
    private final int id;
    private String simbolo;
    private final Empresa empresa;
    private double cotacao;
    private final Historico historico;
    private final Natureza natureza;

    public Ativo(int id, String simbolo, Empresa empresa, Natureza natureza, double cotacao) {
        this.id = id;
        setSimbolo(simbolo);
        this.empresa = empresa;
        this.natureza = natureza;
        this.cotacao = cotacao;
        this.historico = new Historico();
    }

    public Ativo(int id, String simbolo, Empresa empresa, Natureza natureza) {
        this(id, simbolo, empresa, natureza, 0.0);
    }

    public Ativo(Empresa empresa, Natureza natureza, double cotacao) {
        this.id = ++count;
        this.empresa = empresa;
        setSimbolo(generateSimbolo());
        this.natureza = natureza;
        this.cotacao = cotacao;
        this.historico = new Historico();
    }

    public Ativo(Empresa empresa, Natureza natureza) {
        this(empresa, natureza, 0.0);
    }

    public Ativo(String simbolo, Empresa empresa, Natureza natureza, double cotacao) {
        this(++count, simbolo, empresa, natureza, 0.0);
    }

    public Ativo(String simbolo, Empresa empresa, Natureza natureza) {
        this(simbolo, empresa, natureza, 0.0);
    }

    public abstract String formatToSave();

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Ativo.count = count;
    }

    public int getId() {
        return id;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {

        if(!Validador.validarCodigo(simbolo)) {
            throw new IllegalArgumentException("Formato de código incorreto.");
        }
        this.simbolo = simbolo;
    }

    public abstract String generateSimbolo();

    public Empresa getEmpresa() {
        return empresa;
    }

    public double getCotacao() {
        return cotacao;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }

    public Historico getHistorico() {
        return historico;
    }

    public Natureza getNatureza() {
        return natureza;
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "id=" + id +
                ", simbolo='" + simbolo + '\'' +
                ", empresa=" + empresa +
                ", cotacao=" + cotacao +
                ", historico=" + historico +
                ", natureza=" + natureza +
                '}';
    }
}
