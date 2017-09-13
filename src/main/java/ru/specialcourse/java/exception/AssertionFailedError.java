package ru.specialcourse.java.exception;

public class AssertionFailedError extends AssertionError {

    public AssertionFailedError(String message) {
        super(message == null ? "" : message);
    }
}
