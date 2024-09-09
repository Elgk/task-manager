package ru.skillbox.task.manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.skillbox.task.manager.entities.RoleEnum;

public record UserCreateDto(
    @NotBlank
    String username,
    @NotBlank
    String password,
    @NotBlank
    @Email
    String email
) {
}
