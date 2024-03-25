package Tests;

import Array.Array;
import BinaryFile.File;
import BinaryFile.Record;
import List.List;

import java.util.Random;

public class SortingTests {
    public void ListTest () {
        System.out.println("---------> List: ");
        List list = new List();
        Random random = new Random();
        for (int i = 16; i > 0; i = i - 1) {
            list.insertAtEnd(random.nextInt(1000));
        }
        System.out.println("Gerado");
        list.print();
        list.merge_sort_second_implementation();
        System.out.println("Ordenado: ");
        list.print();
    }

    public void ArrayTest () {
        System.out.println("---------> Array: ");
        Array array = new Array();
        for (int i = 160; i > 0; i = i - 17) {
            array.add(i);
        }
        System.out.println("Gerado: ");
        array.print();
        System.out.println("Ordenado: ");
        array.merge_second_implementation();
        array.print();
    }

    public void FileTest(){
        System.out.println("---------> File: ");
        File file = new File("Teste.dat");
        file.seekFile(0);
        file.insertAtEnd(new Record(129, "Teste2", 21));
        file.insertAtEnd(new Record(968, "Teste", 20));
        file.insertAtEnd(new Record(255, "Teste3", 22));
        file.insertAtEnd(new Record(341, "Teste4", 23));
        file.insertAtEnd(new Record(43, "Teste5", 24));
        file.insertAtEnd(new Record(75, "Teste6", 25));
        file.insertAtEnd(new Record(637, "Teste7", 26));
        file.insertAtEnd(new Record(38, "Teste8", 27));
        file.insertAtEnd(new Record(66, "Teste9", 28));
        file.insertAtEnd(new Record(547, "Teste10", 29));
        file.insertAtEnd(new Record(29, "Teste11", 30));
        file.insertAtEnd(new Record(13, "Teste12", 31));
        System.out.println("Gerado: ");
        file.showFile();
        System.out.println("Ordenado: ");
        file.couting_sort();
        file.showFile();
    }
}
