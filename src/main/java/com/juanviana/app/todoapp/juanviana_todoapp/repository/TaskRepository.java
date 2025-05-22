package com.juanviana.app.todoapp.juanviana_todoapp.repository;


import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.user_id.id = ?1") 
    List<Task> getAllTasksById(Long userId);

}
