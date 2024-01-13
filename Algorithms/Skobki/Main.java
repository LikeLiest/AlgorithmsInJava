package AlgorithmsInJava.Algorithms.Skobki;

import java.util.*;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        out.println(isValidBrackets("()()()[][][]{}{}{}"));
        out.println(isValidBrackets("((())]"));
    }

    private static boolean isValidBrackets (String bracketString) {
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(']','[');
        brackets.put(')','(');
        brackets.put('}','{');

//        Стек
        Deque<Character> stack = new LinkedList<>();

        for(Character c : bracketString.toCharArray()) {
            if (brackets.containsValue(c)) {
                stack.push(c);
            } else if (brackets.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                    return false;
                }
            }
        }

//        После завершения цикла проверяется, пусти ли стек. Если стек пуст, это означает, что все скобки закрыты и вернется TRUE.
//        Иначе false - что укажет на несбалансированность скобок

        return stack.isEmpty();
    }


//    Класс Deque (double-ended queue) в Java представляет собой интерфейс, который расширяет функциональность интерфейса Queue.
//    Deque поддерживает добавление и удаление элементов с обоих концов очереди.
//    Он предоставляет методы для вставки,
//    удаления и получения элементов как с начала, так и с конца очереди.

}
