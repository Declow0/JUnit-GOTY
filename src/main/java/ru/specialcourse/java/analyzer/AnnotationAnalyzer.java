package ru.specialcourse.java.analyzer;

import ru.specialcourse.java.exception.MultipleAnnotationException;

public interface AnnotationAnalyzer {
    public void analyze(Class<?> className) throws MultipleAnnotationException;
}
