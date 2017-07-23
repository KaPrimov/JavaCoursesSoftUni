package dao.impl;

import dao.api.AccountDao;
import entities.accounts.interfaces.Account;

import javax.persistence.EntityManager;
import java.math.BigDecimal;


public abstract class AccountDaoImpl extends AbstractJpaDao implements AccountDao {

    protected AccountDaoImpl(EntityManager em) {
        super(em);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deposit(String accountNumber, BigDecimal money) {
        if (money.toString().charAt(0) == '-') {
            throw new IllegalArgumentException("You can not deposit negative sum");
        }

        Account account = (Account) super.getEntityManager().createQuery("SELECT a FROM AccountImpl AS a WHERE a.accountNumber = :number")
                .setParameter("number", accountNumber)
                .getSingleResult();

        account.setBalance(account.getBalance().add(money));

        super.beginTransaction();
        super.persist(account);
        super.commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void withDraw(String accountNumber, BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("You can not deposit negative amount");
        }

        Account account = (Account)  super.getEntityManager().createQuery("SELECT a FROM AccountImpl AS a WHERE a.accountNumber = :number")
                .setParameter("number", accountNumber)
                .getSingleResult();

        if(account.getBalance().compareTo(money) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(money));

        super.beginTransaction();
        super.persist(account);
        super.commit();
    }
}


