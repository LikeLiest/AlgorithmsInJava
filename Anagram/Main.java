package AlgorithmsInJava.Anagram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
//        Анаграмма - это случай, когда при перестановке букв в слове может получиться новое слово
//        Фара - Арфа
//        Корма - Комар

        var anagram = new Anagram();

        out.println(anagram.isAnagram("Java", "Hello"));
        out.println(anagram.isAnagram("Фара", "Арфа"));
        out.println(anagram.isAnagram("север", "ветер"));
    }

    static class Anagram {
    //        После небольшой оптимизации в методе isAnagram этот метод больше не нужен.
            private Map<Character, Integer> getLetterStat (String word){

    //        У HashMap самый быстрый доступ по ключу, но порядок элементов не гарантирован
    //        Чтобы, сохранить порядок элементов нужно использовать LinkedHashMap
    //        TreeMap - элементы хранятся в отсортированном порядке. В соответствии с естественным порядке ключе или компаратор

            Map<Character, Integer> stat = new HashMap<>();

            for (int i = 0; i < word.length(); i++) {
                char c = word.toLowerCase().charAt(i);
                /*
                *    Увеличивает счетчик для ключа c в Map на 1. Если ключ c уже существует в Map,
                *    то его текущее значение увеличивается на 1. Если ключа c еще нет в Map, то создается запись с ключом c и значением 1.
                */
                stat.put(c, stat.getOrDefault(c, 0) + 1);
            }
            return stat;
        }

            public boolean isAnagram (String left, String right){
            if (left.length() != right.length())
                return false;

            var leftStat = left.toCharArray();
            Arrays.sort(leftStat);

            var rightStat = right.toCharArray();
            Arrays.sort(rightStat);


            return Arrays.equals(leftStat,rightStat);
        }
    }
}


