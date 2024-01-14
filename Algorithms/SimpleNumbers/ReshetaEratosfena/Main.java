package AlgorithmsInJava.Algorithms.SimpleNumbers.ReshetaEratosfena;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Main {

// Если вам нужно найти простые числа из определенного диапазона,
// который заранее известен, то рекомендуется использовать этот алгоритм
// Работает раза в 4 быстрее простого поиска простых чисел
    public static void main(String[] args) {
        var primes = EratosthenesSieve.getPrimesLessThan(100_000_000);
        System.out.println(primes.size());
    }


    @SuppressWarnings("all")
    static class EratosthenesSieve {
            private static List<Integer> getPrimesLessThan (final int sieveSize){
    //        BitSet - позволяет оперировать битами, что немного уменьшает затраты памяти
            var sieve = new BitSet(sieveSize);
            sieve.set(2, sieveSize - 1, true);
            sieve.set(0, false);
            sieve.set(1, false);

            for (var i = 2; i * i < sieve.length(); i++) {
                if (sieve.get(i)) {

    //                2 4 6 8 ... 2
    //                3 6 9 12 ... 3
    //                4 8 12 16 ... -
    //                5 10 15 20 .. 5
    //                ...

                    for (var j = i * i; j < sieve.length(); j += i) {
                        sieve.set(j, false);
                    }
                }
            }

            var primes = new ArrayList<Integer>();

            for (var i = 2; i < sieve.length(); i++) {
                if (sieve.get(i)) {
                    primes.add(i);
                }
            }

            return primes;
        }
    }
}
