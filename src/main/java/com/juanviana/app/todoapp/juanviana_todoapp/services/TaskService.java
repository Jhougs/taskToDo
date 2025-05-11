package com.juanviana.app.todoapp.juanviana_todoapp.services;

import java.util.List;
import java.util.Optional;

import com.juanviana.app.todoapp.juanviana_todoapp.dto.modelsDto.TaskDto;
import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;


public interface TaskService {

    List<TaskDto> getAllTasks();
    Task getTaskById(Long id, String taskId);
    Task createTask(Long id, Task task);
    Optional<Task> updateTask(long id, Task task);
    void deleteTask(long id);

}
