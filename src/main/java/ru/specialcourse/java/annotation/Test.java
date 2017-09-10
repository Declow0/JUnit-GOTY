package ru.specialcourse.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Test {
    class None extends Throwable {
        private None() {
        }
    }

    Class<? extends Throwable> expected() default None.class;
}
