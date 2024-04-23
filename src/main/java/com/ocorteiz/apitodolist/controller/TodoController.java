package com.ocorteiz.apitodolist.controller;

import com.ocorteiz.apitodolist.entity.Todo;
import com.ocorteiz.apitodolist.service.TodoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    @Transactional
    ResponseEntity<?> create(@RequestBody @Valid Todo todo){
        return todoService.create(todo);
    }

    @GetMapping
    ResponseEntity<?> list(){
        return todoService.list();
    }

    @PostMapping("{id}")
    ResponseEntity<?> show(@PathVariable Long id){
        return todoService.show(id);
    }

    @PutMapping("{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Todo todo){
        return todoService.upadte(id, todo);
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        return todoService.delete(id);
    }

    @DeleteMapping("/realizada/{id}")
    ResponseEntity<?> realizada(@PathVariable Long id) {
        return todoService.realizada(id);
    }

}
