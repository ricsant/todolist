package com.ricardosaraiva.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="todos")
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean done;
    private int priority;
}
