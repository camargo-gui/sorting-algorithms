package BinaryFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.rmi.registry.Registry;
import java.util.Random;

public class File {
    private String fileName;
    private RandomAccessFile file;

    private int mov, comp;

    public File(String fileName)
    {
        try
        {
            file = new RandomAccessFile(fileName, "rw");
        } catch (IOException ignored)
        { }
    }

    public void truncate(long pos)
    {
        try
        {
            file.setLength(pos * Record.length());
        } catch (IOException ignored)
        { }
    }

    public boolean eof()
    {
        boolean retorno = false;
        try
        {
            if (file.getFilePointer() == file.length())
                retorno = true;
        } catch (IOException ignored)
        { }
        return (retorno);
    }

    public int filesize(){
        try{
            return (int) file.length()/Record.length();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initComp(){
        comp = 0;
    }

    public void initMov(){
        mov = 0;
    }

    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
    }

    public void buildOrderedFile(){
        truncate(0);
        for(int i = 0; i<50; i++){
            insertAtEnd(new Record(i, "Teste", 0));
        }
    }

    public void buildReversedFile(){
        truncate(0);
        for(int i = 50; i>0; i--){
            insertAtEnd(new Record(i, "Teste", 0));
        }
    }

    public void buildRandomFile(){
        truncate(0);
        for(int i = 0; i<50; i++){
            insertAtEnd(new Record(new Random().nextInt(1024), "Teste", 0));
        }
    }

    public void copy(File file){
        Record rec = new Record();

        seekFile(0);
        file.truncate(0);
        file.seekFile(0);
        while(!eof()){
            rec.read(this.file);
            rec.write(file.file);
        }
    }


    public void insertAtEnd(Record rec)
    {
        seekFile(filesize());//ultimo byte
        rec.write(file);
    }

    public void showFile()
    {
        int i;
        Record aux = new Record();
        seekFile(0);
        i = 0;
        while (!this.eof())
        {
            System.out.println("Pos " + i);
            aux.read(file);
            aux.print();
            i++;
        }
    }

    public void showRecord(int pos)
    {
        Record aux = new Record();
        seekFile(pos);
        System.out.println("Pos " + pos);
        aux.read(file);
        aux.print();
    }

    public void seekFile(int pos)
    {
        try
        {
            file.seek((long) pos * Record.length());
        } catch (IOException ignored)
        { }
    }

    public int max(){
        Record rec = new Record();
        seekFile(0);
        rec.read(file);
        int major = rec.getcod();
        while(!eof()){
            rec.read(file);
            if(rec.getcod() > major){
                major = rec.getcod();
            }
        }
        return major;
    }

    public int minor(){
        Record rec = new Record();
        seekFile(0);
        rec.read(file);
        int minor = rec.getcod();
        while(!eof()){
            rec.read(file);
            if(rec.getcod() < minor){
                minor = rec.getcod();
            }
        }
        return minor;
    }

    public int binary_search(int info, int end){
        Record reg = new Record();
        int start = 0, half = end/2;
        seekFile(half);
        reg.read(file);
        comp++;
        while(start < end && reg.getcod() != info){
            comp++;
            if(reg.getcod() < info){
                start = half + 1;
            }
            comp++;
            if(reg.getcod() > info){
                end = half - 1;
            }
            half = (start+end)/2;
            seekFile(half);
            reg.read(file);
            comp++;
        }
        comp++;
        if(info > reg.getcod()){
            return half + 1;
        }
        return half;
    }

    public void binary_insertion_sort(){
        Record rec1 = new Record(), rec2 = new Record();
        int i = 1, tam = filesize(), j, pos;
        while(i < tam){
            seekFile(i);
            rec1.read(file);
            pos = binary_search(rec1.getcod(), i - 1);
            j = i;
            while(j > pos){
                seekFile(j-1);
                rec1.read(file);
                rec2.read(file);
                seekFile(j-1);
                rec2.write(file);
                rec1.write(file);
                j--;
                mov+=2;
            }
            i++;
        }
    }


    public void insertion_sort(){
        Record rec1 = new Record(), rec2 = new Record();
        boolean change;
        int i = 1, j, tam = filesize();
        while(i < tam){
            change = true;
            j = i;
            while(j > 0 && change){
                change = false;
                seekFile(j-1);
                rec1.read(file);
                rec2.read(file);
                comp++;
                if(rec1.getcod() > rec2.getcod()){
                    seekFile(j-1);
                    rec2.write(file);
                    rec1.write(file);
                    change = true;
                    mov+=2;
                }
                j--;
            }
            i ++;
        }
    }

    public void selection_sort(){
        Record rec = new Record(), minor = new Record();
        int pos = 0, length = filesize(), posMinor = 0, i;
        while(pos < length - 1){
            posMinor = pos;
            seekFile(posMinor);
            minor.read(file);
            i = pos + 1;
            while(i < length){
                seekFile(i);
                rec.read(file);
                comp++;
                if(rec.getcod() < minor.getcod()){
                    minor = new Record(rec);
                    posMinor = i;
                }
                i++;
            }
            seekFile(pos);
            rec.read(file);
            seekFile(pos);
            minor.write(file);
            seekFile(posMinor);
            rec.write(file);
            pos++;
            mov+=2;
        }
    }

    public void bubble_sort(){
        Record rec1 = new Record(), rec2 = new Record();
        boolean change = true;
        int tam = filesize();
        while(tam > 1 && change){
            change = false;
            for(int i = 0; i < tam - 1; i++){
                seekFile(i);
                rec1.read(file);
                rec2.read(file);
                comp++;
                if(rec1.getcod() > rec2.getcod()){
                    seekFile(i);
                    rec2.write(file);
                    rec1.write(file);
                    change = true;
                    mov+=2;
                }
            }
            tam--;
        }
    }


    public void shake_sort(){
        Record rec1 = new Record(), rec2 = new Record();
        int start = 0, end = filesize() - 1, pos;
        boolean change = true;
        while(start < end && change){
            change = false;
            for(pos = start; pos <= end; pos++){
                seekFile(pos);
                rec1.read(file);
                rec2.read(file);
                comp++;
                if(rec1.getcod() > rec2.getcod()){
                    seekFile(pos);
                    rec2.write(file);
                    rec1.write(file);
                    change = true;
                    mov+=2;
                }
            }
            end--;
            if(change){
                change = false;
                for(pos = end - 1; pos >= start; pos--){
                    seekFile(pos);
                    rec1.read(file);
                    rec2.read(file);
                    comp++;
                    if(rec1.getcod() > rec2.getcod()){
                        seekFile(pos);
                        rec2.write(file);
                        rec1.write(file);
                        change = true;
                        mov+=2;
                    }
                }
            }
            start++;
        }
    }

    public void couting_sort(){
        int major = 0, tam = filesize(), pos;
        File aux = new File("aux.dat");
        Record rec = new Record();
        seekFile(0);
        while(!eof()){
            rec.read(file);
            comp++;
            if(rec.getcod() > major){
                major = rec.getcod();
            }
        }

        int []  B = new int[major+1];

        seekFile(0);
        while(!eof()){
            rec.read(file);
            B[rec.getcod()] += 1;
        }

        for(int i=1; i<major; i++){
            B[i] += B[i-1];
        }

        for(int i = tam; i>=0; i--){
            seekFile(i);
            rec.read(file);
            pos = B[rec.getcod()];
            B[rec.getcod()] -= 1;
            aux.seekFile(pos-1);
            rec.write(aux.file);
            mov++;
        }

        for(int i = 0; i < filesize(); i++){
            aux.seekFile(i);
            seekFile(i);
            rec.read(aux.file);
            rec.write(file);
            mov++;
        }
    }

    public void couting_sort(int radix){
        int major = 0, tam = filesize(), pos;
        File aux = new File("aux.dat");
        Record rec = new Record();
        seekFile(0);
        while(!eof()){
            rec.read(file);
            comp++;
            if(getDigit(rec.getcod(),radix) > major){
                major = getDigit(rec.getcod(),radix);
            }
        }

        int []  B = new int[major+1];

        seekFile(0);
        while(!eof()){
            rec.read(file);
            B[getDigit(rec.getcod(),radix)] += 1;
        }

        for(int i=1; i<=major; i++){
            B[i] += B[i-1];
        }

        for(int i = tam; i>=0; i--){
            seekFile(i);
            rec.read(file);
            pos = B[getDigit(rec.getcod(),radix)];
            B[getDigit(rec.getcod(),radix)] -= 1;
            aux.seekFile(pos-1);
            rec.write(aux.file);
            mov++;
        }

        for(int i = 0; i < filesize(); i++){
            aux.seekFile(i);
            seekFile(i);
            rec.read(aux.file);
            rec.write(file);
            mov++;
        }
    }

    public void radix_sort(int maxLength){
        for(int i = 0; i<maxLength; i++){
            couting_sort(i);
        }
    }

    public int getDigit(int number, int index){
        if(index < 0 || number == 0){
            return 0;
        }
        int divider = (int) Math.pow(10, index);
        return (number/divider)%10;
    }

    public void heap_sort(){
        int TL = filesize(), root, nodeL, nodeR, majorNode;
        Record recL = new Record(), recR = new Record(), recRoot = new Record(), majorRec = new Record(), aux = new Record();

        while(TL > 1){
            for(root = TL/2-1; root >= 0; root--){
                nodeL = 2 * root + 1;
                nodeR = nodeL + 1;
                seekFile(nodeL);
                recL.read(file);
                if(nodeR < TL){
                    seekFile(nodeR);
                    recR.read(file);
                    majorNode = recR.getcod() > recL.getcod() ? nodeR : nodeL;
                    comp++;
                }
                else {
                    majorNode = nodeL;
                }
                seekFile(root);
                recRoot.read(file);
                seekFile(majorNode);
                majorRec.read(file);
                if(majorRec.getcod() > recRoot.getcod()){
                    aux = new Record(majorRec);
                    seekFile(majorNode);
                    recRoot.write(file);
                    seekFile(root);
                    aux.write(file);
                    mov+=2;
                }
            }
            seekFile(0);
            recRoot.read(file);
            seekFile(TL-1);
            aux.read(file);
            seekFile(0);
            aux.write(file);
            seekFile(TL-1);
            recRoot.write(file);
            mov+=2;
            TL--;
        }
    }

    public void bucket_sort(int n){
        int i, pos, min = minor(), max = max();
        Record rec = new Record();
        File [] buckets = new File[n];

        for(i=0; i<n; i++){
            buckets[i] = new File(i+".txt");
        }

        for(i=0; i<filesize(); i++){
            seekFile(i);
            rec.read(file);
            pos = (rec.getcod()-min) * (n-1) / (max - min);
            buckets[pos].insertAtEnd(rec);
            mov++;
        }

        for(i=0;i<n;i++){
            if(buckets[i].file != null){
                buckets[i].insertion_sort();
            }
        }

        pos = 0;
        for(i=0; i<n; i++){
            if(buckets[i] != null){
                buckets[i].seekFile(0);
                while(!buckets[i].eof()){
                    rec.read(buckets[i].file);
                    seekFile(pos);
                    rec.write(file);
                    pos++;
                    mov++;
                }
            }
        }
    }

    public void shell_sort(){
        Record aux = new Record(), aux2 = new Record();
        int i, j, length = filesize(), dist = 1;

        while(dist < length){
            dist = dist * 3 + 1;
        }
        dist /= 3;

        while(dist > 0){
            for(i = dist; i < length; i++){
                j = i;
                seekFile(j);
                aux.read(file);
                seekFile(j-dist);
                aux2.read(file);
                comp++;
                while(j-dist >= 0 && aux.getcod() < aux2.getcod()){
                    seekFile(j);
                    aux2.write(file);
                    mov++;
                    j-=dist;
                    if(j-dist >= 0){
                        seekFile(j-dist);
                        aux2.read(file);
                    }
                    comp++;
                }
                seekFile(j);
                aux.write(file);
                mov++;
            }
            dist /= 3;
        }
    }

    public void gnome_sort(){
        int pos = 0, length = filesize();
        Record rec = new Record(), rec2 = new Record();
        while(pos < length){
            if(pos == 0){
                pos++;
            }
            seekFile(pos-1);
            rec2.read(file);
            rec.read(file);
            comp++;
            while(pos > 0 && rec.getcod() < rec2.getcod()){
                seekFile(pos-1);
                rec.write(file);
                rec2.write(file);
                pos--;
                mov+=2;
                if(pos > 0){
                    seekFile(pos-1);
                    rec2.read(file);
                    rec.read(file);
                }
                comp++;
            }
            pos++;
        }
    }

    public int gapCalculate(int gap){
        int newGap = (int) (gap/1.3);
        return Math.max(newGap, 1);
    }

    public void comb_sort(){
        int i, j;
        Record rec1 = new Record(), rec2 = new Record();
        int length = filesize(), gap = length;
        while(gap > 1){
            i = 0;
            gap = gapCalculate(gap);
            j = i + gap;
            while(j < length){
                seekFile(i);
                rec1.read(file);
                seekFile(j);
                rec2.read(file);
                comp++;
                if(rec1.getcod() > rec2.getcod()){
                    seekFile(i);
                    rec2.write(file);
                    seekFile(j);
                    rec1.write(file);
                    mov+=2;
                }
                i ++;
                j = i+gap;
            }
        }
    }

    public void quick_sort1(){
        quick_sort_sp(0, filesize() -1);
    }
    public void quick_sort2(){
        quick_sort_cp(0, filesize() -1);
    }

    public void quick_sort_sp(int start, int end){
        Record recI = new Record(), recJ = new Record();
        int i = start, j = end;

        while(i < j){

            seekFile(i);
            recI.read(file);
            seekFile(j);
            recJ.read(file);

            comp++;
            while(i<j && recI.getcod() <= recJ.getcod()){
                i++;
                seekFile(i);
                recI.read(file);
                comp++;
            }

            comp++;
            if(recI.getcod() != recJ.getcod()){
                seekFile(i);
                recI.read(file);
                seekFile(j);
                recJ.read(file);
                seekFile(j);
                recI.write(file);
                seekFile(i);
                recJ.write(file);
                mov+=2;
            }

            seekFile(i);
            recI.read(file);
            seekFile(j);
            recJ.read(file);

            comp++;
            while (i<j && recI.getcod() <= recJ.getcod()){
                j--;
                seekFile(j);
                recJ.read(file);
                comp++;
            }

            comp++;
            if(recI.getcod() != recJ.getcod()){
                seekFile(i);
                recI.read(file);
                seekFile(j);
                recJ.read(file);
                seekFile(j);
                recI.write(file);
                seekFile(i);
                recJ.write(file);
                mov+=2;
            }
        }

        if(start < i-1){
            quick_sort_sp(start, i-1);
        }
        if(j+1 < end){
            quick_sort_sp(j+1, end);
        }
    }

    public void quick_sort_cp(int start, int end){
        int i = start, j = end;
        Record recI = new Record(), recJ = new Record(), recPivot = new Record();

        seekFile((i+j)/2);
        recPivot.read(file);

        while (i < j) {
            seekFile(i);
            recI.read(file);
            comp++;
            while (recI.getcod() < recPivot.getcod()){
                i++;
                recI.read(file);
                comp++;
            }

            seekFile(j);
            recJ.read(file);
            comp++;
            while(recJ.getcod() > recPivot.getcod()){
                j--;
                seekFile(j);
                recJ.read(file);
                comp++;
            }

            if(i <= j){
                seekFile(i);
                recJ.write(file);
                seekFile(j);
                recI.write(file);
                i++;
                j--;
                mov+=2;
            }
        }

        if (start < i){
            quick_sort_cp(start, j);
        }

        if (j < end) {
            quick_sort_cp(i, end);
        }
    }

    public void partition(File a, File b){
        Record recA = new Record(), recB = new Record();
        a.seekFile(0); b.seekFile(0);
        int length = filesize(), k = 0, aux = length/2;
        while(k < aux){
            seekFile(k);
            recA.read(file);
            recA.write(a.file);
            seekFile(k+aux);
            recB.read(file);
            recB.write(b.file);
            k++;
            mov+=2;
        }
    }

    public void segmentation(File a, File b, int seg){
        Record recA = new Record(), recB = new Record();
        int aux = seg, k = 0, length = filesize(),  i = 0, j = 0;
        seekFile(0);
        while(k < length){
            while(i < seg && j < seg) {
                a.seekFile(i);
                recA.read(a.file);
                b.seekFile(j);
                recB.read(b.file);

                comp++;
                if (recA.getcod() < recB.getcod()) {
                    seekFile(k++);
                    recA.write(file);
                    i++;
                } else {
                    seekFile(k++);
                    recB.write(file);
                    j++;
                }
                mov++;
            }

                while (i < seg) {
                    a.seekFile(i++);
                    seekFile(k++);
                    recA.read(a.file);
                    recA.write(file);
                    mov++;
                }

                while (j < seg) {
                    b.seekFile(j++);
                    seekFile(k++);
                    recB.read(b.file);
                    recB.write(file);
                    mov++;
                }
            seg += aux;
        }
    }

    public void merge_sort_first_implementation(){
        File a = new File("aux1.dat"), b = new File("aux2.dat");
        int seg = 1, length = filesize();
        while(seg < length){
            a.truncate(0); b.truncate(0);
            partition(a,b);
            segmentation(a,b,seg);
            seg *= 2;
        }
    }


    // second implementation

    public void fusion(File aux, int start1, int end1, int start2, int end2){
        aux.truncate(0);
        int t_start1 = start1;
        Record rec1 = new Record(), rec2 = new Record();
        while(start1 <= end1 && start2 <= end2){
            seekFile(start1);
            rec1.read(file);
            seekFile(start2);
            rec2.read(file);

            comp++;
            if(rec1.getcod() < rec2.getcod()){
                rec1.write(aux.file);
                start1++;
            }
            else {
                rec2.write(aux.file);
                start2++;
            }
            mov++;
        }

        while(start1 <= end1){
            seekFile(start1);
            rec1.read(file);
            rec1.write(aux.file);
            start1++;
            mov++;
        }

        while(start2 <= end2){
            seekFile(start2);
            rec2.read(file);
            rec2.write(aux.file);
            start2++;
            mov++;
        }

        int length = aux.filesize();
        aux.seekFile(0);
        seekFile(t_start1);
        for(int i = 0; i<length; i++){
            rec1.read(aux.file);
            rec1.write(file);
            mov++;
        }
    }

    public void merge(File aux, int left, int right){
        int half;
        if(left < right){
            half = (left+right)/2;
            merge(aux, left, half);
            merge(aux, half + 1, right);
            fusion(aux, left, half, half + 1, right);
        }
    }

    public void merge_sort_second_implementation(){
        int length = filesize();
        File aux = new File("aux.dat");
        merge(aux, 0, length -1);
    }


}
