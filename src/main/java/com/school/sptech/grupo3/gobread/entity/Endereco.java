package com.school.sptech.grupo3.gobread.entity;

import lombok.*;

@Getter
@Setter
@Builder
public class Endereco {
    private String cep;
    private String rua;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco() {
    }

    public Endereco(String cep, String rua, Integer numero, String complemento, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
