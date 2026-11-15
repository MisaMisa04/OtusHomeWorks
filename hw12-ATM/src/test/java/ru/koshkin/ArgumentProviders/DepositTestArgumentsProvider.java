package ru.koshkin.ArgumentProviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class DepositTestArgumentsProvider implements ArgumentsProvider {

    public final Map<Integer, Integer> targetInitialATMMap = Map.of(
            1, 5,
            5, 2,
            10, 4);
    public final int targetInitialATMBalance = 55;

    // I decided to do this cause now we have named variables instead of faceless "Object... arguments"
    interface DepositTestArgument extends Arguments {
        static Arguments of(Map<Integer, Integer> initialMap, int initialBalance,
                            Map<Integer, Integer> deposit,
                            Map<Integer, Integer> expectedMapAfterDeposit, int expectedBalanceAfterDeposit) {
            return Arguments.of(initialMap, initialBalance, deposit, expectedMapAfterDeposit, expectedBalanceAfterDeposit);
        }
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                // coins of same nominal
                DepositTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        Map.of(1, 2),
                        Map.of(1, 7,
                                5, 2,
                                10, 4),
                        57
                ),
                // coins of different nominal
                DepositTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        Map.of(1, 4,
                                5, 12,
                                10, 5),
                        Map.of(1, 9,
                                5, 14,
                                10, 9),
                        169
                ),
                // coins with new nominal
                DepositTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        Map.of(1, 4,
                                50, 2),
                        Map.of(1, 9,
                                5, 2,
                                10, 4,
                                50, 2),
                        159
                ),
                // ZERO!
                DepositTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        Map.of(),
                        targetInitialATMMap,
                        targetInitialATMBalance
                )
        );
    }
}
