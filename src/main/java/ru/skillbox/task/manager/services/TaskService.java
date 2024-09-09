package ru.skillbox.task.manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.task.manager.dto.TaskCreateDto;
import ru.skillbox.task.manager.dto.TaskDto;
import ru.skillbox.task.manager.entities.Executer;
import ru.skillbox.task.manager.entities.Task;
import ru.skillbox.task.manager.entities.User;
import ru.skillbox.task.manager.exceptions.ContentNotFoundException;
import ru.skillbox.task.manager.repositories.ExecuterRepository;
import ru.skillbox.task.manager.repositories.TaskRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ExecuterRepository executerRepository;

    public List<TaskDto> findAll(){
        return taskRepository.findAll().stream().map(this::convertEntityToDto).toList();
    }

    public TaskDto findById(Long id){
        return convertEntityToDto(taskRepository.findById(id).orElseThrow(() ->
                new ContentNotFoundException(String.format("Task with id: %d not exists",id))));
    }

    public TaskDto create(TaskCreateDto taskCreateDto, String username){
        User user = userService.findByUsername(username);
        Task newTask = Task.builder()
                .title(taskCreateDto.title())
                .decription(taskCreateDto.decription())
                .priority(taskCreateDto.priority())
                .status(taskCreateDto.status())
                .owner(user)
                .build();
        return convertEntityToDto(taskRepository.save(newTask));
    }
    private List<Executer> findAllByTaskId(Long id){
        return executerRepository.findAllByTaskId(id);
    }
    private TaskDto convertEntityToDto(Task task){
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDecription(),
                task.getStatus().getStatusValue(),
                task.getPriority().getPriorityValue(),
                task.getOwner(),
                findAllByTaskId(task.getId()).stream().map(Executer::getUser).toList()
        );
    }
}
