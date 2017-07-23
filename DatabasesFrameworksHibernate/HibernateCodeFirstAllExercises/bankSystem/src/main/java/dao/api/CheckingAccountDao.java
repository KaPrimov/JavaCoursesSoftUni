package dao.api;

import java.math.BigDecimal;

public interface CheckingAccountDao extends AccountDao {

    void deductFee(String accountNumber, BigDecimal fee);

}
