package com.juanviana.app.todoapp.juanviana_todoapp.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanviana.app.todoapp.juanviana_todoapp.dto.modelsDto.TaskDto;
import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;
import com.juanviana.app.todoapp.juanviana_todoapp.services.TaskService;
import com.juanviana.app.todoapp.juanviana_todoapp.utils.UtilFunctions;
import com.juanviana.app.todoapp.juanviana_todoapp.validation.TaskValidation;

import ch.qos.logback.classic.pattern.Util;
import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;






@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskValidation taskValidation;

    


    @PostMapping("create/{id}")
    public ResponseEntity<?> createTask(@Valid  @RequestBody Task task, BindingResult result , @PathVariable Long id ) {

        taskValidation.validate(task, result);
        if(result.hasFieldErrors()){
            return UtilFunctions.taskValidation(result);
        }
        Optional<Task> taskAns = taskService.createTask(id, task);
        if(taskAns.isPresent()){
            return ResponseEntity.ok("Task created successfully");
        } else return ResponseEntity.badRequest().build();

        
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateTask(@Valid @RequestBody Task task, BindingResult result , @PathVariable Long id) {
        taskValidation.validate(task, result);
        if(result.hasFieldErrors()) {
            return UtilFunctions.taskValidation(result);
        }
        Optional<Task> taskAns = taskService.updateTask(id,task);
        if(taskAns.isPresent()) {
            return ResponseEntity.ok("Task Uptaded succesfully");
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getAllTasks();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getTasksUser(@PathVariable Long id) {
        List<TaskDto> task = taskService.getAllTasksById(id);
        if(task == null ) return ResponseEntity.badRequest().build();
        else  return ResponseEntity.status(HttpStatus.ACCEPTED).body(task.stream().toList());
    }
}
