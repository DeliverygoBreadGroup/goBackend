package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.apiviacep.ViaCepApi;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final ViaCepApi viaCepApi;

    public AddressViaCep buscarEnderecoViaCep(String cep) {
        return viaCepApi.getAddressByCep(cep);
    }
}
