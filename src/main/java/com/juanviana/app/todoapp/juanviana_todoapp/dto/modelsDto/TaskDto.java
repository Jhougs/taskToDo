package com.juanviana.app.todoapp.juanviana_todoapp.dto.modelsDto;



public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private Long userId; // Solo incluir el ID del usuario

    
    public TaskDto(){ }
    public TaskDto(Long id, String title, String description, Boolean completed, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
    }
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean isCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    
    

}
