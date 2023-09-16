package com.school.sptech.grupo3.gobread.controller.response;

import com.school.sptech.grupo3.gobread.entity.Endereco;
import lombok.Builder;

@Builder
public record ComercioResponse(
        String responsavel,
        String razaoSocial,
        String email,
        String senha,
        Endereco endereco
) {}
