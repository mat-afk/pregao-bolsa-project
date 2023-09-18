package estruturasdedados;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    private static class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T data, int index) {
        Node<T> newNode = new Node<>(data);
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            if(index == 0) {
                newNode.next = head;
                head = newNode;
            } else {
                Node<T> current = head;
                for(int i = 0; i < index; i++) {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
        }
        size++;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if(head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while(current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void remove(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            if(index == 0) {
                removeFirst();
            } else {
                Node<T> current = head;
                for(int i = 0; i < size - 1; i++) {
                    current = current.next;
                }
                current.next = current.next.next;
            }
        }
        size--;
    }

    public void remove(T data) {
        Node<T> current = head;
        Node<T> prev = null;

        while (current != null) {
            if (current.data.equals(data)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }

        throw new IllegalArgumentException("Elemento não encontrado na lista");
    }

    public void removeFirst() {
        if(head == null) {
            throw new IndexOutOfBoundsException("Lista vazia");

        } else {
            head = head.next;
        }
        size--;
    }

    public void removeLast() {
        if(head == null) {
            throw new IndexOutOfBoundsException("Lista vazia");

        } else if(head.next == null) {
            head = null; 

        } else {
            Node<T> current = head;
            while(current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        size--;
    }

    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                T element = get(currentIndex);
                currentIndex++;
                return element;
            }
        };
    }
}
