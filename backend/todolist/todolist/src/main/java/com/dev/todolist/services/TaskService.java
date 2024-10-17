package com.dev.todolist.services;

import com.dev.todolist.domain.entities.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    TaskEntity saveTask(TaskEntity taskEntity);

    List<TaskEntity> findAll();

     List<TaskEntity> findAllbyUserId(String userId);

    Optional<TaskEntity> findOne(Long id);

    boolean isExists(Long id);

    TaskEntity partialUpdate(Long id, TaskEntity taskEntity);

    void delete(Long id);
}
