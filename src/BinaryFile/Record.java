package BinaryFile;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Record {
        public final int TF = 20;
        private int cod, tl = TF, age;
        private char name[] = new char[TF];

        public Record(){}

        public Record(int cod, String Sname, int age)
        {
            this.cod = cod;
            this.age = age;
            this.tl = Sname.length();
            for (int i = 0; i < Sname.length(); i++)
            {
                name[i] = Sname.charAt(i);
            }
        }

    public Record(Record rec) {
            cod = rec.cod;
            age = rec.age;
            name = rec.name;
            tl = rec.name.length;
    }

    public int getcod()
        {
            return (cod);
        }

        public String getname()
        {
            return (new String(name));
        }

        public void write(RandomAccessFile file)
        {
            try
            {
                file.writeInt(cod);
                file.writeInt(age);
                file.writeInt(tl);
                for (int i = 0; i < TF; i++)
                {
                    file.writeChar(name[i]);
                }
            } catch (IOException e)
            { }
        }

        public void read(RandomAccessFile file)
        {
            try
            {
                this.cod = file.readInt();
                this.age = file.readInt();
                this.tl = file.readInt();
                for (int i = 0; i < this.TF; i++)
                    this.name[i] = file.readChar();
                for (int i = tl; i < TF; i++)
                    this.name[i] = ' ';
            } catch (IOException e)
            { }
        }

        public void print()
        {
            int i;
            System.out.print("cod....." + this.cod);
            System.out.print(" name.......");
            String Sname = new String(name);
            System.out.print(Sname);
            System.out.println(" age......." + this.age);
            System.out.println("----------------------------------");
        }

        static int length() {
            return (52); 
            //int cod, tl=20, age; ------------> 12 bytes
            //private char name[] = new char[20]; --> 40 bytes
            //------------------------------------------------- +
            //                      Total : 40 + 12 = 52 bytes
        }    
}
