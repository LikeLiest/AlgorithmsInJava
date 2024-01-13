package AlgorithmsInJava.Algorithms.SimpleNumbers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(getPrimes(100));
    }


// Простое число - это число, которое делится только на себя и 1. Т.е 2,3,5,7,11 ...

    private static List<Integer> getPrimes(int count) {
        List<Integer> primes = new ArrayList<>();

        if (count > 0) {
//            n = 2
            primes.add(2);
//            Каждое четное число не может быть простым
            for (int i = 3; primes.size() < count; i += 2) {
                if (isPrime(i,primes)) {
                    primes.add(i);
                }
            }
        }
        return primes;
    }

/*
*   Мы исходим из того, что некое число M состоит из множителей (которых не меньше двух),
*   то максимальный множитель, который может быть, это N, где N * N = M.
*   Если множитель больше N, то M мы уже никак не получим, т.к. перебираем множители всегда от меньшего к большему.
*   Отсюда следует, что искать множители надо в диапазоне между 2 и квадратным корнем из M.
*   Например, если N = 100, то квадратный корень из N равен 10, и мы можем проверить только числа от 2 до 10,
*   что является меньшим набором чисел, чем все числа от 2 до 100. Проверка всех чисел от 2 до 100 заняла бы больше времени и ресурсов.
*/

    private static boolean isPrime(int number,List<Integer> primes) {
        /*
        * По теореме о делителях, если число number не является простым,
        * то у него должен быть делитель, который меньше или равен его квадратному корню.
        */
        double sqrt = sqrt(number);

        for (int n:primes){
            if (n > sqrt){
                return true;
            }
            if (number % n  == 0) {
                return false;
            }
        }

        return true;

    }
}
