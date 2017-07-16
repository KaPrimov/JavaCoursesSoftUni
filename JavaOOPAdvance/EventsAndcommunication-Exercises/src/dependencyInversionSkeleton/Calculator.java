package dependencyInversionSkeleton;

import dependencyInversionSkeleton.strategies.Strategy;

/**
 * Created by Kalin on 4/12/2017.
 */
public interface Calculator {
    void changeStrategy(Strategy strategy);

    int performCalculation(int firstOperand, int secondOperand);
}
