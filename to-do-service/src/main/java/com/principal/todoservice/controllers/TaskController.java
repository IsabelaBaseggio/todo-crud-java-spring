package com.principal.todoservice.controllers;

import com.principal.todoservice.models.RequestUpdatedTask;
import com.principal.todoservice.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import com.principal.todoservice.models.RequestTask;
import com.principal.todoservice.models.Task;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addTask(@RequestBody @Valid RequestTask task) {
        Task newTask = new Task(task);
        taskService.addTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task adicionada com sucesso!");
    }

    // Endpoint para editar uma tarefa com um taskId específico
    @PutMapping("/edit/{taskId}")
    public ResponseEntity editTask(@PathVariable("taskId") int taskId, @RequestBody @Valid RequestUpdatedTask updatedTask) {
        boolean editedTask = taskService.editTask(taskId, updatedTask);
        if (!editedTask) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma task encontrada com o ID fornecido");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Task atualizada com sucesso!");
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") int taskId){
        boolean deletedTask = taskService.deleteTask(taskId);
        if (!deletedTask){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma task encontrada com o ID fornecido");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Task removida com sucesso!");
    }
}
