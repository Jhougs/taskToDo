package com.juanviana.app.todoapp.juanviana_todoapp.services;

import java.util.List;

import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;

public interface TaskService {

    List<Task> getAllTasks(Long id);
    Task getTaskById(Long id, String taskId);
    Task createTask(Long id, Task task);
    Task updateTask(long id, Task task);
    void deleteTask(long id);

}
