package com.ocorteiz.apitodolist.controller;

import com.ocorteiz.apitodolist.entity.SubTodo;
import com.ocorteiz.apitodolist.entity.SubTodoDTO;
import com.ocorteiz.apitodolist.service.SubTodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subtodo")
public class SubTodoController {

    private final SubTodoService subTodoService;

    public SubTodoController(SubTodoService subTodoService) {
        this.subTodoService = subTodoService;
    }

    @GetMapping
    ResponseEntity<?> list(){
        return subTodoService.list();
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody SubTodo subTodo) {
        return subTodoService.create(subTodo);
    }
}
