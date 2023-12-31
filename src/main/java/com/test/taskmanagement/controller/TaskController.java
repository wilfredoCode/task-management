package com.test.taskmanagement.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.test.taskmanagement.entity.Task;
import com.test.taskmanagement.service.TaskService;

@RestController
@RequestMapping(path = "api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping
    public List<Task> getAll(){
        return taskService.getTasks();
    }
    @GetMapping("/{taskId}")
    public Optional<Task> getById(@PathVariable("taskId") UUID taskId){
        return taskService.getTask(taskId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public List<Task> createTasks(@RequestBody List<Task> task){
       return taskService.createTasks(task);
    }
    @PutMapping
    public List<Task> updateTasks(@RequestBody List<Task> task){
       return taskService.updateTasks(task);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskId") UUID taskId){
       taskService.deleteTask(taskId);
    }
}
