package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.apiviacep.ViaCepApi;
import com.school.sptech.grupo3.gobread.arquivoCsv.ArquivoCsvService;
import com.school.sptech.grupo3.gobread.arquivoCsv.ListaObj;
import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.Endereco;
import com.school.sptech.grupo3.gobread.mapper.ModelMapper;
import com.school.sptech.grupo3.gobread.mapper.ResponseMapper;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import com.school.sptech.grupo3.gobread.repository.ComercioRepository;
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
    private final ComercioRepository rep;
    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;
    private final ArquivoCsvService arquivoCsvService;



    public ResponseEntity<ComercioResponse> criarComercio(ComercioRequest comercioRequest) {
        final Comercio comercio = modelMapper.from(comercioRequest);
        final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(comercio.getEndereco().getCep());
        final Comercio comercioEnderecoAtualiazado = comercio.atualizarEndereco(enderecoViaCep);
        rep.save(comercioEnderecoAtualiazado);
        final ComercioResponse comercioResponse = responseMapper.from(comercioEnderecoAtualiazado);
        return ResponseEntity.status(201).body(comercioResponse);
    }


    public ResponseEntity<ComercioResponse> buscarComercioPorId(int id) {
        if(rep.existsById(id)){
            return ResponseEntity.status(200).body(responseMapper.from(rep.findById(id).orElseThrow()));
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<ComercioResponse> atualizarComercio(int id, ComercioRequest comercioRequest){
        if(rep.existsById(id)){
            final Comercio comercio = modelMapper.from(comercioRequest);
            final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(comercio.getEndereco().getCep());
            final Comercio comercioEnderecoAtualizado = comercio.atualizarEndereco(enderecoViaCep);
            comercioEnderecoAtualizado.setId(id);
            comercioEnderecoAtualizado.getEndereco().setId(id);
            rep.save(comercioEnderecoAtualizado);
            final ComercioResponse comercioResponse = responseMapper.from(comercioEnderecoAtualizado);
            return ResponseEntity.status(200).body(comercioResponse);
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<ComercioResponse> deletarComercio(int id) {
        if(rep.existsById(id)){
            rep.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }


    public boolean gerarArquivoCsv(){
        List<Cliente>  clientes = this.clienteRepository.findAll();
        ListaObj<Cliente> listaObjClientes = new ListaObj<>(clientes.size());
        for(int i = 0; i < clientes.size(); i++){
            listaObjClientes.adiciona(clientes.get(i));
        }
        ListaObj<Cliente> clientesOrdenados = this.arquivoCsvService.selectionSortCliente(listaObjClientes);
        arquivoCsvService.gravaArquivoCsv(clientesOrdenados, "relatorio-clientes");
        return true;
    }


}
