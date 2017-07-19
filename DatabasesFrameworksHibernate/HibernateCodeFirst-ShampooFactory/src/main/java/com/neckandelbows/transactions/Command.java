package com.neckandelbows.transactions;

import java.util.List;

/**
 * Created by User on 13.7.2017 г..
 */
public interface Command<E> {
    E execute();
}
