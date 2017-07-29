package softunicourse.services.api;

import java.util.List;

public interface ServiceInterface<E, K> {

    E findByID(K id);

    void remove(E object);

    List<E> findAll();

    void save(E object);

}
