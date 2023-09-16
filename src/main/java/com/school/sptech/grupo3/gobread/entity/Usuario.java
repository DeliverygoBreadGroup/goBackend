package com.school.sptech.grupo3.gobread.entity;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Usuario {
    private String email;
    private String senha;
    private String telefone;

    public Usuario(String email, String senha, String telefone) {
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }
}
