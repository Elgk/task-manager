package ru.skillbox.task.manager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.skillbox.task.manager.entities.TaskPriority;
import ru.skillbox.task.manager.entities.TaskStatus;

@Schema(description = "Модель Задача")
public record TaskCreateDto(
        @Schema(description = "наименование", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Size(max = 100, message = "Max title size is 100 characters")
        String title,
        @Schema(description = "описание", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        @Size(max = 500, message = "Max description size is 500 characters")
        String decription,
        @Schema(description = "статус", allowableValues = {"WAITING", "INPROGRESS", "COMPLETED"}, requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "task status cannot be empty")
        TaskStatus status,
        @Schema(description = "приоритет", allowableValues = {"LOW", "MEDIUM", "HIGH"}, requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "task priority cannot be empty")
        TaskPriority priority
) {
}
