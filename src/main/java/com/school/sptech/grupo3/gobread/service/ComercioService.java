package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.apiviacep.ViaCepApi;
import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.Endereco;
import com.school.sptech.grupo3.gobread.mapper.ModelMapper;
import com.school.sptech.grupo3.gobread.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComercioService {
    private final ModelMapper modelMapper;
    private final ResponseMapper responseMapper;

    private final EnderecoService enderecoService;

    private final List<Comercio> listaComercio = new ArrayList<>();



    public ResponseEntity<ComercioResponse> criarComercio(ComercioRequest comercioRequest) {
        final Comercio comercio = modelMapper.from(comercioRequest);
        final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(comercio.getEndereco().getCep());
        comercio.setEndereco(new Endereco(
                comercio.getEndereco().getCep(),
                enderecoViaCep.logradouro(),
                comercio.getEndereco().getNumero(),
                comercio.getEndereco().getComplemento(),
                enderecoViaCep.bairro(),
                enderecoViaCep.localidade(),
                enderecoViaCep.uf()
        ));
        listaComercio.add(comercio);
        return ResponseEntity.status(201).body(responseMapper.from(comercio));
    }


}
