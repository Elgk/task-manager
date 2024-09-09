package ru.skillbox.task.manager.dto;

public record UserDto(
        Long id,
        String username,
        String password,
        String email
) {
}
