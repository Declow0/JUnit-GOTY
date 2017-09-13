package ru.specialcourse.java.assertion;

import ru.specialcourse.java.exception.AssertionFailedError;

public class Assert {
    protected Assert() {

    }

    public static void assertTrue(boolean condition) {
        assertTrue(null, condition);
    }

    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }
    }

    public static void assertFalse(boolean condition) {
        assertFalse(null, condition);
    }

    public static void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }

    public static void assertEquals(byte expected, byte actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, byte expected, byte actual) {
        assertEquals(message, Byte.valueOf(expected), Byte.valueOf(actual));
    }

    public static void assertNotEquals(byte unexpected, byte actual) {
        assertNotEquals(null, unexpected, actual);
    }

    public static void assertNotEquals(String message, byte unexpected, byte actual) {
        assertNotEquals(message, Byte.valueOf(unexpected), Byte.valueOf(actual));
    }

    public static void assertEquals(int expected, int actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, int expected, int actual) {
        assertEquals(message, Integer.valueOf(expected), Integer.valueOf(actual));
    }

    public static void assertNotEquals(int unexpected, int actual) {
        assertNotEquals(null, unexpected, actual);
    }

    public static void assertNotEquals(String message, int unexpected, int actual) {
        assertNotEquals(message, Integer.valueOf(unexpected), Integer.valueOf(actual));
    }

    public static void assertEquals(short expected, short actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, short expected, short actual) {
        assertEquals(message, Short.valueOf(expected), Short.valueOf(actual));
    }

    public static void assertNotEquals(short unexpected, short actual) {
        assertNotEquals(null, unexpected, actual);
    }

    public static void assertNotEquals(String message, short unexpected, short actual) {
        assertNotEquals(message, Short.valueOf(unexpected), Short.valueOf(actual));
    }

    public static void assertEquals(long expected, long actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, long expected, long actual) {
        assertEquals(message, Long.valueOf(expected), Long.valueOf(actual));
    }

    public static void assertNotEquals(long unexpected, long actual) {
        assertNotEquals(null, unexpected, actual);
    }

    public static void assertNotEquals(String message, long unexpected, long actual) {
        assertNotEquals(message, Long.valueOf(unexpected), Long.valueOf(actual));
    }

    public static void assertEquals(float expected, float actual) {
        assertEquals(null, expected, actual, 0);
    }

    public static void assertEquals(String message, float expected, float actual) {
        assertEquals(message, expected, actual, 0);
    }

    public static void assertEquals(float expected, float actual, float delta) {
        assertEquals(null, expected, actual ,delta);
    }

    public static void assertEquals(String message, float expected, float actual, float delta) {
        if (floatIsDifferent(expected, actual, delta)) {
            failEquals(message, expected, actual);
        }
    }

    public static void assertNotEquals(float unexpected, float actual) {
        assertNotEquals(null, unexpected, actual, 0);
    }

    public static void assertNotEquals(String message, float unexpected, float actual) {
        assertNotEquals(message, unexpected, actual, 0);
    }

    public static void assertNotEquals(float unexpected, float actual, float delta) {
        assertNotEquals(null, unexpected, actual, delta);
    }

    public static void assertNotEquals(String message, float unexpected, float actual, float delta) {
        if (!floatIsDifferent(unexpected,actual, delta)) {
            failNotEquals(message, actual);
        }
    }

    public static void assertEquals(double expected, double actual) {
        assertEquals(null, expected, actual, 0);
    }

    public static void assertEquals(String message, double expected, double actual) {
        assertEquals(message, expected, actual, 0);
    }

    public static void assertEquals(double expected, double actual, double delta) {
        assertEquals(null, expected, actual ,delta);
    }

    public static void assertEquals(String message, double expected, double actual, double delta) {
        if (doubleIsDifferent(expected, actual, delta)) {
            failEquals(message, expected, actual);
        }
    }

    public static void assertNotEquals(double unexpected, double actual) {
        assertNotEquals(null, unexpected, actual, 0);
    }

    public static void assertNotEquals(String message, double unexpected, double actual) {
        assertNotEquals(message, unexpected, actual, 0);
    }

    public static void assertNotEquals(double unexpected, double actual, double delta) {
        assertNotEquals(null, unexpected, actual, delta);
    }

    public static void assertNotEquals(String message, double unexpected, double actual, double delta) {
        if (!doubleIsDifferent(unexpected, actual, delta)) {
            failNotEquals(message, actual);
        }
    }

    public static void assertEquals(char expected, char actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, char expected, char actual) {
        assertEquals(null, Character.valueOf(expected), Character.valueOf(actual));
    }

    public static void assertNotEquals(char unexpected, char actual) {
        assertNotEquals(null, unexpected, actual);
    }

    public static void assertNotEquals(String message, char unexpected, char actual) {
        assertNotEquals(message, Character.valueOf(unexpected), Character.valueOf(actual));
    }

    public static void assertEquals(Object expected, Object actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        if (isEquals(expected, actual)) {
            return;
        }

        failEquals(message, expected, actual);
    }

    public static void assertNotEquals(Object expected, Object actual) {
        assertNotEquals(null, expected, actual);
    }

    public static void assertNotEquals(String message, Object expected, Object actual) {
        if (isEquals(expected, actual)) {
            failNotEquals(message, actual);
        }
    }

    private static boolean doubleIsDifferent(double expected, double actual, double delta) {
        if (expected == actual) {
            return false;
        }
        if (Math.abs(expected - actual) <= delta) {
            return false;
        }

        return true;
    }

    private static boolean floatIsDifferent(float expected, float actual, float delta) {
        if (expected == actual) {
            return false;
        }
        if (Math.abs(expected - actual) <= delta) {
            return false;
        }

        return true;
    }

    private static boolean isEquals(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        }

        return expected.equals(actual);
    }

    private static void fail(String message) {
        throw new AssertionFailedError(message);
    }

    private static void failEquals(String message, Object expected, Object actual) {
        fail(formatExpected(message, expected, actual));
    }

    private static void failNotEquals(String message, Object actual) {
        fail(formatUnexpected(message, actual));
    }

    private static String formatExpected(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null && message.length() > 0) {
            formatted = message + " ";
        }
        return formatted + "expected: " + expected.getClass().getName() + " <" + expected +
                ">, but actual: " + actual.getClass() + " <" + actual + ">";
    }

    private static String formatUnexpected(String message, Object actual) {
        String formatted = "values should be different";
        if (message != null && message.length() > 0) {
            formatted = message + " ";
        }
        return formatted + ", but actual: " + actual.getClass().getName() + " <" + actual + ">";
    }
}
