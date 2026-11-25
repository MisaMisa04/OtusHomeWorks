package ru.koshkin.ArgumentProviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class WithdrawMustFailTestArgumentsProvider implements ArgumentsProvider {

    public final Map<Integer, Integer> targetInitialATMMap = Map.of(
            1, 3,
            5, 2,
            10, 8
    );
    public final int targetInitialATMBalance = 93;


    interface WithdrawFailTestArgument extends Arguments {
        static Arguments of(Map<Integer, Integer> initialMap, int initialBalance,
                            int withdrawSum) {
            return Arguments.of(initialMap, initialBalance, withdrawSum);
        }
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                // absolutely impossible - withdraw is ge balance
                WithdrawFailTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        100
                ),
                // withdraw is smaller than balance, but no combination of coins available
                WithdrawFailTestArgument.of(
                        targetInitialATMMap,
                        targetInitialATMBalance,
                        29
                )
        );
    }
}
