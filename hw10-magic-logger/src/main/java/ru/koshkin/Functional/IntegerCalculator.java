package ru.koshkin.Functional;

public interface IntegerCalculator {


    enum Actions {
        SUM, SUBTRACT, MULTIPLE, DIVIDE;
    }
    
    int calculate(int operandA, int operandB, Actions action);
}
