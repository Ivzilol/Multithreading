package Semaphores;

public class Connection {

    private static final Connection instance = new Connection();

    private int connections = 0;

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() throws InterruptedException {
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
