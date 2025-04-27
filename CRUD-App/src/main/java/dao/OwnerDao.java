package dao;

import models.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class OwnerDao implements CrudDao
{
    public Object save(Object owner){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(owner);
        tx.commit();
        session.close();
        return owner;
    }

    public void deleteById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Owner owner = session.get(Owner.class, id);
        session.delete(owner);
        tx.commit();
        session.close();
    }

    public void deleteByEntity(Object owner){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(owner);
        tx.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from Owner").executeUpdate();
        tx.commit();
        session.close();
    }

    public Object update(Object owner){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(owner);
        tx.commit();
        session.close();
        return owner;
    }

    public Object getById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Owner owner = session.get(Owner.class, id);
        session.close();
        return owner;
    }

    public List<Object> getAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Object> owners = session.createQuery("from Owner").list();
        session.close();
        return owners;
    }
}
