package ru.koshkin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.koshkin.ATM.ATM;
import ru.koshkin.ArgumentProviders.DepositTestArgumentsProvider;
import ru.koshkin.ArgumentProviders.WithdrawMustFailTestArgumentsProvider;
import ru.koshkin.ArgumentProviders.WithdrawMustSuccessTestArgumentsProvider;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class TestATM {

    @ParameterizedTest(name = "deposit of {2}")
    @ArgumentsSource(DepositTestArgumentsProvider.class)
    public void depositTest(Map<Integer, Integer> initialMap, int initialBalance,
                            Map<Integer, Integer> deposit,
                            Map<Integer, Integer> expectedMapAfterDeposit, int expectedBalanceAfterDeposit) {
        ATM atm = new ATM(initialMap);
        assertThat(atm.getATMNominals2Amount()).isEqualTo(initialMap);
        assertThat(atm.getATMBalance()).isEqualTo(initialBalance);
        atm.deposit(deposit);
        assertThat(atm.getATMNominals2Amount()).isEqualTo(expectedMapAfterDeposit);
        assertThat(atm.getATMBalance()).isEqualTo(expectedBalanceAfterDeposit);
    }

    @ParameterizedTest(name = "withdraw of {2}")
    @ArgumentsSource(WithdrawMustSuccessTestArgumentsProvider.class)
    public void withdrawMustSuccessTest(Map<Integer, Integer> initialMap, int initialBalance,
                                        int withdrawSum,
                                        Map<Integer, Integer> expectedWithdrawMap, Map<Integer, Integer> expectedMapAfterWithdraw, int expectedBalanceAfterWithdraw) {
        ATM atm = new ATM(initialMap);
        assertThat(atm.getATMNominals2Amount()).isEqualTo(initialMap);
        assertThat(atm.getATMBalance()).isEqualTo(initialBalance);
        Map<Integer, Integer> actualWithdrawMap = atm.withdraw(withdrawSum);
        assertThat(actualWithdrawMap).isEqualTo(expectedWithdrawMap);
        assertThat(atm.getATMNominals2Amount()).isEqualTo(expectedMapAfterWithdraw);
        assertThat(atm.getATMBalance()).isEqualTo(expectedBalanceAfterWithdraw);
    }



    @ParameterizedTest(name = "withdraw of {2}")
    @ArgumentsSource(WithdrawMustFailTestArgumentsProvider.class)
    public void withdrawMustFailTest(Map<Integer, Integer> initialMap, int initialBalance,
                                     int withdrawSum) {
        final ATM atm = new ATM(initialMap);
        assertThat(atm.getATMNominals2Amount()).isEqualTo(initialMap);
        assertThat(atm.getATMBalance()).isEqualTo(initialBalance);
        assertThatThrownBy(()-> atm.withdraw(withdrawSum)).isInstanceOf(UnsupportedOperationException.class);

    }

}
