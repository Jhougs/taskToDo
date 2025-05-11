package com.juanviana.app.todoapp.juanviana_todoapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanviana.app.todoapp.juanviana_todoapp.model.User;
import com.juanviana.app.todoapp.juanviana_todoapp.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
        
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>)userRepository.findAll();
        
    }

    @Override
    public User createUser(User user) {
        
        return userRepository.save(user);
        
    }
    
    
    @Override
    public Optional<User> updateUser(Long id, User user) {
        Optional<User> userReturnedToUpdate = userRepository.findById(id);
        if(userReturnedToUpdate.isPresent()){

            User userToUpdate = userReturnedToUpdate.orElseThrow();
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setRole(user.getRole());
            userRepository.save(userToUpdate);
        };
        return userReturnedToUpdate;
        
    
    }
    @Override
    public Optional<User> deleteUser(Long id) {

        Optional<User> userToDelete = userRepository.findById(id);
        userToDelete.ifPresent(delete -> userRepository.delete(delete));
        return userToDelete;
    }


}
