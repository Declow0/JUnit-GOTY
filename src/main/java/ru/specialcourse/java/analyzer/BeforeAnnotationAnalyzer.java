package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.annotation.Before;
import ru.specialcourse.java.exception.MultipleBeforeAnnotationException;

import java.lang.reflect.Method;

public class BeforeAnnotationAnalyzer implements AnnotationAnalyzer {
    private static final byte MAX_BEFORE_ANNOTATIONS = 1;

    public void analyze(Class<?> cl) throws MultipleBeforeAnnotationException {
        int beforeAnnotations = 0;
        Method[] methods = cl.getMethods();

        for (Method method: methods) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeAnnotations++;

                if (beforeAnnotations > MAX_BEFORE_ANNOTATIONS) {
                    throw new MultipleBeforeAnnotationException();
                }
            }
        }
    }
}
