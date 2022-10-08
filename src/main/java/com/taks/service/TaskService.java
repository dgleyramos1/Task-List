package com.taks.service;

import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.taks.entity.Task;
import com.taks.exception.ParkingNotFoundException;
import com.taks.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new ParkingNotFoundException(id));
    }

    @Transactional
    public Task create(Task taskCreate) {
        return taskRepository.save(taskCreate);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        taskRepository.deleteById(id);
    }

    @Transactional
    public Task update(Long id, Task taskCreate) {
        Task task = findById(id);
        task.setId(id);
        task.setDescription(taskCreate.getDescription());
        task.setTask(taskCreate.getTask());
        taskRepository.save(task);
        return task;
    }

}