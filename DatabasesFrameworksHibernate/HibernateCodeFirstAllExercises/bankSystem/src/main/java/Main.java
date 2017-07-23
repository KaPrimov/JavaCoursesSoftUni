import entities.accounts.implementations.CheckingAccountImpl;
import entities.accounts.implementations.SavingAccountImpl;
import entities.accounts.interfaces.CheckingAccount;
import entities.accounts.interfaces.SavingsAccount;
import service.api.CheckingAccountService;
import service.api.SavingsAccountService;
import service.impl.CheckingAccountServiceImpl;
import service.impl.SavingsAccountServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager em = emf.createEntityManager();

        CheckingAccount checkingAccount = new CheckingAccountImpl("one", new BigDecimal("21321.312"), new BigDecimal("1523.123"));
        SavingsAccount savingsAccount = new SavingAccountImpl("two", new BigDecimal("21321.312"), 0.123);

        CheckingAccountService checkingAccountService = new CheckingAccountServiceImpl(em);
        SavingsAccountService savingsAccountService = new SavingsAccountServiceImpl(em);

        //checkingAccountService.begin();

        //checkingAccountService.save(checkingAccount);
        //savingsAccountService.save(savingsAccount);

        savingsAccountService.addInterest("6", 10);
        checkingAccountService.deductFee("5", new BigDecimal("10000"));
        checkingAccountService.commit();
    }

}
