package com.principal.todoservice.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Task {

    private static int countId = 0;
    private int id;

    @NotNull(message = "description is mandatory")
    @NotBlank(message = "description is mandatory")
    private String description;

    @NotNull(message = "dueDate is mandatory")
    @NotBlank(message = "dueDate is mandatory")
    private String dueDate;

    private boolean isCompleted;

    public Task(RequestTask requestTask){
        countId++;
        this.id = countId;
        this.description = requestTask.description();
        this.dueDate = requestTask.dueDate();
        this.isCompleted = false;
    }

    // Métodos getters e setters para os atributos de task
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
