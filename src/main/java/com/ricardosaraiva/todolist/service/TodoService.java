package com.ricardosaraiva.todolist.service;

import com.ricardosaraiva.todolist.entity.Todo;
import com.ricardosaraiva.todolist.exception.BadRequestException;
import com.ricardosaraiva.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list() {
        Sort sort = Sort.by("priority").descending().and(Sort.by("name").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(Long id, Todo todo) {
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> {
            existingTodo.setName(todo.getName());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setDone(todo.isDone());
            existingTodo.setPriority(todo.getPriority());
            todoRepository.save(existingTodo);
        }, () -> {
            throw new BadRequestException("Todo %d não existe!".formatted(id));
        });

        return list();
    }

    public List<Todo> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }

}
