package com.taks.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taks.controller.dtos.TaskDTO;
import com.taks.controller.mapper.TaskMapper;
import com.taks.entity.Task;
import com.taks.service.TaskService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/task")
@Api(tags = "Task Controller")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll() {
        List<Task> taskList = taskService.findAll();
        List<TaskDTO> result = taskMapper.toTaskDTOList(taskList);
        return ResponseEntity.ok(result);

    }
}