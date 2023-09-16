package com.school.sptech.grupo3.gobread.controller.request;

import lombok.Builder;

@Builder
public record ComercioRequest(
    String razaoSocial,
    String responsavel,
    String cnpj,
    String telefone,
    String email,
    String senha,
    EnderecoRequest endereco
) {}
