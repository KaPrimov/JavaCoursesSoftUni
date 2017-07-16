package dependencyInversionSkeleton;


import dependencyInversionSkeleton.strategies.AdditionStrategy;
import dependencyInversionSkeleton.strategies.Strategy;

public class PrimitiveCalculator implements Calculator {

    private Strategy strategy;

    public PrimitiveCalculator(){
        this.strategy = new AdditionStrategy();
    }

    @Override
    public void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public int performCalculation(int firstOperand, int secondOperand){
        return this.strategy.calculate(firstOperand, secondOperand);
    }
}
