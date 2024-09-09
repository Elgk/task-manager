package ru.skillbox.task.manager.dto;

import ru.skillbox.task.manager.entities.User;

import java.util.List;

public record TaskDto(
        Long id,
        String title,
        String description,
        String status,
        String priotity,
        User owner,
        List<User> executerList
) {
}
