package AlgorithmsInJava.BinarySearch;

public class Main {
    public static void main(String[] args) {
        int[] sortedArray = new int[1_000_000];

        for (int i = 0; i < 1_000_000; i++) {
           sortedArray[i] = i * 10;
        }

        int index = binaryIndexOf(sortedArray,500_000);
        System.out.println(index);

    }

    private static int binaryIndexOf(int[] sortedArray, int element) {
        int left = 0;
        int right = sortedArray.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            int current = sortedArray[middle];

            if (current == element) {
                return middle;
            } else if (current < element) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return  -1;
    }
}
