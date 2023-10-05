package com.school.sptech.grupo3.gobread.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Builder(toBuilder = true)
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Produto produto;

    private Integer qtdProduto;

    public ItemPedido(Integer id, Pedido pedido, Produto produto, Integer qtdProduto) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.qtdProduto = qtdProduto;
    }

    public ItemPedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", produto=" + produto +
                ", qtdProduto=" + qtdProduto +
                '}';
    }
}
