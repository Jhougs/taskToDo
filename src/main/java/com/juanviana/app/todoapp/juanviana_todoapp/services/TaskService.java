package com.juanviana.app.todoapp.juanviana_todoapp.services;

import java.util.List;

import com.juanviana.app.todoapp.juanviana_todoapp.dto.modelsDto.TaskDto;


public interface TaskService {

    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Long id, String taskId);
    TaskDto createTask(TaskDto task);
    TaskDto updateTask(long id, TaskDto task);
    void deleteTask(long id);

}
