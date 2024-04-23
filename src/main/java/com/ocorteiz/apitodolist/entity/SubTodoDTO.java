package com.ocorteiz.apitodolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class SubTodoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Boolean realizado;
    @JsonIgnore
    private Todo todo;

    public SubTodoDTO(Long id, String nome, String descricao, Boolean realizado) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.realizado = realizado;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }
}
