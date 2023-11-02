package com.principal.todoservice.controllers;

import com.principal.todoservice.models.RequestUpdatedTask;
import com.principal.todoservice.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import com.principal.todoservice.models.RequestTask;
import com.principal.todoservice.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // listar todas as tasks
    @GetMapping
    public ResponseEntity getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    // endpoint para adicionar uma nova tarefa
    @PostMapping("/add")
    public ResponseEntity addTask(@Valid @RequestBody RequestTask task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + getErrorsMessage(bindingResult));
        }
        try {
            Task newTask = new Task(task);
            taskService.addTask(newTask);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task adicionada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao adicionar a tarefa: " + e.getMessage());
        }
    }

    // Endpoint para editar uma tarefa com um taskId específico
    @PutMapping("/edit/{taskId}")
    public ResponseEntity editTask(@PathVariable("taskId") int taskId, @Valid @RequestBody RequestUpdatedTask updatedTask, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + getErrorsMessage(bindingResult));
        }
        try {
            boolean editedTask = taskService.editTask(taskId, updatedTask);
            return ResponseEntity.status(HttpStatus.OK).body("Task atualizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao editar a tarefa: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") int taskId) {
        try {
            boolean deletedTask = taskService.deleteTask(taskId);
            if (!deletedTask) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma task encontrada com o ID fornecido");
            }
            return ResponseEntity.status(HttpStatus.OK).body("Task removida com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao excluir a tarefa: " + e.getMessage());
        }
    }

    private String getErrorsMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append(". ");
        }
        return errorMessage.toString();
    }
}