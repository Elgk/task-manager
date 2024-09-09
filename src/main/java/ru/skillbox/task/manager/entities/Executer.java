package ru.skillbox.task.manager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Table(name = "executers")
public class Executer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long taskId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
