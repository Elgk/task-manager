package ru.skillbox.task.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.task.manager.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
