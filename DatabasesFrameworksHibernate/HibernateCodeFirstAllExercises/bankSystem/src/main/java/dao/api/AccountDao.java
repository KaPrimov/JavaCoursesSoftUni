package dao.api;

import java.math.BigDecimal;

public interface AccountDao extends Dao{

    void deposit(String accountNumber, BigDecimal money);

    void withDraw(String accountNumber, BigDecimal money);

}
