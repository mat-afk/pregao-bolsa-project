package entities;

import dao.RegistroDAO;
import estruturasdedados.Pilha;

public class Historico {
    private Pilha<Registro> registros;

    public Historico() {
        registros = new Pilha<>();
    }

    public void addRegistro(Registro registro) {
        registros.push(registro);
        RegistroDAO.save(registro);
    }

    public Pilha<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(Pilha<Registro> registros) {
        this.registros = registros;
    }
}