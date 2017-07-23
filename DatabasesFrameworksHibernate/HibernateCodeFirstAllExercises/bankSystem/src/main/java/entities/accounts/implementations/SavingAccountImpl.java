package entities.accounts.implementations;

import entities.accounts.interfaces.SavingsAccount;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("S")
public class SavingAccountImpl extends AccountImpl implements SavingsAccount {

    private double interestRate;

    public SavingAccountImpl() {
    }

    public SavingAccountImpl(String accountNumber, BigDecimal balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    @Column(name = "interest_rate")
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
