package lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final Semaphore traffic;

    public Tunnel(int trafficWidth) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        traffic = new Semaphore(trafficWidth, true);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                traffic.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                traffic.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
