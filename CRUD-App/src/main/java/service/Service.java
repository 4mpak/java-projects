package service;

import java.util.List;

public interface Service {

    Object save(Object pet);

    void deleteById(long id);

    void delete(Object object);

    void deleteAll();

    Object update(Object object);

    Object getById(long id);

    List<Object> getAll();
}
