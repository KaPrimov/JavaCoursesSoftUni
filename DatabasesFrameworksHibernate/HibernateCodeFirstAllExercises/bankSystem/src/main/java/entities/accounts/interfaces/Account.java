package entities.accounts.interfaces;

import java.math.BigDecimal;

public interface Account {

    String getAccountNumber();

    void setAccountNumber(String accountNumber);

    BigDecimal getBalance();

    void setBalance(BigDecimal balance);
}
