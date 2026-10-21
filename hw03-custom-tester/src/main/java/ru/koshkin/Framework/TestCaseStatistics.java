package ru.koshkin.Framework;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestCaseStatistics {
    private int passed = 0;
    private int failed = 0;
    private int exceptions = 0;
    private int total = 0;

    public void incrementPassed() {
        this.passed++;
    }

    public void incrementFailed() {
        this.failed++;
    }

    public void incrementExceptions() {
        this.exceptions++;
    }

    public void incrementTotal() {
        this.total++;
    }

    @Override
    public String toString() {
        return String.format("TOTAL TESTS: %d\n\t-->TESTS PASSED: %d\n\t-->TESTS FAILED: %d\n\t-->EXCEPTION OCCURRED: %d", total, passed, failed, exceptions);
    }
}
