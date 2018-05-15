package ru.specialcourse.java.exception;

public class MultipleBeforeAnnotationException extends MultipleAnnotationException {
    private static final String MESSAGE = "Multiple @Before annotation doesn't allowed in a test class";

    public MultipleBeforeAnnotationException() {
        super(MESSAGE);
    }
}
