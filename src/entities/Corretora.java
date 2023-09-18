package entities;

import estruturasdedados.Fila;
import estruturasdedados.LinkedList;
import main.Validador;
import ordens.Ordem;

public class Corretora {

    private int id;
    private String nome;
    private String cnpj;
    private LinkedList<Investidor> clientes;
    private Fila<Ordem> ordens;

    public Corretora(int id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.clientes = new LinkedList<>();
        this.ordens = new Fila<>();
    }

    public void addCliente(Investidor cliente) {
        clientes.addLast(cliente);
    }

    public void removerCliente(Investidor cliente) {
        clientes.remove(cliente);
    }

    public void receberOrdem(Ordem ordem) {
        ordens.enqueue(ordem);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if(!cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}") && !Validador.validarCNPJ(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        this.cnpj = cnpj;
    }

    public LinkedList<Investidor> getClientes() {
        return clientes;
    }

    public Fila<Ordem> getOrdens() {
        return ordens;
    }
}