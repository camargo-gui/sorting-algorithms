package BinaryFile;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Record {
        public final int tf=510;
        private int cod;
        private char trash[] = new char[tf];

        public Record(int cod){
            this.cod = cod;
            for (int i=0 ; i<tf ; i++)
                trash[i]='X';
        }

        public Record(Record rec){
            this.cod = rec.cod;
            for (int i=0 ; i<tf ; i++)
                trash[i]=rec.trash[i];
        }

        public Record(){}

        public int getcod()
        {
            return (cod);
        }

        public void setcod(int cod)
        {
            this.cod = cod;
        }

        public void write(RandomAccessFile file)
        {
            try
            {
                file.writeInt(cod);
                for(int i=0 ; i<tf ; i++)
                    file.writeChar(trash[i]);
            }catch(IOException e){}
        }

        public void read(RandomAccessFile file)
        {
            try
            {
                cod = file.readInt();
                for(int i=0 ; i<tf ; i++)
                    trash[i]=file.readChar();
            }catch(IOException e){}
        }

        public void print()
        {
            System.out.print(" cod: " + this.cod + "\n");
        }

        static int length() {
            return (1024);
        //int numero; 4 bytes
        //char lixo[] = new char[tf]; 2044 bytes
        //--------------------------------------
        // 2048 bytes
        }    
}
