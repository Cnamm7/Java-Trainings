package main;

import VolatileExercise.VolatileData;
import VolatileExercise.VolatileThread;

import static java.lang.Math.PI;
import static java.lang.Math.max;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        runVolatile();
        max(2, 7);
        double a = PI;
    }

    public static void runVolatile() throws InterruptedException {
        int noOfThreads = 2;
        VolatileData volatileData = new VolatileData();
        Thread[] threads = new Thread[noOfThreads];
        for (int i = 0; i < noOfThreads; ++i) {
            threads[i] = new VolatileThread(volatileData);
        }
        for (int i = 0; i < noOfThreads; ++i) {
            threads[i].start();
            ;
        }
        for (int i = 0; i < noOfThreads; ++i) {
            threads[i].join();
        }
    }


}


