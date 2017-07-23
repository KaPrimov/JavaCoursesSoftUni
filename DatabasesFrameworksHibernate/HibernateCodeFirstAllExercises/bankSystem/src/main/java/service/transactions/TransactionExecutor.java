package service.transactions;

import java.util.List;

public interface TransactionExecutor<E> {

    E runInTransaction(Command<E> command);

    List<E> runInTransaction(MultiCommand<E> commands);

    void runInTransaction(VoidCommand command);

}
