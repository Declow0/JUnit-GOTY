package ru.specialcourse.java;

import ru.specialcourse.java.executor.Executor;

public class Runner {
    public static void main(String[] args) {
        // TODO: вычленить классы из args
        Class[] classes = new Class[0];
        Executor.execute(classes);
    }
}
