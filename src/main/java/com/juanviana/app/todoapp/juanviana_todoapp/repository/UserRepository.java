package com.juanviana.app.todoapp.juanviana_todoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.juanviana.app.todoapp.juanviana_todoapp.model.User;

public interface  UserRepository extends CrudRepository<User, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, puedes agregar un método para encontrar un usuario por su nombre de usuario o correo electrónico

}
