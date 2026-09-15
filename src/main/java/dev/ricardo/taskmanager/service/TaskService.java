package dev.ricardo.taskmanager.service;

import dev.ricardo.taskmanager.exception.TaskNotFoundException;
import dev.ricardo.taskmanager.model.Task;
import dev.ricardo.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }
}