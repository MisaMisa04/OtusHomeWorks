package ru.koshkin.ArgumentProviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class WithdrawMustSuccessTestArgumentsProvider implements ArgumentsProvider {

    public final Map<Integer, Integer> targetInitialATMMap = Map.of(
            1, 5,
            5, 2,
            10, 8
    );

    public final int targetInitialATMBalance = 95;


    interface WithdrawTestArgument extends Arguments {
        static Arguments of(Map<Integer, Integer> initialMap, int initialBalance,
                            int withdrawSum,
                            Map<Integer, Integer> expectedWithdrawMap, Map<Integer, Integer> expectedMapAfterWithdraw, int expectedBalanceAfterWithdraw) {
            return Arguments.of(initialMap, initialBalance, withdrawSum, expectedWithdrawMap, expectedMapAfterWithdraw, expectedBalanceAfterWithdraw);
        }
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                // sum is easy to give with 1 coin
                WithdrawTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        5,
                        Map.of(5, 1),
                        Map.of(
                                1, 5,
                                5, 1,
                                10, 8
                        ),
                        90),
                // sum is easy to give with 2 coins
                WithdrawTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        15,
                        Map.of(10, 1, 5, 1),
                        Map.of(
                                1, 5,
                                5, 1,
                                10, 7
                        ),
                        80
                ),
                // lil harder - 3 coins
                WithdrawTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        28,
                        Map.of(10, 2, 5, 1, 1, 3),
                        Map.of(
                                1, 2,
                                5, 1,
                                10, 6
                        ),
                        67),
                // really hard - can't be given with even maximum of greatest coins
                // so should try to give max of that and coins with smaller nominal
                WithdrawTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        91,
                        Map.of(10, 8, 5, 2, 1, 1),
                        Map.of(
                                1, 4,
                                5, 0,
                                10, 0
                        ),
                        4),
                // ZERO!
                WithdrawTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        0,
                        Map.of(),
                        targetInitialATMMap,
                        targetInitialATMBalance)
        );
    }
}
