package homework1;

import homework1.box.Box;
import homework1.fruits.Apple;
import homework1.fruits.Orange;
import java.util.ArrayList;
import java.util.Arrays;

public class MainHomeWork1 {
    public static void main(String[] args) {
        System.out.println("Курс Java3. Домашняя работа к 1-му уроку");

        System.out.println("---------------------------------------------");
        System.out.println("Задание 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа)");
        System.out.println("---------------------------------------------");

        Integer[] intArray = {9, 5, 2, 0, 6, 7, 6, 12, 548, 1, 6};
        String[] stringArray = {"str1", "str2", "str3"};

        System.out.println("Исходные массивы:");
        printArray(intArray);
        printArray(stringArray);

        System.out.println("Поменяем элементы и индексами 1 и 2 местами");
        exchangeAnyArray(intArray, 1, 2);
        exchangeAnyArray(stringArray, 1, 2);

        System.out.println("Измененные массивы:");

        printArray(intArray);
        printArray(stringArray);

        System.out.println("---------------------------------------------");
        System.out.println("Задание 2. Написать метод, который преобразует массив в ArrayList");
        System.out.println("---------------------------------------------");

        ArrayList<?> intArrayList = arrayToArrayList(intArray);
        ArrayList<?> stringArrayList = arrayToArrayList(stringArray);

        System.out.println(intArrayList);
        System.out.println(stringArrayList);

        System.out.println("---------------------------------------------");
        System.out.println("Задание 3. Фрукты");
        System.out.println("---------------------------------------------");

        System.out.println("Создаем коробку с яблоками и сразу заполняем ее:");
        Apple[] apples = {new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple()};
        Box<Apple> appleBox = new Box<>(apples);
        appleBox.printInfo();

        System.out.println("Создаем коробку с апельсинами и сразу заполняем ее:");
        Orange[] oranges = {new Orange(), new Orange(), new Orange(), new Orange()};
        Box<Orange> orangeBox = new Box<>(oranges);
        orangeBox.printInfo();
        System.out.println();

        System.out.println("Равны ли по весу коробки appleBox и orangeBox? " + appleBox.compare(orangeBox));
        System.out.println();

        System.out.println("Добавим еще апельсин в коробку:");
        orangeBox.addFruitToBox(new Orange());
        orangeBox.printInfo();
        System.out.println();

        System.out.println("Сравним еще раз коробки appleBox и orangeBox. Результат сравнения: " + appleBox.compare(orangeBox));
        System.out.println();

        System.out.println("Создадим еще коробку с яблоками и сразу добавим туда одно яблоко: ");
        Box<Apple> appleBox2 = new Box<>(new Apple[]{new Apple()});
        appleBox2.printInfo();

        System.out.println("Создадим еще одну пустую коробку для апельсинов: ");
        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.printInfo();
        System.out.println();

        System.out.println("Пересыпим все фрукты из старых коробок в новые:");
        appleBox.pourOver(appleBox2);
        orangeBox.pourOver(orangeBox2);

        System.out.println("Информация о старой коробке с яблоками");
        appleBox.printInfo();
        System.out.println("Информация о старой коробке с апельсинами");
        orangeBox.printInfo();

        System.out.println("Информация о новой коробке с яблоками");
        appleBox2.printInfo();
        System.out.println("Информация о новой коробке с апельсинами");
        orangeBox2.printInfo();


    }


    public static <T> void exchangeAnyArray(T[] array, int i, int j) {
        T temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> void printArray(T[] array) {
        for (T t : array) {
            System.out.print(t + " ");
        }
        System.out.println();
    }


    public static <T> ArrayList<T> arrayToArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

}
