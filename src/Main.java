import List.List;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List list = new List();
        Random random = new Random();
        for(int i = 50; i>0; i--){
            list.insertAtEnd(i);
        }
        System.out.println("Gerado");
        list.print();
        list.insertion_sort();
        System.out.println("Ordenado: ");
        list.remove(1);
        list.print();
    }
}