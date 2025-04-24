package com.juanviana.app.todoapp.juanviana_todoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {


}
