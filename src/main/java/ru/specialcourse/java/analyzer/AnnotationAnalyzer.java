package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.exception.MultipleAnnotationException;
import ru.specialcourse.java.exception.NotAcceptableMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public interface AnnotationAnalyzer {
    Method[] analyze(Class<?> clazz) throws MultipleAnnotationException, NotAcceptableMethod;

    default void checkMethod(Method method) throws NotAcceptableMethod {
        int mod = method.getModifiers();
        if (Modifier.isAbstract(mod) || Modifier.isNative(mod) || !Modifier.isPublic(mod) || Modifier.isStatic(mod)) {
            throw new NotAcceptableMethod(method);
        }
    }
}
