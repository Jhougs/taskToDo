package com.juanviana.app.todoapp.juanviana_todoapp.services;

import java.util.List;


import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;


public interface TaskService {

    List<Task> getAllTasks();
    Task getTaskById(Long id, String taskId);
    Task createTask(Task task);
    Task updateTask(long id, Task task);
    void deleteTask(long id);

}
