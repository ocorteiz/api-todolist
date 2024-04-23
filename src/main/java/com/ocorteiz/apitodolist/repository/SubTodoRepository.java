package com.ocorteiz.apitodolist.repository;

import com.ocorteiz.apitodolist.entity.SubTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubTodoRepository extends JpaRepository<SubTodo, Long> {
    Optional<SubTodo> findAllById(Long id);
}
