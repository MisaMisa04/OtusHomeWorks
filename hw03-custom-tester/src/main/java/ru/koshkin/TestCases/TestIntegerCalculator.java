package ru.koshkin.TestCases;

import ru.koshkin.Annotations.After;
import ru.koshkin.Annotations.Before;
import ru.koshkin.Annotations.Test;
import ru.koshkin.Framework.CustomAssertion;
import ru.koshkin.Functional.IntegerCalculator;

import static ru.koshkin.Functional.IntegerCalculator.Actions.SUM;
import static ru.koshkin.Functional.IntegerCalculator.Actions.SUBSTRACT;
import static ru.koshkin.Functional.IntegerCalculator.Actions.MULTIPLE;
import static ru.koshkin.Functional.IntegerCalculator.Actions.DIVIDE;

public class TestIntegerCalculator {

    private IntegerCalculator calculator;

    @Before
    public void initCalculator() {
        this.calculator = new IntegerCalculator();
    }

    @Test
    public CustomAssertion sum() {
        int expected = 10;
        int actual = calculator.calculate(7, 3, SUM);
        return CustomAssertion.assertEquals(expected, actual);
    }

    @Test
    public CustomAssertion multiple() {
        int expected = 64;
        int actual = calculator.calculate(16, 4, MULTIPLE);
        return CustomAssertion.assertEquals(expected, actual);
    }

    @Test
    public CustomAssertion substract() {
        int expected = 7;
        int actual = calculator.calculate(15, 8, SUBSTRACT);
        return CustomAssertion.assertEquals(expected, actual);
    }

    @Test
    public CustomAssertion divide() {
        int expected = 6;
        int actual = calculator.calculate(24, 4, DIVIDE);
        return CustomAssertion.assertEquals(expected, actual);
    }

    @Test
    public CustomAssertion causesDivideByZeroException() {
        int expected = 0;
        int actual = calculator.calculate(48, 0, DIVIDE);
        return CustomAssertion.assertEquals(expected, actual);
    }

    @Test
    public CustomAssertion willBeFailed() {
        int expected = 15;
        int actual = calculator.calculate(4, 9, SUBSTRACT);
        return CustomAssertion.assertEquals(expected, actual);
    }


    @After
    public void after1() {
        System.out.println("after1");
    }

    @After
    public void after2() {
        System.out.println("after2");
    }

//    @After
//    public void willCauseException() throws Exception {
//        throw new Exception("Insert exception text");
//    }

}
