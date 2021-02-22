package homework5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final Semaphore tunnelSemaphore;

    public Tunnel(int length, int tunnelThroughput) {
        this.length = length;
        this.description = "Тоннель " + length + " метров";
        this.tunnelSemaphore = new Semaphore(tunnelThroughput);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                tunnelSemaphore.acquire(); //ожидаем очереди в тоннель
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                tunnelSemaphore.release(); //освобождаем место в тоннеле
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
