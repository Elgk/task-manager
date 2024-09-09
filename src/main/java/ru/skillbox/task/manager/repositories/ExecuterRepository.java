package ru.skillbox.task.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.task.manager.entities.Executer;

import java.util.List;

public interface ExecuterRepository extends JpaRepository<Executer, Long> {
    List<Executer> findAllByTaskId(Long taskId);
}
