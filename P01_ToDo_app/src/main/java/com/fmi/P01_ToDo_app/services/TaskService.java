package com.fmi.P01_ToDo_app.services;

import com.fmi.P01_ToDo_app.models.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private List<Task> taskCollection = new ArrayList<>();
    private SequanceGenerator sequanceGenerator;

    public  TaskService(SequanceGenerator sequanceGenerator){
        this.sequanceGenerator = sequanceGenerator;
    }

    public Task getTaskById(int id){
        for (Task task: taskCollection){
            if(task.getId() == id){
                return task;
            }
        }

        return null;
    }

    public List<Task> getAllTasks(){
        return this.taskCollection;
    }

    public Task addTask(Task taskToAdd){
        taskToAdd.setId(this.sequanceGenerator.getNextId());
        this.taskCollection.add(taskToAdd);

        return taskToAdd;
    }

    public boolean updateTask(int id,Task newTask){
        Task currTask = getTaskById(id);
        if(currTask == null){
            return false;
        }

        currTask.setTitle(newTask.getTitle());
        currTask.setDescription(newTask.getDescription());
        currTask.setStatus(newTask.getStatus());
        currTask.setDueDate(newTask.getDueDate());

        this.taskCollection.set(id,currTask);
        return true;
    }

    public boolean deleteTask(int id){
        for(int i = 0; i < this.taskCollection.size(); i++){
            Task currTask = taskCollection.get(i);

            if(currTask.getId() == id){
                taskCollection.set(i,null);
                return true;
            }
        }

        return false;
    }

    public List<Task> filterTasksByStatus(String status){
        List<Task> filteredTask = this.taskCollection.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());

        return filteredTask;
    }

    public List<Task> sortingTasksByDueDate(){
        List sortedTasks = this.taskCollection.stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .toList();

        return sortedTasks;
    }
}
