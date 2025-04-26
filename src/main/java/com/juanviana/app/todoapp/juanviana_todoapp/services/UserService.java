package com.juanviana.app.todoapp.juanviana_todoapp.services;



import java.util.List;
import java.util.Optional;

import com.juanviana.app.todoapp.juanviana_todoapp.model.User;


public interface UserService  {

    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User createUser(User user);
    Optional<User> updateUser(Long id, User user);
    Optional<User> deleteUser(Long id);

}
