package interfaces;

import java.sql.SQLException;

public interface DBContext {

    <E> boolean persist(E entity) throws IllegalArgumentException, SQLException, IllegalAccessException;

    <E> Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException;

    <E> Iterable<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException;

    <E> E findFirst(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException;

    <E> E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException;

}
