package home.webapplication.service.impl;

import home.webapplication.domain.User;
import home.webapplication.domain.repository.UserRepository;
import home.webapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by PC-User on 11.03.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() throws SQLException{
        return userRepository.getAllUsers();

    }
    public List<User> deleteUser(String id) throws SQLException {
        return userRepository.deleteUser(id);
    }

    @Override
    public void addUser(User user) throws SQLException  {
        userRepository.addUser(user);
    }

    @Override
    public void updateUser(User user) throws SQLException  {
        userRepository.updateUser(user);
    }

    @Override
    public User getUserById(String id) throws SQLException  {
        return userRepository.getUserById(id);
    }
    public List<User> search(User searchUser) throws SQLException{
        return userRepository.search(searchUser);
    }
}
