package com.ricardodiasguilherme.taskmanagement.model;

import model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {
    @Test
    public void shouldCreateTaskWithTitleAndDescription() {
        Task task = new Task("Study Java", "Learn Spring Boot");

        assertEquals("Study Java", task.getTitle());
        assertEquals("Learn Spring Boot", task.getDescription());
        assertFalse(task.isCompleted());
        assertNull(task.getId());
    }

    @Test
    public void shouldUpdateTaskFields() {
        Task task = new Task("Study Java", "Learn Spring Boot");

        task.setTitle("Study Spring Boot");
        task.setDescription("Learn JPA");
        task.setCompleted(true);

        assertEquals("Study Spring Boot", task.getTitle());
        assertEquals("Learn JPA", task.getDescription());
        assertTrue(task.isCompleted());
    }
}
