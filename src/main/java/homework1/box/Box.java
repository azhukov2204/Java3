package homework1.box;

import homework1.fruits.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Box<T extends Fruit> {
    private final ArrayList<T> fruits;


    public Box() {
        fruits = new ArrayList<>(Collections.emptyList());
    }

    public Box(List<T> fruits) {
        this.fruits = new ArrayList<>(fruits);
    }

    public Box(T[] fruits) {
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    public float getWeight() {
        float boxWeight=0;
        for (T fruit : fruits) {
            boxWeight+=fruit.getWeightOfFruit();
        }
        return boxWeight;
    }

    //сравнивать по весу можно коробки любых типов:
    public boolean compare(Box<?> box) {
        return (box.getWeight() == this.getWeight());
    }

    public void addFruitToBox(T fruit) {
        fruits.add(fruit);
    }

    public void removeFruitFromBox(T fruit) {
        fruits.remove(fruit);
    }

    //пересыпать можно лишь в коробку того же типа:
    public void pourOver(Box<T> pourBox) {
        for (T fruit : fruits) {
           pourBox.addFruitToBox(fruit);
        }
        fruits.clear();
    }

    public void printInfo() {
        System.out.printf("Количество фруктов в коробке: %d%n", fruits.size());
        System.out.printf("Вес коробки %f%n", getWeight());
    }



}
