package estruturasdedados;

public class Pilha<E> {
    
    private E[] pilha;
    private final int DEFAULT_SIZE = 10;
    private int topo;
    
    @SuppressWarnings("unchecked")
    public Pilha() {
        this.pilha = (E[]) new Object[DEFAULT_SIZE];
        this.topo = -1;
    }
    
    public void push(E elemento) {
        if(empty()) {
            redimensionarPilha();
        } else {
            topo++;
            pilha[topo] = elemento;
        }
    }
    
    public E pop() {
        if(empty()) {
            System.out.println("A pilha está vazia");
            return null;
        } else {
            E elemento = pilha[topo];
            topo--;
            return elemento;
        }
    }
    
    public E peek() {
        if(empty()) {
            System.out.println("A pilha está vazia");
            return null;
        } else {
            return pilha[topo];
        }
    }
    
    public boolean empty() {
        return topo == -1;
    }
    
    public boolean full() {
        return topo == pilha.length - 1;
    }
    
    public int size() {
        return topo + 1;
    }

    @SuppressWarnings("unchecked")
    private void redimensionarPilha() {
        int newSize = pilha.length * 2;
        E[] newStack = (E[]) new Object[newSize];
        System.arraycopy(pilha, 0, newStack, 0, topo);
        pilha = newStack;
    }
}
