package br.com.efi.exclusives.report.json;

import java.io.IOException;

import java.util.HashMap;

import org.json.JSONObject;

import com.school.sptech.grupo3.gobread.integrations.efiPay.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class CreateReport {
    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		JSONObject body = new JSONObject();
		body.put("dataMovimento", "2022-04-24");
		body.put("tipoRegistros", new JSONObject().put("pixRecebido", true)
			.put("pixDevolucaoEnviada", false)
			.put("tarifaPixRecebido", true)
			.put("pixEnviadoChave", true)
			.put("pixEnviadoDadosBancarios", false)
			.put("pixDevolucaoRecebida", true));
 
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("createReport", new HashMap<String,String>(), body);
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