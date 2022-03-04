package lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {
    private final ArrayList<Stage> stages;
    private CountDownLatch prepareCheck;
    private CountDownLatch startCheck;
    private CyclicBarrier finishCheck;
    private int finishCount;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        finishCount = 0;
    }

    public void init(int carsCount) {

        prepareCheck = new CountDownLatch(carsCount);
        startCheck = new CountDownLatch(3); // Типа на старт, внимание, марш! )
        finishCheck = new CyclicBarrier(carsCount + 1);
    }

    public CountDownLatch getPrepareCheck() {
        return prepareCheck;
    }

    public CountDownLatch getStartCheck() {
        return startCheck;
    }

    public CyclicBarrier getFinishCheck() {
        return finishCheck;
    }

    public synchronized void setFinishCount(String name) {
        if (finishCount == 0) { //Победитель
            System.out.println(name + " победил!");
        } else {
            System.out.printf("%s пришел %d\n", name, finishCount + 1);
        }
        finishCount++;
    }
}
