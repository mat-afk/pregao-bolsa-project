package ativos;

import java.util.Date;

public abstract class Ativo {

    private int id;
    private String nome;
    private String codigo;
    private double cotacao;
    private Date dataNegociacao;

    public Ativo(int id, String nome, String codigo, double cotacao) {
        this.id = id;
        this.nome = nome;
        setCodigo(codigo);
        this.cotacao = cotacao;
    }

    public Ativo(int id, String nome, String codigo) {
        this(id, nome, codigo, 0.0);
    }

    public abstract double calcularValor();

    public boolean validarCodigo(String codigo) {

        /*
        XXXX = 04 letras maiúsculas que representam o nome do emissor
        Y = 01 número que representa o tipo da ação, adotado 3 para ordinária; 4 para preferencial; 5, 6, 7, 8 para preferenciais classes A, B, C e D, respectivamente
        */

        return codigo.matches("^[A-Z]{4}[345678]$");
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if(!validarCodigo(codigo)) {
            throw new IllegalArgumentException("Formato de código incorreto.");
        }
        this.codigo = codigo;
    }

    public double getCotacao() {
        return cotacao;
    }

    public Date getDataNegociacao() {
        return dataNegociacao;
    }
}
