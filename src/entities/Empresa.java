package entities;

public class Empresa {
    private final int id;
    private String nome;
    private String simbolo;
    private double capitalizacao;
    private String setor;

    public Empresa(int id, String nome, String simbolo, double capitalizacao, String setor) {
        this.id = id;
        this.nome = nome;
        this.simbolo = simbolo;
        this.capitalizacao = capitalizacao;
        this.setor = setor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public double getCapitalizacao() {
        return capitalizacao;
    }

    public void setCapitalizacao(double capitalizacao) {
        this.capitalizacao = capitalizacao;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}
