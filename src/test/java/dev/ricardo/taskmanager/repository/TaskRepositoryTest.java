package dev.ricardo.taskmanager.repository;

import dev.ricardo.taskmanager.model.Task;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldSaveAndFindTaskById() {
        Task task = new Task("Study Spring Data JPA", "Implement repository layer");

        Task savedTask = taskRepository.save(task);

        entityManager.flush();
        entityManager.clear();

        Task retrievedTask = taskRepository.findById(savedTask.getId()).orElseThrow();

        assertNotNull(savedTask.getId());
        assertEquals("Study Spring Data JPA", retrievedTask.getTitle());
        assertEquals("Implement repository layer", retrievedTask.getDescription());
        assertFalse(retrievedTask.isCompleted());
    }
}
