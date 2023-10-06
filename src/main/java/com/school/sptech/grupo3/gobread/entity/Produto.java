package com.school.sptech.grupo3.gobread.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer valorPorcao;
    private String tipoPorcao;
//    @ManyToMany(mappedBy = "produtos")
//    private List<Comercio> comercios = new ArrayList<>();

    public Produto(Integer id, String nome, Integer valorPorcao, String tipoPorcao) {
        this.id = id;
        this.nome = nome;
        this.valorPorcao = valorPorcao;
        this.tipoPorcao = tipoPorcao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValorPorcao() {
        return valorPorcao;
    }

    public void setValorPorcao(Integer valorPorcao) {
        this.valorPorcao = valorPorcao;
    }

    public String getTipoPorcao() {
        return tipoPorcao;
    }

    public void setTipoPorcao(String tipoPorcao) {
        this.tipoPorcao = tipoPorcao;
    }
}
