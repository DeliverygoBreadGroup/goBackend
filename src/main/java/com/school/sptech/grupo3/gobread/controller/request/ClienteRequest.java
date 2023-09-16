package com.school.sptech.grupo3.gobread.controller.request;

public record ClienteRequest(
    String nome,
    String cpf,
    String telefone,
    String email,
    String senha,
    EnderecoRequest endereco
) {}
