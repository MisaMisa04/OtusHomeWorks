package ru.koshkin.Framework;

import lombok.Data;

@Data
public class TestResult {

    private String methodName;
    private boolean successful = false;
    private String text = null;
    private boolean gotException = false;

}
