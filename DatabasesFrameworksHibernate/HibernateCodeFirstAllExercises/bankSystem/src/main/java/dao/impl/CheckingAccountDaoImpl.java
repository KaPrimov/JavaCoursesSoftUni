package dao.impl;

import dao.api.CheckingAccountDao;
import entities.accounts.interfaces.CheckingAccount;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;

public class CheckingAccountDaoImpl extends AccountDaoImpl implements CheckingAccountDao {

    public CheckingAccountDaoImpl(EntityManager em) {
        super(em);
    }

    @Override
    public void deductFee(String accountNumber, BigDecimal fee) {
        Query query = super.getEntityManager().createQuery("SELECT a FROM AccountImpl AS a " +
                "WHERE a.id = :number").setParameter("number", Long.parseLong(accountNumber));

        CheckingAccount checkingAccount = (CheckingAccount) query.getSingleResult();

        if (checkingAccount.getFee().compareTo(fee) < 0) {
            throw new IllegalArgumentException("You have insufficient funds");
        }

        checkingAccount.setFee(checkingAccount.getFee().subtract(fee));
        super.beginTransaction();
        super.persist(checkingAccount);
    }
}
