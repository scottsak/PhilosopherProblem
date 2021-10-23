import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */


public class Philosopher implements Runnable {

    // Creating objects for forks around the philosopher
    private Object lfork;
    private Object rfork;

    public Philosopher(Object lfork, Object rfork) {
        this.lfork = lfork;
        this.rfork = rfork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
          Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                
                // thinking
                doAction(": Thinking");
                synchronized (lfork) {
                    doAction(": Picked up left fork");
                    synchronized (rfork) {
                        // eating
                        doAction(": Picked up right fork - eating"); 
                        
                        doAction(": Put down right fork");
                    }
                    
                    // Back to thinking
                    doAction(": Put down left fork. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}

