package service.impl;

import dao.api.AccountDao;
import entities.accounts.implementations.AccountImpl;
import entities.accounts.interfaces.Account;
import service.api.AccountService;
import service.transactions.VoidCommand;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public abstract class AbstractAccountService extends AbstractService implements AccountService{


    protected AbstractAccountService(EntityManager em) {
        super(em);
    }

    @Override
    public void deposit(String accountNumber, BigDecimal fee) {
        super.runInTransaction(new VoidCommand() {
            @Override
            public void execute() {
                Account account =(Account) dao.findById(AccountImpl.class, accountNumber);
                ((AccountDao)dao).deposit(accountNumber, fee);
            }
        });
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal fee) {
        super.runInTransaction(new VoidCommand() {
            @Override
            public void execute() {
                Account account =(Account) dao.findById(AccountImpl.class, accountNumber);
                ((AccountDao)dao).withDraw(accountNumber, fee);
            }
        });
    }
}
