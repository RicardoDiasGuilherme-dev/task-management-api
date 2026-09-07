package dev.ricardo.taskmanager.repository;

import dev.ricardo.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
