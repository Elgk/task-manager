package ru.skillbox.task.manager.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.task.manager.dto.TaskCreateDto;
import ru.skillbox.task.manager.dto.TaskDto;
import ru.skillbox.task.manager.services.TaskService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@Validated
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Получить список всех задач")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Выполнено успешно",
                    content = {@Content(schema = @Schema(implementation = TaskDto.class))}),
    })
    public List<TaskDto> findAll(){
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Запрос на получение задачи по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
            @ApiResponse(responseCode = "401", description = "не авторизованный пользователь"),
            @ApiResponse(responseCode = "400", description = "не корректный завпрос"),
            @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена")
    })
    public TaskDto findById(@PathVariable Long id){
        return taskService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать задачу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Задача создана успешно"),
            @ApiResponse(responseCode = "401", description = "не авторизованный пользователь")
    })
    public TaskDto createNew(@RequestBody @Valid TaskCreateDto task, Principal principal){
        return  taskService.create(task, principal.getName());
    }
}
