package ru.koshkin.ATM;

import lombok.Getter;

import java.util.*;

public class ATM {

    @Getter
    private final HashMap<Integer, Integer> ATMNominals2Amount;

    private final SortedSet<Integer> sortedATMNominalsDesc = new TreeSet<>((v1, v2) -> v2 - v1);

    @Getter
    private int ATMBalance = 0;

    public ATM(Map<Integer, Integer> ATMNominals2Amount) {
        this.ATMNominals2Amount = new HashMap<>(ATMNominals2Amount); // this must be mutable HashMap in any case!
        ATMNominals2Amount.forEach((nominal, amount) -> ATMBalance += nominal * amount);
        sortedATMNominalsDesc.addAll(ATMNominals2Amount.keySet());
    }

    public void deposit(Map<Integer, Integer> depositNominal2Amount) {
        depositNominal2Amount.forEach((depositNominal, depositAmount) -> {

            ATMNominals2Amount.compute(depositNominal, (ATMNominal, ATMAmount) -> {
                if (ATMAmount == null)
                    ATMAmount = depositAmount;
                else
                    ATMAmount += depositAmount;
                return ATMAmount;
            });
            ATMBalance += depositNominal * depositAmount;

        });
        sortedATMNominalsDesc.addAll(depositNominal2Amount.keySet());
    }

    public Map<Integer, Integer> withdraw(int sum) {
        if (sum > ATMBalance)
            throw new UnsupportedOperationException("Requested sum is more than balance");
        final Map<Integer, Integer> result = new HashMap<>();
        int withdrawRemains = sum;
        // greedy
        for (final int ATMNominal : sortedATMNominalsDesc) {
            int withdrawAmount = withdrawRemains / ATMNominal;
            if (withdrawAmount > 0 && Optional.ofNullable(ATMNominals2Amount.get(ATMNominal)).orElse(0) > 0) {
                final int take = Math.min(withdrawAmount, ATMNominals2Amount.get(ATMNominal));
                result.put(ATMNominal, take);
                withdrawRemains -= ATMNominal * take;
            }
            if (withdrawRemains == 0) {
                subtractFromATM(result);
                return result;
            }
        }
        throw new UnsupportedOperationException("Sorry bro, I can't give U this sum...");
    }

    private void subtractFromATM(Map<Integer, Integer> result) {
        result.forEach((subtractNominal, subtractAmount) -> {
            // here cannot be NPE, checked it earlier
            ATMNominals2Amount.compute(subtractNominal, (ATMNominal, ATMAmount) -> ATMAmount - subtractAmount);
            ATMBalance -= subtractNominal * subtractAmount;
        });
    }

}
