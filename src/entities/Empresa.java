package entities;

import dao.AtivoDAO;
import entities.ativos.Ativo;
import estruturasdedados.LinkedList;

public class Empresa {
    private static int count = 0;
    private final int id;
    private String nome;
    private String simbolo;
    private double capitalizacao;
    private String setor;
    private LinkedList<Ativo> ativos;

    public Empresa(int id, String nome, String simbolo, double capitalizacao, String setor) {
        this.id = id;
        this.nome = nome;
        this.simbolo = simbolo;
        this.capitalizacao = capitalizacao;
        this.setor = setor;
    }

    public Empresa(String nome, String simbolo, double capitalizacao, String setor) {
        this(++count, nome, simbolo, capitalizacao, setor);
    }

    public void lancarAtivo(Ativo acao) {
        ativos.addLast(acao);
        AtivoDAO.save(acao);
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

    public String formatToSave() {
        return String.format("%-5s%-30s%-10s%-15s%-20s",
                getId(), getNome(), getSimbolo(), getCapitalizacao(), getSetor());
    }
}
