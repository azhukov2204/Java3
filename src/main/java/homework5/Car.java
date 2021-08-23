package homework5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private static volatile CyclicBarrier startBarrier; //Чтоб все потоки начали гонку одновременно. Статический, т.к. барьер один для всех членов класса
    private static volatile boolean hasWinner;

    private final Race race;
    private final int speed;
    private final String name;
    private final CountDownLatch preparingCDL;
    private final CountDownLatch finishingCDL;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch preparingCDL, CountDownLatch finishingCDL) {
        this.race = race;
        this.speed = speed;
        this.preparingCDL = preparingCDL;
        this.finishingCDL = finishingCDL;
        CARS_COUNT++;
        this.name = "Участник №" + CARS_COUNT;
        Car.startBarrier = new CyclicBarrier(CARS_COUNT); //инициализация CyclicBarrier. При добавлении новых машин CyclicBarrier будет переинициализирован
        Car.hasWinner = false;
    }

    @Override
    public void run() {
        preparing(); //подготовка к заезду
        try {
            Car.startBarrier.await(); //начинаем гонку одновременно по готовности всех
            racing(); //поехали (все одновременно)
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


    private void preparing() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(1000 + (int) (Math.random() * 3000));
            System.out.println(this.name + " готов");
            preparingCDL.countDown(); //после подготовки уменьшаем счетчик
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void racing() {
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (!Car.hasWinner) {
            Car.hasWinner = true;
            System.out.println(this.name + " Победитель!!!");
        }
        finishingCDL.countDown(); //по окончанию заезда уменьшаем счетчик
    }

}