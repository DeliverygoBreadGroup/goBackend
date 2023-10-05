package com.school.sptech.grupo3.gobread.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double precoUnitario;
    private Integer qtdProduto;
    private Integer valorPorcao;

    public Produto(Integer id, String nome, Double precoUnitario, Integer qtdProduto, Integer valorPorcao) {
        this.id = id;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.qtdProduto = qtdProduto;
        this.valorPorcao = valorPorcao;
    }

    public Produto() {
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

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public Integer getValorPorcao() {
        return valorPorcao;
    }

    public void setValorPorcao(Integer valorPorcao) {
        this.valorPorcao = valorPorcao;
    }

}
