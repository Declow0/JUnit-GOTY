package ru.specialcourse.java;

import ru.specialcourse.java.annotation.After;
import ru.specialcourse.java.annotation.Before;
import ru.specialcourse.java.annotation.Test;
import ru.specialcourse.java.assertion.Assert;

public class SimpleTest {
    @Before
    public void setUp() {
        System.out.println("Настройка");
    }

    @Test(expected = NullPointerException.class)
    public void test1() {
        System.out.println("Тест1");

        Object a = null;
//        a.getClass();

        Assert.assertEquals(3, 3);
    }

    @Test
    public void test2() {
        System.out.println("Тест2");
        Assert.assertEquals(3, 3);
    }

    @After
    public void cleanUp() {
        System.out.println("Очистка\n");
    }
}
