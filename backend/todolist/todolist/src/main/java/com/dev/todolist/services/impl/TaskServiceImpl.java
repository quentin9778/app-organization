package com.dev.todolist.services.impl;

import com.dev.todolist.domain.entities.TaskEntity;
import com.dev.todolist.repositories.TaskRepository;
import com.dev.todolist.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    @Override
    public TaskEntity saveTask(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskEntity> findAll() {
        return (List<TaskEntity>) taskRepository.findAll();
    }
    public List<TaskEntity> findAllbyUserId(String userId) {
        return taskRepository.findByUserId(userId); // Récupérer les tâches par userId
    }


    @Override
    public Optional<TaskEntity> findOne(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return taskRepository.existsById(id);
    }

    @Override
    public TaskEntity partialUpdate(Long id, TaskEntity taskEntity) {
        taskEntity.setId(id);
        return taskRepository.findById(id).map(existingTask -> {
            Optional.ofNullable(taskEntity.getName()).ifPresent(existingTask::setName);
            Optional.ofNullable(taskEntity.getStatus()).ifPresent(existingTask::setStatus);
            Optional.ofNullable(taskEntity.getTag()).ifPresent(existingTask::setTag);
            return taskRepository.save(existingTask);
        }).orElseThrow(()->new RuntimeException("Task does not exist"));
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
