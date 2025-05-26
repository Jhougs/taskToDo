package com.juanviana.app.todoapp.juanviana_todoapp.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.juanviana.app.todoapp.juanviana_todoapp.model.Task;

@Component
public class TaskValidation implements Validator {

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
        errors.rejectValue("description", "NotBlank.task.description", "La descripción no puede ser nula o vacía");
    }

    // Verifica si title fue enviado y no está vacío o solo espacios
    if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
        errors.rejectValue("title", "NotBlank.task.title", "El título no puede ser nulo o vacío");
    } 
    }

}
