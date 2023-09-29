package com.test.taskmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.test.taskmanagement.entity.Task;
import com.test.taskmanagement.exception.CustomHttpExeception;
import com.test.taskmanagement.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService {
    @Autowired
    TaskRepository tasksRepository;

    public List<Task> getTasks() {
        log.info("Start, getTasks");
        List<Task> tasks = tasksRepository.findAll();
        log.info("OK, getTasks");
        return tasks;
    }

    public Optional<Task> getTask(UUID id) {
        log.info("Start, getTask");
        Optional<Task> task = tasksRepository.findById(id);
        if (task.isEmpty()) {
            String error = String.format("Task not found [%s]", id);
            log.error(error);
            throw new CustomHttpExeception(HttpStatus.NOT_FOUND, error);
        }
        log.info("OK, getTask");
        return task;
    }

    public List<Task> createTasks(List<Task> tasks) {
        log.info("Start, createTasks");
        List<Task> response = tasksRepository.saveAll(tasks);
        log.info("OK, createTasks");
        return response;
    }

    public List<Task> updateTasks(List<Task> updatedTasks) {
        log.info("Start, updateTasks");
        List<Task> updatedTasksList = new ArrayList<>();

        for (Task updatedTask : updatedTasks) {

            if (updatedTask.getTaskId() == null) {
                String error = "Task cannot be null";
                log.error(error);
                throw new CustomHttpExeception(HttpStatus.BAD_REQUEST, error);
            }

            Optional<Task> existingTaskOptional = tasksRepository.findById(updatedTask.getTaskId());
            if (existingTaskOptional.isPresent()) {
                Task existingTask = existingTaskOptional.get();
                existingTask.setTitle(updatedTask.getTitle());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setExpirationDate(updatedTask.getExpirationDate());
                existingTask.setStatus(updatedTask.getStatus());

                updatedTasksList.add(existingTask);
            } else {
                String error = String.format("Task not found [%s]", updatedTask.getTaskId());
                log.error(error);
                throw new CustomHttpExeception(HttpStatus.NOT_FOUND, error);
            }
        }
        List<Task> response = tasksRepository.saveAll(updatedTasksList);
        log.info("OK, updateTasks");
        return response;
    }

    public void deleteTask(UUID id) {
        log.info("Start, deleteTask");
        Optional<Task> task = tasksRepository.findById(id);
        if (task.isEmpty()) {
            String error = String.format("Task not found [%s]", id);
            log.error(error);
            throw new CustomHttpExeception(HttpStatus.NOT_FOUND, error);
        }
        tasksRepository.deleteById(id);
        log.info("OK, deleteTask");
    }
}
