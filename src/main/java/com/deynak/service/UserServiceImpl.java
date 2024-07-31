package com.deynak.service;

import com.deynak.dao.UserDao;
import com.deynak.dao.UserDaoHibernateImpl;
import com.deynak.dao.UserDaoJDBCImpl;
import com.deynak.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoJDBCImpl();


    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
