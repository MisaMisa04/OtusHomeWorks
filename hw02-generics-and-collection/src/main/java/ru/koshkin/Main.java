package ru.koshkin;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] arr = {"apple", "banana", "apple", "cherry", "date", "banana", "elderberry", "fig", "grape", "cherry", "honeydew", "kiwi", "lemon", "mango", "kiwi"};
        // 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа)
        System.out.printf("%s %s - 5th and 9th elements before swap\n", arr[5], arr[9]);
        swap(arr, 5, 9);
        System.out.printf("%s %s - 5th and 9th elements after swap\n\n", arr[5], arr[9]);
        // 2. Написать метод, который преобразует массив в ArrayList;
        List<String> list = makeArrayList(arr);
        System.out.printf("List is %s\n\n", list);
        // Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем)
        Set<String> uniqueWords = getUniqueWords(arr);
        System.out.printf("uniqueWords are %s\n\n", uniqueWords);
        // Посчитать, сколько раз встречается каждое слово
        Map<String, Integer> countSameWords = countSameWords(arr);
        System.out.printf("Count: %s\n", countSameWords);

    }

    private static <T> void swap(T[] arr, int i, int k) {
        T temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }

    private static <T> ArrayList<T> makeArrayList(T[] arr) {
        return new ArrayList<>(List.of(arr));
//        если надо развёрнуто
//        ArrayList<T> result = new ArrayList<>();
//        for (T elem : arr) {
//            result.add(elem);
//        }
//        return result;
    }

    private static <T> Set<T> getUniqueWords(T[] arr) {
        return new HashSet<>(List.of(arr));
//        если надо развёрнуто
//        Set<T> result = new HashSet<>();
//        for (T elem : arr) {
//            result.add(elem);
//        }
//        return result;
    }


    private static <T> Map<T, Integer> countSameWords(T[] arr) {
        Map<T, Integer> result = new HashMap<>();
        for (T elem : arr) {
            result.compute(elem, (k, v) -> v == null ? 1 : ++v);
        }
        return result;
    }


}
