package com.ocorteiz.apitodolist.service;

import com.ocorteiz.apitodolist.entity.Todo;
import com.ocorteiz.apitodolist.entity.TodoDTO;
import com.ocorteiz.apitodolist.infra.HandleErrors;
import com.ocorteiz.apitodolist.repository.SubTodoRepository;
import com.ocorteiz.apitodolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final SubTodoRepository subTodoRepository;

    public TodoService(TodoRepository todoRepository, SubTodoRepository subTodoRepository) {
        this.todoRepository = todoRepository;
        this.subTodoRepository = subTodoRepository;
    }

    public ResponseEntity<List<Todo>> create(Todo todo){
        todoRepository.save(todo);
        return list();
    }

    public ResponseEntity<List<Todo>> list(){
        Sort sort = Sort.by(Sort.Direction.DESC, "prioridade").and(
                Sort.by(Sort.Direction.ASC, "nome")
        );


        List<Todo> listaTodo = todoRepository.findAll(sort);


        return ResponseEntity.ok().body(listaTodo);
    }

    public ResponseEntity<?> upadte(Long id, Todo todo){
        Todo existingTodo = todoRepository.findById(id).orElse(null);

        if(existingTodo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HandleErrors.ErrorResponse("Tarefa não encontrada"));
        }

        if (todo.getNome() != null && !todo.getNome().isEmpty()) {
            existingTodo.setNome(todo.getNome());
        }

        if (todo.getDescricao() != null && !todo.getDescricao().isEmpty()) {
            existingTodo.setDescricao(todo.getDescricao());
        }

        if (todo.getPrioridade() != 0) {
            existingTodo.setPrioridade(todo.getPrioridade());
        }

        if (todo.getRealizado() != existingTodo.getRealizado()) {
            existingTodo.setRealizado(todo.getRealizado());
        }

        todoRepository.save(existingTodo);

        return list();

    }

    public ResponseEntity<?> delete(Long id){
        Todo existingTodo = todoRepository.findById(id).orElse(null);

        if (existingTodo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HandleErrors.ErrorResponse("Tarefa não encontrada"));
        }

        todoRepository.deleteById(id);

        return list();
    }

    public ResponseEntity<?> realizada(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        if(todoOptional.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HandleErrors.ErrorResponse("Tarefa não encontrada"));
        }

        Todo todo = todoOptional.get();
        todo.realizada();

        todoRepository.save(todo);

        return list();
    }

    public ResponseEntity<?> show(@PathVariable Long todoId) {
        Optional<Todo> todoOptional = todoRepository.findById(todoId);

        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            return ResponseEntity.ok(todo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HandleErrors.ErrorResponse("Todo não encontrado"));
        }
    }
}
