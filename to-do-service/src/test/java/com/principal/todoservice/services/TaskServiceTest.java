package com.principal.todoservice.services;

import com.principal.todoservice.models.RequestTask;
import com.principal.todoservice.models.RequestUpdatedTask;
import com.principal.todoservice.models.Task;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    @Test
    public void testGetAllTasks() throws Exception {
        TaskService taskService = new TaskService();
        taskService.addTask(new Task(new RequestTask("Tarefa 1", "2023-11-01")));
        taskService.addTask(new Task(new RequestTask("Tarefa 2", "2023-11-01")));

        assertEquals(2, taskService.getAllTasks().size());
    }


    @Test
    public void testAddTask() throws Exception {
        TaskService taskService = new TaskService();
        List<Task> taskList = taskService.getAllTasks();

        Task newTask = new Task(new RequestTask("Nova Tarefa", "2023-11-01"));
        taskService.addTask(newTask);

        assertEquals(1, taskList.size());
    }

    @Test
    public void testAddTask_InvalidDueDate() {
        TaskService taskService = new TaskService();
        RequestTask requestTask = new RequestTask("Nova Tarefa", "2023/11/01");
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask(new Task(requestTask)));
    }

    @Test
    public void testEditTask() throws Exception {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Tarefa Antiga", "2023-11-01"));
        taskService.addTask(task);

        RequestUpdatedTask updatedTask = new RequestUpdatedTask("Tarefa Atualizada", "2023-11-01", "true");
        boolean isEdited = taskService.editTask(task.getId(), updatedTask);

        assertTrue(isEdited);
    }

    @Test
    public void testEditTask_InvalidDueDate() throws Exception {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Tarefa Antiga", "2023-11-01"));
        taskService.addTask(task);

        RequestUpdatedTask updatedTask = new RequestUpdatedTask("Tarefa Atualizada", "2023/11/01", "true");

        assertThrows(IllegalArgumentException.class, () -> taskService.editTask(task.getId(), updatedTask));
    }

    @Test
    public void testEditTask_TaskNotFound() {
        TaskService taskService = new TaskService();
        RequestUpdatedTask updatedTask = new RequestUpdatedTask("Tarefa Atualizada", "2023-11-01", "true");

        assertThrows(IllegalArgumentException.class, () -> taskService.editTask(1, updatedTask));
    }

    @Test
    public void testEditTask_IsCompletedInvalid() throws Exception {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Tarefa Antiga", "2023-11-01"));
        taskService.addTask(task);

        RequestUpdatedTask updatedTask = new RequestUpdatedTask("Tarefa Atualizada", "2023-11-01", "invalid");
        assertThrows(IllegalArgumentException.class, () -> taskService.editTask(task.getId(), updatedTask));
    }

    @Test
    public void testEditTask_FieldsNotNull() throws Exception {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Tarefa Antiga", "2023-11-01"));
        taskService.addTask(task);

        RequestUpdatedTask updatedTask = new RequestUpdatedTask(null, "2023-11-01", "true");
        assertThrows(IllegalArgumentException.class, () -> taskService.editTask(task.getId(), updatedTask));
    }

    @Test
    public void testDeleteTask() throws Exception {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Tarefa 1", "2023-11-01"));
        taskService.addTask(task);

        assertTrue(taskService.deleteTask(task.getId()));
    }

    @Test
    public void testDeleteTask_TaskDoesNotExist() throws Exception {
        TaskService taskService = new TaskService();
        Task task = new Task(new RequestTask("Task 1", "2023-11-01"));
        taskService.addTask(task);

        assertThrows(Exception.class, () -> taskService.deleteTask(2));
    }

    @Test
    public void testDeleteTask_ListIsEmpty() {
        TaskService taskService = new TaskService();

        assertThrows(Exception.class, () -> taskService.deleteTask(1));
    }

}
