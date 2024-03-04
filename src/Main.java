import List.List;
import Array.Array;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List list = new List();
        Random random = new Random();
        for(int i = 50; i>0; i--){
            list.insertAtEnd(random.nextInt(100));
        }
        System.out.println("Gerado");
        list.print();
        list.bubble_sort();
        System.out.println("Ordenado: ");
        list.print();

//        Array array = new Array();
//        for(int i = 50; i > 0; i --) {
//            array.add(i);
//        }
//        System.out.println("Gerado: ");
//        array.print();
//        System.out.println("Ordenado: ");
//        array.shake_sort();
//        array.print();


    }
}