package BinaryFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.rmi.registry.Registry;

public class File {
    private String fileName;
    private RandomAccessFile file;

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
        while(start < end && reg.getcod() != info){
            if(reg.getcod() > info){
                start = half + 1;
            }
            if(reg.getcod() < info){
                end = half - 1;
            }
            half = (start+end)/2;
            seekFile(half);
            reg.read(file);
        }
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
                if(rec1.getcod() > rec2.getcod()){
                    seekFile(j-1);
                    rec2.write(file);
                    rec1.write(file);
                    change = true;
                }
                j--;
            }
            i ++;
        }
    }

    //resolver atribuição menor
    public void selection_sort(){
        Record rec = new Record(), minor = new Record();
        int pos = 1, tam = filesize(), posMinor = 0, i;
        seekFile(0);
        minor.read(file);
        while(pos < tam){
            i = pos;
            while(i < tam){
                seekFile(i);
                rec.read(file);
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
                if(rec1.getcod() > rec2.getcod()){
                    seekFile(i);
                    rec2.write(file);
                    rec1.write(file);
                    change = true;
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
                if(rec1.getcod() > rec2.getcod()){
                    seekFile(pos);
                    rec2.write(file);
                    rec1.write(file);
                    change = true;
                }
            }
            end--;
            if(change){
                change = false;
                for(pos = end; pos >= start; pos--){
                    seekFile(pos);
                    rec1.read(file);
                    rec2.read(file);
                    if(rec1.getcod() > rec2.getcod()){
                        seekFile(pos);
                        rec2.write(file);
                        rec1.write(file);
                        change = true;
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
            if(rec.getcod() > major){
                major = rec.getcod();
            }
        }

        int []  B = new int[major ];

        seekFile(0);
        while(!eof()){
            rec.read(file);
            B[rec.getcod() - 1] += 1;
        }

        for(int i=1; i<major; i++){
            B[i] += B[i-1];
        }

        for(int i = tam; i>=0; i--){
            seekFile(i);
            rec.read(file);
            pos = B[rec.getcod() - 1];
            B[rec.getcod() - 1] -= 1;
            aux.seekFile(pos-1);
            rec.write(aux.file);
        }

        for(int i = 0; i < filesize(); i++){
            aux.seekFile(i);
            seekFile(i);
            rec.read(aux.file);
            rec.write(file);
        }
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
                while(j-dist >= 0 && aux.getcod() < aux2.getcod()){
                    seekFile(j);
                    aux2.write(file);
                    j-=dist;
                    seekFile(j-dist);
                    aux2.read(file);
                }
                seekFile(j);
                aux.write(file);
            }
            dist /= 3;
        }
    }


}
