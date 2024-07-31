package com.deynak.dao;

import com.deynak.model.User;
import com.deynak.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getConnectionHibernate().openSession();) {
            session.beginTransaction();
            String hql = "CREATE TABLE IF NOT EXISTS User (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age INT)";
            session.createNativeQuery(hql).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getConnectionHibernate().openSession();) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getConnectionHibernate().openSession();) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getConnectionHibernate().openSession();) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getConnectionHibernate() .openSession()) {
            userList = session.createQuery("from User", User.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        System.out.println(userList);
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getConnectionHibernate().openSession();) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
