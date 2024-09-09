package ru.skillbox.task.manager.entities;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskPriority {
    LOW("низкий"),
    MEDIUM("средний"),
    HIGH("высокий");

    private final String priorityValue;

    public String getPriorityValue(){
        return priorityValue;
    }
}
