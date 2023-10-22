package com.school.sptech.grupo3.gobread.arquivoCsv;

import com.school.sptech.grupo3.gobread.entity.Cliente;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import java.io.*;
import java.util.Formatter;
import java.util.FormatterClosedException;

@Component
public class ArquivoCsvService {
    public void gravaArquivoCsv(ListaObj<Cliente> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;

        nomeArq += ".csv";

        try{
            Resource resource = new ClassPathResource("");

            String resourcesPath = resource.getFile().getAbsolutePath();

            String caminhoArquivo = resourcesPath + File.separator + nomeArq;

            arq = new FileWriter(caminhoArquivo);
            saida = new Formatter(arq);
        }
        catch(IOException e){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try{
            for (int i = 0; i < lista.getTamanho(); i++) {
                Cliente cliente = lista.getElemento(i);
                saida.format("%s;%s;%s;%s;%s\n",
                        cliente.getNome(),
                        cliente.getEmail(),
                        cliente.getTelefone(),
                        cliente.getEndereco().getRua(),
                        cliente.getEndereco().getBairro()
                );
            }
        }
        catch(FormatterClosedException e){
            System.out.println("Erro ao gravar o arquivo");
            e.printStackTrace();
            deuRuim = true;
        }
        finally{
            saida.close();
            try{
                arq.close();
            }
            catch(IOException e){
                System.out.println("Erro ao fecharo o arquivo");
                deuRuim = true;
            }
            if(deuRuim){
                System.exit(1);
            }
        }
    }

    public ListaObj<Cliente> selectionSortCliente(ListaObj<Cliente> listaClientes) {
        for (int i = 0; i < listaClientes.getTamanho() - 1; i++) {
            int indexMin = i;
            for (int j = i + 1; j < listaClientes.getTamanho(); j++) {
                if (listaClientes.getElemento(j).getNome().compareTo(listaClientes.getElemento(indexMin).getNome()) < 0) {
                    indexMin = j;
                }
            }
            Cliente temp = listaClientes.getElemento(i);
            listaClientes.adicionaNoIndice(i, listaClientes.getElemento(indexMin));
            listaClientes.adicionaNoIndice(indexMin, temp);
        }

        return listaClientes;
    }
}
