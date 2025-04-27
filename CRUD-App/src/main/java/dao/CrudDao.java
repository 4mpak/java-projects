package dao;

import java.util.List;

public interface CrudDao
{
    Object save(Object entity);

    void deleteById(long id);

    void deleteByEntity(Object entity);

    void deleteAll();

    Object update(Object entity);

    Object getById(long id);

    List<Object> getAll();
}
