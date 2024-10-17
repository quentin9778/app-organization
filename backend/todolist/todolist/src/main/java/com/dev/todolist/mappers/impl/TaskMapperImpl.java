package com.dev.todolist.mappers.impl;

import com.dev.todolist.domain.dto.TaskDto;
import com.dev.todolist.domain.entities.TaskEntity;
import com.dev.todolist.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements Mapper<TaskEntity, TaskDto> {
    private ModelMapper modelMapper;

    public TaskMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public TaskDto mapTo(TaskEntity taskEntity) {
        return modelMapper.map(taskEntity,TaskDto.class);
    }

    @Override
    public TaskEntity mapFrom(TaskDto taskDto) {
        return modelMapper.map(taskDto, TaskEntity.class);
    }
}
