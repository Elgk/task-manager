package ru.skillbox.task.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.task.manager.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}
