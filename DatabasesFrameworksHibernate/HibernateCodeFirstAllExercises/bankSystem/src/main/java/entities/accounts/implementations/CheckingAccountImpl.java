package entities.accounts.implementations;

import entities.accounts.interfaces.CheckingAccount;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("C")
public class CheckingAccountImpl extends AccountImpl implements CheckingAccount {

    private BigDecimal fee;

    public CheckingAccountImpl() {}

    public CheckingAccountImpl(String accountNumber, BigDecimal balance, BigDecimal fee) {
        super(accountNumber, balance);
        this.fee = fee;
    }

    @Override
    @Column(name = "fee")
    public BigDecimal getFee() {
        return fee;
    }

    @Override
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
