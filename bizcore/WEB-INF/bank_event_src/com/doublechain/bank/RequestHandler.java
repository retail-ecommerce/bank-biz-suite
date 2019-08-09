package com.doublechain.bank;

import com.doublechain.bank.BankUserContext;

public class RequestHandler {
	
	public void apply(BankUserContext context, CommonChangeRequest request, ChangeRequestItem item) {
		
		log("applying change request ", item.getHandlerBeanName());
		
	}
	protected void log(String ...args) {
		System.out.println(String.join(" ", args));
	}
}	
