package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.annotation.Test;
import ru.specialcourse.java.exception.NotAcceptableMethod;
import ru.specialcourse.java.exception.NotEmptyParametersException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestAnnotationAnalyzer implements AnnotationAnalyzer {
    @Override
    public Method[] analyze(Class<?> clazz) throws NotAcceptableMethod, NotEmptyParametersException {
        Method[] methods = clazz.getMethods();
        List<Method> withTestAnnotationMethods = new ArrayList<>();

        for (Method method: methods) {
            if (method.isAnnotationPresent(Test.class)) {
                checkMethod(method);
                withTestAnnotationMethods.add(method);
            }
        }

        return withTestAnnotationMethods.toArray(new Method[withTestAnnotationMethods.size()]);
    }

    public Class<? extends Throwable> getExpectedExceptionClass(Method withTestAnnotationMethod) {
        if (withTestAnnotationMethod.isAnnotationPresent(Test.class)) {
            return withTestAnnotationMethod.getAnnotation(Test.class).expected();
        }

        return null;
    }
}
