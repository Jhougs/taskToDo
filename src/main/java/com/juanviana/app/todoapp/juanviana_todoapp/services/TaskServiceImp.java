package com.juanviana.app.todoapp.juanviana_todoapp.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.juanviana.app.todoapp.juanviana_todoapp.dto.modelsDto.TaskDto;
import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;
import com.juanviana.app.todoapp.juanviana_todoapp.repository.TaskRepository;

public class TaskServiceImp implements TaskService {

    
    @Autowired
    private TaskRepository taskRepository;

    // Convertir RequestDTO → Entity
    private Task convertToEntity(TaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        return task;
    }

    // Convertir Entity → ResponseDTO
    private TaskDto convertToDTO(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        
        return dto;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<TaskDto> taskDtos = StreamSupport.stream(taskRepository.findAll().spliterator(), false)
            .map(this::convertToDTO)
            .toList();

        return taskDtos;
        
    }

    
    @Override
    public TaskDto createTask(TaskDto taskRequestDTO) {
        Task task = convertToEntity(taskRequestDTO);
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
        
    }

    @Override
    public void deleteTask(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTask'");
    }

    @Override
    public TaskDto updateTask(long id, TaskDto task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTask'");
    }

    @Override
    public TaskDto getTaskById(Long id, String taskId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTaskById'");
    }

}
