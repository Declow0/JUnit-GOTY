package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.annotation.Before;
import ru.specialcourse.java.exception.MultipleBeforeAnnotationException;
import ru.specialcourse.java.exception.NotAcceptableMethod;

import java.lang.reflect.Method;

public class BeforeAnnotationAnalyzer implements AnnotationAnalyzer {
    public Method[] analyze(Class<?> clazz) throws MultipleBeforeAnnotationException, NotAcceptableMethod {
        Method[] methods = clazz.getMethods();
        Method withBeforeAnnotationMethod = null;

        for (Method method: methods) {
            if (method.isAnnotationPresent(Before.class)) {
                if (withBeforeAnnotationMethod != null) {
                    throw new MultipleBeforeAnnotationException();
                }

                checkMethod(method);
                withBeforeAnnotationMethod = method;
            }
        }

        return new Method[]{withBeforeAnnotationMethod};
    }
}
