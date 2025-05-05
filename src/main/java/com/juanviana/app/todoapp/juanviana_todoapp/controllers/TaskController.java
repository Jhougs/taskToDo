package com.juanviana.app.todoapp.juanviana_todoapp.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanviana.app.todoapp.juanviana_todoapp.dto.modelsDto.TaskDto;
import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;
import com.juanviana.app.todoapp.juanviana_todoapp.services.TaskService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private TaskService taskService;


    @PostMapping("create/{id}")
    public ResponseEntity<?> createTask( @RequestBody Task task, @PathVariable Long id ) {

        
        taskService.createTask(id, task);
        return ResponseEntity.ok("Task created successfully");
        
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable Long id) {
        Optional<Task> taskAns = taskService.updateTask(id,task);
        if(taskAns.isEmpty()) return ResponseEntity.badRequest().build();
        else return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskAns.orElseThrow());
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getAllTasks();
    }

}
