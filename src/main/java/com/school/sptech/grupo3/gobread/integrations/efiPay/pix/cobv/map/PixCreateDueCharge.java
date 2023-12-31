package br.com.efi.pix.cobv.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.json.JSONObject;

import com.school.sptech.grupo3.gobread.integrations.efiPay.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixCreateDueCharge {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

    HashMap<String, Object> options = new HashMap<String, Object>();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

        JSONObject abatimento = new JSONObject().put("modalidade", 1).put("valorPerc", "5.00");
        JSONObject multa = new JSONObject().put("modalidade", 2).put("valorPerc", "15.00");      
       JSONObject juros = new JSONObject().put("modalidade", 2).put("valorPerc", "2.00");
       JSONObject desconto = new JSONObject().put("modalidade", 3).put("valorPerc", "1.00");

        List<Object> infoAdicionais =  new ArrayList<Object>();
        infoAdicionais.add(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));

        Map<String, Object> body = new HashMap<String, Object>();	
        body.put("calendario", new JSONObject().put("dataDeVencimento", "2022-10-30").put("validadeAposVencimento", 30));
      body.put("devedor", new JSONObject().put("cpf", "12345678909")
          .put("nome", "Francisco da Silva")
          .put("logradouro", "Alameda Souza, Numero 80, Bairro Braz")
          .put("cidade", "Recife")
          .put("uf", "PE")
          .put("cep", "70011750"));
      body.put("valor", new JSONObject().put("original", "123.45")
          .put("abatimento", abatimento).put("desconto", desconto)
          .put("juros", juros)
          .put("multa", multa));
      body.put("chave", "Insira_aqui_sua_chave");
      body.put("solicitacaoPagador", "Serviço realizado.");
      body.put("infoAdicionais", infoAdicionais);


        try {
          EfiPay efi= new EfiPay(options);
          Map<String, Object> response = efi.call("pixCreateDueCharge", params, body);
          System.out.println(response);

        }catch (EfiPayException e){
          System.out.println(e.getError());
          System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
          System.out.println(e.getMessage());
        }
	}
}
