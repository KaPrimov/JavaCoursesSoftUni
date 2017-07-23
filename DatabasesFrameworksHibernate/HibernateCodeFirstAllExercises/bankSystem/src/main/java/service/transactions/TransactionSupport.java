package service.transactions;

public interface TransactionSupport<E> {

    void rollback();

    void beginTransaction();

    void commit();

}
