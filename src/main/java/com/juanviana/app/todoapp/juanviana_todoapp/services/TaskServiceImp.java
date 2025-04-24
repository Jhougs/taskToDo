package com.juanviana.app.todoapp.juanviana_todoapp.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;
import com.juanviana.app.todoapp.juanviana_todoapp.repository.TaskRepository;

@Service
public class TaskServiceImp implements TaskService {

    
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
      

        return StreamSupport.stream(taskRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Task getTaskById(Long id, String taskId) {
        return taskRepository.findById(id).orElseThrow();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(long id, Task task) {
        Task taskToUpdate  = taskRepository.findById(id).orElseThrow();
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setCompleted(task.isCompleted());
        return taskRepository.save(taskToUpdate);
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);   
    }

    // // Convertir RequestDTO → Entity
    // private Task convertToEntity(Task dto) {
    //     Task task = new Task();
    //     task.setTitle(dto.getTitle());
    //     task.setDescription(dto.getDescription());
    //     task.setCompleted(dto.isCompleted());
    //     return task;
    // }

    // // Convertir Entity → ResponseDTO
    // private TaskDto convertToDTO(Task task) {
    //     TaskDto dto = new TaskDto();
    //     dto.setId(task.getId());
    //     dto.setTitle(task.getTitle());
    //     dto.setDescription(task.getDescription());
    //     dto.setCompleted(task.isCompleted());
        
    //     return dto;
    // }

    
}
