package algoritmosdeordenacao;

public class BubbleSort<T extends Comparable<T>> implements Sorting<T> {

    @Override
    public void sort(T[] arr) {
        int n = arr.length;
        T temp;
        for (int i = 0; i < n; i++) {
            for (int x = 1; x < (n - i); x++) {
                if (arr[x - 1].compareTo(arr[x]) > 0) {
                    temp = arr[x - 1];
                    arr[x - 1] = arr[x];
                    arr[x] = temp;
                }
            }
        }
    }
}


