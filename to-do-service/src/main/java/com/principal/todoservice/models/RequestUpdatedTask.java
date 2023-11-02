package com.principal.todoservice.models;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestUpdatedTask(@NotNull(message = "Description deve ser informado") @NotBlank(message = "Description deve ser informado") String description, @NotNull(message = "DueDate deve ser informado") @NotBlank(message = "DueDate deve ser informado") String dueDate, String isCompleted) {
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
