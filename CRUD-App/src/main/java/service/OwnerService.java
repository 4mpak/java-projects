package service;

import dao.OwnerDao;

import java.util.List;

public class OwnerService implements Service
{
    private OwnerDao ownerDao = new OwnerDao();

    public OwnerService() {}

    public Object save(Object owner){
        return ownerDao.save(owner);
    }

    public void deleteById(long id){
        ownerDao.deleteById(id);
    }

    public void delete(Object owner){
        ownerDao.deleteByEntity(owner);
    }

    public void deleteAll(){
        ownerDao.deleteAll();
    }

    public Object update(Object owner){
        return ownerDao.update(owner);
    }

    public Object getById(long id){
        return ownerDao.getById(id);
    }

    public List<Object> getAll(){
        return ownerDao.getAll();
    }
}
