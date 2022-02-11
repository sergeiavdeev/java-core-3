package lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HomeworkApp {

    public static void main(String[] args) {

        String[] arrS = {"Aa", "Bb", "Cc"};
        Integer[] arrI = {10, 20, 30};

        arrayElementReplace(arrS, 0, arrS.length - 1);
        arrayElementReplace(arrI, 1, arrI.length - 1);
        System.out.println(Arrays.toString(arrS));
        System.out.println(Arrays.toString(arrI));

        List<String> stringList = toArrayList(arrS);
        List<Integer> integerList = toArrayList(arrI);
        System.out.println(stringList);
        System.out.println(integerList);

        Box<Orange> orangeBox = new Box<>();
        Box<Apple> appleBox = new Box<>();
        Box<Apple> appleBox1 = new Box<>();
        orangeBox.addAll(new Orange[] {new Orange(), new Orange()});
        appleBox.addAll(new Apple[]{new Apple(), new Apple(), new Apple()});
        appleBox1.addAll(new Apple[]{new Apple(), new Apple(), new Apple()});

        System.out.println("Вес коробок одинаковый: " + orangeBox.compare(appleBox));

        //orangeBox.empty(appleBox); не прокатывает, нельзя смешивать фрукты
        appleBox.empty(appleBox1);

        System.out.println("Яблоки в первой коробке: " + appleBox.getWeight());
        System.out.println("Яблоки во второй коробке: " + appleBox1.getWeight());

        //Box<Fruit> fruitBox = new Box<>(); не прокатит и фрукты смешать не получится

    }

    private static <T> void arrayElementReplace(T[] arr, int index1, int index2) {

        T tmpEl = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmpEl;
    }

    private static <T> ArrayList<T> toArrayList(T[] arr) {

        ArrayList<T> result = new ArrayList<>(arr.length);
        Collections.addAll(result, arr);
        return result;
    }
}
