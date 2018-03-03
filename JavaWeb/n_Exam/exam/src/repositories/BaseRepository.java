package repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class BaseRepository {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("casebook_db");

    protected EntityTransaction transaction;

    protected EntityManager entityManager;

    protected BaseRepository() {
    }

    protected void initEntityManager() {
        this.entityManager = emf.createEntityManager();
    }

    protected void initTransaction() {
        if(this.transaction != null && this.transaction.isActive()) {
            throw new IllegalArgumentException("Transaction is active!");
        }

        this.transaction = this.entityManager.getTransaction();
        this.transaction.begin();
    }

    protected void commitTransaction() {
        if(this.transaction == null || !this.transaction.isActive()) {
            throw new IllegalArgumentException("Transaction is null or inactive!");
        }

        this.transaction.commit();
    }

    protected void dispose() {
        this.entityManager.close();
    }

    protected RepositoryActionResult execute(RepositoryActionInvoker invoker) {
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
