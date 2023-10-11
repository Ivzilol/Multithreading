package MultipleLocksSinchronizedCodeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Worker {

    private final Random random = new Random();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private final List<Integer> list1 = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();

    public void stageOne() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(1);
        }
        list1.add(random.nextInt(100));
    }

    public void stageTwo() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(1);
        }
        list2.add(random.nextInt(100));
    }

    public void process() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }
    public void main() throws InterruptedException {
        System.out.println("Starting.....");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            try {
                process();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });



        Thread t2 = new Thread(() -> {
            try {
                process();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time take: " + (end - start));
        System.out.println("List1: " + list1.size() + " " + "List2: " + list2.size());
    }
}
