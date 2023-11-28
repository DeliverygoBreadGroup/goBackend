package com.school.sptech.grupo3.gobread.arquivoCsv;

import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArquivoTxtService {

    public static void gravaRegistro(String registro, String nomeArq, String action) {
        BufferedWriter saida = null;

        try {
            // Obtém o caminho do diretório resources
            String resourcesPath = ArquivoTxtService.class.getClassLoader().getResource("").getPath();

            // Cria o caminho completo do arquivo
            String caminhoArquivo = resourcesPath + File.separator + nomeArq;

            // Abre o arquivo para escrita
            saida = new BufferedWriter(new FileWriter(caminhoArquivo, !action.equals("clean")));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        try {
            if (action.equals("write")) {
                saida.append(registro + "\n");
            } else if (action.equals("clean")) {
                saida.write(registro);
            }
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar ou fechar o arquivo");
            erro.printStackTrace();
        }
    }


    public static void gravaArquivoTxT(Comercio comercio, String nomeArq){
        gravaRegistro("",nomeArq, "clean");

        int contaRegistroDados = 0;

        String header = "00DADOS CLIENTES";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        gravaRegistro(header, nomeArq, "write");

        List<Cliente> clientes = new ArrayList<>();

        for(int i = 0; i < comercio.getPedidos().size(); i++){
            Cliente cliente = comercio.getPedidos().get(i).getCliente();
            if(!clientes.contains(cliente)){
                clientes.add(cliente);
            }
        }

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);

            String corpo = "02";
            corpo += String.format("%02d", c.getId());
            corpo += String.format("%-30.30s", c.getNome());
            corpo += String.format("%-30.30s", c.getEmail());
            corpo += String.format("%-12.12s", c.getTelefone());
            corpo += String.format("%-8.8s", c.getEndereco().getCep());
            corpo += String.format("%-30.30s", c.getEndereco().getRua());
            corpo += String.format("%-30.30s", c.getEndereco().getBairro());

            gravaRegistro(corpo, nomeArq, "write");

            for(int j = 0; j < c.getPedidos().size(); j++){
                Pedido pedido = c.getPedidos().get(j);
                String corpoPedido = "03";
                corpoPedido += String.format("%02d", pedido.getId());
                corpoPedido += String.format("%-7.7s", pedido.getDiaEntrega());
                corpoPedido += String.format("%-5.5s", pedido.getHorarioEntrega());
                if(pedido.getComercio().equals(comercio)){
                    gravaRegistro(corpoPedido, nomeArq, "write");
                    contaRegistroDados++;
                }
            }
            contaRegistroDados++;
        }

        String trailer = "01";
        trailer += String.format("%05d", contaRegistroDados);
        trailer += "goBreadGroup!";

        gravaRegistro(trailer, nomeArq, "write");
    }

}
