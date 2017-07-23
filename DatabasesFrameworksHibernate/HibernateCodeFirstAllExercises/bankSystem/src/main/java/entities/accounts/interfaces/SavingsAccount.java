package entities.accounts.interfaces;

public interface SavingsAccount extends Account {

    double getInterestRate();

    void setInterestRate(double interestRate);
}
