package ru.specialcourse.java.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    public static void execute(Class[] classes, int numberThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);
    }

    public static void execute(Class[] classes) {
        execute(classes, 1);
    }
}
