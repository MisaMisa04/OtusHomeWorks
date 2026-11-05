package ru.koshkin.Functional;

import ru.koshkin.annotations.MagicLogger;

public class IntegerCalculatorImpl implements IntegerCalculator {

    @MagicLogger
    public int calculate(int operandA, int operandB, Actions action) {
        switch (action) {
            case SUM -> {
                return operandA + operandB;
            }
            case SUBTRACT -> {
                return operandA - operandB;
            }
            case MULTIPLE -> {
                return operandA * operandB;
            }
            case DIVIDE -> {
                return operandA / operandB;
            }
            default -> throw new UnsupportedOperationException("Action not resolved");
        }
    }
}
