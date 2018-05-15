package ru.specialcourse.java.printer;

public class Printer {
    private static long run = 0;
    private static long passed = 0;
    private static long failed = 0;

    synchronized public static void print(String string, long run, long passed, long failed) {
        Printer.run += run;
        Printer.passed += passed;
        Printer.failed += failed;
        System.out.println(string);
    }

    public static void printReport() {
        StringBuilder report = new StringBuilder();
        report.append("Results:");
        report.append(System.lineSeparator());
        report.append(System.lineSeparator());
        report.append("Tests run: ");
        report.append(run);
        report.append(", Passed: ");
        report.append(passed);
        report.append(", Failed: ");
        report.append(failed);

        System.out.println(report.toString());
    }
}
