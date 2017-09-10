package ru.specialcourse.java.exception;

public class MultipleBeforeAnnotationException extends MultipleAnnotationException {
    private static final String MESSAGE = "Multiple @Before annotation doesn't allowed here.";

    public MultipleBeforeAnnotationException() {
        super(MESSAGE);
    }
}
