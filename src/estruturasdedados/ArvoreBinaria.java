package estruturasdedados;

import java.util.Comparator;

public class ArvoreBinaria<T> {
    private T valor;
    private ArvoreBinaria<T> esquerda;
    private ArvoreBinaria<T> direita;
    private Comparator<T> comparador;

    public ArvoreBinaria(T valor, Comparator<T> comparador) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.comparador = comparador;
    }

    public void inserir(T valor) {
        if (comparador.compare(valor, this.valor) < 0) {
            if (this.esquerda == null) {
                this.esquerda = new ArvoreBinaria<T>(valor, comparador);
            } else {
                this.esquerda.inserir(valor);
            }
        } else if (comparador.compare(valor, this.valor) > 0) {
            if (this.direita == null) {
                this.direita = new ArvoreBinaria<T>(valor, comparador);
            } else {
                this.direita.inserir(valor);
            }
        }
    }

    public boolean buscar(T valor) {
        if (comparador.compare(valor, this.valor) == 0) {
            return true;
        } else if (comparador.compare(valor, this.valor) < 0) {
            if (this.esquerda == null) {
                return false;
            } else {
                return this.esquerda.buscar(valor);
            }
        } else {
            if (this.direita == null) {
                return false;
            } else {
                return this.direita.buscar(valor);
            }
        }
    }

    public void remover(T valor) {
        if (comparador.compare(valor, this.valor) == 0) {
            if (this.esquerda == null && this.direita == null) {
                this.valor = null;
            } else if (this.esquerda == null) {
                this.valor = this.direita.valor;
                this.esquerda = this.direita.esquerda;
                this.direita = this.direita.direita;
            } else if (this.direita == null) {
                this.valor = this.esquerda.valor;
                this.direita = this.esquerda.direita;
                this.esquerda = this.esquerda.esquerda;
            } else {
                this.valor = this.direita.menorValor();
                this.direita.remover(this.valor);
            }
        } else if (comparador.compare(valor, this.valor) < 0) {
            if (this.esquerda != null) {
                this.esquerda.remover(valor);
            }
        } else {
            if (this.direita != null) {
                this.direita.remover(valor);
            }
        }
    }

    private T menorValor() {
        if (this.esquerda == null) {
            return this.valor;
        } else {
            return this.esquerda.menorValor();
        }
    }
}
