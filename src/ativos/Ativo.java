package ativos;

import entities.Empresa;
import main.Validador;

public abstract class Ativo {

    private int id;
    private String nome;
    private String codigo;
    private Empresa empresa;
    private double cotacao;
    private Historico historico;

    public Ativo(int id, String nome, String codigo, Empresa empresa, double cotacao) {
        this.id = id;
        this.nome = nome;
        setCodigo(codigo);
        this.empresa = empresa;
        this.cotacao = cotacao;
        this.historico = new Historico();
    }

    public Ativo(int id, String nome, String codigo, Empresa empresa) {
        this(id, nome, codigo, empresa, 0.0);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if(!Validador.validarCodigo(codigo)) {
            throw new IllegalArgumentException("Formato de código incorreto.");
        }
        this.codigo = codigo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public double getCotacao() {
        return cotacao;
    }

    public Historico getHistorico() {
        return historico;
    }
}
