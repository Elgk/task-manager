package ru.skillbox.task.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.task.manager.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
