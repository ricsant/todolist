package com.ricardosaraiva.todolist.controller;

import com.ricardosaraiva.todolist.entity.Todo;
import com.ricardosaraiva.todolist.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.create(todo));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> list() {
        return ResponseEntity.ok(todoService.list());
    }

    @PutMapping("{id}")
    public ResponseEntity<List<Todo>> update(@PathVariable Long id, @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.update(id, todo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<Todo>> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(todoService.delete(id));
    }

}
