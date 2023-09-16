package com.school.sptech.grupo3.gobread.mapper;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ResponseMapper {
    public ComercioResponse from(Comercio comercio) {
        return ComercioResponse.builder()
                .responsavel(comercio.getResponsavel())
                .razaoSocial(comercio.getRazaoSocial())
                .email(comercio.getEmail())
                .senha(comercio.getSenha())
                .endereco(comercio.getEndereco())
                .build();
    };

    public ClienteResponse from(Cliente cliente) {
        return ClienteResponse.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .telefone(cliente.getTelefone())
                .email(cliente.getEmail())
                .senha(cliente.getSenha())
                .endereco(cliente.getEndereco())
                .build();
    };
}
