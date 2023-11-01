package com.principal.todoservice.service;

import com.principal.todoservice.models.RequestTask;
import com.principal.todoservice.models.RequestUpdatedTask;
import com.principal.todoservice.models.Task;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    @Test
    public void testGetAllTasks() {
        TaskService taskService = new TaskService();
        taskService.addTask(new Task(new RequestTask("Task 1", "2023-11-01")));
        taskService.addTask(new Task(new RequestTask("Task 2", "2023-11-02")));

        assertEquals(2, taskService.getAllTasks().size());
    }


    @Test
    public void testAddTask() {
        TaskService taskService = new TaskService();
        List<Task> taskList = taskService.getAllTasks();

        Task newTask = new Task(new RequestTask("Nova Tarefa", "2023-11-03"));
        taskService.addTask(newTask);

        assertEquals(1, taskList.size());
    }

    @Test
    public void testEditTask() {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Tarefa Antiga", "2023-11-04"));
        taskService.addTask(task);

        RequestUpdatedTask updatedTask = new RequestUpdatedTask("Tarefa Atualizada", "2023-11-05", true);
        boolean isEdited = taskService.editTask(1, updatedTask);

        assertTrue(isEdited);
    }

    @Test
    public void testEditTask_TaskNotFound() {
        TaskService taskService = new TaskService();
        RequestUpdatedTask updatedTask = new RequestUpdatedTask("Tarefa Atualizada", "2023-11-05", true);

        boolean isEdited = taskService.editTask(1, updatedTask);

        assertFalse(isEdited);
    }

    // TESTAR VALOR NÃO BOOLEAN
//    @Test
//    public void testEditTask_IsCompletedInvalid() {
//        TaskService taskService = new TaskService();
//        Task task = new Task(new RequestTask("Tarefa Antiga", "2023-11-04"));
//        taskService.addTask(task);
//
//        RequestUpdatedTask updatedTask = new RequestUpdatedTask("Tarefa Atualizada", "2023-11-05", "invalid");
//        boolean isEdited = taskService.editTask(task.getId(), updatedTask);
//
//        assertFalse(isEdited);
//    }

    @Test
    public void testEditTask_FieldsNotNull() {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Tarefa Antiga", "2023-11-04"));
        taskService.addTask(task);

        RequestUpdatedTask updatedTask = new RequestUpdatedTask(null, "2023-11-05", true);
        boolean isEdited = taskService.editTask(task.getId(), updatedTask);

        assertTrue(isEdited);
    }

    @Test
    public void testDeleteTask_TaskExists() {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Task 1", "2023-11-01"));
        taskService.addTask(task);

        boolean isDeleted = taskService.deleteTask(task.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteTask_TaskDoesNotExist() {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Task 1", "2023-11-01"));
        taskService.addTask(task);

        boolean isDeleted = taskService.deleteTask(2);
        assertFalse(isDeleted);
    }

    @Test
    public void testDeleteTask_ListIsEmpty() {
        TaskService taskService = new TaskService();

        boolean isDeleted = taskService.deleteTask(1);
        assertFalse(isDeleted);
    }
}

