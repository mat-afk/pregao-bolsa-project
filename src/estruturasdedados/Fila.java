package estruturasdedados;
import java.util.Iterator;

public class Fila<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Fila() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Fila vazia");
        }
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Fila vazia");
        }
        return head.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilaIterator();
    }

    private class FilaIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalArgumentException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
