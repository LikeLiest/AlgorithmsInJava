package AlgorithmsInJava.ArrayReverse;

import java.util.Arrays;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        var arr = new Integer[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        out.println(Arrays.toString(arr));

        invertArray(arr);

        out.println(Arrays.toString(arr));
    }

    public static <T> void invertArray(T[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            T temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
}
