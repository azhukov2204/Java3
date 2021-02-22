package homework5;

import java.util.concurrent.CountDownLatch;

public class MainHomeWork5 {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        CountDownLatch preparingCDL = new CountDownLatch(CARS_COUNT);
        CountDownLatch finishingCDL = new CountDownLatch(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(300, 2), new Road(400));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, (int) (Math.random() * 10) + 20, preparingCDL, finishingCDL);
        }
        for (Car car : cars) {
            new Thread(car).start();
        }

        try {
            //после подготовки всех авто выведем сообщение о начале гонки:
            preparingCDL.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            //по окончанию заезда всех авто выведем сообщение об окончании гонки:
            finishingCDL.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
