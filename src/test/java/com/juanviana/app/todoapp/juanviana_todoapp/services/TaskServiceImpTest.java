package com.juanviana.app.todoapp.juanviana_todoapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.juanviana.app.todoapp.juanviana_todoapp.dto.modelsDto.TaskDto;
import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;
import com.juanviana.app.todoapp.juanviana_todoapp.model.User;
import com.juanviana.app.todoapp.juanviana_todoapp.repository.TaskRepository;
import com.juanviana.app.todoapp.juanviana_todoapp.repository.UserRepository;


public class TaskServiceImpTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImp taskService;

    @Mock
    private UserRepository userRepository;

    @BeforeAll
    static void init(){

    }

    @BeforeEach
    void before(){
        MockitoAnnotations.openMocks(this);

    }

    @AfterEach
    void afterEach(){

    }

    @AfterAll
    static void afterAll(){

    }


    @Test
    void testCreateTask() {
        
        //Task task1 = createTask(1L, "Tarea 1", "Desc 1", false);
        Long userId = 2L ; 
        Task task2 = createTask(2L, "Tarea 2", "Desc 2", true);

        User mockUser  = new User();
        mockUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        
        Task savedTask = createTask(10L, "Tarea nueva", "Descripción nueva", false);
        savedTask.setUser(mockUser);
        when(taskRepository.save(task2)).thenReturn(savedTask);

        Optional<Task> result = taskService.createTask(userId, task2);

        assertTrue(result.isPresent());
        assertEquals("Tarea nueva", result.get().getTitle());
        assertEquals("Descripción nueva", result.get().getDescription());
        assertEquals(mockUser, result.get().getUser());
        verify(userRepository).findById(userId);
        verify(taskRepository).save(task2);


    }

    @Test
    void testDeleteTask() {

        Long userId = 2L;
        taskService.deleteTask(userId);
        verify(taskRepository).deleteById(userId);
        //Task task2 = createTask(2L, "Tarea 2", "Desc 2", true);

    }

    @Test
    void testGetAllTasks() {

        Task task1 = createTask(1L, "Tarea 1", "Desc 1", false);
        Task task2 = createTask(2L, "Tarea 2", "Desc 2", true);

        when(taskRepository.findAll()).thenReturn(java.util.Arrays.asList(task1,task2));
        List<TaskDto> result = taskService.getAllTasks();

        assertNotNull(result);
        assertEquals("Tarea 1", result.get(0).getTitle());
        assertEquals(2, result.size());
        assertEquals("Tarea 2", result.get(1).getTitle());
        verify(taskRepository).findAll();
        

    }

    @Test
    void testGetAllTasksById() {

        User user = new User();
        Task task1 = createTask(1L, "Tarea 1", "Desc 1", false);
        task1.setUser(user);
        Task task2 = createTask(1L, "Tarea 2", "Desc 2", true);
        task2.setUser(user);
        
        when(taskRepository.getAllTasksById(1L)).thenReturn(java.util.Arrays.asList(task1,task2));
        List<TaskDto> result = taskService.getAllTasksById(1L);
        
        verify(taskRepository).getAllTasksById(1L);
        org.junit.jupiter.api.Assertions.assertEquals(1L, result.get(0).getId());
        org.junit.jupiter.api.Assertions.assertEquals(1L,result.get(1).getId());

    }

    @Test
    void testUpdateTask() {

        Long idTask = 1L;
        Task taskCreated = createTask(idTask, "Tarea 1", "Desc 1", false);
        
        when(taskRepository.findById(idTask)).thenReturn(Optional.of(taskCreated));
        
        Task taskToUpdate = createTask(2L, "Tarea 2", "Desc 2", true);
        Task updatedTask = createTask(idTask, "Tarea 2", "Desc 2", true);
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);
        Optional<Task> result = taskService.updateTask(idTask, taskToUpdate);

        assertTrue(result.isPresent());
        assertTrue(result.get().isCompleted());
        assertEquals(idTask, result.get().getId());
        verify(taskRepository).findById(idTask);
        verify(taskRepository).save(any(Task.class));
            }


    private Task createTask(Long id, String title, String desc, boolean completed) {
    Task t = new Task();
    t.setId(id);
    t.setTitle(title);
    t.setDescription(desc);
    t.setCompleted(completed);
    return t;
}

}
