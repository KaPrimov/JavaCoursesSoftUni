package entities.accounts.interfaces;

import java.math.BigDecimal;

public interface CheckingAccount extends Account {

    BigDecimal getFee();

    void setFee(BigDecimal fee);
}
