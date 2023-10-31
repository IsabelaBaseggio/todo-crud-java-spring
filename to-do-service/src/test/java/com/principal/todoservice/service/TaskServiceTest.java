package com.principal.todoservice.service;

import com.principal.todoservice.models.RequestTask;
import com.principal.todoservice.models.RequestUpdatedTask;
import com.principal.todoservice.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private List<Task> mockTaskList;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskService();
    }

    @Test
    public void testGetAllTasks() {
        List<Task> expectedTasks = new ArrayList<>();
        when(mockTaskList).thenReturn(expectedTasks);
        List<Task> actualTasks = taskService.getAllTasks();
        assertEquals(expectedTasks, actualTasks);
    }

    @Test
    public void testAddTask() {
        Task task = new Task(new RequestTask("Test Description", "2023-11-01"));
        taskService.addTask(task);
        verify(mockTaskList, times(1)).add(task);
    }

    @Test
    public void testEditTask() {
        RequestTask requestTask = new RequestTask("Test Description", "2023-11-01");
        Task task = new Task(requestTask);
        when(mockTaskList.contains(any(Task.class))).thenReturn(true);
        when(mockTaskList.get(0)).thenReturn(task);
        boolean edited = taskService.editTask(1, new RequestUpdatedTask("Updated Description", "2023-11-02", true));
        assertTrue(edited);
        assertEquals("Updated Description", task.getDescription());
        assertEquals("2023-11-02", task.getDueDate());
        assertTrue(task.isCompleted());
    }

    @Test
    public void testEditTaskNotFound() {
        when(mockTaskList.contains(any(Task.class))).thenReturn(false);
        boolean edited = taskService.editTask(1, new RequestUpdatedTask("Updated Description", "2023-11-02", true));
        assertFalse(edited);
    }

    @Test
    public void testDeleteTask() {
        RequestTask requestTask = new RequestTask("Test Description", "2023-11-01");
        Task task = new Task(requestTask);
        when(mockTaskList.contains(any(Task.class))).thenReturn(true);
        when(mockTaskList.get(0)).thenReturn(task);
        boolean deleted = taskService.deleteTask(1);
        assertTrue(deleted);
        verify(mockTaskList, times(1)).remove(task);
    }

    @Test
    public void testDeleteTaskNotFound() {
        when(mockTaskList.contains(any(Task.class))).thenReturn(false);
        boolean deleted = taskService.deleteTask(1);
        assertFalse(deleted);
    }
}

