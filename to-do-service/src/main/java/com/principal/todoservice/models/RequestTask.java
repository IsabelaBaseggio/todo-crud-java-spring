package com.principal.todoservice.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestTask(@NotNull(message = "Description deve ser informado") @NotBlank(message = "Description deve ser informado") String description, @NotNull(message = "DueDate deve ser informado") @NotBlank(message = "DueDate deve ser informado") String dueDate) {
}
