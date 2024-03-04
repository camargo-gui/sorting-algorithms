package Array;

public class Array {
    private int[] array;
    private int TL;

    public Array() {
        this.array = new int[100];
        this.TL = 0;
    }

    public void add(int element) {
        this.array[TL] = element;
        this.TL += 1;
    }

    public void print(){
        for(int i =0; i<TL; i++){
            System.out.println(array[i]);
        }
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

    public void selection_sort(){
        int pos = 0, i, minor;
        while (pos < TL - 1) {
            i = pos + 1;
            minor = pos;
            while (i < TL) {
                if(array[i] < array[minor]){
                    minor = i;
                }
                i++;
            }
            array[minor] = array[pos];
            array[pos] = array[minor];
            pos ++;
        }
    }

    public void bubble_sort(){
        int aux, TL = this.TL;
        boolean change = true;
        while(TL > 0 && change) {
            change = false;
            for (int i = 0; i < TL -1; i++) {
                if (array[i] > array[i+1]){
                    change = true;
                    aux = array[i];
                    array[i] = array[i + 1];
                    array[i+1] = aux;
                }
            }
            TL--;
        }
    }

    public void shake_sort(){
        int aux, start = 0, end = TL - 1;
        boolean change = true;
        while(start < end && change) {
            change = false;
            for (int i = start; i < end; i ++){
                if(array[i] > array[i + 1]){
                    aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                    change = true;
                }
            }
            end--;
            if(change){
                change = false;
                for(int j=end; j>start; j--){
                    if(array[j] < array[j-1]){
                        aux = array[j];
                        array[j] = array[j-1];
                        array[j-1] = aux;
                        change = true;
                    }
                }
            }
            start++;
        }
    }

    public void counting_sort(){
        //find the major
        int major = 0, pos;
        for(int i=0; i<TL; i++){
            if(array[i] > major){
                major = array[i];
            }
        }
        int [] B = new int[major], C = new int[major];

        //count
        for(int i=0; i<TL; i++){
            B[array[i] - 1] += 1;
        }

        //cumulative
        for(int i=1; i<TL; i++){
            B[i] = B[i] + B[i-1];
        }

        for(int i=TL-1; i >= 0; i--){
            pos = B[array[i]-1];
            B[array[i-1]] -= 1;
            C[pos] = array[i];
        }

        array = C;

    }


}
