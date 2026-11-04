package ru.koshkin;

import lombok.SneakyThrows;

import static ru.koshkin.Framework.TestsRunner.runTests;

public class CustomTestFramework {

    @SneakyThrows
    public static void main(String[] args) {
        String className = "ru.koshkin.TestCases.TestIntegerCalculator";
        runTests(className);
    }
}
