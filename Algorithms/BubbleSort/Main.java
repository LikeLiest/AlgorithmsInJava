package AlgorithmsInJava.Algorithms.BubbleSort;

import java.util.Arrays;
import java.util.Random;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[20];

        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(0, 20);
        }
        out.println(Arrays.toString(array));
        out.println(Arrays.toString(bubbleSort(array)));
    }

    private static int[] bubbleSort(int[] array) {
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 1; i < array.length; i++) {
              int previous =  array[i - 1];
              int current = array[i];

              if (previous < current) {
                  swap(array,i - 1, i);
                  sorted = false;
              }
            }
        }
        return  array;
    }

    private static void swap(int[] array, int index1, int index2) {
        int buffer = array[index1];
        array[index1] = array[index2];
        array[index2] = buffer;
    }
}
