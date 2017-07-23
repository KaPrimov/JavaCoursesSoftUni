package app.serviceLayer.api;

import java.util.List;

public interface ServiceInterface<E, K> {

    E findByID(K id);

    void remove(E object);

    List<E> findAll(Class<E> serviceClass);

    void save(E object);
}
