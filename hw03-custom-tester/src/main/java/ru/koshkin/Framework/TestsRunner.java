package ru.koshkin.Framework;

import ru.koshkin.Annotations.After;
import ru.koshkin.Annotations.Before;
import ru.koshkin.Annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestsRunner {

    public static void runTests(String className) throws ClassNotFoundException, NoSuchMethodException {
        final List<TestResult> testResults = new ArrayList<>();
        final TestCaseStatistics statistics = new TestCaseStatistics();
        Class<?> clazz = Class.forName(className);
        List<Method> beforeMethods = getAnnotatedMethods(clazz, Before.class);
        List<Method> testMethods = getAnnotatedMethods(clazz, Test.class);
        List<Method> afterMethods = getAnnotatedMethods(clazz, After.class);
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        testMethods.forEach((testMethod -> {
            TestResult result = new TestResult();
            result.setMethodName(testMethod.getName());
            try {
                System.out.printf("Starting of %s\n", testMethod.getName());
                Object testCase = declaredConstructor.newInstance();
                runMethods(beforeMethods, testCase);
                testMethod.setAccessible(true);
                CustomAssertion assertion = (CustomAssertion) testMethod.invoke(testCase);
                statistics.incrementTotal();

                result.setSuccessful(assertion.isSuccess());
                result.setText(assertion.getText());

                if (assertion.isSuccess()) statistics.incrementPassed();
                else statistics.incrementFailed();

                runMethods(afterMethods, testCase);
            } catch (Exception e) {
                System.out.println(formExceptionText(testMethod, e));
                result.setGotException(true);
                statistics.incrementTotal();
                statistics.incrementExceptions();
            } finally {
                testResults.add(result);
                System.out.printf("End of %s\n\n", testMethod.getName());
            }
        }));
        printResults(statistics, testResults);
    }

    private static void printResults(TestCaseStatistics statistics, List<TestResult> testResults) {
        final String separator = "---------------------------------------------------------";
        System.out.printf("%s\n%s\n%s\n", separator, statistics, separator);
        testResults.forEach((result) -> {
            System.out.printf("%s\n\t\t-->Success: %s\n\t\t-->Exception: %b\n",
                    result.getMethodName(),
                    String.format("%b%s", result.isSuccessful(), result.getText() == null ? "" : ": " + result.getText()),
                    result.isGotException()
            );

        });
    }

    private static List<Method> getAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Stream.of(clazz.getDeclaredMethods()).filter((method -> method.isAnnotationPresent(annotation))).collect(Collectors.toList());
    }

    private static void runMethods(List<Method> methods, Object invoker) {
        methods.forEach((method -> {
            method.setAccessible(true);
            try {
                method.invoke(invoker);
            } catch (Exception e) {
                throw new RuntimeException(formExceptionText(method, e));
            }
        }));
    }

    private static String formExceptionText(Method method, Exception e) {
        final String f = "Exception when invoking method %s: %s";
        if (e instanceof InvocationTargetException ex) {
            if (ex.getTargetException().getMessage() == null)
                return String.format(f, method.getName(), ex.getMessage());
            else
                return String.format(f, method.getName(), ex.getTargetException().getMessage());
        } else
            return String.format(f, method.getName(), e.getMessage());
    }
}
