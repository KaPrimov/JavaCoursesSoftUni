package service.transactions;

import java.util.List;

public interface MultiCommand<E> {

    List<E> execute();

}
