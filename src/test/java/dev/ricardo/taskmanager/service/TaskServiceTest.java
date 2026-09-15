package dev.ricardo.taskmanager.service;

import dev.ricardo.taskmanager.exception.TaskNotFoundException;
import dev.ricardo.taskmanager.model.Task;
import dev.ricardo.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnTaskWhenTaskExists() {
        Task task = new Task("Study Spring Boot", "Learn service layer");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertSame(task, result);

        verify(taskRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist() {

        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(999L));

        verify(taskRepository).findById(999L);
    }

    @Test
    void shouldCreateTask() {
        Task task = new Task("Finish Portfolio", "Complete Backend Project");

        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertSame(task, result);

        verify(taskRepository).save(task);
    }

    @Test
    void shouldReturnAllTasks() {
        Task firstTask = new Task("Study Java", "Review Java Concepts");

        Task secondTask = new Task("Study Spring", "Learn Spring Boot");

        List<Task> tasks = List.of(firstTask, secondTask);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        assertSame(firstTask, result.get(0));
        assertSame(secondTask, result.get(1));

        verify(taskRepository).findAll();
    }

    @Test
    void shouldUpdateTaskWhenTaskExists() {
        Task existingTask = new Task("Old Title", "Old Description");

        Task updatedTask = new Task("New Title", "New Description");

        updatedTask.setCompleted(true);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));

        when(taskRepository.save(existingTask)).thenReturn(existingTask);

        Task result = taskService.updateTask(1L, updatedTask);

        assertSame(existingTask, result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Description", result.getDescription());
        assertTrue(result.isCompleted());

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(existingTask);
    }

    @Test
    void shouldThrowExceptionWhenUpdateTaskDoesNotExist() {
        Task updatedTask = new Task("New Title", "New Description");

        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(999L, updatedTask));

        verify(taskRepository).findById(999L);
        verify(taskRepository, never()).save(any());
    }

    @Test
    void shouldDeleteTaskWhenTaskExists() {
        Task task = new Task("Delete Me", "Task To Be Deleted");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskService.deleteTask(1L);

        verify(taskRepository).findById(1L);
        verify(taskRepository).delete(task);
    }

    @Test
    void shouldThrowExceptionWhenDeletingTaskDoesNotExist() {
        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(999L));

        verify(taskRepository).findById(999L);
        verify(taskRepository, never()).delete(any());
    }
}
