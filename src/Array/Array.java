package Array;

import List.Node;
import List.List;

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

    public int max(){
        int major = 0;
        for(int i =0; i<TL; i++){
            if(array[i] > major){
                major = array[i];
            }
        }
        return major;
    }

    public int min(){
        int minor = array[0];
        for(int i = 1; i< TL; i++){
            if(i<minor){
                minor = i;
            }
        }
        return minor;
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

    public int getDigit(int number, int index){
        if(index < 0 || number == 0){
            return 0;
        }
        int divider = (int) Math.pow(10, index);
        return (number/divider)%10;
    }

    public void counting_sort(){
        //find the major
        int major = 0, pos;
        for(int i=0; i<TL; i++){
            if(array[i] > major){
                major = array[i];
            }
        }
        int [] B = new int[major + 1], C = new int[major + 1];

        //count
        for(int i=0; i<TL; i++){
            B[array[i]] += 1;
        }

        //cumulative
        for(int i=1; i<=major; i++){
            B[i] = B[i] + B[i-1];
        }

        for(int i=TL-1; i >= 0; i--){
            pos = B[array[i]];
            B[array[i]] -= 1;
            C[pos] = array[i];
        }
        array = C;
    }

    //arrumar <----------------------
    public void counting_sort(int radix){
        int major = 0, pos, digit;
        for(int i=0; i<TL; i++){
            digit = getDigit(array[i], radix);
            if(digit > major){
                major = digit;
            }
        }
        int [] B = new int[major + 1], C = new int[TL];

        //count
        for(int i=0; i<TL; i++){
            B[getDigit(array[i], radix)] += 1;
        }

        //cumulative
        for(int i=1; i<=major; i++){
            B[i] = B[i] + B[i-1];
        }

        for(int i=TL-1; i >= 0; i--){
            digit = getDigit(array[i], radix);
            pos = B[digit];
            B[digit]-= 1;
            C[pos - 1] = array[i];
        }
        array = C;
    }

    public void radix_sort(int maxLenght){
        for(int i = 0; i<maxLenght; i++){
            counting_sort(i);
        }
    }

    public void heap_sort(){
        int TL = this.TL, root, nodeL, nodeR, majorNode, aux;
        while(TL > 1){
            for(root = TL/2-1; root >=0; root--){
                nodeL =  2*root+1;
                nodeR = nodeL + 1;
                majorNode = nodeR < TL && array[nodeR] > array[nodeL] ? nodeR : nodeL;
                if(array[majorNode] > array[root]){
                    aux = array[majorNode];
                    array[majorNode] = array[root];
                    array[root] = aux;
                }
            }
            aux = array[TL-1];
            array[TL-1] = array[0];
            array[0] = aux;
            TL--;
        }

    }

    public void bucket_sort(int n){
        int aux,i, pos, index, min = min(), max = max();
        Node node;
        List[] buckets = new List[n];

        for (i=0; i<n; i++){
            buckets[i] = new List();
        }

        for (i=0;i<TL;i++){
            index = ((array[i]-min())*(n-1))/(max-min);
            buckets[index].insertAtEnd(array[i]);
        }

        for (i=0;i<n;i++){
            if(buckets[i].length() > 0){
                buckets[i].insertion_sort();
            }
        }

        aux = 0;
        for (i=0; i<n; i++){
            node = buckets[i].getStart();
            for(pos = 0; pos < buckets[i].length(); pos++){
                array[aux] = node.getInfo();
                aux ++;
                node = node.getNext();
            }
        }

    }

    public void shell_sort(){
        int dist = 1, i, j, aux;

        while (dist < TL){
            dist = dist * 3 + 1;
        }
        dist /= 3;

        while(dist > 0){
            for (i = dist; i < TL; i++){
                aux = array[i];
                j = i;
                while(j-dist >= 0 && aux < array[j - dist]){
                    array[j] = array[j-dist];
                    j = j - dist;
                }
                array[j] = aux;
            }
            dist/=3;
        }
    }

    public void gnome_sort(){
        int i = 0, aux;
        while(i < TL){
            if(i == 0){
                i++;
            }
            while(i > 0 && array[i] < array[i-1]){
                aux = array[i];
                array[i] = array[i-1];
                array[i-1] = aux;
                i--;
            }
            i++;
        }
    }



}
