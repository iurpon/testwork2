package home.webapplication.service;

import home.webapplication.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by PC-User on 11.03.2017.
 */
public interface UserService {
    List<User> getAllUsers()  throws SQLException;
    List<User> deleteUser(String id) throws SQLException ;
    void addUser(User user) throws SQLException ;
    void updateUser(User user) throws SQLException ;
    User getUserById(String id) throws SQLException ;

    List<User> search(User searchUser) throws SQLException;
}
