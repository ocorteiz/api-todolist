package com.ocorteiz.apitodolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "campo nome não pode estar vao")
    private String nome;
    @NotBlank(message = "campo descrição não pode estar vazio")
    private String descricao;
    @NotNull(message = "campo prioridade não pode ser nulo")
    private int prioridade;
    private boolean realizado = false;
    @OneToMany(mappedBy = "todo", fetch = FetchType.EAGER)
    private List<SubTodo> subtodo;


    public Todo(){}

    public Todo(String nome, String descricao, int prioridade, boolean realizado) {
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.realizado = realizado;
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

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public boolean getRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public void realizada() {
        this.realizado = true;
    }

    public List<SubTodo> getSubtodo() {
        return subtodo;
    }

    public void setSubtodo(List<SubTodo> subtodo) {
        this.subtodo = subtodo;
    }
}
