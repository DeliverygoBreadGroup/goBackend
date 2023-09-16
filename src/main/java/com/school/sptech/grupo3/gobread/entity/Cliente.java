package com.school.sptech.grupo3.gobread.entity;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import lombok.*;

import javax.annotation.processing.Generated;

@Getter
@Setter
public class Cliente extends Usuario {
        private String nome;
        private String cpf;
        private Endereco endereco;

        public Cliente(String email, String senha, String telefone, String nome, String cpf, Endereco endereco) {
                super(email, senha, telefone);
                this.nome = nome;
                this.cpf = cpf;
                this.endereco = endereco;
        }

        @Override
        public String toString() {
                return "Cliente{" +
                        "nome='" + nome + '\'' +
                        ", cpf='" + cpf + '\'' +
                        ", endereco=" + endereco +
                        "} " + super.toString();


        }
}
