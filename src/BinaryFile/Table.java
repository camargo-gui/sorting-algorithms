package BinaryFile;

import java.io.*;

public class Table {

    public void run() throws IOException {
        buildTable();
        showTable();

    }

    public void buildTable() throws IOException {
        long initialTime, generalTime = System.currentTimeMillis();
        int orderedTotalTime, orderedComp, orderedMov, reversedComp, reversedMov, randomComp, randomMov, reversedTotalTime, randomTotalTime;

        Comparisons comp = new Comparisons();
        Movements mov = new Movements();

        FileWriter table = initTable();

        File orderedFile = new File("OrderedFile.dat");
        File randomFile = new File("randomFile.dat");
        File reversedFile = new File("reversedFile.dat");

        orderedFile.buildOrderedFile();
        randomFile.buildRandomFile();
        reversedFile.buildReversedFile();

        int TL = orderedFile.filesize();

        File reversedCopy = new File("reversedCopy.dat");
        File randomCopy = new File("randomCopy.dat");

        //Insertion Sort

        System.out.println("Insertion Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.insertion_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");

        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.insertion_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");

        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.insertion_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Insertion Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, comp.orderedInsertionSort(TL), comp.reveresedInsertionSort(TL), comp.randomInsertionSort(TL), mov.orderedInsertionSort(TL), mov.reversedInsertionSort(TL), mov.randomInsertionSort(TL));

        //Binary Insertion Sort

