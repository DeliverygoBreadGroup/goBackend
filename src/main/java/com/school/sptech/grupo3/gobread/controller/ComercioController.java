package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.service.ComercioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Path;

@RestController
@RequestMapping("/comercios")
@RequiredArgsConstructor
public class ComercioController {

    private final ComercioService comercioService;

    @PostMapping
    public ResponseEntity<ComercioResponse> cadastrarComercio(@Valid @RequestBody ComercioRequest comercioRequest) {
        return comercioService.criarComercio(comercioRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComercioResponse> buscarPorId(@PathVariable int id){
        return comercioService.buscarComercioPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComercioResponse> atualizarComercio(@PathVariable int id, @Valid @RequestBody ComercioRequest comercioRequest){
        return comercioService.atualizarComercio(id, comercioRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ComercioResponse> deletarComercio(@PathVariable int id){
        return comercioService.deletarComercio(id);
    }


    @GetMapping("/csv")
    public ResponseEntity<Void> csv(){
        comercioService.gerarArquivoCsv();
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/download/clientes-csv")
    public ResponseEntity<byte[]> download(){

        boolean arquivoGerado = comercioService.gerarArquivoCsv();
        if(arquivoGerado){
            try {
                Resource resource = new ClassPathResource("/relatorio-clientes.csv");
                File file = resource.getFile();
                InputStream fileInputStream = new FileInputStream(file);

                return ResponseEntity.status(200)
                        .header("Content-Disposition",
                                "attachment; filename=relatorio-clientes.csv")
                        .body(fileInputStream.readAllBytes());
            } catch (FileNotFoundException e){
                e.printStackTrace();
                throw new ResponseStatusException(422, "Diretório não encontrado", null);
            } catch (IOException e){
                e.printStackTrace();
                throw new ResponseStatusException(422, "Não foi possível converter para byte[]", null);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
