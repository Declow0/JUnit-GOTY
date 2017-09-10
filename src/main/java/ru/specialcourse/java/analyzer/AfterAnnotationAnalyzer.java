package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.annotation.After;
import ru.specialcourse.java.exception.MultipleAfterAnnotationException;

import java.lang.reflect.Method;

public class AfterAnnotationAnalyzer implements AnnotationAnalyzer {
    public void analyze(Class<?> clazz) throws MultipleAfterAnnotationException {
        Method[] methods = clazz.getMethods();
        boolean afterAnnotationIsPresent = false;

        for (Method method : methods) {
            if (afterAnnotationIsPresent) {
                throw new MultipleAfterAnnotationException();
            }
            if (method.isAnnotationPresent(After.class)) {
                afterAnnotationIsPresent = true;
            }
        }
    }
}
