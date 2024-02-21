package Array;

public class Array {
    private int[] array;
    private int TL;

    public void constructor() {
        this.array = new int[1000];
        this.TL = 0;
    }

    public void add(int element) {
        this.array[TL] = element;
        this.TL += 1;
    }

    public int exhaustive_search(int element) {
        int pos = 0;
        while (pos < TL && element != this.array[pos]) {
            pos++;
        }
        if (element == array[pos]) {
            return pos;
        }
        return -1;
    }

    public int sentinel_search(int element) {
        int pos = 0;
        this.array[TL] = element;
        while (element != this.array[pos]) {
            pos++;
        }
        if (pos < TL) {
            return pos;
        }
        return -1;
    }

    public int sequential_search(int element) {
        int pos = 0;
        while (pos < TL && array[pos] > element) {
            pos++;
        }
        if (element == array[pos]){
            return pos;
        }
        return -1;
    }

    public int binary_search(int element) {
        int start = 0, end = TL, half = end/2;
        while (start < end && array[half] != element) {
            if (element > array[half]) {
                start += 1;
            }
            else {
                end -= 1;
            }
            half = (start + end)/2;
        }
        if (element == array[half]) {
            return half;
        }
        return -1;
    }

    public void insertion_sort() {
        int pos = 0, i = 1, aux;
        while (i < TL) {
            aux = array[i];
            pos = i;
            while(array[pos] > array[pos-1]) {
                array[pos] = array[pos-1];
                pos--;
            }
            array[pos] = aux;
            i++;
        }
    }



}
