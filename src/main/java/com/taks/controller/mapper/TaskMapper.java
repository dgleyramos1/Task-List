package com.taks.controller.mapper;

import org.springframework.stereotype.Component;

import com.taks.controller.dtos.TaskDTO;
import com.taks.entity.Task;

import org.modelmapper.ModelMapper;

@Component
public class TaskMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public TaskDTO toTaskDTO(Task task) {
        return MODEL_MAPPER.map(task, TaskDTO.class);
    }
}