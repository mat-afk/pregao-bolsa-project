package entities;

import database.DatabaseManager;
import estruturasdedados.Pilha;

public class Historico {
    private Pilha<Registro> registros;

    public Historico() {
        registros = new Pilha<>();
    }

    public void addRegistro(Registro registro) {
        registros.push(registro);
        DatabaseManager.gravarRegistro(registro);
    }

    public Pilha<Registro> getRegistros() {
        return registros;
    }
}