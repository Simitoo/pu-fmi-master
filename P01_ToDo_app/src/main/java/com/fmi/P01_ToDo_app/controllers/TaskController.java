package com.fmi.P01_ToDo_app.controllers;

import com.fmi.P01_ToDo_app.constants.MessageConstants;
import com.fmi.P01_ToDo_app.models.Task;
import com.fmi.P01_ToDo_app.services.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @Value("${user.example}")
    private String userName;

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return this.taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable int id){
        return this.taskService.getTaskById(id);
    }

    @PostMapping("/tasks")
    public String createNewTask(@RequestBody Task task) {
        this.taskService.addTask(task);
        return MessageConstants.TASK_CREATED;
    }

    @GetMapping("/tasks/filter")
    public List<Task> filterTasks(@RequestParam String status){
        return this.taskService.filterTasksByStatus(status);
    }

    @GetMapping("/tasks/sortBy")
    public List<Task> sortTasks(){
        return  this.taskService.sortingTasksByDueDate();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<String> updateTask(@PathVariable int id, @RequestBody Task task){
        boolean isUpdated = this.taskService.updateTask(id, task);
        return isUpdated
                ? ResponseEntity.ok(MessageConstants.TASK_UPDATED)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageConstants.TASK_NOT_FOUND);

    }

    @DeleteMapping("/tasks")
    public ResponseEntity<String> deleteTask(@RequestParam int id){
        boolean isDeleted = this.taskService.deleteTask(id);
        return isDeleted
                ? ResponseEntity.ok(MessageConstants.TASK_DELETED)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageConstants.TASK_NOT_FOUND);
    }
}
