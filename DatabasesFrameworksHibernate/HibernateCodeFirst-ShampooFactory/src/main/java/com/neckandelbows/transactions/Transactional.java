package com.neckandelbows.transactions;

import java.util.List;

/**
 * Created by User on 14.7.2017 г..
 */
public interface Transactional<E> {

    E runInTransaction(Command<E> command);

    List<E> runInTransaction(MultiCommand<E> command);
}
