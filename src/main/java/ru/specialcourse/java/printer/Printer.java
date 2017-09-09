package ru.specialcourse.java.printer;

public class Printer {
    synchronized public static void print(String string) {
        System.out.println(string);
    }
}
