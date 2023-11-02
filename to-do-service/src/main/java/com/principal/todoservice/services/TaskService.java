package com.principal.todoservice.services;

import com.principal.todoservice.models.RequestUpdatedTask;
import com.principal.todoservice.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> taskList;

    public TaskService() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public boolean addTask(Task task) throws Exception {
        if (!isValidFormat(task.getDueDate())) {
            throw new IllegalArgumentException("Formato inválido de data. O formato deve ser yyyy-MM-dd.");
        }
        taskList.add(task);
        return true;
    }

    public boolean editTask(int taskId, RequestUpdatedTask updatedTask) throws Exception {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                if (updatedTask.getDescription() == null) {
                    throw new IllegalArgumentException("Descrição não pode ser nula.");
                }
                if (updatedTask.getDueDate() == null) {
                    throw new IllegalArgumentException("Data de vencimento não pode ser nula.");
                }
                if (!isValidFormat(updatedTask.getDueDate())) {
                    throw new IllegalArgumentException("Formato inválido de data. O formato deve ser yyyy-MM-dd.");
                }
                if (updatedTask.isCompleted() == null) {
                    return true;
                } else if (updatedTask.isCompleted().equalsIgnoreCase("false")) {
                    task.setCompleted(false);
                    return true;
                } else if (updatedTask.isCompleted().equalsIgnoreCase("true")) {
                    task.setCompleted(true);
                    return true;
                } else {
                    throw new IllegalArgumentException("Valor inválido para isCompleted. Deve ser 'true' ou 'false'.");
                }
            }
        }
        throw new IllegalArgumentException("Nenhuma tarefa encontrada com o ID fornecido.");
    }

    private boolean isValidFormat(String inputDate) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        return inputDate.matches(regex);
    }

    public boolean deleteTask(int taskId) throws Exception {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                taskList.remove(task);
                return true;
            }
        }
        throw new Exception("Nenhuma tarefa encontrada com o ID fornecido.");
    }
}