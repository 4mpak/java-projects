package dao;

import models.Pet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;
import java.util.List;

public class PetDao implements CrudDao
{
    public Object save(Object pet){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(pet);
        tx.commit();
        session.close();
        return pet;
    }

    public void deleteById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Pet pet = session.get(Pet.class, id);
        session.delete(pet);
        tx.commit();
        session.close();
    }

    public void deleteByEntity(Object pet){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(pet);
        tx.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from Pet").executeUpdate();
        tx.commit();
        session.close();
    }

    public Object update(Object pet){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(pet);
        tx.commit();
        session.close();
        return pet;
    }

    public Object getById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Pet pet = session.get(Pet.class, id);
        session.close();
        return pet;
    }

    public List<Object> getAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Object> pets = session.createQuery("from Pet").list();
        session.close();
        return pets;
    }
}
