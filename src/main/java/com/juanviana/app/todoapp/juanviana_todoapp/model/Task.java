package com.juanviana.app.todoapp.juanviana_todoapp.model;


import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    String user_id;
    public Task() {
    }


    public Task(Long id, String title, String description, Boolean completed, String user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.user_id = user;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Boolean isCompleted() {
        return completed;
    }


    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    public String getUser() {
        return user_id;
    }


    public void setUser(String user) {
        this.user_id = user;
    }

    


    

}
