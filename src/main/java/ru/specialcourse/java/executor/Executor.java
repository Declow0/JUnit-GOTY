package ru.specialcourse.java.executor;

import ru.specialcourse.java.task.TestTask;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    public static void execute(Collection<Class> classes, int numberThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);

        for (Class clazz : classes) {
            TestTask testTask = new TestTask(clazz);
            executorService.submit(testTask);
        }

        executorService.shutdown();
    }

    public static void execute(Collection<Class> classes) {
        execute(classes, 1);
    }
}
