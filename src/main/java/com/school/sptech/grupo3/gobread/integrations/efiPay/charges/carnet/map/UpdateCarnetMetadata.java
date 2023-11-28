package br.com.efi.charges.carnet.map;

import java.util.HashMap;
import java.util.Map;

import com.school.sptech.grupo3.gobread.integrations.efiPay.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateCarnetMetadata {

	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */
		
		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());
		
		/* ************************************************* */ 
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("custom_id", "Carnet 0001");
		body.put("notification_url", "http://domain.com/notification");
		
		try {
			EfiPay efi = new EfiPay(options);
			Map<String, Object> response = efi.call("updateCarnetMetadata", params, body);
			System.out.println(response);
		}catch (EfiPayException e){
			System.out.println(e.getCode());
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
