package com.juanviana.app.todoapp.juanviana_todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;
import com.juanviana.app.todoapp.juanviana_todoapp.services.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private TaskService taskService;


    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Task task) {
         //@valid es una anotación que se usa para validar el objeto TaskDto
        
        taskService.createTask(task);
        return ResponseEntity.ok("Task created successfully");
        
    }
    

}
