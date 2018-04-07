package ru.specialcourse.java;

import ru.specialcourse.java.annotation.Before;
import ru.specialcourse.java.annotation.Test;

public class SimpleTest2 {
    @Before
    public void setUp() {
        System.out.println("Настройка");
    }

    @Before
    public void setUp2() {
        System.out.println("Настройка");
    }

    @Test
    public static void kek() {

    }

    @Test
    public void kek2() {

    }
}
