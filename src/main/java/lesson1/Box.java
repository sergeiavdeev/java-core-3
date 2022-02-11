package lesson1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box <T extends Fruit & Boxable> {

    private final List<T> fruitList;

    public Box() {
        fruitList = new ArrayList<>();
    }

    public void add(T fruit) {

        fruitList.add(fruit);
    }

    public void addAll(T[] arr) {
        Collections.addAll(fruitList, arr);
    }

    public float getWeight() {

        float result = 0.0f;

        for (T fruit : fruitList) {
            result += fruit.getWeight();
        }

        return result;
    }

    public boolean compare(Box<?> box) {

        return getWeight() == box.getWeight();
    }

    public void empty(Box<T> destination) {

        for (T fruit : fruitList) {

            destination.add(fruit);
        }
        fruitList.clear();
    }
}
