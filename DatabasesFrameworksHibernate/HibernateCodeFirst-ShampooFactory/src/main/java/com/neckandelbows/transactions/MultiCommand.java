package com.neckandelbows.transactions;

import java.util.List;

/**
 * Created by User on 14.7.2017 г..
 */
public interface MultiCommand<E> {

    List<E> execute();
}
