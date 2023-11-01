package com.principal.todoservice.models;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestUpdatedTask(@NotNull @NotBlank String description, @NotNull @NotBlank String dueDate, String isCompleted) {
    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String isCompleted() {
        return isCompleted;
    }
}
