package ru.specialcourse.java.task;

public class TestTask implements Runnable {
    private Class clazz;

    public TestTask(Class clazz) {
        this.clazz = clazz;
    }

    private TestTask() {
    }

    public void run() {
        System.out.println(clazz.getName());
    }
}
