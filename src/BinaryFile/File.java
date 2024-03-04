package BinaryFile;

import java.io.IOException;
import java.io.RandomAccessFile;

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


    // fazer com busca binária
    public void insertion_sort(){
        Record rec1 = new Record(), rec2 = new Record();
        int i = 0, j, aux, tam = filesize();
        while(i < tam){
            j = i + 1;
            seekFile(i);
            rec1.read(file);
            rec2.read(file);
            while(j > 0 && rec2.getcod() < rec1.getcod()){
                seekFile(j-1);
                rec2.write(file);
                rec1.write(file);
                j--;
                seekFile(j-1);
                rec1.read(file);
                rec2.read(file);
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


    // Por que precisa ser pos <= ??
    public void shake_sort(){
        Record rec1 = new Record(), rec2 = new Record();
        int start = 0, end = filesize() - 1, pos;
        boolean change = true;
        while(start < end && change){
            change = false;
            for(pos = start; pos < end; pos++){
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

}
