package TheCoins;

import TheCalculation.ICalcualte;

public abstract class Coin implements ICalcualte {

    public abstract double getValue();

    @Override
    public double calculate(double amount) {

        return amount;
    }
}
