package service.transactions;

public interface Command<E> {

    E execute();

}
