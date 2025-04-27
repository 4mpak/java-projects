package service;

import dao.PetDao;

import java.util.List;

public class PetService implements Service
{
    private PetDao petDao = new PetDao();

    public PetService() {}

    public Object save(Object pet){
        return petDao.save(pet);
    }

    public void deleteById(long id){
        petDao.deleteById(id);
    }

    public void delete(Object pet){
        petDao.deleteByEntity(pet);
    }

    public void deleteAll(){
        petDao.deleteAll();
    }

    public Object update(Object pet){
        return petDao.update(pet);
    }

    public Object getById(long id){
        return petDao.getById(id);
    }

    public List<Object> getAll(){
        return petDao.getAll();
    }
}
