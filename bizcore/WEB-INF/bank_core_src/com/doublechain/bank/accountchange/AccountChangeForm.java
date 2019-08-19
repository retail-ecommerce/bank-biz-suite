package com.doublechain.bank.accountchange;
import com.doublechain.bank.BaseForm;
import com.doublechain.bank.genericform.GenericForm;
import com.doublechain.bank.formfield.FormField;
import com.doublechain.bank.formaction.FormAction;
import com.doublechain.bank.formmessage.FormMessage;
import com.doublechain.bank.formfieldmessage.FormFieldMessage;



public class AccountChangeForm extends BaseForm {
	
	
	public AccountChangeForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public AccountChangeForm accountChangeIdField(String parameterName, String initValue){
		FormField field = idFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountChangeForm accountChangeIdField(String initValue){
		return accountChangeIdField("accountChangeId",initValue);
	}
	public AccountChangeForm accountChangeIdField(){
		return accountChangeIdField("accountChangeId","");
	}


	public AccountChangeForm nameField(String parameterName, String initValue){
		FormField field = nameFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountChangeForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public AccountChangeForm nameField(){
		return nameField("name","");
	}


	public AccountChangeForm previousBalanceField(String parameterName, String initValue){
		FormField field = previousBalanceFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountChangeForm previousBalanceField(String initValue){
		return previousBalanceField("previousBalance",initValue);
	}
	public AccountChangeForm previousBalanceField(){
		return previousBalanceField("previousBalance","");
	}


	public AccountChangeForm typeField(String parameterName, String initValue){
		FormField field = typeFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountChangeForm typeField(String initValue){
		return typeField("type",initValue);
	}
	public AccountChangeForm typeField(){
		return typeField("type","");
	}


	public AccountChangeForm amountField(String parameterName, String initValue){
		FormField field = amountFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountChangeForm amountField(String initValue){
		return amountField("amount",initValue);
	}
	public AccountChangeForm amountField(){
		return amountField("amount","");
	}


	public AccountChangeForm currentBalanceField(String parameterName, String initValue){
		FormField field = currentBalanceFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountChangeForm currentBalanceField(String initValue){
		return currentBalanceField("currentBalance",initValue);
	}
	public AccountChangeForm currentBalanceField(){
		return currentBalanceField("currentBalance","");
	}


	public AccountChangeForm accountIdField(String parameterName, String initValue){
		FormField field = accountIdFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountChangeForm accountIdField(String initValue){
		return accountIdField("accountId",initValue);
	}
	public AccountChangeForm accountIdField(){
		return accountIdField("accountId","");
	}


	public AccountChangeForm changeRequestIdField(String parameterName, String initValue){
		FormField field = changeRequestIdFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountChangeForm changeRequestIdField(String initValue){
		return changeRequestIdField("changeRequestId",initValue);
	}
	public AccountChangeForm changeRequestIdField(){
		return changeRequestIdField("changeRequestId","");
	}


	public AccountChangeForm currentStatusField(String parameterName, String initValue){
		FormField field = currentStatusFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountChangeForm currentStatusField(String initValue){
		return currentStatusField("currentStatus",initValue);
	}
	public AccountChangeForm currentStatusField(){
		return currentStatusField("currentStatus","");
	}

	
	


	public AccountChangeForm accountIdFieldOfAccount(String parameterName, String initValue){
		FormField field =  idFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm accountIdFieldOfAccount(String initValue){
		return accountIdFieldOfAccount("accountId",initValue);
	}
	public AccountChangeForm accountIdFieldOfAccount(){
		return accountIdFieldOfAccount("accountId","");
	}


	public AccountChangeForm nameFieldOfAccount(String parameterName, String initValue){
		FormField field =  nameFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm nameFieldOfAccount(String initValue){
		return nameFieldOfAccount("name",initValue);
	}
	public AccountChangeForm nameFieldOfAccount(){
		return nameFieldOfAccount("name","");
	}


	public AccountChangeForm balanceFieldOfAccount(String parameterName, String initValue){
		FormField field =  balanceFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm balanceFieldOfAccount(String initValue){
		return balanceFieldOfAccount("balance",initValue);
	}
	public AccountChangeForm balanceFieldOfAccount(){
		return balanceFieldOfAccount("balance","");
	}


	public AccountChangeForm createTimeFieldOfAccount(String parameterName, String initValue){
		FormField field =  createTimeFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm createTimeFieldOfAccount(String initValue){
		return createTimeFieldOfAccount("createTime",initValue);
	}
	public AccountChangeForm createTimeFieldOfAccount(){
		return createTimeFieldOfAccount("createTime","");
	}


	public AccountChangeForm updateTimeFieldOfAccount(String parameterName, String initValue){
		FormField field =  updateTimeFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm updateTimeFieldOfAccount(String initValue){
		return updateTimeFieldOfAccount("updateTime",initValue);
	}
	public AccountChangeForm updateTimeFieldOfAccount(){
		return updateTimeFieldOfAccount("updateTime","");
	}


	public AccountChangeForm platformIdFieldOfAccount(String parameterName, String initValue){
		FormField field =  platformIdFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm platformIdFieldOfAccount(String initValue){
		return platformIdFieldOfAccount("platformId",initValue);
	}
	public AccountChangeForm platformIdFieldOfAccount(){
		return platformIdFieldOfAccount("platformId","");
	}


	public AccountChangeForm changeRequestIdFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  idFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm changeRequestIdFieldOfChangeRequest(String initValue){
		return changeRequestIdFieldOfChangeRequest("changeRequestId",initValue);
	}
	public AccountChangeForm changeRequestIdFieldOfChangeRequest(){
		return changeRequestIdFieldOfChangeRequest("changeRequestId","");
	}


	public AccountChangeForm nameFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  nameFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm nameFieldOfChangeRequest(String initValue){
		return nameFieldOfChangeRequest("name",initValue);
	}
	public AccountChangeForm nameFieldOfChangeRequest(){
		return nameFieldOfChangeRequest("name","");
	}


	public AccountChangeForm createTimeFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  createTimeFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm createTimeFieldOfChangeRequest(String initValue){
		return createTimeFieldOfChangeRequest("createTime",initValue);
	}
	public AccountChangeForm createTimeFieldOfChangeRequest(){
		return createTimeFieldOfChangeRequest("createTime","");
	}


	public AccountChangeForm platformIdFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  platformIdFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountChangeForm platformIdFieldOfChangeRequest(String initValue){
		return platformIdFieldOfChangeRequest("platformId",initValue);
	}
	public AccountChangeForm platformIdFieldOfChangeRequest(){
		return platformIdFieldOfChangeRequest("platformId","");
	}

	


	

	
 	public AccountChangeForm transferToAnotherAccountAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherAccount/accountChangeId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public AccountChangeForm  requestChangeAction(){
		FormAction action = new FormAction();
		action.setLabel("变更请求");
		action.setLocaleKey("account_change.requestChange");
		action.setUrl("accountChangeManager/requestChange/accountChangeId/name/platformId");
		this.addFormAction(action);
		return this;
	}

	public AccountChangeForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


