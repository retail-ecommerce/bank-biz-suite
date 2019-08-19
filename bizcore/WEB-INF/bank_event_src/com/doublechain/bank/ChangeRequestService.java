package com.doublechain.bank;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.doublechain.bank.account.Account;
import com.doublechain.bank.accountchange.AccountChange;
import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.changerequest.ChangeRequestTokens;
import com.doublechain.bank.namechangeevent.NameChangeEvent;
import com.doublechain.bank.platform.Platform;
import com.doublechain.bank.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.terapico.caf.Password;
import com.terapico.uccaf.BaseUserContext;

public class ChangeRequestService extends BaseManagerImpl{
	@Override
	public Object checkAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters)
			throws IllegalAccessException {
	

		String whiteList[]= {
				"process",
				
				"process3",
				"process4","sample","unreg",
				"testPassword"};
		
		if(Arrays.asList(whiteList).contains(methodName)) {
			return this.accessOK();
		}

		
		return super.checkAccess(baseUserContext, methodName, parameters);
	}
	public ChangeRequest process(BankUserContext userContext, ChangeRequest request) throws Exception {
		
		
		ChangeRequest newReq = userContext.getManagerGroup()
			.getChangeRequestManager()
			.internalSaveChangeRequest(userContext, request);
		
		for(NameChangeEvent nv:request.getNameChangeEventList() ) {
			Account a1 = userContext.getManagerGroup().getAccountManager()
					.loadAccount(userContext, nv.getAccount().getId(), new String[] {});
			a1.updateName(nv.getName());
			userContext.getManagerGroup().getAccountManager().internalSaveAccount(userContext, a1);
			
		}
		
		for(Transaction tx:request.getTransactionList() ) {
			Account a1 = userContext.getManagerGroup().getAccountManager().loadAccount(userContext, 
					tx.getFromAccount().getId(), new String[] {});
			Account a2 = userContext.getManagerGroup().getAccountManager().loadAccount(userContext, 
					tx.getToAccount().getId(), new String[] {});
			
			
			AccountChange ac1 = new AccountChange().updateAmount(tx.getAmount())
					.updateName(tx.getName()).updatePreviousBalance(a1.getBalance())
					.updateType("转出").updateCurrentBalance(a1.getBalance().subtract(tx.getAmount()))
					.updateChangeRequest(newReq);
			
			AccountChange ac2=	new AccountChange().updateAmount(tx.getAmount())
					.updateName(tx.getName()).updatePreviousBalance(a2.getBalance())
					.updateType("转入").updateCurrentBalance(a2.getBalance().add(tx.getAmount()))
					.updateChangeRequest(newReq);
			a1.addAccountChange(ac1);
			a2.addAccountChange(ac2);
			a1.updateBalance(a1.getBalance().subtract(tx.getAmount()));
			a2.updateBalance(a2.getBalance().add(tx.getAmount()));
			
			
			userContext.getManagerGroup().getAccountManager().internalSaveAccount(userContext, a1);
			userContext.getManagerGroup().getAccountManager().internalSaveAccount(userContext, a2);
			
			
			
			
		}
		
		
		return request;
		
		/*
		
		for(ChangeRequestItem item: request.getItemList()) {
			
			RequestHandler handler=lookupHandler( userContext,item);
			
			if(handler==null) {
				continue;
			}
			
			handler.apply(userContext, request, item);
			
		}*/
		
		
		
	}
	//curl -X PUT -d hello http://localhost:8080/termsys/commonChangeService/process2/
	public ChangeRequest process2(BankUserContext userContext, ChangeRequest changeRequest) throws Exception {
		
		userContext.getManagerGroup().getChangeRequestManager().internalSaveChangeRequest(userContext, changeRequest);
		
		userContext.log(changeRequest.toString());
		return changeRequest;
	}
	public String process3(BankUserContext userContext, String changeRequest) {
		
		userContext.log(changeRequest);
		return changeRequest;
	}
	//curl -X POST -d password=clearTextPassword http://localhost:8080/termsys/commonChangeService/testPassword/password/
	public String testPassword(BankUserContext userContext, Password password) {
		
		//userContext.log(changeRequest);
		return password.getClearTextPassword();
	}
	//curl -X PUT -d hello http://localhost:8080/termsys/commonChangeService/process4/
	public String process4(BankUserContext userContext, byte[] changeRequest) {
		Runtime instance = Runtime.getRuntime();
		//instance.
		userContext.log("receive bytes "+changeRequest.length);
		//return instance.freeMemory()+"/"+instance.totalMemory();
		return instance.totalMemory()-instance.freeMemory()+"/";
	}
	
	
	
	
	public ChangeRequest sample(BankUserContext userContext, String sampleId) throws Exception {
		
		ChangeRequest request = userContext.getManagerGroup().getChangeRequestManager()
				.loadChangeRequest(userContext,
				sampleId, 
				ChangeRequestTokens.start().withTransactionList().toArray());
		return request;
	}
	
	
	protected RequestHandler lookupHandler(BankUserContext userContext, ChangeRequestItem item) {
		
		
		RequestHandler handler=(RequestHandler) userContext.getBean(item.getHandlerBeanName());
		if(handler==null) {
			return null;
		}
		
		return handler;
	}
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
	protected static ChangeRequest emitRequest(ChangeRequest req) throws IOException {
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
		
		ChangeRequest req = new ChangeRequest()
				.updateName("test cr")
				.updatePlatform(new Platform().updateId("P000001"));
				
		Transaction tx = new Transaction().updateName("test tx")
				.updateFromAccount(new Account().updateId("A000001"))
				.updateToAccount(new Account().updateId("A000002"))
				.updateType("转账")
				
				.updateAmount(new BigDecimal("21.00"));
		
		
		
		
		req.addTransaction(tx);
		
		tx = new Transaction().updateName("test tx")
					.updateFromAccount(new Account().updateId("A000002"))
					.updateToAccount(new Account().updateId("A000001"))
					.updateType("转账")
					
					.updateAmount(new BigDecimal("11.00"));
			
		req.addTransaction(tx);
		
		NameChangeEvent ne=new NameChangeEvent()
				.updateAccount(new Account().updateId("A000002"))
				.updateName("OLD NAME");
		req.addNameChangeEvent(ne);
		
		
		try {
			emitRequest(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
