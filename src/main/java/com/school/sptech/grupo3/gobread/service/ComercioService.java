package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.apiviacep.ViaCepApi;
import com.school.sptech.grupo3.gobread.arquivoCsv.ArquivoCsvService;
import com.school.sptech.grupo3.gobread.arquivoCsv.ListaObj;
import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.request.LoginRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.controller.response.LoginResponse;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.Endereco;
import com.school.sptech.grupo3.gobread.mapper.ModelMapper;
import com.school.sptech.grupo3.gobread.mapper.ResponseMapper;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import com.school.sptech.grupo3.gobread.repository.ComercioRepository;
import com.school.sptech.grupo3.gobread.security.GerenciadorTokenJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;


    public ResponseEntity<ComercioResponse> criarComercio(ComercioRequest comercioRequest) {
        final Comercio comercio = modelMapper.from(comercioRequest);
        final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(comercio.getEndereco().getCep());
        final Comercio comercioEnderecoAtualizado = comercio.atualizarEndereco(enderecoViaCep);
        String senhaCriptografada = passwordEncoder.encode(comercioEnderecoAtualizado.getSenha());
        comercioEnderecoAtualizado.setSenha(senhaCriptografada);
        rep.save(comercioEnderecoAtualizado);
        final ComercioResponse comercioResponse = responseMapper.from(comercioEnderecoAtualizado);
        return ResponseEntity.status(201).body(comercioResponse);
    }

    public LoginResponse autenticar(LoginRequest usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.email(), usuarioLoginDto.senha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Comercio usuarioAutenticado =
                rep.findByEmail(usuarioLoginDto.email())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);
        LoginResponse response = new LoginResponse(token);
        return response;
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
        ListaObj<Cliente> listaObjClientes = new ListaObj<>(clientes.size(), Cliente.class);
        for(int i = 0; i < clientes.size(); i++){
            listaObjClientes.adiciona(clientes.get(i));
        }
        this.arquivoCsvService.bubbleSort(listaObjClientes);
        arquivoCsvService.gravaArquivoCsv(listaObjClientes, "relatorio-clientes");
        return true;
    }



}
