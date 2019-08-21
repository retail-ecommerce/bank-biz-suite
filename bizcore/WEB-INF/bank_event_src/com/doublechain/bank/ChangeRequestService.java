package com.doublechain.bank;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.doublechain.bank.account.Account;
import com.doublechain.bank.account.AccountManagerException;
import com.doublechain.bank.accountchange.AccountChange;
import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.changerequest.ChangeRequestManager;
import com.doublechain.bank.changerequest.ChangeRequestManagerException;
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

public class ChangeRequestService extends CustomBankCheckerManager{
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
		
		
		checkerOf(userContext).checkChangeRequestAsObject(request);
		
		
		ChangeRequest newReq = changeRequestManagerOf(userContext)
			.internalSaveChangeRequest(userContext, request);
		
		for(NameChangeEvent nv:request.getNameChangeEventList() ) {
			
			NameChangeEventHandler handler = new NameChangeEventHandler();
			handler.apply(userContext, newReq, nv);
		}
		
		for(Transaction tx:request.getTransactionList() ) {
			TxRequestHandler handler = new TxRequestHandler();
			handler.apply(userContext, newReq, tx);
		}
		
		
		return request;
		
	}
	
	
	
	
	
}

/*
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
 * 
 * 
 * */
