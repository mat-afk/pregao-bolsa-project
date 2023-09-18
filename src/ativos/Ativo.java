package ativos;

import entities.Empresa;
import main.Validador;

public abstract class Ativo {

    private int id;
    private String codigo;
    private Empresa empresa;
    private double cotacao;
    private Historico historico;
    private String tipo;

    public Ativo(int id, String codigo, Empresa empresa, double cotacao) {
        this.id = id;
        setCodigo(codigo);
        this.empresa = empresa;
        this.cotacao = cotacao;
        this.historico = new Historico();
        setTipo();
    }

    public Ativo(int id, String codigo, Empresa empresa) {
        this(id, codigo, empresa, 0.0);
    }

    public int getId() {
        return id;
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

    public String getTipo() {
        return tipo;
    }

    private void setTipo() {
        if(this instanceof Ordinaria) {
            tipo = "Ordinaria";
        } else if(this instanceof Preferencial) {
            tipo = "Preferencial";
        } else {
            tipo = "FII";
        }
    }
}
