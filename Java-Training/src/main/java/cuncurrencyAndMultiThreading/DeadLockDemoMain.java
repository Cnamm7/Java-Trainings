package cuncurrencyAndMultiThreading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {
    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500_000;
    private Random rps = new Random();

    public Philosopher(String name, Lock firstChopestick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopestick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        int sushiEaten = 0;
        while (sushiCount > 0) {

            firstChopstick.lock();
            //put down chopstick methode
            if (!secondChopstick.tryLock()) {
                System.out.println(this.getName() + " released their first chopstick.");
                firstChopstick.unlock();
                try {
                    Thread.sleep(rps.nextInt(3));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    if (sushiCount > 0) {
                        sushiCount--;
                        sushiEaten++;
                        System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    secondChopstick.unlock();
                    firstChopstick.unlock();
                }
            }
        }
        System.out.println(this.getName() + " took " + sushiEaten);
    }
}

public class DeadLockDemoMain {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher("Barron", chopstickA, chopstickB).start();
        new Philosopher("Olivia", chopstickB, chopstickC).start();
        new Philosopher("Steve", chopstickC, chopstickA).start();

        /*
        one way to solve it without implementing the put down chopstick methode is to try
        run them in order, like what happened here at steve
         */
//        new Philosopher("Barron", chopstickA, chopstickB).start();
//        new Philosopher("Olivia", chopstickB, chopstickC).start();
//        new Philosopher("Steve", chopstickA, chopstickC).start();
    }
}
