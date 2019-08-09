package com.doublechain.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.terapico.caf.form.ImageInfo;
import com.terapico.utils.DebugUtil;

public class BaseBankFormProcessor extends BaseFormProcessor{
	protected BankUserContext userContext;
	
	public BankUserContext getUserContext() {
		return userContext;
	}
	public void setUserContext(BankUserContext userContext) {
		this.userContext = userContext;
	}
	protected void addMessageToException(BankException e, String msg) {
		Message message = new Message();
		message.setBody(msg);
		e.addErrorMessage(message);
	}
	public Map<String, Object> mapToUiForm(BankUserContext userContext) {
		return null; 
	}
}