        System.out.println("Binary Insertion Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.binary_insertion_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");

        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.binary_insertion_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.binary_insertion_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Bin Insertion Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, comp.binaryInsertionSort(TL), comp.binaryInsertionSort(TL), comp.binaryInsertionSort(TL), mov.orderedInsertionSort(TL), mov.reversedInsertionSort(TL), mov.randomInsertionSort(TL));

        //Selection Sort

        System.out.println("Selection Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.selection_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");

        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.selection_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");

        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.selection_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Selection Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, comp.selectionSort(TL), comp.selectionSort(TL), comp.selectionSort(TL), mov.orderedSelectionSort(TL), mov.reversedSelectionSort(TL), mov.randomSelectionSort(TL));

        //Bubble Sort

        System.out.println("Bubble Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.bubble_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");

        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.bubble_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");

        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.bubble_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Bubble Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, comp.bubbleAndShakeSort(TL), comp.bubbleAndShakeSort(TL), comp.bubbleAndShakeSort(TL), mov.orderedBubbleAndShake(TL), mov.reversedBubbleAndShake(TL), mov.randomBubbleAndShake(TL));

        //Shake Sort

        System.out.println("Shake Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.shake_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");

        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.shake_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");

        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.shake_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Shake Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, comp.bubbleAndShakeSort(TL), comp.bubbleAndShakeSort(TL), comp.bubbleAndShakeSort(TL), mov.orderedBubbleAndShake(TL), mov.reversedBubbleAndShake(TL), mov.randomBubbleAndShake(TL));

        // Counting Sort

        System.out.println("Counting Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.couting_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");

        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.couting_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");

        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.couting_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Counting Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        //Radix Sort

        System.out.println("Radix Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.radix_sort(4);
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");

        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.radix_sort(4);
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");

        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.radix_sort(4);
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Radix Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        //Heap Sort

        System.out.println("Heap Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.heap_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");

        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.heap_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");

        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.heap_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Heap Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        ///Bucket Sort

        System.out.println("Bucket Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.bucket_sort(10);
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");
        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.bucket_sort(10);
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.bucket_sort(10);
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Bucket Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        //Shell Sort

        System.out.println("Shell Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();
        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.shell_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");
        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.shell_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.shell_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Shell Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        ///Gnome Sort

        System.out.println("Gnome Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();
        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.gnome_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");
        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.gnome_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.gnome_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Gnome Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        //Comb Sort

        System.out.println("Comb Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.comb_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");
        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.comb_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.comb_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Comb Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        //Quick Sort No Pivot

        System.out.println("Quick Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();
        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.quick_sort1();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");
        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.quick_sort1();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.quick_sort1();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Quick Sort s/p", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        //Quick Sort With Pivot

        System.out.println("Quick Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();
        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.quick_sort2();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");
        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.quick_sort2();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.quick_sort2();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Quick Sort c/p", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        //Merge Sort First Implementation

        System.out.println("Merge Sort 1");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.merge_sort_first_implementation();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");
        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.merge_sort_first_implementation();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.merge_sort_first_implementation();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Merge Sort 1", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        //Merge Sort Second Implementation

        System.out.println("Merge Sort 2");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.merge_sort_second_implementation();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");
        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.merge_sort_second_implementation();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.merge_sort_second_implementation();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Merge Sort 2", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        //Tim Sort

        System.out.println("Tim Sort");

        System.out.println("Ordenado");
        orderedFile.showFile();

        //Ordered File
        orderedFile.initComp();
        orderedFile.initMov();
        initialTime = System.currentTimeMillis();
        orderedFile.tim_sort();
        orderedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        orderedComp = orderedFile.getComp();
        orderedMov = orderedFile.getMov();

        System.out.println("Resultado");
        orderedFile.showFile();

        System.out.println("Reverso");
        //Reversed File
        reversedFile.copy(reversedCopy);
        reversedCopy.showFile();
        reversedCopy.initComp();
        reversedCopy.initMov();
        initialTime = System.currentTimeMillis();
        reversedCopy.tim_sort();
        reversedTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        reversedComp = reversedCopy.getComp();
        reversedMov = reversedCopy.getMov();

        System.out.println("Resultado");
        reversedCopy.showFile();

        System.out.println("Aleatório");
        //Random File
        randomFile.copy(randomCopy);
        randomCopy.showFile();
        randomCopy.initComp();
        randomCopy.initMov();
        initialTime = System.currentTimeMillis();
        randomCopy.tim_sort();
        randomTotalTime = (int) (System.currentTimeMillis() - initialTime) / 1000;
        randomComp = randomCopy.getComp();
        randomMov = randomCopy.getMov();

        System.out.println("Resultado");
        randomCopy.showFile();

        writeRow(table, "Tim Sort", orderedComp, reversedComp, randomComp, orderedMov, reversedMov, randomMov, orderedTotalTime, reversedTotalTime, randomTotalTime, 0, 0, 0, 0, 0, 0);

        table.close();

        System.out.println((System.currentTimeMillis() - generalTime)/1000 + " segundos");

    }

    public void showTable() throws IOException {
        FileInputStream stream = new FileInputStream("Tabela.txt");

        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();

        while (linha != null) {
            System.out.println(linha);
            linha = br.readLine();
        }
    }

    private FileWriter initTable() throws IOException {
        FileWriter arq = new FileWriter("Tabela.txt");

        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("===============================================================================================================================================================================================================================================================================================%n");
        gravarArq.printf("| %-17s \t| %-78s | %-78s | %-78s |%n", "Métodos Ordenação", "Arquivo Ordenado", "Arquivo em Ordem Reverso", "Arquivo Randômico");
        gravarArq.printf("===============================================================================================================================================================================================================================================================================================%n");
        gravarArq.printf("\t %-18s | %-13s | %-12s | %-12s | %-12s | %-16s | %-13s | %-12s | %-12s | %-12s | %-16s | %-13s | %-12s | %-12s | %-12s | %-12s |%n",
                "", "Comp. Prog. *", "Comp. Equa. #", "Mov. Prog. +", "Mov. Equa. -", "Tempo",
                "Comp. Prog. *", "Comp. Equa. #", "Mov. Prog. +", "Mov. Equa. -", "Tempo",
                "Comp. Prog. *", "Comp. Equa. #", "Mov. Prog. +", "Mov. Equa. -", "Tempo");
        gravarArq.printf("===============================================================================================================================================================================================================================================================================================%n");

        return arq;
    }

    public void writeRow(FileWriter table, String methodName, int orderedComp, int reversedComp, int randomComp,
                         int orderedMov, int reversedMov, int randomMov,
                         long orderedTempo, long reversedTempo, long randomTempo,
                         int orderedCompEqua, int reversedCompEqua, int randomCompEqua,
                         int orderedMovEqua, int reversedMovEqua, int randomMovEqua) throws IOException {

        PrintWriter gravarArq = new PrintWriter(table);

        gravarArq.printf("| %-17s \t| %,13d | %,12d | %,12d | %,12d | %,17d | %,13d | %,12d | %,12d | %,12d | %,17d | %,13d | %,12d | %,12d | %,12d | %,12d |%n",
                methodName, orderedComp, orderedCompEqua, orderedMov, orderedMovEqua, orderedTempo,
                reversedComp, reversedCompEqua, reversedMov, reversedMovEqua, reversedTempo,
                randomComp, randomCompEqua, randomMov, randomMovEqua, randomTempo);

        gravarArq.flush();
    }


}
