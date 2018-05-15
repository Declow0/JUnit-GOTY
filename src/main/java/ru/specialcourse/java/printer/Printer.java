package ru.specialcourse.java.printer;

public class Printer {
    private static long run = 0;
    private static long passed = 0;
    private static long failed = 0;
    private static long classes = 0;
    private static long invalidClasses = 0;

    synchronized public static void print(String string, long run, long passed, long failed, boolean isInvalid) {
        Printer.run += run;
        Printer.passed += passed;
        Printer.failed += failed;
        classes++;
        if (isInvalid) {
            invalidClasses++;
        }
        System.out.println(string);
    }

    public static void printReport() {
        StringBuilder report = new StringBuilder();
        report.append("Results:");
        report.append(System.lineSeparator());
        report.append(System.lineSeparator());
        report.append("Test classes: ");
        report.append(classes);
        report.append(", Invalid classes: ");
        report.append(invalidClasses);
        report.append(", Tests run: ");
        report.append(run);
        report.append(", Passed: ");
        report.append(passed);
        report.append(", Failed: ");
        report.append(failed);

        System.out.println(report.toString());
    }
}
