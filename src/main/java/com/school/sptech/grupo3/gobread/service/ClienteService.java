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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ModelMapper modelMapper;
    private final ResponseMapper responseMapper;
    private final EnderecoService enderecoService;
    private final ClienteRepository rep;
//    private final List<Cliente> listaCliente = new ArrayList<>();

//    public  ResponseEntity<ClienteResponse> buscarClientePorId(int id) {
//      if(id >= 0 && id < listaCliente.size()){
//          Cliente cliente = listaCliente.get(id);
//          return ResponseEntity.status(200).body(responseMapper.from(cliente));
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

//    public ResponseEntity<ClienteResponse> atualizarCliente(int id, ClienteRequest clienteRequest){
//        final Cliente cliente = modelMapper.from(clienteRequest);
//        final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(cliente.getEndereco().getCep());
//        cliente.setEndereco(new Endereco(
//                cliente.getEndereco().getCep(),
//                enderecoViaCep.logradouro(),
//                cliente.getEndereco().getNumero(),
//                cliente.getEndereco().getComplemento(),
//                enderecoViaCep.bairro(),
//                enderecoViaCep.localidade(),
//                enderecoViaCep.uf()
//        ));
//        if(id >= 0 && id < listaCliente.size()){
//            listaCliente.set(id, cliente);
//            return ResponseEntity.status(200).body(responseMapper.from(cliente));
//        }
//        return ResponseEntity.status(404).build();
//    }

//    public  ResponseEntity<ClienteResponse> deletarCliente(int id) {
//        if(id >= 0 && id < listaCliente.size()){
//            Cliente cliente = listaCliente.remove(id);
//            return ResponseEntity.status(204).build();
//        }
//        return ResponseEntity.status(404).build();
//    }

}
