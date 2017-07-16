package database;

import javax.naming.OperationNotSupportedException;

public interface IDatabase<T extends Person> {

    void add(T person) throws OperationNotSupportedException;
    void remove();
    T[] fetch();
    int getIndex();
    T findById(long id);
    T findByUsername(String username);

}
