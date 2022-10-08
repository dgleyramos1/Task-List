package com.taks.controller.mapper;

import org.springframework.stereotype.Component;

import com.taks.controller.dtos.TaskCreateDTO;
import com.taks.controller.dtos.TaskDTO;
import com.taks.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Component
public class TaskMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public TaskDTO toTaskDTO(Task task) {
        return MODEL_MAPPER.map(task, TaskDTO.class);
    }

    public List<TaskDTO> toTaskDTOList(List<Task> taskList) {
        return taskList.stream().map(this::toTaskDTO).collect(Collectors.toList());
    }

    public Task toTaskCreate(TaskCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Task.class);
    }
}