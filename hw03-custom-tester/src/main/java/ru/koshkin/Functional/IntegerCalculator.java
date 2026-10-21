package ru.koshkin.Functional;

public class IntegerCalculator {

    public enum Actions {
        SUM, SUBSTRACT, MULTIPLE, DIVIDE;
    }

    public int calculate(int operandA, int operandB, Actions action) {
        switch (action) {
            case SUM -> {
                return operandA + operandB;
            }
            case SUBSTRACT -> {
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
