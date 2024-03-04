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
            list.insertAtEnd(random.nextInt(100));
        }
        System.out.println("Gerado");
        list.print();
        list.binary_insertion_sort();
        System.out.println("Ordenado: ");
        list.print();
    }

    public void ArrayTest () {
        Array array = new Array();
        for (int i = 50; i > 0; i--) {
            array.add(i);
        }
        System.out.println("Gerado: ");
        array.print();
        System.out.println("Ordenado: ");
        array.shake_sort();
        array.print();
    }

    public void FileTest(){
        File file = new File("Teste.dat");
        file.seekFile(0);
        file.insertAtEnd(new Record(9, "Teste", 20));
        file.insertAtEnd(new Record(12, "Teste2", 21));
        file.insertAtEnd(new Record(15, "Teste3", 22));
        file.insertAtEnd(new Record(1, "Teste4", 23));
        file.insertAtEnd(new Record(3, "Teste5", 24));
        file.insertAtEnd(new Record(5, "Teste6", 25));
        file.insertAtEnd(new Record(7, "Teste7", 26));
        file.insertAtEnd(new Record(8, "Teste8", 27));
        file.insertAtEnd(new Record(6, "Teste9", 28));
        file.insertAtEnd(new Record(4, "Teste10", 29));
        file.insertAtEnd(new Record(2, "Teste11", 30));
        file.insertAtEnd(new Record(10, "Teste12", 31));
        file.insertAtEnd(new Record(11, "Teste13", 32));
        file.shake_sort();
        file.showFile();
    }
}
