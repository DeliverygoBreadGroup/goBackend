package br.com.efi.pix.split.cob.json;

import java.util.HashMap;


import org.json.JSONObject;

import com.school.sptech.grupo3.gobread.integrations.efiPay.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
 
public class PixSplitLinkCharge {
    public static void main(String[] args) {
		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

    	HashMap<String, String> params = new HashMap<String, String>();
		params.put("txid", "7978c0c97ea847e78e8849634473c1f2");
		params.put("splitConfigId", "6aeddee74dd1a890c0ace71f53f70002");

		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("pixSplitLinkCharge", params, new JSONObject());
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
