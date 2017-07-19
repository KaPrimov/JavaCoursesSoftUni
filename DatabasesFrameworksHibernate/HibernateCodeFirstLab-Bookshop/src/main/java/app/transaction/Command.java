package app.transaction;

public interface Command<E> {

    E execute();

}
