package dao.impl;

import dao.api.SavingsAccountDao;
import entities.accounts.interfaces.SavingsAccount;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SavingsAccountDaoImpl extends AccountDaoImpl implements SavingsAccountDao {
    public SavingsAccountDaoImpl(EntityManager em) {
        super(em);
    }

    @Override
    public void addInterest(String accountNumber, double interest) {
        Query query = super.getEntityManager().createQuery("SELECT a FROM AccountImpl AS a " +
                "WHERE a.id = :number").setParameter("number", Long.parseLong(accountNumber));

        SavingsAccount savingAccount = (SavingsAccount) query.getSingleResult();

        savingAccount.setInterestRate(interest);
        super.beginTransaction();
        super.persist(savingAccount);
        //super.commit();
    }
}
