package ru.specialcourse.java.executor;

import ru.specialcourse.java.printer.Printer;
import ru.specialcourse.java.task.TestTask;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    public static void execute(Collection<Class> classes, int numberThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);
        CountDownLatch latch = new CountDownLatch(classes.size());

        for (Class clazz : classes) {
            TestTask testTask = new TestTask(clazz, latch);
            executorService.submit(testTask);
        }

        executorService.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Printer.printReport();
    }

    public static void execute(Collection<Class> classes) {
        execute(classes, 1);
    }
}
