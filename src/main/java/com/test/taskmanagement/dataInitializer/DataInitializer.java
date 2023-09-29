package com.test.taskmanagement.dataInitializer;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.test.taskmanagement.entity.Task;
import com.test.taskmanagement.repository.TaskRepository;

@Component
public class DataInitializer implements CommandLineRunner{

    @Autowired
    private TaskRepository taskRepository;
    
    @Override
    public void run(String... args) throws Exception {

        for (int i = 1; i <= 10; i++) {
            Task task = new Task();
            task.setTaskId(UUID.randomUUID());
            task.setTitle("Tarea " + i);
            task.setDescription("Descripción de la Tarea " + i);
            task.setExpirationDate(new Date());
            task.setStatus("Pendiente");
        
            taskRepository.save(task);
        }
    }
    
}
