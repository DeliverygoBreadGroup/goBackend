package br.com.efi.charges.subscription.map;

import java.util.HashMap;
import java.util.Map;

import com.school.sptech.grupo3.gobread.integrations.efiPay.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class CreatePlan {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("name", "My plan");
		body.put("interval", 2);
		body.put("repeats", 2);

		try {
		    EfiPay efi = new EfiPay(options);
		    Map<String, Object> response = efi.call("createPlan", new HashMap<String,String>(), body);
		    System.out.println(response);
		}catch (EfiPayException e){
		    System.out.println(e.getCode());
		    System.out.println(e.getError());
		    System.out.println(e.getErrorDescription());
		}catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	}
}
