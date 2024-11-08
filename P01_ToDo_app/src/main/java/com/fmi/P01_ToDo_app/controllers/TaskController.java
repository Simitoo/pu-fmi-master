package com.fmi.P01_ToDo_app.controllers;

import com.fmi.P01_ToDo_app.models.Task;
import com.fmi.P01_ToDo_app.services.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ArrayList<Task> getTask(){
        return this.taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable int id){
        return this.taskService.getTask(id);
    }

    @PostMapping("/tasks")
    public String createNewTask(@RequestBody Task task) {
        this.taskService.addTask(task);
        return "Task has been created";
    }



    @GetMapping("/tasks/filter")
    public List<Task> filterTask(@RequestParam String status){
        return this.taskService.filterTasks(status);
    }

    @DeleteMapping("/tasks")
    public String deleteTask(@RequestParam int id){
        this.taskService.deleteTask(id);

        return "Task has been Deleted!";
    }
}
