package cuncurrencyAndMultiThreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShopperTryLock extends Thread {
    private int itemsToAdd = 0;
    private static int itemsOnNotepad = 0;
    private static Lock pencil = new ReentrantLock();

    public ShopperTryLock(String name) {
        this.setName(name);
    }

    public void run() {
        while (itemsOnNotepad <= 20) {
            if (itemsToAdd > 0 && pencil.tryLock()) {
                try {
                    itemsOnNotepad += itemsToAdd;
                    System.out.println(this.getName() + " added " + itemsToAdd + " item(s) to notepad.");
                    itemsToAdd = 0;
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    pencil.unlock();
                }
            } else {
                try {
                    Thread.sleep(100);
                    itemsToAdd++;
                    System.out.println(this.getName() + " found something to buy.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class TryLockDemoMain {
    public static void main(String[] args) throws InterruptedException{
        Thread barron = new ShopperTryLock("Barron");
        Thread olivia = new ShopperTryLock("Olivia");
        long start = System.currentTimeMillis();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + (float)(finish - start) / 1000 + " seconds");
    }
}
