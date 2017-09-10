package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.annotation.Test;
import ru.specialcourse.java.exception.NotAcceptableMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestAnnotationAnalyzer implements AnnotationAnalyzer {
    public Method[] analyze(Class<?> clazz) throws NotAcceptableMethod {
        Method[] methods = clazz.getMethods();
        List<Method> withTestMethods = new ArrayList<>();

        for (Method method: methods) {
            if (method.isAnnotationPresent(Test.class)) {
                checkMethod(method);
                withTestMethods.add(method);
            }
        }

        return (Method[]) withTestMethods.toArray();
    }
}
