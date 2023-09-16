package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.request.EnderecoRequest;
import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.Endereco;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Usuario;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
public class ModelMapper {

    public Comercio from(ComercioRequest comercioRequest) {
        return new Comercio(
                comercioRequest.email(),
                comercioRequest.senha(),
                comercioRequest.telefone(),
                comercioRequest.razaoSocial(),
                comercioRequest.responsavel(),
                comercioRequest.cnpj(),
                from(comercioRequest.endereco())
                );

    };

    public Cliente from(ClienteRequest clienteRequest) {
        return new Cliente(
                clienteRequest.email(),
                clienteRequest.senha(),
                clienteRequest.telefone(),
                clienteRequest.nome(),
                clienteRequest.cpf(),
                from(clienteRequest.endereco())
        );
    };

    public Endereco from(EnderecoRequest enderecoRequest) {
        return Endereco.builder()
                .cep(enderecoRequest.cep())
                .numero(enderecoRequest.numero())
                .complemento(enderecoRequest.complemento())
                .build();
    };



}
