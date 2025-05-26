package com.juanviana.app.todoapp.juanviana_todoapp.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanviana.app.todoapp.juanviana_todoapp.dto.modelsDto.TaskDto;
import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;
import com.juanviana.app.todoapp.juanviana_todoapp.model.User;
import com.juanviana.app.todoapp.juanviana_todoapp.repository.TaskRepository;
import com.juanviana.app.todoapp.juanviana_todoapp.repository.UserRepository;

@Service
public class TaskServiceImp implements TaskService {

    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TaskDto> getAllTasks() {
      
        List<Task> tasks = StreamSupport.stream(taskRepository.findAll().spliterator(), false).toList();
        return tasks.stream()
        .map(task -> new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.isCompleted(),
            task.getUser() != null ? task.getUser().getId() : null
        )).toList();

    }

    @Override
    public Optional<Task> createTask(Long id, Task task ) {

        Optional<User> userFind = userRepository.findById(id);
        if(task.isCompleted() == null) task.setCompleted(false);
        if(userFind.isPresent()){
            task.setUser(userFind.orElseThrow());            
        }
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public Optional<Task> updateTask(long id, Task task) {
        Optional<Task> taskToUpdate  = taskRepository.findById(id);
        if(taskToUpdate.isPresent()){
            Task newTask = taskToUpdate.get();
            newTask.setTitle(task.getTitle());
            newTask.setDescription(task.getDescription());
            newTask.setCompleted(task.isCompleted());
            return Optional.of(taskRepository.save(newTask));
        }
        return Optional.empty();

    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);   
    }

    @Override
    public List<TaskDto> getAllTasksById(Long id) {

        List<Task> tasks = taskRepository.getAllTasksById(id);
        if(!tasks.isEmpty()){
            return tasks.stream()
            .map(task -> new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getUser().getId() != null ? task.getUser().getId() : null
                ))
            .collect(Collectors.toList());
        } else return null;  
        
        
    }
}
