package ru.specialcourse.java.exception;

import java.lang.reflect.Method;

public class NotAcceptableMethod extends Exception {
    private static final String START_MESSAGE = "Method";
    private static final String END_MESSAGE = "is not acceptable";

    public NotAcceptableMethod(Method method) {
        super(START_MESSAGE + ' ' + method.getName() + ' ' + END_MESSAGE);
    }
}
