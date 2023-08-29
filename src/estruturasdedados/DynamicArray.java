package estruturasdedados;

import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {
    private T[] arr;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    
    @SuppressWarnings("unchecked")
    public DynamicArray() {
        this.arr = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    public void add(T element) {
        if (size == arr.length) {
            resizeArray();
        }
        arr[size++] = element;
    }
    
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        
        size--;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return arr[index];
    }
    
    @SuppressWarnings("unchecked")
    private void resizeArray() {
        int newCapacity = arr.length * 2;
        T[] newArr = (T[]) new Object[newCapacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
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
