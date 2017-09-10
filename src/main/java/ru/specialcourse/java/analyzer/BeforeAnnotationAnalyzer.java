package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.annotation.Before;
import ru.specialcourse.java.exception.MultipleBeforeAnnotationException;
import ru.specialcourse.java.exception.NotAcceptableMethod;

import java.lang.reflect.Method;

public class BeforeAnnotationAnalyzer implements AnnotationAnalyzer {
    public Method[] analyze(Class<?> clazz) throws MultipleBeforeAnnotationException, NotAcceptableMethod {
        Method[] methods = clazz.getMethods();
        Method withBeforeMethod = null;

        for (Method method: methods) {
            if (withBeforeMethod != null) {
                throw new MultipleBeforeAnnotationException();
            }

            if (method.isAnnotationPresent(Before.class)) {
                checkMethod(method);
                withBeforeMethod = method;
            }
        }

        return new Method[]{withBeforeMethod};
    }
}
