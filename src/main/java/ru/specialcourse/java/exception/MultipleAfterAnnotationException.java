package ru.specialcourse.java.exception;

public class MultipleAfterAnnotationException extends MultipleAnnotationException {
    private static final String MESSAGE = "Multiple @After annotation doesn't allowed here";

    public MultipleAfterAnnotationException() {
        super(MESSAGE);
    }
}
