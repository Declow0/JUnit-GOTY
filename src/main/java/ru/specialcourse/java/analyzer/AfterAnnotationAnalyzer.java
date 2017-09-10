package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.annotation.After;
import ru.specialcourse.java.exception.MultipleAfterAnnotationException;
import ru.specialcourse.java.exception.NotAcceptableMethod;

import java.lang.reflect.Method;

public class AfterAnnotationAnalyzer implements AnnotationAnalyzer {
    public Method[] analyze(Class<?> clazz) throws MultipleAfterAnnotationException, NotAcceptableMethod {
        Method[] methods = clazz.getMethods();
        Method withAfterMethod = null;

        for (Method method : methods) {
            if (withAfterMethod != null) {
                throw new MultipleAfterAnnotationException();
            } else if (method.isAnnotationPresent(After.class)) {
                checkMethod(method);
                withAfterMethod = method;
            }
        }

        return new Method[]{withAfterMethod};
    }
}
