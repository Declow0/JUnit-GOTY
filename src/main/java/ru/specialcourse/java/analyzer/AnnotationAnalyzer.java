package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.exception.MultipleAnnotationException;
import ru.specialcourse.java.exception.NotAcceptableMethod;
import ru.specialcourse.java.exception.NotEmptyParametersException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public interface AnnotationAnalyzer {
    Method[] analyze(Class<?> clazz) throws MultipleAnnotationException, NotAcceptableMethod,
            NotEmptyParametersException;

    default void checkMethod(Method method) throws NotAcceptableMethod, NotEmptyParametersException {
        int mod = method.getModifiers();
        if (Modifier.isAbstract(mod) || Modifier.isNative(mod) || !Modifier.isPublic(mod) || Modifier.isStatic(mod)
                || !(method.getReturnType().equals(void.class))) {
            throw new NotAcceptableMethod(method);
        }

        if (method.getParameterCount() != 0) {
            throw new NotEmptyParametersException(method);
        }
    }
}
