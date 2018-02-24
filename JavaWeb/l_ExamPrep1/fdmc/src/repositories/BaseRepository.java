package repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BaseRepository {
    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("fdmc");

    private EntityTransaction transaction;

    protected EntityManager em;

    protected BaseRepository() { }

    private void initEntityManager() {
        this.em = emf.createEntityManager();
    }

    private void initTransaction() {
        if(this.transaction != null &&
                this.transaction.isActive()) {
            throw new IllegalArgumentException("Transaction is active.");
        }

        this.transaction = this.em.getTransaction();
        this.transaction.begin();
    }

    private void commitTransaction() {
        if(this.transaction == null
                || !this.transaction.isActive()) {
            throw new IllegalArgumentException("Transaction is null or inactive.");
        }

        this.transaction.commit();
    }

    private void dispose() {
        this.em.close();
    }

    protected RepositoryActionResult execute(RepositoryInvoker invoker) {
        RepositoryActionResult actionResult = new RepositoryActionResult(null);

        try {
            this.initEntityManager();
            this.initTransaction();
            invoker.invoke(actionResult);
            this.commitTransaction();
            this.dispose();
        } catch (Exception e) {
            if(this.transaction != null) {
                this.transaction.rollback();
            }

            e.printStackTrace();
        }

        return actionResult;
    }
}
