package com.principal.todoservice.service;

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

    public void addTask(Task task) {
        taskList.add(task);
    }

    public boolean editTask(int taskId, RequestUpdatedTask updatedTask) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                if (updatedTask.getDescription() != null) {
                    task.setDescription(updatedTask.getDescription());
                }
                if (updatedTask.getDueDate() != null) {
                    task.setDueDate(updatedTask.getDueDate());
                }
                if (updatedTask.isCompleted() || !updatedTask.isCompleted()) {
                    task.setCompleted(updatedTask.isCompleted());
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean deleteTask(int taskId) {
        for(Task task : taskList){
            if (task.getId() == taskId){
                taskList.remove(task);
                return true;
            }
        }
        return false;
    }
}
