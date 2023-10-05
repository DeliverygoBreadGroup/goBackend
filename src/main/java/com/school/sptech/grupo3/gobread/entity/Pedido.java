package com.school.sptech.grupo3.gobread.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.ArrayList;
import java.util.List;
@Builder(toBuilder = true)
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String diaEntrega;
    private String horaEntrega;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;

    public Pedido(Integer id, String diaEntrega, String horaEntrega, List<ItemPedido> itensPedido) {
        this.id = id;
        this.diaEntrega = diaEntrega;
        this.horaEntrega = horaEntrega;
        this.itensPedido = itensPedido;
    }

    public Pedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiaEntrega() {
        return diaEntrega;
    }

    public void setDiaEntrega(String diaEntrega) {
        this.diaEntrega = diaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "diaEntrega='" + diaEntrega + '\'' +
                ", horaEntrega='" + horaEntrega + '\'' +
                '}';
    }
}
