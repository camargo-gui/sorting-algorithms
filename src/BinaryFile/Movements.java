package BinaryFile;

public class Movements {

    public int orderedInsertionSort(int TL){
        return 3 * (TL - 1);
    }

    public int reversedInsertionSort(int TL){
        return (int) ((Math.pow(TL,2) + 9*TL - 10) / 4);
    }

    public int randomInsertionSort(int TL){
        return (int) ((Math.pow(TL,2) + 3*TL - 4) / 4);
    }

    public int orderedSelectionSort(int TL){
        return (int) 3 * (TL - 1);
    }

    public int randomSelectionSort(int TL){
        return (int) (TL * (Math.log(TL) + 0.5772));
    }

    public int reversedSelectionSort(int TL){
        return (int) ((Math.pow(TL,2))/4 + 3*(TL-1));
    }

    public int orderedBubbleAndShake(int TL){
        return 0;
    }

    public int randomBubbleAndShake(int TL){
        return (int) (3 * (Math.pow(TL,2) - TL) / 2);
    }

    public int reversedBubbleAndShake(int TL){
        return (int) (3 * (Math.pow(TL,2) - TL) / 4);
    }

}
