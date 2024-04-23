package com.ocorteiz.apitodolist.service;

import com.ocorteiz.apitodolist.entity.SubTodo;
import com.ocorteiz.apitodolist.entity.SubTodoDTO;
import com.ocorteiz.apitodolist.entity.Todo;
import com.ocorteiz.apitodolist.infra.HandleErrors;
import com.ocorteiz.apitodolist.repository.SubTodoRepository;
import com.ocorteiz.apitodolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubTodoService {

    private final SubTodoRepository subTodoRepository;
    private final TodoRepository todoRepository;

    public SubTodoService(SubTodoRepository subTodoRepository, TodoRepository todoRepository) {
        this.subTodoRepository = subTodoRepository;
        this.todoRepository = todoRepository;
    }

    public ResponseEntity<?> list(){
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");

        List<SubTodo> listaSubTodos = subTodoRepository.findAll(sort);

        List<SubTodoDTO> subTodoDTOS = listaSubTodos.stream()
                .map(todo -> new SubTodoDTO(todo.getId(), todo.getNome(), todo.getDescricao(), todo.getRealizada()))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(subTodoDTOS);
    }

    public ResponseEntity<?> create(SubTodo subTodo) {
        Long todoId = subTodo.getTodo().getId();

        Optional<Todo> optionalTodo = todoRepository.findById(todoId);

        if (optionalTodo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HandleErrors.ErrorResponse("Todo não encontrado"));
        }

        Todo todo = optionalTodo.get();
        subTodo.setTodo(todo);

        SubTodo subTodoCreated = subTodoRepository.save(subTodo);

        return ResponseEntity.ok().body(subTodoCreated);
    }
}
