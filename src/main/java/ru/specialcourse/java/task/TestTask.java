package ru.specialcourse.java.task;

import ru.specialcourse.java.analyzer.AfterAnnotationAnalyzer;
import ru.specialcourse.java.analyzer.BeforeAnnotationAnalyzer;
import ru.specialcourse.java.analyzer.TestAnnotationAnalyzer;
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

    public void run() {
        StringBuilder stringBuilder = new StringBuilder(); // TODO: StringBuffer?

        try {
            Method beforeMethod = beforeAnnotationAnalyzer.analyze(clazz)[0];
            Method[] testMethods = testAnnotationAnalyzer.analyze(clazz);
            Method afterMethod = afterAnnotationAnalyzer.analyze(clazz)[0];

            Object objectInstance = clazz.newInstance();

            for (Method testMethod : testMethods) {
                Class exceptedException = testAnnotationAnalyzer.getExpectedExceptionClass(testMethod);

                try {
                    beforeMethod.invoke(objectInstance);

                    try {
                        testMethod.invoke(objectInstance);
                    } catch (Throwable e) {
                        // TODO: Обработка excepted exception и assert exception
                        stringBuilder.append("Исключение");
                    }

                    afterMethod.invoke(objectInstance);
                } catch (Exception e) {
                    // TODO: обработка исключений при исполнении методов
                    stringBuilder.append("Исключение");
                }
            }
        } catch (Exception e) {
            // TODO: обработать исключения неправильного считывания методов или создания экземпляра
            stringBuilder.append("Исключение");
        }

        Printer.print(stringBuilder.toString());
    }
}
