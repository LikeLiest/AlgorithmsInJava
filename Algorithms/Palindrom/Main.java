package AlgorithmsInJava.Algorithms.Palindrom;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        out.println(isWordPalindrome("Word")); // F
        out.println(isWordPalindrome("ded")); // T

//         --------------------------------------------------------

        out.println(isTextPalindrome("Искать такси")); // T
        out.println(isTextPalindrome("Лидер бредил")); // T
        out.println(isTextPalindrome("А роза упала на лапу Азора")); // T
        out.println(isTextPalindrome("Уж редко рукою окурок держу")); // T
        out.println(isTextPalindrome("Hello world")); // F

    }

    private static boolean isWordPalindrome(String word) {
        var chars = word.toLowerCase().toCharArray();
        var left = 0;
        var right = chars.length - 1;

        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isTextPalindrome(String text) {
        var chars = text.toLowerCase().toCharArray();
        var left = 0;
        var right = text.length() - 1;

        while (left < right) {
            if (chars[left] != chars[right])
                return false;

            do {
                left++;
            } while (left < right && chars[left] == ' ');

            do {
                right--;
            } while (right > left && chars[right] == ' ');
        }

        return true;
    }
}
