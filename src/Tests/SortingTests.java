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
        for (int i = 1600; i > 0; i = i - 13) {
            list.insertAtEnd(random.nextInt(1000));
        }
        System.out.println("Gerado");
        list.print();
        list.tim_sort();
        System.out.println("Ordenado: ");
        list.print();
    }

    public void ArrayTest () {
        System.out.println("---------> Array: ");
        Array array = new Array();
        for (int i = 0; i < 64; i++) {
            array.add(new Random().nextInt(160));
        }
//        array.add(2);
//        array.add(5);
//        array.add(1);
//        array.add(9);
//        array.add(3);
//        array.add(7);
//        array.add(4);
//        array.add(6);
        System.out.println("Gerado: ");
        array.print();
        System.out.println("Ordenado: ");
        array.iterative_merge_second_implementation();
        array.print();
    }

    public void FileTest(){
        System.out.println("---------> File: ");
        File file = new File("Teste.dat");
        file.seekFile(0);
        for(int i = 0; i < 8; i++) {
            Record record = new Record(new Random().nextInt(100));
            file.insertAtEnd(record);
        }
        System.out.println("Gerado: ");
        file.showFile();
        System.out.println("Ordenado: ");
        file.HeapSortAlternativo();
        file.showFile();
    }
}
