package com.taks.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taks.controller.dtos.TaskCreateDTO;
import com.taks.controller.dtos.TaskDTO;
import com.taks.controller.mapper.TaskMapper;
import com.taks.entity.Task;
import com.taks.service.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
    @ApiOperation("Find all Tasks")
    public ResponseEntity<List<TaskDTO>> getAll() {
        List<Task> taskList = taskService.findAll();
        List<TaskDTO> result = taskMapper.toTaskDTOList(taskList);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ApiOperation("Create new Task")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskCreateDTO dto) {
        Task taskCreate = taskMapper.toTaskCreate(dto);
        Task task = taskService.create(taskCreate);
        TaskDTO result = taskMapper.toTaskDTO(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete task")
    public ResponseEntity<TaskDTO> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update task")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody TaskCreateDTO dto) {
        Task taskCreate = taskMapper.toTaskCreate(dto);
        Task task = taskService.update(id, taskCreate);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
}