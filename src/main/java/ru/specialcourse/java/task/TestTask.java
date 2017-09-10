package ru.specialcourse.java.task;

import ru.specialcourse.java.analyzer.AfterAnnotationAnalyzer;
import ru.specialcourse.java.analyzer.AnnotationAnalyzer;
import ru.specialcourse.java.analyzer.BeforeAnnotationAnalyzer;
import ru.specialcourse.java.analyzer.TestAnnotationAnalyzer;

import java.lang.reflect.Method;

public class TestTask implements Runnable {
    private Class clazz;
    private AnnotationAnalyzer beforeAnnotationAnalyzer;
    private AnnotationAnalyzer testAnnotationAnalyzer;
    private AnnotationAnalyzer afterAnnotationAnalyzer;

    public TestTask(Class clazz) {
        this.clazz = clazz;
        beforeAnnotationAnalyzer = new BeforeAnnotationAnalyzer();
        testAnnotationAnalyzer = new TestAnnotationAnalyzer();
        afterAnnotationAnalyzer = new AfterAnnotationAnalyzer();
    }

    private TestTask() {
    }

    public void run() {
        try {
            Method beforeMethod = beforeAnnotationAnalyzer.analyze(clazz)[0];
            Method[] testMethods = testAnnotationAnalyzer.analyze(clazz);
            Method afterMethod = afterAnnotationAnalyzer.analyze(clazz)[0];

            Object objectInstance = clazz.newInstance();

            for (Method testMethod : testMethods) {
                beforeMethod.invoke(objectInstance);
                testMethod.invoke(objectInstance);
                afterMethod.invoke(objectInstance);
            }
        } catch (Exception e) {
            // TODO: обработать исключения неправильного считывания методов
        }
    }
}
