package ru.specialcourse.java.task;

import ru.specialcourse.java.analyzer.AfterAnnotationAnalyzer;
import ru.specialcourse.java.analyzer.BeforeAnnotationAnalyzer;
import ru.specialcourse.java.analyzer.TestAnnotationAnalyzer;
import ru.specialcourse.java.annotation.Test;
import ru.specialcourse.java.exception.AssertionFailedError;
import ru.specialcourse.java.exception.NotThrownExpectedException;
import ru.specialcourse.java.printer.Printer;

import java.lang.reflect.Method;

public class TestTask implements Runnable {
    private Class clazz;
    private BeforeAnnotationAnalyzer beforeAnnotationAnalyzer;
    private TestAnnotationAnalyzer testAnnotationAnalyzer;
    private AfterAnnotationAnalyzer afterAnnotationAnalyzer;

    public TestTask(Class clazz) {
        this.clazz = clazz;
        beforeAnnotationAnalyzer = new BeforeAnnotationAnalyzer();
        testAnnotationAnalyzer = new TestAnnotationAnalyzer();
        afterAnnotationAnalyzer = new AfterAnnotationAnalyzer();
    }

    private TestTask() {
    }

    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        long pass = 0, failed = 0;

        try {
            Method beforeMethod = beforeAnnotationAnalyzer.analyze(clazz)[0];
            Method[] testMethods = testAnnotationAnalyzer.analyze(clazz);
            Method afterMethod = afterAnnotationAnalyzer.analyze(clazz)[0];

            for (Method testMethod : testMethods) {
                Object objectInstance = clazz.newInstance();

                Class<? extends Throwable> expectedException =
                        testAnnotationAnalyzer.getExpectedExceptionClass(testMethod);

                try {
                    if (beforeMethod != null) {
                        beforeMethod.invoke(objectInstance);
                    }

                    try {
                        testMethod.invoke(objectInstance);

                        if (expectedException != Test.None.class) {
                            throw new NotThrownExpectedException(expectedException);
                        }

                        pass++;
                    } catch (NotThrownExpectedException e) {
                        failed++;

                        // нет ожидаемого исключения
                        e.printStackTrace(System.err);
                    } catch (Throwable e) {
                        failed++;

                        // Обработка excepted exception и assert exception
                        if (!e.getCause().getClass().isAssignableFrom(expectedException)) {
                            if (e.getCause().getClass().isAssignableFrom(AssertionFailedError.class)) {
                                // исключения при проверке
                                e.getCause().printStackTrace(System.err);
                            } else {
                                // исключения иные
                                e.getCause().printStackTrace(System.err);
                            }
                        }
                    }

                    if (afterMethod != null) {
                        afterMethod.invoke(objectInstance);
                    }
                } catch (Exception e) {
                    // исключения при исполнении методов Before & After
                    e.getCause().printStackTrace(System.err);
                }
            }
        } catch (Exception e) {
            // исключения неправильного считывания методов или создания экземпляра
            e.printStackTrace(System.err);
        }

        Printer.print(stringBuilder.toString());
        // Counter.count(pass, failed);
        System.out.println(pass + " " + failed);
    }
}
