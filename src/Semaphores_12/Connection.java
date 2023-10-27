package Semaphores_12;

import java.util.concurrent.Semaphore;

public class Connection {

    private static final Connection instance = new Connection();

    private final Semaphore sem = new Semaphore(10, true);

    private int connections = 0;

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() throws InterruptedException {
        sem.acquire();
        try {
            doConnect();
        } finally {
            sem.release();
        }
    }

    public void doConnect() throws InterruptedException {
        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }
        Thread.sleep(2000);
        synchronized (this) {
            connections--;
        }
    }
}
