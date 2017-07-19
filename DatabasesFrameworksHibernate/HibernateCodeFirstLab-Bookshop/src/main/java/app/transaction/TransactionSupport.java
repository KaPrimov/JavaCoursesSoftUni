package app.transaction;

public interface TransactionSupport<E> {

    void rollback();

    void beginTransaction();

    void commit();

}
