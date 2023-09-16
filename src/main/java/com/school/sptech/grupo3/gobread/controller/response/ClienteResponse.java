package com.school.sptech.grupo3.gobread.controller.response;

import com.school.sptech.grupo3.gobread.entity.Endereco;
import lombok.Builder;

@Builder

public record ClienteResponse(
    String nome,
    String cpf,
    String telefone,
    String email,
    String senha,
    Endereco endereco
) {}
