package ru.koshkin.Proxy;

import lombok.extern.slf4j.Slf4j;
import ru.koshkin.Functional.IntegerCalculator;
import ru.koshkin.Functional.IntegerCalculatorImpl;

@Slf4j
public class ProxyMain {

    public static void main(String[] args) {
        log.info("=====NO PROXY, JUST COMMON OBJECT STARTS HERE=====");
        IntegerCalculator calculator = new IntegerCalculatorImpl();
        int result = calculator.calculate(6, 3, IntegerCalculator.Actions.DIVIDE);
        log.info("Result is {}", result);
        log.info("calculator class is {}", calculator.getClass());
        log.info("=====NO PROXY, JUST COMMON OBJECT ENDS HERE=====\n\n\n");

        log.info("=====PROXY STARTS HERE=====");
        IntegerCalculator proxyCalculator = MagicLoggerProxy.wrap(new IntegerCalculatorImpl(), IntegerCalculator.class);
        result = proxyCalculator.calculate(6, 3, IntegerCalculator.Actions.DIVIDE);
        log.info("If u see MagicLoggerProxy log above, than everything is cool!");
        log.info("Result is {}", result);
        log.info("proxyCalculator class is {}", proxyCalculator.getClass());
        log.info("=====PROXY ENDS HERE=====");


    }


}
