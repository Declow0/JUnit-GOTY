package ru.specialcourse.java.exception;

import java.lang.reflect.Method;

public class NotEmptyParametersException extends Exception {
    private static final String MESSAGE = "Method's parameters are not allowed here: ";

    public NotEmptyParametersException(Method method) {
        super(MESSAGE + method.getDeclaringClass().getName() + "::" + method.getName());
    }
}
