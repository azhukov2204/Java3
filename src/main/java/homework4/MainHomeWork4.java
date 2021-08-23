package homework4;

public class MainHomeWork4 {

    final Object lockMonitor = new Object();
    char nextPrintingChar = 'A';

    public static void main(String[] args) {
        System.out.println("Домашняя работа №4. Многопоточность");

        MainHomeWork4 testSyncMultiThreads = new MainHomeWork4();
        new Thread(() -> testSyncMultiThreads.printLetter('C')).start();
        new Thread(() -> testSyncMultiThreads.printLetter('B')).start();
        new Thread(() -> testSyncMultiThreads.printLetter('A')).start();
    }


    public void printLetter(char letter) {
        System.out.println("Запуск потока печати буквы " + letter);
        try {
            Thread.sleep(500);
            synchronized (lockMonitor) {
                for (int i = 0; i < 5; i++) {
                    //ждем своей очереди:
                    while (letter != nextPrintingChar) {
                        lockMonitor.wait();
                    }
                    System.out.print(letter);
                    Thread.sleep(1000);

                    //переключаем на следующую букву:
                    switch (nextPrintingChar) {
                        case 'A' -> nextPrintingChar = 'B';
                        case 'B' -> nextPrintingChar = 'C';
                        default -> nextPrintingChar = 'A';
                    }
                    lockMonitor.notifyAll(); //выводим потоки из режима ожидания
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
