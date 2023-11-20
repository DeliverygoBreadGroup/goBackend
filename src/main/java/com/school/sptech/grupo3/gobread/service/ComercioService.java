package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.arquivoCsv.ArquivoCsvService;
import com.school.sptech.grupo3.gobread.arquivoCsv.ListaObj;
import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.request.LoginRequest;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.controller.response.ComercioSemPedidoResponse;
import com.school.sptech.grupo3.gobread.controller.response.LoginComercioResponse;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.mapper.ComercioMapper;
import com.school.sptech.grupo3.gobread.mapper.EnderecoMapper;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import com.school.sptech.grupo3.gobread.repository.ComercioRepository;
import com.school.sptech.grupo3.gobread.security.GerenciadorTokenJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComercioService {
    private final ComercioRepository rep;
    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;
    private final ArquivoCsvService arquivoCsvService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;


    public ResponseEntity<ComercioResponse> criarComercio(ComercioRequest comercioRequest) {
        Optional<Comercio> comercioOptional = this.rep.findByEmail(comercioRequest.email());
        if(comercioOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existente");
        }

        final Comercio comercio = ComercioMapper.toComercio(comercioRequest);
        final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(comercio.getEndereco().getCep());
        final Comercio comercioEnderecoAtualizado = comercio.atualizarEndereco(enderecoViaCep);
        String senhaCriptografada = passwordEncoder.encode(comercioEnderecoAtualizado.getSenha());
        comercioEnderecoAtualizado.setSenha(senhaCriptografada);
        rep.save(comercioEnderecoAtualizado);
        final ComercioResponse comercioResponse = ComercioMapper.toComercioSemPedidoResponse(comercioEnderecoAtualizado);
        return ResponseEntity.status(201).body(comercioResponse);
    }

    public LoginComercioResponse autenticar(LoginRequest usuarioLoginDto) {

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
        LoginComercioResponse response = new LoginComercioResponse();
        response.setToken(token);
        response.setComercio(ComercioMapper.toComercioResponse(usuarioAutenticado));
        return response;
    }


    public ComercioResponse buscarComercioPorId(int id) {
        Comercio comercio = this.rep.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comercio não encontrado"));
        return ComercioMapper.toComercioResponse(comercio);
    }

    public ResponseEntity<ComercioResponse> atualizarComercio(int id, ComercioRequest comercioRequest){
        if(rep.existsById(id)){
            final Comercio comercio = ComercioMapper.toComercio(comercioRequest);
            final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(comercio.getEndereco().getCep());
            final Comercio comercioEnderecoAtualizado = comercio.atualizarEndereco(enderecoViaCep);
            comercioEnderecoAtualizado.setId(id);
            comercioEnderecoAtualizado.getEndereco().setId(id);
            rep.save(comercioEnderecoAtualizado);
            final ComercioResponse comercioResponse = ComercioMapper.toComercioSemPedidoResponse(comercioEnderecoAtualizado);
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


    public List<ComercioSemPedidoResponse> buscarPeloBairro(String bairro) {
        List<Comercio> comercios = this.rep.findByEnderecoBairro(bairro);
        return ComercioMapper.toListComercioSemPedidosResponse(comercios);
    }
}
