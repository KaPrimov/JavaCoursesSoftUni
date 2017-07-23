package service.api;

import java.math.BigDecimal;

public interface CheckingAccountService extends AccountService {
    void deductFee(String accountNumber, BigDecimal fee);
}
