package main;

import VolatileExercise.VolatileData;
import VolatileExercise.VolatileThread;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        runVolatile();
        String hello = "hello";
        hello = hello.toUpperCase();
        System.out.println(hello);
        Character[] s = {'s', 'i', 'n', 'a'};
        List<Character> si = Arrays.stream(s).toList();
        si.forEach(System.out::println);
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

    public static void printOutDuplicateCharacters(String input) {
        Map<Character, Integer> numberOfCharacter = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            if (!numberOfCharacter.containsKey(input.charAt(i))) {
                numberOfCharacter.put(input.charAt(i), 1);
            } else {
                numberOfCharacter.put(input.charAt(i), numberOfCharacter.get(input.charAt(i)) + 1);
            }
        }
        for (char c : numberOfCharacter.keySet()) {
            if (numberOfCharacter.get(c) > 1) {
                System.out.println(c + ":" + numberOfCharacter.get(c));
            }
        }
    }
}


