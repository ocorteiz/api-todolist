package com.ocorteiz.apitodolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "subtodos")
public class SubTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean realizada = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    public SubTodo(){
    }

    public SubTodo(String nome, String descricao, Boolean realizada) {
        this.nome = nome;
        this.descricao = descricao;
        this.realizada = realizada;
    }

    public Long getId() {
        return id;
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

    public Boolean getRealizada() {
        return realizada;
    }

    public void setRealizada(Boolean realizada) {
        this.realizada = realizada;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo =  todo;
    }
}
