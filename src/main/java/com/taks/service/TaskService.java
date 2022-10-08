package com.taks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taks.entity.Task;
import com.taks.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task create(Task taskCreate) {
        return taskRepository.save(taskCreate);
    }

}