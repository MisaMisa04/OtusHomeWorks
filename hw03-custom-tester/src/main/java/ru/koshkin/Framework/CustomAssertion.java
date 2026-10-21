package ru.koshkin.Framework;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomAssertion {

    private boolean success;
    private String text;

    public static <T> CustomAssertion assertEquals(T expected, T actual) {
        if (expected.equals(actual))
            return new CustomAssertion(true, null);
        else
            return new CustomAssertion(false, String.format("assertion failed: expect %s but got %s", expected, actual));
    }

}
