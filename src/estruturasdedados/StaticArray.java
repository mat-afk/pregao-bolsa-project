package estruturasdedados;

public class StaticArray {
    private int [] arr;
    private int size;
    private final int capacity;
    
    public StaticArray(int capacity) {
        this.arr = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }
    
    public void add(int element) {
        if(size == capacity) {
            throw new IllegalStateException("Array is full");
        }
        arr[size++] = element;
    }
    
    public void remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        for(int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
}
