package ru.skillbox.task.manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlredyExists extends RuntimeException{
    public UserAlredyExists(String message){
        super(message);
    }
}
