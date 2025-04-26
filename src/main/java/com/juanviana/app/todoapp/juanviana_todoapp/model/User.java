package com.juanviana.app.todoapp.juanviana_todoapp.model;





import java.util.List;

import com.juanviana.app.todoapp.juanviana_todoapp.utils.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // lave primaria y autoincrementable
    private Long id;

    @Column(nullable = false, unique = true) // no puede ser nulo y unico
    private String email;


    private String password;

    private Role role;

    @OneToMany(mappedBy = "user_id")
    List<Task> tasks;


    public User() {
        
    }


    public User(Long id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }


    

    



}
