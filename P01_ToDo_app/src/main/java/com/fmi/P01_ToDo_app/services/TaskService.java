package com.fmi.P01_ToDo_app.services;

import com.fmi.P01_ToDo_app.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private ArrayList<Task> taskCollection = new ArrayList<>();
    private SequanceGenerator sequanceGenerator;

    public  TaskService(SequanceGenerator sequanceGenerator){
        this.sequanceGenerator = sequanceGenerator;
    }

    public Task getTask(int id){
        for (Task task: taskCollection){
            if(task.getId() == id){
                return task;
            }
        }

        return null;
    }

    public ArrayList<Task> getAllTasks(){
        return this.taskCollection;
    }

    public Task addTask(Task taskToAdd){
        taskToAdd.setId(this.sequanceGenerator.getNextId());
        this.taskCollection.add(taskToAdd);

        return taskToAdd;
    }

    public void updateTask(Task newTask){
        for(Task currTask : taskCollection){
            if(currTask.getId() == newTask.getId()){
                taskCollection.set(currTask.getId(), newTask);
            }
        }
    }

    public void deleteTask(int id){
        for(int i = 0; i < this.taskCollection.size(); i++){
            Task currTask = taskCollection.get(i);

            if(currTask.getId() == id){
                taskCollection.set(i,null);
            }
        }
    }

    public List<Task> filterTasks(String status){
        List<Task> filteredTask = taskCollection.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());

        return filteredTask;
    }

}
