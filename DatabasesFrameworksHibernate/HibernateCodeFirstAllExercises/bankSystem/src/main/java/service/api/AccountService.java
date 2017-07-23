package service.api;

import java.math.BigDecimal;

public interface AccountService extends Service {

    public void deposit(String accountNumber, BigDecimal fee);
    public void withdraw(String accountNumber, BigDecimal fee);

}
