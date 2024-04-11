package BinaryFile;

import java.io.IOException;
import java.io.RandomAccessFile;
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

    public boolean eof() {
        boolean retorno = false;
        try {
            if (file.getFilePointer() == file.length())
                retorno = true;
        } catch (IOException ignored) {
        }
        return (retorno);
    }

    public int filesize() {
        try {
            return (int) file.length() / Record.length();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initComp() {
        comp = 0;
    }

    public void initMov() {
        mov = 0;
    }

    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
    }

    public void buildOrderedFile() {
        truncate(0);
        for (int i = 0; i < 1024; i++) {
            insertAtEnd(new Record(i));
        }
    }

    public void buildReversedFile() {
        truncate(0);
        for (int i = 1024; i > 0; i--) {
            insertAtEnd(new Record(i));
        }
    }

    public void buildRandomFile() {
        truncate(0);
        for (int i = 0; i < 1024; i++) {
            insertAtEnd(new Record(new Random().nextInt(1024)));
        }
    }

    public void copy(File file) {
        Record rec = new Record();

        seekFile(0);
        file.truncate(0);
        file.seekFile(0);
        while (!eof()) {
            rec.read(this.file);
            rec.write(file.file);
        }
    }


    public void insertAtEnd(Record rec) {
        seekFile(filesize());//ultimo byte
        rec.write(file);
    }

    public void showFile() {
        int i;
        Record aux = new Record();
        seekFile(0);
        i = 0;
        while (!this.eof()) {
            System.out.print("Pos " + i);
            aux.read(file);
            aux.print();
            i++;
        }
    }

    public void showRecord(int pos) {
        Record aux = new Record();
        seekFile(pos);
        System.out.println("Pos " + pos);
        aux.read(file);
        aux.print();
    }

    public void seekFile(int pos) {
        try {
            file.seek((long) pos * Record.length());
        } catch (IOException ignored) {
        }
    }

    public int max() {
        Record rec = new Record();
        seekFile(0);
        rec.read(file);
        int major = rec.getcod();
        while (!eof()) {
            rec.read(file);
            if (rec.getcod() > major) {
                major = rec.getcod();
            }
        }
        return major;
    }

    public int minor() {
        Record rec = new Record();
        seekFile(0);
        rec.read(file);
        int minor = rec.getcod();
        while (!eof()) {
            rec.read(file);
            if (rec.getcod() < minor) {
                minor = rec.getcod();
            }
        }
        return minor;
    }

    public int binary_search(int info, int end) {
        Record reg = new Record();
        int start = 0, half = end / 2;
        seekFile(half);
        reg.read(file);
        comp++;
        while (start < end && reg.getcod() != info) {
            comp++;
            if (reg.getcod() < info) {
                start = half + 1;
            } else {
                end = half - 1;
            }
            half = (start + end) / 2;
            seekFile(half);
            reg.read(file);
            comp++;
        }
        comp++;
        if (info > reg.getcod()) {
            return half + 1;
        }
        return half;
    }

    public void binary_insertion_sort() {
        Record rec = new Record(), aux = new Record();
        int i = 1, tam = filesize(), j, pos;
        while (i < tam) {
            seekFile(i);
            aux.read(file);
            pos = binary_search(aux.getcod(), i - 1);
            j = i;
            while (j > pos) {
                seekFile(j - 1);
                rec.read(file);
                rec.write(file);
                j--;
                mov ++;
            }
            seekFile(j);
            mov++;
            aux.write(file);
            i++;
        }
    }


    public void insertion_sort() {
        Record rec = new Record(), aux = new Record();
        int i = 1, j, tam = filesize();
        while (i < tam) {
            j = i;
            seekFile(j);
            aux.read(file);
            seekFile(j - 1);
            rec.read(file);
            comp++;
            while (j > 0 && rec.getcod() > aux.getcod()) {
                seekFile(j);
                rec.write(file);
                mov++;
                j--;
                if (j > 0) {
                    seekFile(j - 1);
                    rec.read(file);
                }
                comp++;
            }
            seekFile(j);
            aux.write(file);
            mov++;
            i++;
        }
    }

    public void selection_sort() {
        Record rec = new Record(), minor = new Record();
        int pos = 0, length = filesize(), posMinor, i;
        while (pos < length - 1) {
            posMinor = pos;
            seekFile(posMinor);
            minor.read(file);
            i = pos + 1;
            while (i < length) {
                seekFile(i);
                rec.read(file);
                comp++;
                if (rec.getcod() < minor.getcod()) {
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
            mov += 2;
        }
    }

    public void bubble_sort() {
        Record rec1 = new Record(), rec2 = new Record();
        int tam = filesize();
        boolean change = true;
        while (tam > 1 && change) {
            change = false;
            for (int i = 0; i < tam - 1; i++) {
                seekFile(i);
                rec1.read(file);
                rec2.read(file);
                comp++;
                if (rec1.getcod() > rec2.getcod()) {
                    change = true;
                    seekFile(i);
                    rec2.write(file);
                    rec1.write(file);
                    mov += 2;
                }
            }
            tam--;
        }
    }


    public void shake_sort() {
        Record rec1 = new Record(), rec2 = new Record();
        int start = 0, end = filesize() - 1, pos;
        boolean change = true;
        while (start < end && change) {
            change = false;
            for (pos = start; pos < end; pos++) {
                seekFile(pos);
                rec1.read(file);
                rec2.read(file);
                comp++;
                if (rec1.getcod() > rec2.getcod()) {
                    seekFile(pos);
                    rec2.write(file);
                    rec1.write(file);
                    mov += 2;
                    change = true;
                }
            }
            end--;
            if(change){
                change = false;
                for (pos = end - 1; pos > start; pos--) {
                    seekFile(pos);
                    rec1.read(file);
                    rec2.read(file);
                    comp++;
                    if (rec1.getcod() > rec2.getcod()) {
                        seekFile(pos);
                        rec2.write(file);
                        rec1.write(file);
                        mov += 2;
                        change = true;
                    }
                }
            }
            start++;
        }
    }

    public void couting_sort() {
        File count = new File("count.dat"), sorted = new File("sorted.dat");
        count.truncate(0);
        sorted.truncate(0);
        int length = filesize(), major = 0;
        Record rec = new Record(), rec2 = new Record();

        for (int i = 0; i < length; i++) {
            seekFile(i);
            rec.read(file);
            comp++;
            if (rec.getcod() > major) {
                major = rec.getcod();
            }
        }

        for (int i = 0; i < major + 1; i++) {
            count.seekFile(i);
            rec.setcod(0);
            rec.write(count.file);
            mov++;
        }

        //count
        for (int i = 0; i < length; i++) {
            seekFile(i);
            rec.read(file);
            count.seekFile(rec.getcod());
            rec2.read(count.file);
            rec2.setcod(rec2.getcod() + 1);
            count.seekFile(rec.getcod());
            rec2.write(count.file);
            mov++;
        }

        //acumulative
        int acumulative = 0;
        for (int i = 0; i < major + 1; i++) {
            count.seekFile(i);
            rec.read(count.file);
            acumulative += rec.getcod();
            count.seekFile(i);
            rec.setcod(acumulative);
            rec.write(count.file);
            mov++;
        }

        int pos;
        for (int i = length - 1; i >= 0; i--) {
            seekFile(i);
            rec.read(file);
            count.seekFile(rec.getcod());
            rec2.read(count.file);
            pos = rec2.getcod();
            rec2.setcod(rec2.getcod() - 1);
            count.seekFile(rec.getcod());
            rec2.write(count.file); //mov
            sorted.seekFile(pos - 1);
            rec.write(sorted.file); //mov
            mov+=2;
        }

        seekFile(0);
        sorted.seekFile(0);
        while (!sorted.eof()) {
            rec.read(sorted.file);
            rec.write(file);
            mov++;
        }

    }

    public void couting_sort(int radix) {
        File count = new File("count.dat"), sorted = new File("sorted.dat");
        count.truncate(0);
        sorted.truncate(0);
        int length = filesize(), major = 0;
        Record rec = new Record(), rec2 = new Record();

        for (int i = 0; i < length; i++) {
            seekFile(i);
            rec.read(file);
            comp++;
            if (getDigit(rec.getcod(), radix) > major) {
                major = getDigit(rec.getcod(), radix);
            }
        }

        for (int i = 0; i < major + 1; i++) {
            count.seekFile(i);
            rec.setcod(0);
            rec.write(count.file);
            mov++;
        }

        //count
        for (int i = 0; i < length; i++) {
            seekFile(i);
            rec.read(file);
            count.seekFile(getDigit(rec.getcod(), radix));
            rec2.read(count.file);
            rec2.setcod(rec2.getcod() + 1);
            count.seekFile(getDigit(rec.getcod(), radix));
            rec2.write(count.file);
            mov++;
        }

        //acumulative
        int acumulative = 0;
        for (int i = 0; i < major + 1; i++) {
            count.seekFile(i);
            rec.read(count.file);
            acumulative += rec.getcod();
            count.seekFile(i);
            rec.setcod(acumulative);
            rec.write(count.file);
            mov++;
        }

        int pos;
        for (int i = length - 1; i >= 0; i--) {
            seekFile(i);
            rec.read(file);
            count.seekFile(getDigit(rec.getcod(), radix));
            rec2.read(count.file);
            pos = rec2.getcod();
            rec2.setcod(rec2.getcod() - 1);
            count.seekFile(getDigit(rec.getcod(), radix));
            rec2.write(count.file);
            sorted.seekFile(pos - 1);
            rec.write(sorted.file);
            mov+=2;
        }


        seekFile(0);
        sorted.seekFile(0);
        while (!sorted.eof()) {
            rec.read(sorted.file);
            rec.write(file);
            mov++;
        }

    }


    public void radix_sort(int maxLength) {
        for (int i = 0; i < maxLength; i++) {
            couting_sort(i);
        }
    }

    public int getDigit(int number, int index) {
        if (index < 0 || number == 0) {
            return 0;
        }
        int divider = (int) Math.pow(10, index);
        return (number / divider) % 10;
    }

    public void heap_sort() {
        int TL = filesize(), root, nodeL, nodeR, majorNode;
        Record recL = new Record(), recR = new Record(), recRoot = new Record(), majorRec = new Record(), aux = new Record();

        while (TL > 1) {
            for (root = TL / 2 - 1; root >= 0; root--) {
                nodeL = 2 * root + 1;
                nodeR = nodeL + 1;
                seekFile(nodeL);
                recL.read(file);
                if (nodeR < TL) {
                    seekFile(nodeR);
                    recR.read(file);
                    majorNode = recR.getcod() > recL.getcod() ? nodeR : nodeL;
                    comp++;
                } else {
                    majorNode = nodeL;
                }
                seekFile(root);
                recRoot.read(file);
                seekFile(majorNode);
                majorRec.read(file);
                comp++;
                if (majorRec.getcod() > recRoot.getcod()) {
                    aux = new Record(majorRec);
                    seekFile(majorNode);
                    recRoot.write(file);
                    seekFile(root);
                    aux.write(file);
                    mov += 2;
                }
            }
            seekFile(0);
            recRoot.read(file);
            seekFile(TL - 1);
            aux.read(file);
            seekFile(0);
            aux.write(file);
            seekFile(TL - 1);
            recRoot.write(file);
            mov += 2;
            TL--;
        }
    }

    public void bucket_sort(int n) {
        int i, pos, min = minor(), max = max();
        Record rec = new Record();
        File[] buckets = new File[n];

        for (i = 0; i < n; i++) {
            buckets[i] = new File(i + ".dat");
            buckets[i].truncate(0);
        }

        for (i = 0; i < filesize(); i++) {
            seekFile(i);
            rec.read(file);
            pos = (rec.getcod() - min) * (n - 1) / (max - min);
            buckets[pos].insertAtEnd(rec);
            mov++;
        }

        for (i = 0; i < n; i++) {
            if (buckets[i].file != null) {
                buckets[i].insertion_sort();
            }
        }

        pos = 0;
        for (i = 0; i < n; i++) {
            if (buckets[i] != null) {
                buckets[i].seekFile(0);
                while (!buckets[i].eof()) {
                    rec.read(buckets[i].file);
                    seekFile(pos);
                    rec.write(file);
                    pos++;
                    mov++;
                }
            }
        }
    }

    public void shell_sort() {
        Record aux = new Record(), aux2 = new Record();
        int i, j, length = filesize(), dist = 1;

        while (dist < length) {
            dist = dist * 3 + 1;
        }
        dist /= 3;

        while (dist > 0) {
            for (i = dist; i < length; i++) {
                j = i;
                seekFile(j);
                aux.read(file);
                seekFile(j - dist);
                aux2.read(file);
                comp++;
                while (j - dist >= 0 && aux.getcod() < aux2.getcod()) {
                    seekFile(j);
                    aux2.write(file);
                    mov++;
                    j -= dist;
                    if (j - dist >= 0) {
                        seekFile(j - dist);
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

    public void gnome_sort() {
        int pos = 0, length = filesize();
        Record rec = new Record(), rec2 = new Record();
        while (pos < length) {
            if (pos == 0) {
                pos++;
            }
            seekFile(pos - 1);
            rec2.read(file);
            rec.read(file);
            comp++;
            while (pos > 0 && rec.getcod() < rec2.getcod()) {
                seekFile(pos - 1);
                rec.write(file);
                rec2.write(file);
                pos--;
                mov += 2;
                if (pos > 0) {
                    seekFile(pos - 1);
                    rec2.read(file);
                    rec.read(file);
                }
                comp++;
            }
            pos++;
        }
    }

    public int gapCalculate(int gap) {
        int newGap = (int) (gap / 1.3);
        return Math.max(newGap, 1);
    }

    public void comb_sort() {
        int i, j;
        Record rec1 = new Record(), rec2 = new Record();
        int length = filesize(), gap = length;
        while (gap > 1) {
            i = 0;
            gap = gapCalculate(gap);
            j = i + gap;
            while (j < length) {
                seekFile(i);
                rec1.read(file);
                seekFile(j);
                rec2.read(file);
                comp++;
                if (rec1.getcod() > rec2.getcod()) {
                    seekFile(i);
                    rec2.write(file);
                    seekFile(j);
                    rec1.write(file);
                    mov += 2;
                }
                i++;
                j = i + gap;
            }
        }
    }

    public void quick_sort1() {
        quick_sort_sp(0, filesize() - 1);
    }

    public void quick_sort2() {
        quick_sort_cp(0, filesize() - 1);
    }

    public void quick_sort_sp(int start, int end) {
        Record recI = new Record(), recJ = new Record();
        int i = start, j = end;

        while (i < j) {

            seekFile(i);
            recI.read(file);
            seekFile(j);
            recJ.read(file);

            comp++;
            while (i < j && recI.getcod() <= recJ.getcod()) {
                i++;
                seekFile(i);
                recI.read(file);
                comp++;
            }

            comp++;
            if (recI.getcod() != recJ.getcod()) {
                seekFile(i);
                recI.read(file);
                seekFile(j);
                recJ.read(file);
                seekFile(j);
                recI.write(file);
                seekFile(i);
                recJ.write(file);
                mov += 2;
            }

            seekFile(i);
            recI.read(file);
            seekFile(j);
            recJ.read(file);

            comp++;
            while (i < j && recI.getcod() <= recJ.getcod()) {
                j--;
                seekFile(j);
                recJ.read(file);
                comp++;
            }

            comp++;
            if (recI.getcod() != recJ.getcod()) {
                seekFile(i);
                recI.read(file);
                seekFile(j);
                recJ.read(file);
                seekFile(j);
                recI.write(file);
                seekFile(i);
                recJ.write(file);
                mov += 2;
            }
        }

        if (start < i - 1) {
            quick_sort_sp(start, i - 1);
        }
        if (j + 1 < end) {
            quick_sort_sp(j + 1, end);
        }
    }

    public void quick_sort_cp(int start, int end) {
        int i = start, j = end;
        Record recI = new Record(), recJ = new Record(), recPivot = new Record();

        seekFile((i + j) / 2);
        recPivot.read(file);

        while (i < j) {
            seekFile(i);
            recI.read(file);
            comp++;
            while (recI.getcod() < recPivot.getcod()) {
                i++;
                recI.read(file);
                comp++;
            }

            seekFile(j);
            recJ.read(file);
            comp++;
            while (recJ.getcod() > recPivot.getcod()) {
                j--;
                seekFile(j);
                recJ.read(file);
                comp++;
            }

            if (i <= j) {
                seekFile(i);
                recJ.write(file);
                seekFile(j);
                recI.write(file);
                i++;
                j--;
                mov += 2;
            }
        }

        if (start < i) {
            quick_sort_cp(start, j);
        }

        if (j < end) {
            quick_sort_cp(i, end);
        }
    }

    public void partition(File a, File b) {
        Record recA = new Record(), recB = new Record();
        a.seekFile(0);
        b.seekFile(0);
        int length = filesize(), k = 0, aux = length / 2;
        while (k < aux) {
            seekFile(k);
            recA.read(file);
            recA.write(a.file);
            seekFile(k + aux);
            recB.read(file);
            recB.write(b.file);
            k++;
            mov += 2;
        }
    }

    public void segmentation(File a, File b, int seg) {
        Record recA = new Record(), recB = new Record();
        int aux = seg, k = 0, length = filesize(), i = 0, j = 0;
        seekFile(0);
        while (k < length) {
            while (i < seg && j < seg) {
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

    public void merge_sort_first_implementation() {
        File a = new File("aux1.dat"), b = new File("aux2.dat");
        int seg = 1, length = filesize();
        while (seg < length) {
            a.truncate(0);
            b.truncate(0);
            partition(a, b);
            segmentation(a, b, seg);
            seg *= 2;
        }
    }


    // second implementation

    public void fusion(File aux, int start1, int end1, int start2, int end2) {
        aux.truncate(0);
        int t_start1 = start1;
        Record rec1 = new Record(), rec2 = new Record();
        while (start1 <= end1 && start2 <= end2) {
            seekFile(start1);
            rec1.read(file);
            seekFile(start2);
            rec2.read(file);

            mov += 2;

            comp++;
            if (rec1.getcod() < rec2.getcod()) {
                rec1.write(aux.file);
                start1++;
            } else {
                rec2.write(aux.file);
                start2++;
            }
            mov++;
        }

        while (start1 <= end1) {
            seekFile(start1);
            rec1.read(file);
            rec1.write(aux.file);
            start1++;
            mov++;
        }

        while (start2 <= end2) {
            seekFile(start2);
            rec2.read(file);
            rec2.write(aux.file);
            start2++;
            mov++;
        }

        int length = aux.filesize();
        aux.seekFile(0);
        seekFile(t_start1);
        for (int i = 0; i < length; i++) {
            rec1.read(aux.file);
            rec1.write(file);
            mov++;
        }
    }

    public void merge(File aux, int left, int right) {
        int half;
        if (left < right) {
            half = (left + right) / 2;
            merge(aux, left, half);
            merge(aux, half + 1, right);
            fusion(aux, left, half, half + 1, right);
        }
    }

    public void merge_sort_second_implementation() {
        int length = filesize();
        File aux = new File("aux.dat");
        aux.truncate(0);
        merge(aux, 0, length - 1);
    }

    private void insertion_sort_for_tim(int start, int end) {
        Record rec = new Record(), aux = new Record();
        int i = start + 1, j;
        while (i <= end) {
            j = i;
            seekFile(j);
            rec.read(file);
            seekFile(j - 1);
            aux.read(file);
            comp++;
            while (j > 0 && rec.getcod() < aux.getcod()) {
                seekFile(j);
                aux.write(file);
                mov++;
                j--;
                if (j > 0) {
                    seekFile(j - 1);
                    aux.read(file);
                    mov++;
                }
                comp++;
            }
            seekFile(j);
            rec.write(file);
            mov++;
            i++;
        }
    }

    public void tim_sort() {
        int length = filesize(), run = 32;
        File aux = new File("aux.dat");
        for (int i = 0; i < length; i += run) {
            insertion_sort_for_tim(i, Math.min(i + run - 1, length - 1));
        }

        for (int size = run; size < length; size = 2 * size) {
            for (int left = 0; left < length; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (length - 1));
                fusion(aux, left, mid, mid + 1, right);
            }
        }
    }
    public void StartQuick(){
        QuickComPivot(0, filesize() - 1);
    }
    public void QuickSortSemPivot(int inicio, int fim) {
        Record rec1 = new Record(), rec2 = new Record();
        int auxInicio = inicio, auxFim = fim;

        while (inicio < fim) {
            seekFile(fim);
            rec2.read(file);

            seekFile(inicio);
            rec1.read(file);

            while (fim > inicio && rec1.getcod() < rec2.getcod()) {
                rec1.read(file);
                inicio++;
            }

            if (rec1.getcod() != rec2.getcod()) {
                seekFile(fim);
                rec1.write(file);
                seekFile(inicio);
                rec2.write(file);
            }

            seekFile(inicio);
            rec1.read(file);

            seekFile(fim);
            rec2.read(file);

            while (fim > inicio && rec2.getcod() > rec1.getcod()) {
                fim--;
                seekFile(fim);
                rec2.read(file);
            }

            if (rec1.getcod() != rec2.getcod()) {
                seekFile(fim);
                rec1.write(file);
                seekFile(inicio);
                rec2.write(file);
            }
        }

        if (auxInicio  < inicio - 1) {
            QuickSortSemPivot(auxInicio, inicio-1);
        }

        if (auxFim > fim + 1) {
            QuickSortSemPivot(fim+1,auxFim );
        }

    }

    public void QuickComPivot(int inicio, int fim){
        int i = inicio, j = fim, pivot, aux;
        Record rec1 = new Record(), rec2 = new Record();

        while(i < j){
            seekFile((inicio+fim)/2);
            rec1.read(file);
            pivot = rec1.getcod();

            seekFile(i);
            rec1.read(file);

            while(rec1.getcod() < pivot){
                rec1.read(file);
                i++;
            }

            seekFile(j);
            rec2.read(file);

            while(rec2.getcod() > pivot){
                j--;
                seekFile(j);
                rec2.read(file);
            }

            if(i <= j){
                seekFile(i);
                rec2.write(file);
                seekFile(j);
                rec1.write(file);
                i++;
                j--;
            }
        }

        if(inicio < j){
            QuickComPivot(inicio, j);
        }

        if(i < fim){
            QuickComPivot(i, fim);
        }
    }

    public void Particao(File aux1, File aux2){
        Record rec = new Record();
        int tam = filesize();
        seekFile(0);
        for(int i = 0; i < tam/2; i++){
            rec.read(file);
            aux1.insertAtEnd(rec);
        }
        for(int i = tam/2; i < tam; i++){
            rec.read(file);
            aux2.insertAtEnd(rec);
        }
    }

    public void Fusao(File aux1, File aux2, int sequencia){
        Record rec1 = new Record(), rec2 = new Record();
        int k = 0, tam = filesize(), i = 0, j = 0;

        while(k < tam){

            while(i < sequencia && j < sequencia){
                aux1.seekFile(i);
                rec1.read(aux1.file);
                aux2.seekFile(j);
                rec2.read(aux2.file);

                seekFile(k++);
                if(rec1.getcod() < rec2.getcod()){
                    rec1.write(file);
                    i++;
                }
                else {
                    rec2.write(file);
                    j++;
                }
            }

            while(i < sequencia){
                seekFile(k++);
                aux1.seekFile(i);
                rec1.read(aux1.file);
                rec1.write(file);
                i++;
            }


            while(j < sequencia){
                seekFile(k++);
                aux2.seekFile(j);
                rec2.read(aux2.file);
                rec2.write(file);
                j++;
            }

            sequencia += 1;
        }
    }



    public void MergeSort1(){
        File file1 = new File("file1.dat"), file2 = new File("file2.dat");
        int sequencia = 1, tam = filesize();
        while(sequencia < tam){
            file1.truncate(0);
            file2.truncate(0);
            Particao(file1, file2);
            Fusao(file1, file2, sequencia);
            sequencia *= 2;
        }
    }

    public void Fusao2(File aux, int left1, int right1, int left2, int right2){
        Record rec1 = new Record(), rec2 = new Record();
        int inicio = left1;
        aux.truncate(0);

        while(left1 <= right1 && left2 <= right2){
            seekFile(left1);
            rec1.read(file);
            seekFile(left2);
            rec2.read(file);

            if(rec1.getcod() < rec2.getcod()){
                rec1.write(aux.file);
                left1++;
            }
            else {
                rec2.write(aux.file);
                left2++;
            }
        }

        while(left1 <= right1){
            seekFile(left1);
            rec1.read(file);
            rec1.write(aux.file);
            left1++;
        }

        while(left2 <= right2){
            seekFile(left2);
            rec2.read(file);
            rec2.write(aux.file);
            left2++;
        }

        aux.seekFile(0);
        int length = aux.filesize();
        seekFile(inicio);
        for(int i = 0; i < length; i++){
            rec1.read(aux.file);
            rec1.write(file);
        }
    }

    public void StartMerge(){
        File aux = new File("aux.dat");
        MergeSort2(aux, 0, filesize() - 1);
    }

    public void MergeSort2(File aux, int inicio, int fim){

        if(inicio < fim){
            int meio = (inicio+fim)/2;
            MergeSort2(aux, inicio, meio);
            MergeSort2(aux,meio+1, fim);
            Fusao2(aux, inicio, meio, meio+1, fim);
        }
    }

    public void MergeSortIterativo(){
        int bloco = 1, left, half, right;
        File aux = new File("aux.dat");
        int tam = filesize();

        while(bloco < tam){
            left = 0;
            while(left + bloco < tam){
                half = left + bloco;
                right = left + 2 * bloco - 1;
                if (right >= tam) right = tam -1;
                Fusao2(aux, left, half - 1, half, right);
                left = left + bloco * 2;
            }
            bloco *= 2;
        }
    }

    public void HeapSortAlternativo(){
        File aux = new File("aux.dat");
        Record rec1 = new Record(), rec2 = new Record(), maior, recRoot = new Record(), auxRec;
        int root, nodeL, nodeR, posMaior;
        int tam = filesize(), j = tam;
        while(tam > 1){

            for(root = tam/2-1; root >= 0; root--){
                seekFile(root);
                recRoot.read(file);
                nodeL = 2 * root + 1;
                nodeR = nodeL + 1;
                seekFile(nodeL);
                rec1.read(file);
                if(nodeR < tam){
                    seekFile(nodeR);
                    rec2.read(file);
                }
                if(nodeR < tam && rec2.getcod() > rec1.getcod()){
                    maior = rec2;
                    posMaior = nodeR;
                }
                else {
                    maior = rec1;
                    posMaior = nodeL;
                }
                if(recRoot.getcod() < maior.getcod()){
                    seekFile(posMaior);
                    recRoot.write(file);
                    seekFile(root);
                    maior.write(file);
                }
            }

            seekFile(0);
            rec1.read(file);
            aux.seekFile(j);
            rec1.write(aux.file);
            j--;

            System.out.println("Original --->\n");
            showFile();
            System.out.println("Original --->\n");

            root = 0;
            nodeL = 1;
            while(nodeL < tam - 1){
                nodeR = nodeL + 1;
                if(nodeR < tam){
                    seekFile(nodeR);
                    rec1.read(file);
                    seekFile(root);
                    rec1.write(file);
                }
                seekFile(nodeL);
                rec1.read(file);
                seekFile(nodeR);
                rec1.write(file);

                root = nodeL;
                nodeL = 2 * nodeL + 1;
            }

            System.out.println("Aux --->\n");
            aux.showFile();
            System.out.println("Aux --->\n");

            tam--;
        }


    }

}
