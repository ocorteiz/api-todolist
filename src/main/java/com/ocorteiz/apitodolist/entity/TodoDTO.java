package com.ocorteiz.apitodolist.entity;

import java.util.List;

public class TodoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private boolean realizado;
    private List<SubTodo> subTodo;

    public TodoDTO(Long id, String nome, String descricao, boolean realizado, List<SubTodo> subTodo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.realizado = realizado;
        this.subTodo = subTodo;
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

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

}
