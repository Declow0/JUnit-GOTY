package ru.specialcourse.java;

import ru.specialcourse.java.annotation.After;
import ru.specialcourse.java.annotation.Before;
import ru.specialcourse.java.annotation.Test;

public class SimpleTest2 {
    @Before
    public void setUp() {
        System.out.println("Настройка в 2");
    }

    @Test
    public static void kek() {
        System.out.println("Тест №1 в 2");
    }

    @Test
    public void kek2() {
        System.out.println("Тест №2 в 2");
    }

    @After
    public void cleanUp() {
        System.out.println("Очистка в 2");
        Object a = null;
        a.getClass();
    }
}
