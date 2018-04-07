package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.annotation.After;
import ru.specialcourse.java.exception.MultipleAfterAnnotationException;
import ru.specialcourse.java.exception.NotAcceptableMethod;
import ru.specialcourse.java.exception.NotEmptyParametersException;

import java.lang.reflect.Method;

public class AfterAnnotationAnalyzer implements AnnotationAnalyzer {
    @Override
    public Method[] analyze(Class<?> clazz) throws MultipleAfterAnnotationException, NotAcceptableMethod,
            NotEmptyParametersException {
        Method[] methods = clazz.getMethods();
        Method withAfterAnnotationMethod = null;

        for (Method method : methods) {
            if (method.isAnnotationPresent(After.class)) {
                if (withAfterAnnotationMethod != null) {
                    throw new MultipleAfterAnnotationException();
                }

                checkMethod(method);
                withAfterAnnotationMethod = method;
            }
        }

        return new Method[]{withAfterAnnotationMethod};
    }
}
