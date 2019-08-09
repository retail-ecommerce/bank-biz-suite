package com.doublechain.bank.transaction;
import com.doublechain.bank.BaseForm;
import com.doublechain.bank.genericform.GenericForm;
import com.doublechain.bank.formfield.FormField;
import com.doublechain.bank.formaction.FormAction;
import com.doublechain.bank.formmessage.FormMessage;
import com.doublechain.bank.formfieldmessage.FormFieldMessage;



public class TransactionForm extends BaseForm {
	
	
	public TransactionForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public TransactionForm transactionIdField(String parameterName, String initValue){
		FormField field = idFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TransactionForm transactionIdField(String initValue){
		return transactionIdField("transactionId",initValue);
	}
	public TransactionForm transactionIdField(){
		return transactionIdField("transactionId","");
	}


	public TransactionForm nameField(String parameterName, String initValue){
		FormField field = nameFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TransactionForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public TransactionForm nameField(){
		return nameField("name","");
	}


	public TransactionForm fromAccountIdField(String parameterName, String initValue){
		FormField field = fromAccountIdFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TransactionForm fromAccountIdField(String initValue){
		return fromAccountIdField("fromAccountId",initValue);
	}
	public TransactionForm fromAccountIdField(){
		return fromAccountIdField("fromAccountId","");
	}


	public TransactionForm toAccountIdField(String parameterName, String initValue){
		FormField field = toAccountIdFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TransactionForm toAccountIdField(String initValue){
		return toAccountIdField("toAccountId",initValue);
	}
	public TransactionForm toAccountIdField(){
		return toAccountIdField("toAccountId","");
	}


	public TransactionForm amountField(String parameterName, String initValue){
		FormField field = amountFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TransactionForm amountField(String initValue){
		return amountField("amount",initValue);
	}
	public TransactionForm amountField(){
		return amountField("amount","");
	}


	public TransactionForm typeField(String parameterName, String initValue){
		FormField field = typeFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TransactionForm typeField(String initValue){
		return typeField("type",initValue);
	}
	public TransactionForm typeField(){
		return typeField("type","");
	}


	public TransactionForm changeRequestIdField(String parameterName, String initValue){
		FormField field = changeRequestIdFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public TransactionForm changeRequestIdField(String initValue){
		return changeRequestIdField("changeRequestId",initValue);
	}
	public TransactionForm changeRequestIdField(){
		return changeRequestIdField("changeRequestId","");
	}

	
	


	public TransactionForm accountIdFieldOfAccount(String parameterName, String initValue){
		FormField field =  idFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm accountIdFieldOfAccount(String initValue){
		return accountIdFieldOfAccount("accountId",initValue);
	}
	public TransactionForm accountIdFieldOfAccount(){
		return accountIdFieldOfAccount("accountId","");
	}


	public TransactionForm nameFieldOfAccount(String parameterName, String initValue){
		FormField field =  nameFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm nameFieldOfAccount(String initValue){
		return nameFieldOfAccount("name",initValue);
	}
	public TransactionForm nameFieldOfAccount(){
		return nameFieldOfAccount("name","");
	}


	public TransactionForm balanceFieldOfAccount(String parameterName, String initValue){
		FormField field =  balanceFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm balanceFieldOfAccount(String initValue){
		return balanceFieldOfAccount("balance",initValue);
	}
	public TransactionForm balanceFieldOfAccount(){
		return balanceFieldOfAccount("balance","");
	}


	public TransactionForm createTimeFieldOfAccount(String parameterName, String initValue){
		FormField field =  createTimeFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm createTimeFieldOfAccount(String initValue){
		return createTimeFieldOfAccount("createTime",initValue);
	}
	public TransactionForm createTimeFieldOfAccount(){
		return createTimeFieldOfAccount("createTime","");
	}


	public TransactionForm updateTimeFieldOfAccount(String parameterName, String initValue){
		FormField field =  updateTimeFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm updateTimeFieldOfAccount(String initValue){
		return updateTimeFieldOfAccount("updateTime",initValue);
	}
	public TransactionForm updateTimeFieldOfAccount(){
		return updateTimeFieldOfAccount("updateTime","");
	}


	public TransactionForm platformIdFieldOfAccount(String parameterName, String initValue){
		FormField field =  platformIdFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm platformIdFieldOfAccount(String initValue){
		return platformIdFieldOfAccount("platformId",initValue);
	}
	public TransactionForm platformIdFieldOfAccount(){
		return platformIdFieldOfAccount("platformId","");
	}


	public TransactionForm changeRequestIdFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  idFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm changeRequestIdFieldOfChangeRequest(String initValue){
		return changeRequestIdFieldOfChangeRequest("changeRequestId",initValue);
	}
	public TransactionForm changeRequestIdFieldOfChangeRequest(){
		return changeRequestIdFieldOfChangeRequest("changeRequestId","");
	}


	public TransactionForm nameFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  nameFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm nameFieldOfChangeRequest(String initValue){
		return nameFieldOfChangeRequest("name",initValue);
	}
	public TransactionForm nameFieldOfChangeRequest(){
		return nameFieldOfChangeRequest("name","");
	}


	public TransactionForm createTimeFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  createTimeFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm createTimeFieldOfChangeRequest(String initValue){
		return createTimeFieldOfChangeRequest("createTime",initValue);
	}
	public TransactionForm createTimeFieldOfChangeRequest(){
		return createTimeFieldOfChangeRequest("createTime","");
	}


	public TransactionForm platformIdFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  platformIdFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public TransactionForm platformIdFieldOfChangeRequest(String initValue){
		return platformIdFieldOfChangeRequest("platformId",initValue);
	}
	public TransactionForm platformIdFieldOfChangeRequest(){
		return platformIdFieldOfChangeRequest("platformId","");
	}

	


	

	
 	public TransactionForm transferToAnotherFromAccountAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherFromAccount/transactionId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public TransactionForm transferToAnotherToAccountAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherToAccount/transactionId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public TransactionForm transferToAnotherChangeRequestAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherChangeRequest/transactionId/");
		this.addFormAction(action);
		return this;
	}

 

	public TransactionForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


