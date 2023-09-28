package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.entity.Endereco;
import com.school.sptech.grupo3.gobread.mapper.ModelMapper;
import com.school.sptech.grupo3.gobread.mapper.ResponseMapper;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ModelMapper modelMapper;
    private final ResponseMapper responseMapper;
    private final EnderecoService enderecoService;
    private final ClienteRepository rep;

//    public  ResponseEntity<ClienteResponse> buscarClientePorId(int id) {
//      if(rep.existsById(id)){
//          return ResponseEntity.status(200).body(responseMapper.from(rep.findById(id).orElseThrow()));
//      }
//      return ResponseEntity.status(404).build();
//    }

    public ResponseEntity<ClienteResponse> criarCliente(ClienteRequest clienteRequest) {
        final Cliente cliente = modelMapper.from(clienteRequest);
        final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(cliente.getEndereco().getCep());
        final Cliente clienteEnderecoAtualizado = cliente.atualizarEndereco(enderecoViaCep);
        rep.save(clienteEnderecoAtualizado);
        final ClienteResponse clienteResponse = responseMapper.from(clienteEnderecoAtualizado);
        return ResponseEntity.status(201).body(clienteResponse);
    }

    public ResponseEntity<ClienteResponse> atualizarCliente(int id, ClienteRequest clienteRequest){
        if(rep.existsById(id)){
            final Cliente cliente = modelMapper.from(clienteRequest);
            final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(cliente.getEndereco().getCep());
            final Cliente clienteEnderecoAtualizado = cliente.atualizarEndereco(enderecoViaCep);
            clienteEnderecoAtualizado.setId(id);
            clienteEnderecoAtualizado.getEndereco().setId(id);
            rep.save(clienteEnderecoAtualizado);
            final ClienteResponse clienteResponse = responseMapper.from(clienteEnderecoAtualizado);
            return ResponseEntity.status(200).body(clienteResponse);
        }

        return ResponseEntity.status(404).build();
    }



    public ResponseEntity<ClienteResponse> deletarCliente(int id) {
        if(rep.existsById(id)){
            rep.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<Cliente> buscarEnderecoPorFk(int fkEndereco) {
        return ResponseEntity.status(200).body(rep.findByFkEndereco(fkEndereco));
    }
}
