package com.school.sptech.grupo3.gobread.entity;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;

@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class Cliente{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String email;
        private String senha;
        private String telefone;
        private String nome;
        private String cpf;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "fkEndereco", referencedColumnName = "id")
        private Endereco endereco;

        public Cliente(Integer id, String email, String senha, String telefone, String nome, String cpf, Endereco endereco) {
                this.id = id;
                this.email = email;
                this.senha = senha;
                this.telefone = telefone;
                this.nome = nome;
                this.cpf = cpf;
                this.endereco = endereco;
        }

        public Cliente atualizarEndereco(AddressViaCep addressViaCep) {
                return this.toBuilder()
                        .endereco(Endereco.builder()
                                .cep(this.endereco.getCep())
                                .rua(addressViaCep.logradouro())
                                .numero(this.endereco.getNumero())
                                .complemento(this.endereco.getComplemento())
                                .bairro(addressViaCep.bairro())
                                .cidade(addressViaCep.localidade())
                                .estado(addressViaCep.uf())
                                .build())
                        .build();
        }

        @Override
        public String toString() {
                return "Cliente{" +
                        "nome='" + nome + '\'' +
                        ", cpf='" + cpf + '\'' +
                        ", endereco=" + endereco +
                        "} ";


        }
}
