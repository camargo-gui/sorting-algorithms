package Tests;

import Array.Array;
import BinaryFile.File;
import BinaryFile.Record;
import List.List;

import java.util.Random;

public class SortingTests {
    public void ListTest () {
        List list = new List();
        Random random = new Random();
        for (int i = 50; i > 0; i--) {
            list.insertAtEnd(random.nextInt(89) + 10);
        }
        System.out.println("Gerado");
        list.print();
        list.bucket_sort();
        System.out.println("Ordenado: ");
        list.print();
    }

    public void ArrayTest () {
        Array array = new Array();
        for (int i = 99; i > 10; i = i - 5) {
            array.add(i);
        }
        System.out.println("Gerado: ");
        array.print();
        System.out.println("Ordenado: ");
        array.bucket_sort();
        array.print();
    }

    public void FileTest(){
        File file = new File("Teste.dat");
        file.seekFile(0);
        file.insertAtEnd(new Record(12, "Teste2", 21));
        file.insertAtEnd(new Record(98, "Teste", 20));
        file.insertAtEnd(new Record(25, "Teste3", 22));
        file.insertAtEnd(new Record(31, "Teste4", 23));
        file.insertAtEnd(new Record(43, "Teste5", 24));
        file.insertAtEnd(new Record(75, "Teste6", 25));
        file.insertAtEnd(new Record(67, "Teste7", 26));
        file.insertAtEnd(new Record(38, "Teste8", 27));
        file.insertAtEnd(new Record(66, "Teste9", 28));
        file.insertAtEnd(new Record(54, "Teste10", 29));
        file.insertAtEnd(new Record(29, "Teste11", 30));
        file.insertAtEnd(new Record(13, "Teste12", 31));
        file.insertAtEnd(new Record(11, "Teste13", 32));
        System.out.println("Gerado: ");
        file.showFile();
        System.out.println("Ordenado: ");
        file.bucket_sort();
        file.showFile();
    }
}
