package service.impl;

import dao.api.CheckingAccountDao;
import entities.accounts.implementations.AccountImpl;
import entities.accounts.interfaces.Account;
import entities.accounts.interfaces.CheckingAccount;
import service.api.CheckingAccountService;
import service.transactions.VoidCommand;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CheckingAccountServiceImpl extends AbstractAccountService implements CheckingAccountService {

    public CheckingAccountServiceImpl(EntityManager em) {
        super(em);
    }

    @Override
    public void deductFee(String accountNumber, BigDecimal fee) {
        super.runInTransaction(new VoidCommand() {
            @Override
            public void execute() {
                Account account =(CheckingAccount) dao.findById(AccountImpl.class, accountNumber);
                ((CheckingAccountDao)dao).deductFee(accountNumber, fee);
            }
        });
    }
}
