package com.school.sptech.grupo3.gobread.entity;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Entity
public class Cliente {
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
//        @OneToMany(mappedBy = "cliente")
//        private List<Pedido> pedidos;


        public Cliente(Integer id, String email, String senha, String telefone, String nome, String cpf, Endereco endereco) {
                this.id = id;
                this.email = email;
                this.senha = senha;
                this.telefone = telefone;
                this.nome = nome;
                this.cpf = cpf;
                this.endereco = endereco;
        }

        public Cliente() {
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


        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getSenha() {
                return senha;
        }

        public void setSenha(String senha) {
                this.senha = senha;
        }

        public String getTelefone() {
                return telefone;
        }

        public void setTelefone(String telefone) {
                this.telefone = telefone;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getCpf() {
                return cpf;
        }

        public void setCpf(String cpf) {
                this.cpf = cpf;
        }

        public Endereco getEndereco() {
                return endereco;
        }

        public void setEndereco(Endereco endereco) {
                this.endereco = endereco;
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
