package ru.specialcourse.java;

import ru.specialcourse.java.executor.Executor;
import ru.specialcourse.java.pool.ClassPool;

public class Runner {
    public static void main(String[] args) {
        // TODO: вычленить классы из args
        ClassPool classPool = new ClassPool(args);
        Executor.execute(classPool);
    }
}
