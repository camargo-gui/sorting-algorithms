package Array;

import List.Node;
import List.List;
import Stack.Stack;

import javax.swing.text.GapContent;

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
        int pos, i = 1, aux;
        while (i < TL) {
            aux = array[i];
            pos = i;
            while(array[pos] < array[pos-1]) {
                array[pos] = array[pos-1];
                pos--;
            }
            array[pos] = aux;
            i++;
        }
    }

    public void selection_sort(){
        int pos = 0, i, minor, aux;
        while (pos < TL - 1) {
            i = pos + 1;
            minor = pos;
            while (i < TL) {
                if(array[i] < array[minor]){
                    minor = i;
                }
                i++;
            }
            aux = array[pos];
            array[pos] = array[minor];
            array[minor] = aux;
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
            index = ((array[i]-min)*(n-1))/(max-min);
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

    public int gapCalculate(int gap){
        int newGap = (int) (gap/1.3);
        return Math.max(newGap, 1);
    }

    public void comb_sort(){
        int gap = TL, i, j, aux;
        while(gap > 1){
            i = 0;
            gap =  gapCalculate(gap);
            j = i + gap;
            while(j < TL){
                if (array[i] > array[j]){
                    aux = array[j];
                    array[j] = array[i];
                    array[i] = aux;
                }
                i++;
                j = i + gap;
            }
        }
    }

    public void quick_sort(){
        quick_sort_sp(0, TL-1);
    }

    public void quick_sort_sp(int start, int end){
        int i = start, j = end, aux;

        while(i < j){

            while(i < j && array[i] <= array[j]){
                i++;
            }
            aux = array[i];
            array[i] = array[j];
            array[j] = aux;

            while (i<j && array[j] >= array[i]){
                j--;
            }
            aux = array[i];
            array[i] = array[j];
            array[j] = aux;
        }

        if(start < i-1){
            quick_sort_sp(start, i-1);
        }
        if(j+1 < end){
            quick_sort_sp(j+1, end);
        }
    }

    public void quick_sort_cp(int start, int end){
        int i = start, j = end, aux, index = (start + end)/2;
        int pivot = array[index];

        while(i<j){
            while(array[i]<pivot){
                i++;
            }

            while(array[j] > pivot){
                j--;
            }

            if( i <= j){
                aux = array[i];
                array[i] = array[j];
                array[j] = aux;
                i++;
                j--;
            }
        }
        if(start < j){
            quick_sort_cp(start, j);
        }
        if(i < end){
            quick_sort_cp(i,end);
        }
    }

    public void partition(int [] a1, int [] a2){
        int tam = TL/2;
        for(int i = 0; i < tam; i++){
            a1[i] = array[i];
            a2[i] = array[i + tam];
        }
    }

    public void segmentation(int [] a1, int [] a2, int seg) {
        int i =0, j = 0, tam = TL/2, k = 0, aux = seg;
        while (k < TL) {
            while (i < seg && j < seg) {
                if (a1[i] < a2 [j]){
                    array[k++] = a1[i++];
                }
                else {
                    array[k++] = a2[j++];
                }
            }
            while (i<seg){
                array[k++] = a1[i++];
            }
            while (j<seg) {
                array[k++] = a2[j++];
            }
            seg += aux;
        }
    }

    public void merge_sort1(){
        int seg = 1;
        int [] a1 = new int[TL/2], a2 = new int[TL/2];
        while(seg < TL){
            partition(a1, a2);
            segmentation(a1, a2, seg);
            seg *= 2;
        }
    }

    // 2 implementation
    public void fusion(int [] aux, int start1, int end1, int start2, int end2){
        int k = 0, t_start1 = start1;
        while(start1 <= end1 && start2 <= end2){
            if(array[start1] < array[start2]){
                aux[k++] = array[start1++];
            }
            else {
                aux[k++] = array[start2++];
            }
        }

        while(start1 <= end1){
            aux[k++] = array[start1++];
        }

        while(start2 <= end2){
            aux[k++] = array[start2++];
        }

        for(int i = 0; i<k; i++){
            array[i + t_start1] = aux[i];
        }
    }

    public void merge(int [] aux, int left, int right){
        int half;
        if(left < right){
            half = (left+right)/2;
            merge(aux, left, half);
            merge(aux, half+ 1, right);
            fusion(aux, left, half, half+1, right);
        }
    }

    public void merge_second_implementation(){
        int [] aux = new int[TL];
        merge(aux, 0, TL -1);
    }


    public void iterativeQuickSortSP(){
        int i, j, aux, auxStart, auxEnd;
        Stack start = new Stack(), end = new Stack();
        start.push(0);
        end.push(TL - 1);
        while (!start.isEmpty()){
            auxStart = i = start.pop();
            auxEnd = j = end.pop();

            while (i < j){
                while(i < j && array[i] <= array[j]){
                    i++;
                }

                aux = array[i];
                array[i] = array[j];
                array[j] = aux;

                while(i < j && array[i] <= array[j]){
                    j--;
                }

                aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }

            if(auxStart < i-1){
                start.push(auxStart);
                end.push(i-1);
            }

            if(auxEnd > j+1){
                start.push(j+1);
                end.push(auxEnd);
            }
        }
    }

    public void iterativeQuickSortCP(){
        int auxStart, auxEnd, i, j, pivot, aux;
        Stack start = new Stack(), end = new Stack();
        start.push(0);
        end.push(TL - 1);
        while(!start.isEmpty()){
            auxStart = i = start.pop();
            auxEnd = j = end.pop();
            pivot = array[(auxStart+auxEnd)/2];

            while(i < j){

                while(array[i] < pivot){
                    i++;
                }

                while(array[j] > pivot){
                    j--;
                }

                if(i <= j){
                    aux = array[i];
                    array[i] = array[j];
                    array[j] = aux;
                    i++;
                    j--;
                }
            }

            if(auxStart < j){
                start.push(auxStart);
                end.push(j);
            }

            if(i < auxEnd){
                start.push(i);
                end.push(auxEnd);
            }
        }
    }

    public void iterative_merge_second_implementation(){
        int [] aux = new int[TL];
        int left, right, block = 1, half;

        while(block < TL){
            left = 0;
            while(left + block < TL){
                right = left + 2 * block - 1;
                if (right >= TL) right = TL - 1;
                half = left + block;
                fusion(aux, left, half - 1, half, right);
                left = left + block * 2;
            }
            block *= 2;
        }
    }

}
