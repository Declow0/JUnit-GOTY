package ru.specialcourse.java.task;

import ru.specialcourse.java.analyzer.AfterAnnotationAnalyzer;
import ru.specialcourse.java.analyzer.BeforeAnnotationAnalyzer;
import ru.specialcourse.java.analyzer.TestAnnotationAnalyzer;
import ru.specialcourse.java.annotation.Test;
import ru.specialcourse.java.exception.AssertionFailedError;
import ru.specialcourse.java.exception.NotThrownExpectedException;
import ru.specialcourse.java.printer.Printer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

public class TestTask implements Runnable {
    private static final String LINE = "-------------------------------------------------------------------------------";
    private static final String FAILURE = " <<< FAILURE!";
    private Class clazz;
    private BeforeAnnotationAnalyzer beforeAnnotationAnalyzer;
    private TestAnnotationAnalyzer testAnnotationAnalyzer;
    private AfterAnnotationAnalyzer afterAnnotationAnalyzer;
    private CountDownLatch latch;

    public TestTask(Class clazz, CountDownLatch latch) {
        this.clazz = clazz;
        beforeAnnotationAnalyzer = new BeforeAnnotationAnalyzer();
        testAnnotationAnalyzer = new TestAnnotationAnalyzer();
        afterAnnotationAnalyzer = new AfterAnnotationAnalyzer();
        this.latch = latch;
    }

    private TestTask() {
    }

    @Override
    public void run() {
        StringBuilder report = new StringBuilder();
        report.append(LINE);
        report.append(System.lineSeparator());
        report.append("Test class: ");
        report.append(clazz.getCanonicalName());
        report.append(System.lineSeparator());
        report.append(LINE);
        report.append(System.lineSeparator());

        long run = 0, passed = 0, assertFailed = 0, expectedFailed = 0, otherFailed = 0;
        StringBuilder testsErrorsReport = new StringBuilder();
        StringBuilder classErrorsReport = new StringBuilder();

        try {
            Method beforeMethod = beforeAnnotationAnalyzer.analyze(clazz)[0];
            Method[] testMethods = testAnnotationAnalyzer.analyze(clazz);
            Method afterMethod = afterAnnotationAnalyzer.analyze(clazz)[0];

            for (Method testMethod : testMethods) {
                Object objectInstance = clazz.newInstance();

                Class<? extends Throwable> expectedException =
                        testAnnotationAnalyzer.getExpectedExceptionClass(testMethod);

                try {
                    if (beforeMethod != null) {
                        beforeMethod.invoke(objectInstance);
                    }

                    try {
                        run++;
                        testMethod.invoke(objectInstance);

                        if (expectedException != Test.None.class) {
                            throw new NotThrownExpectedException(expectedException);
                        }
                    } catch (NotThrownExpectedException e) {
                        expectedFailed++;

                        // нет ожидаемого исключения
                        appendException(testsErrorsReport, testMethod, e);
                        continue;
                    } catch (Throwable e) {
                        // Обработка excepted exception и assert exception
                        if (!e.getCause().getClass().isAssignableFrom(expectedException)) {
                            if (e.getCause().getClass().isAssignableFrom(AssertionFailedError.class)) {
                                assertFailed++;

                                // исключения при проверке
                                appendException(testsErrorsReport, testMethod, e.getCause());
                            } else {
                                otherFailed++;

                                // исключения иные --- при исполнении методов Test
                                appendException(testsErrorsReport, testMethod, e.getCause());
                            }
                        }
                    }

                    try {
                        if (afterMethod != null) {
                            afterMethod.invoke(objectInstance);
                        }
                        passed++;
                    } catch (Exception e) {
                        otherFailed++;
                        // исключения иные --- при исполнении метода After
                        appendException(testsErrorsReport, afterMethod, e.getCause());
                    }
                } catch (Exception e) {
                    otherFailed++;
                    // исключения иные --- при исполнении метода Before
                    appendException(testsErrorsReport, beforeMethod, e.getCause());

                    // прерываем цикл, поскольку в Before ошибка
                    break;
                }
            }
        } catch (Exception e) {
            otherFailed++;
            classErrorsReport.append("Class has bad structure!");
            classErrorsReport.append(FAILURE);
            classErrorsReport.append(System.lineSeparator());

            // исключения неправильного считывания методов или создания экземпляра, класс некорректен
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            classErrorsReport.append(error.toString());
            classErrorsReport.append(System.lineSeparator());
        }

        report.append("Tests run: ");
        report.append(run);
        report.append(", Passed: ");
        report.append(passed);
        report.append(", Failed on assertions: ");
        report.append(assertFailed);
        report.append(", Failed by not thrown expected exception: ");
        report.append(expectedFailed);
        report.append(", Failed by other exception: ");
        report.append(otherFailed);
        if ((assertFailed + expectedFailed + otherFailed) > 0) {
            report.append(FAILURE);
        }
        report.append(System.lineSeparator());

        report.append(classErrorsReport);

        report.append(testsErrorsReport);

        Printer.print(report.toString(), run, passed, assertFailed + expectedFailed + otherFailed);

        latch.countDown();
    }

    private void appendException(StringBuilder stringBuilder, Method method, Throwable e) {
        stringBuilder.append(clazz.getCanonicalName());
        stringBuilder.append('.');
        stringBuilder.append(method.getName());
        stringBuilder.append("()");
        stringBuilder.append(FAILURE);
        stringBuilder.append(System.lineSeparator());

        StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
        stringBuilder.append(error.toString());
        stringBuilder.append(System.lineSeparator());
    }
}
