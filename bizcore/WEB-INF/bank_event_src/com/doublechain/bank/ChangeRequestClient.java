package com.doublechain.bank;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.doublechain.bank.account.Account;
import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.changerequest.ChangeRequestManagerException;
import com.doublechain.bank.namechangeevent.NameChangeEvent;
import com.doublechain.bank.platform.Platform;
import com.doublechain.bank.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class ChangeRequestClient {
	protected static void log(String message) {
		String content = String.format("%s %s: %s", "TERMSVR","-",message);
		System.out.println(content);
		
	}
	protected static ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper  = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);

		return objectMapper;

	}
	protected static BaseEntity emitRequest(BaseEntity req) throws IOException {
		String requestURL = "http://localhost:8080/bank/commonChangeService/process/";
		
		
		String val = getObjectMapper().writeValueAsString(req);
		
		
		
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType,val);
		//HttpUrl translateURL="http://localhost:8080/bank/commonChangeService/";
		Request request = new Request
				.Builder()
				.put(requestBody)
				.url(requestURL)
				.build();
		OkHttpClient okHttpClient = new OkHttpClient();
		okHttpClient.setConnectTimeout(110, TimeUnit.SECONDS); // connect timeout
		okHttpClient.setReadTimeout(110, TimeUnit.SECONDS);    // socket timeout
		Response response = okHttpClient.newCall(request).execute();
		String result = response.body().string();
		log("server returns:" + result);
		
		
		
		return req;
		
		
		
		
			
		
	}
	public static void main(String args[]) {
		
		
		
		
		//ChangeRequestService service = new ChangeRequestService();
		//CommonChangeRequest request = new CommonChangeRequest();
		//request.getItemList().add(new ChangeRequestItem());
		//service.process(null, request);
		Platform  platform = Platform.refById("P000001");
		ChangeRequest req = new ChangeRequest()
				.updateName("test cr")
				.updatePlatform(platform);
				
		Transaction tx = new Transaction().updateName("test tx")
				.updateFromAccount(Account.refById("A000001"))
				.updateToAccount(Account.refById("A000002"))
				.updateType("转账")
				
				.updateAmount(new BigDecimal("21.00"));
		
		
		
		
		req.addTransaction(tx);
		
		tx = new Transaction().updateName("test tx3")
					.updateFromAccount(Account.refById("A000002"))
					.updateToAccount(Account.refById("A000001"))
					.updateType("转账")
					.updateAmount(new BigDecimal("11.00"));
			
		req.addTransaction(tx);
		
		NameChangeEvent ne=new NameChangeEvent()
				.updateAccount(Account.refById("A000001"))
				.updateName("n"+(int)(Math.random()*10000));
		req.addNameChangeEvent(ne);
		ne=new NameChangeEvent()
				.updateAccount(Account.refById("A000002"))
				.updateName("n"+(int)(Math.random()*10000));
		
		
		req.addNameChangeEvent(ne);
		
		
		
		
		
		
		
		
		BankObjectChecker checker=new BankObjectChecker();
		
		
		checker.checkChangeRequestAsObject(req);
		
		
		
		
		
		
		
		
		
		try {
			
			String val = getObjectMapper().writeValueAsString(req);
			log(val);
			
			checker.throwExceptionIfHasErrors(ChangeRequestManagerException.class);
			IntStream.range(0, 10).forEach(
					nbr -> {
						try {
							emitRequest(req);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					}
				);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
