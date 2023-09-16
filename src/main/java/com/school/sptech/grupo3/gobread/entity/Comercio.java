package com.school.sptech.grupo3.gobread.entity;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comercio extends Usuario {
    private String razaoSocial;
    private String responsavel;
    private String cnpj;
    private Endereco endereco;

    public Comercio(String email, String senha, String telefone, String razaoSocial, String responsavel, String cnpj, Endereco endereco) {
        super(email, senha, telefone);
        this.razaoSocial = razaoSocial;
        this.responsavel = responsavel;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Comercio{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco=" + endereco +
                "} " + super.toString();
    }
}
