package homework7;

import homework7.classesfortests.ForTest;
import homework7.mytesterlib.Tester;

public class MainHW7 {
    public static void main(String[] args) {
        System.out.println("Домашняя работа №7. Reflection API и Аннотации");

        //Проверим все 3 способа получения класса:
        ForTest forTest = new ForTest();
        Tester.start(forTest.getClass()); //из объекта
        Tester.start(ForTest.class); //из класса
        try {
            Tester.start(Class.forName("homework7.classesfortests.ForTest")); //из класса по имени
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
