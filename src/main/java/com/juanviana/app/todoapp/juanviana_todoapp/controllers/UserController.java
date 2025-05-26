package com.juanviana.app.todoapp.juanviana_todoapp.controllers;

import java.util.List;
import java.util.Optional;


import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanviana.app.todoapp.juanviana_todoapp.model.User;
import com.juanviana.app.todoapp.juanviana_todoapp.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser") 
    public ResponseEntity<User> createUser(@RequestBody  User user) {
        System.err.println(ResponseEntity.status(Response.SC_CREATED));
        
        return ResponseEntity.status(Response.SC_CREATED).body(userService.createUser(user));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("Authorities: " + authentication.getAuthorities());
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUserEntity(@RequestBody User user  , @PathVariable Long id ) {
        
        Optional<User> userUptaded = userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userUptaded.orElseThrow());
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> postMethodName(@PathVariable Long id) {
     
        Optional<User> userTodelete = userService.deleteUser(id);
        if(userTodelete.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userTodelete.orElseThrow());
    }
    
    
    
    
    



}
