package estruturasdedados;

public class Fila<E> {

    private E[] elements;
    private int head;
    private int tail;
    private static final int DEFAULT_SIZE = 10;
    
    @SuppressWarnings("unchecked")
    public Fila() {
        elements = (E[]) new Object[DEFAULT_SIZE];
        head = 0;
        tail = -1;
    }
    
    public void add(E element) {
        if (tail == elements.length - 1) {
            redimensionarFila();
        }
        tail++;
        elements[tail] = element;
    }
    
    public E remove() {
        if (head > tail) {
            System.out.println("Fila vazia!");
            return null;
        }
        E element = elements[head];
        head++;
        return element;
    }
    
    public E peek() {
        if (head > tail) {
            System.out.println("Fila vazia!");
            return null;
        }
        return elements[head];
    }

    @SuppressWarnings("unchecked")
    private void redimensionarFila() {
        int newSize = elements.length * 2;
        E[] newQueue = (E[]) new Object[newSize];
        System.arraycopy(elements, 0, newQueue, 0, tail + 1);
        elements = newQueue;
    }
}
