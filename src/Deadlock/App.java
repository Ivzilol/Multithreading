package Deadlock;

public class App {
    public static void main(String[] args) throws InterruptedException {

        Runner2 runner2 = new Runner2();

        Thread t1 = new Thread(() -> {
            try {
                runner2.firstThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                runner2.secondThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner2.finished();
    }
}
