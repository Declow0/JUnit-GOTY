package ru.specialcourse.java;

import ru.specialcourse.java.executor.Executor;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Class> testableClasses = new ArrayList<>();

        if (args.length != 0) {
            Integer threadsNumber = 1;
            boolean isParsed;
            try {
                threadsNumber = Integer.parseInt(args[0]);
                isParsed = true;
            } catch (NumberFormatException e) {
                isParsed = false;
            }

            for (int i = isParsed ? 1 : 0; i < args.length; i++) {
                try {
                    Class testableClass = Class.forName(args[i]);
                    testableClasses.add(testableClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(System.err);
                }
            }

            Executor.execute(testableClasses, threadsNumber);
        }
    }
}
