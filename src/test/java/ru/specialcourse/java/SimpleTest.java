package ru.specialcourse.java;

import ru.specialcourse.java.annotation.After;
import ru.specialcourse.java.annotation.Before;
import ru.specialcourse.java.annotation.Test;
import ru.specialcourse.java.assertion.Assertions;

public class SimpleTest {
    @Before
    public void setUp() {
        System.out.println("Настройка");

        Object a = null;
        a.getClass();
    }

    @Test(expected = NullPointerException.class)
    public void test1() {
        System.out.println("Тест1");

        Object a = null;
//        a.getClass();

        Assertions.assertEquals(3, 3);
    }

    @Test
    public void test2() {
        System.out.println("Тест2");
        Assertions.assertEquals(3, 3);
    }

    @After
    public void cleanUp() {
        System.out.println("Очистка\n");
    }
}
