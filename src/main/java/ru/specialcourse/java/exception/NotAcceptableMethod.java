package ru.specialcourse.java.exception;

import java.lang.reflect.Method;

public class NotAcceptableMethod extends Exception {
    private static final String MESSAGE = " method is not acceptable";

    public NotAcceptableMethod(Method method) {
        super(method.getName() + MESSAGE);
    }
}
