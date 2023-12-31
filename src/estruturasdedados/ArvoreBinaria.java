package estruturasdedados;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.*;
import java.util.function.Consumer;

public class ArvoreBinaria<T> implements Iterable<T> {
    private T valor;
    private ArvoreBinaria<T> esquerda;
    private ArvoreBinaria<T> direita;
    private final Comparator<T> comparador;

    public ArvoreBinaria(T valor, Comparator<T> comparador) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.comparador = comparador;
    }

    public void add(T valor) {
        if (comparador.compare(valor, this.valor) < 0) {
            if (this.esquerda == null) {
                this.esquerda = new ArvoreBinaria<T>(valor, comparador);
            } else {
                this.esquerda.add(valor);
            }
        } else if (comparador.compare(valor, this.valor) > 0) {
            if (this.direita == null) {
                this.direita = new ArvoreBinaria<T>(valor, comparador);
            } else {
                this.direita.add(valor);
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

    @Override
    public Iterator<T> iterator() {
        return new ArvoreIterator();
    }

    private class ArvoreIterator implements Iterator<T> {
        private Pilha<ArvoreBinaria<T>> pilha;

        public ArvoreIterator() {
            pilha = new Pilha<>();
            preencherPilha(ArvoreBinaria.this);
        }

        private void preencherPilha(ArvoreBinaria<T> no) {
            while (no != null) {
                pilha.push(no);
                no = no.esquerda;
            }
        }

        @Override
        public boolean hasNext() {
            return !pilha.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            ArvoreBinaria<T> no = pilha.pop();
            preencherPilha(no.direita);
            return no.valor;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            while (!pilha.isEmpty()) {
                ArvoreBinaria<T> no = pilha.pop();
                action.accept(no.valor);
                preencherPilha(no.direita);
            }
        }
    }
}