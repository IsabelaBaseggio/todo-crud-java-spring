package com.principal.todoservice.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestTask(@NotNull @NotBlank String description, @NotNull @NotBlank String dueDate) {
}
