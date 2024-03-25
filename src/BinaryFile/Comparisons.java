package BinaryFile;

public class Comparisons {
    public int orderedInsertionSort(int TL){
        return TL - 1;
    }

    public int reveresedInsertionSort(int TL){
        return (int) ((Math.pow(TL, 2) + (TL-2)) / 4);
    }

    public int randomInsertionSort(int TL){
        return (int) ((Math.pow(TL, 2) + (TL-4)) / 4);
    }

    public int binaryInsertionSort(int TL){
        return (int) (TL * (Math.log(TL) - Math.log(Math.E) + 0.5));
    }

    public int selectionSort(int TL){
        return (int) (Math.pow(TL, 2) - TL) / 2;
    }

    public int bubbleAndShakeSort(int TL){
        return (int) (Math.pow(TL, 2) - TL) / 2;
    }
}
