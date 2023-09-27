package com.school.sptech.grupo3.gobread.controller.response;

import com.school.sptech.grupo3.gobread.entity.Endereco;
import lombok.Builder;

@Builder
public record ComercioResponse(

        String razaoSocial,
        String responsavel,
        String email,
        String telefone,
        Endereco endereco
) {}
