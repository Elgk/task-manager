package ru.skillbox.task.manager.entities;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskStatus {
    WAITING("в ожидании"),
    INPROGRESS("в процессе"),
    COMPLETED("завершено");

    private final String statusValue;
    public  String getStatusValue(){
        return statusValue;
    }
}
