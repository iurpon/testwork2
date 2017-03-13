package home.webapplication.domain.repository.impl;


import home.webapplication.bl.SessionUtil;
import home.webapplication.domain.User;
import home.webapplication.domain.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepositoryImpl extends SessionUtil implements UserRepository{

    public UserRepositoryImpl(){
    }

    @Override
    public void addUser(User user) throws SQLException{
        openTransactionSession();
        Session session = getSession();
        session.save(user);
        //close session with a transaction
        closeTransactionSesstion();
    }

    @Override
    public void updateUser(User user) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(user);
        //close session with a transaction
        closeTransactionSesstion();
    }

    @Override
    public User getUserById(String id) throws SQLException {
        openTransactionSession();
        int currentId = Integer.parseInt(id);

        String sql = "SELECT * FROM test WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.setParameter("id", currentId);

        User user = (User) query.getSingleResult();
        //close session with a transaction
        closeTransactionSesstion();
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        //open session with a transaction
        openTransactionSession();
        String sql = "SELECT * FROM test";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        List<User> addressList = query.list();
        //close session with a transaction
        closeTransactionSesstion();
        return addressList;
    }

    @Override
    public List<User> deleteUser(String id) throws SQLException {
        User user = getUserById(id);
        openTransactionSession();

        Session session = getSession();
        session.remove(user);

        //close session with a transaction
        closeTransactionSesstion();
        return getAllUsers();
    }

    public List<User> search(User searchUser) throws SQLException{
        List<User> all = getAllUsers();
        List<User> byName = new ArrayList<>();
        for(User u : all)
            if(u.getName().equalsIgnoreCase(searchUser.getName()))
                byName.add(u);

        return byName;
    }
}
