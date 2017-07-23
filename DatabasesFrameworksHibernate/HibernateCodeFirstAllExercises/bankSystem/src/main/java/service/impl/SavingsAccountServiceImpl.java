package service.impl;

import dao.api.SavingsAccountDao;
import entities.accounts.implementations.AccountImpl;
import entities.accounts.interfaces.Account;
import entities.accounts.interfaces.SavingsAccount;
import service.api.SavingsAccountService;
import service.transactions.VoidCommand;

import javax.persistence.EntityManager;

public class SavingsAccountServiceImpl extends AbstractAccountService implements SavingsAccountService {

    public SavingsAccountServiceImpl(EntityManager em) {
        super(em);
    }

    @Override
    public void addInterest(String accountNumber, double interest) {
        super.runInTransaction(new VoidCommand() {
            @Override
            public void execute() {
                Account account =(SavingsAccount) dao.findById(AccountImpl.class, accountNumber);
                ((SavingsAccountDao)dao).addInterest(accountNumber, interest);
            }
        });
    }

}
