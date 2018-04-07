package ru.specialcourse.java.exception;

public class NotThrownExpectedException extends Exception {
    private static final String MESSAGE = " was expected here but it not thrown";

    public NotThrownExpectedException(Class<? extends Throwable> e) {
        super(e.getName() + MESSAGE);
    }
}
