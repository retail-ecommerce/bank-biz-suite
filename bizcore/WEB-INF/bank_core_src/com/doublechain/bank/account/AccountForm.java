package com.doublechain.bank.account;
import com.doublechain.bank.BaseForm;
import com.doublechain.bank.genericform.GenericForm;
import com.doublechain.bank.formfield.FormField;
import com.doublechain.bank.formaction.FormAction;
import com.doublechain.bank.formmessage.FormMessage;
import com.doublechain.bank.formfieldmessage.FormFieldMessage;



public class AccountForm extends BaseForm {
	
	
	public AccountForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public AccountForm accountIdField(String parameterName, String initValue){
		FormField field = idFromAccount(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm accountIdField(String initValue){
		return accountIdField("accountId",initValue);
	}
	public AccountForm accountIdField(){
		return accountIdField("accountId","");
	}


	public AccountForm nameField(String parameterName, String initValue){
		FormField field = nameFromAccount(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public AccountForm nameField(){
		return nameField("name","");
	}


	public AccountForm balanceField(String parameterName, String initValue){
		FormField field = balanceFromAccount(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm balanceField(String initValue){
		return balanceField("balance",initValue);
	}
	public AccountForm balanceField(){
		return balanceField("balance","");
	}


	public AccountForm createTimeField(String parameterName, String initValue){
		FormField field = createTimeFromAccount(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm createTimeField(String initValue){
		return createTimeField("createTime",initValue);
	}
	public AccountForm createTimeField(){
		return createTimeField("createTime","");
	}


	public AccountForm updateTimeField(String parameterName, String initValue){
		FormField field = updateTimeFromAccount(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm updateTimeField(String initValue){
		return updateTimeField("updateTime",initValue);
	}
	public AccountForm updateTimeField(){
		return updateTimeField("updateTime","");
	}


	public AccountForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromAccount(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public AccountForm platformIdField(){
		return platformIdField("platformId","");
	}

	
	


	public AccountForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public AccountForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public AccountForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public AccountForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public AccountForm foundedFieldOfPlatform(String parameterName, String initValue){
		FormField field =  foundedFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public AccountForm foundedFieldOfPlatform(String initValue){
		return foundedFieldOfPlatform("founded",initValue);
	}
	public AccountForm foundedFieldOfPlatform(){
		return foundedFieldOfPlatform("founded","");
	}

	



	public AccountForm transactionIdFieldForTransaction(String parameterName, String initValue){
		FormField field =  idFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm transactionIdFieldForTransaction(String initValue){
		return transactionIdFieldForTransaction("transactionId",initValue);
	}
	public AccountForm transactionIdFieldForTransaction(){
		return transactionIdFieldForTransaction("transactionId","");
	}


	public AccountForm nameFieldForTransaction(String parameterName, String initValue){
		FormField field =  nameFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm nameFieldForTransaction(String initValue){
		return nameFieldForTransaction("name",initValue);
	}
	public AccountForm nameFieldForTransaction(){
		return nameFieldForTransaction("name","");
	}


	public AccountForm fromAccountIdFieldForTransaction(String parameterName, String initValue){
		FormField field =  fromAccountIdFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm fromAccountIdFieldForTransaction(String initValue){
		return fromAccountIdFieldForTransaction("fromAccountId",initValue);
	}
	public AccountForm fromAccountIdFieldForTransaction(){
		return fromAccountIdFieldForTransaction("fromAccountId","");
	}


	public AccountForm toAccountIdFieldForTransaction(String parameterName, String initValue){
		FormField field =  toAccountIdFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm toAccountIdFieldForTransaction(String initValue){
		return toAccountIdFieldForTransaction("toAccountId",initValue);
	}
	public AccountForm toAccountIdFieldForTransaction(){
		return toAccountIdFieldForTransaction("toAccountId","");
	}


	public AccountForm amountFieldForTransaction(String parameterName, String initValue){
		FormField field =  amountFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm amountFieldForTransaction(String initValue){
		return amountFieldForTransaction("amount",initValue);
	}
	public AccountForm amountFieldForTransaction(){
		return amountFieldForTransaction("amount","");
	}


	public AccountForm typeFieldForTransaction(String parameterName, String initValue){
		FormField field =  typeFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm typeFieldForTransaction(String initValue){
		return typeFieldForTransaction("type",initValue);
	}
	public AccountForm typeFieldForTransaction(){
		return typeFieldForTransaction("type","");
	}


	public AccountForm changeRequestIdFieldForTransaction(String parameterName, String initValue){
		FormField field =  changeRequestIdFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm changeRequestIdFieldForTransaction(String initValue){
		return changeRequestIdFieldForTransaction("changeRequestId",initValue);
	}
	public AccountForm changeRequestIdFieldForTransaction(){
		return changeRequestIdFieldForTransaction("changeRequestId","");
	}


	public AccountForm nameChangeEventIdFieldForNameChangeEvent(String parameterName, String initValue){
		FormField field =  idFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm nameChangeEventIdFieldForNameChangeEvent(String initValue){
		return nameChangeEventIdFieldForNameChangeEvent("nameChangeEventId",initValue);
	}
	public AccountForm nameChangeEventIdFieldForNameChangeEvent(){
		return nameChangeEventIdFieldForNameChangeEvent("nameChangeEventId","");
	}


	public AccountForm nameFieldForNameChangeEvent(String parameterName, String initValue){
		FormField field =  nameFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm nameFieldForNameChangeEvent(String initValue){
		return nameFieldForNameChangeEvent("name",initValue);
	}
	public AccountForm nameFieldForNameChangeEvent(){
		return nameFieldForNameChangeEvent("name","");
	}


	public AccountForm accountIdFieldForNameChangeEvent(String parameterName, String initValue){
		FormField field =  accountIdFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm accountIdFieldForNameChangeEvent(String initValue){
		return accountIdFieldForNameChangeEvent("accountId",initValue);
	}
	public AccountForm accountIdFieldForNameChangeEvent(){
		return accountIdFieldForNameChangeEvent("accountId","");
	}


	public AccountForm changeRequestIdFieldForNameChangeEvent(String parameterName, String initValue){
		FormField field =  changeRequestIdFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm changeRequestIdFieldForNameChangeEvent(String initValue){
		return changeRequestIdFieldForNameChangeEvent("changeRequestId",initValue);
	}
	public AccountForm changeRequestIdFieldForNameChangeEvent(){
		return changeRequestIdFieldForNameChangeEvent("changeRequestId","");
	}


	public AccountForm accountChangeIdFieldForAccountChange(String parameterName, String initValue){
		FormField field =  idFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm accountChangeIdFieldForAccountChange(String initValue){
		return accountChangeIdFieldForAccountChange("accountChangeId",initValue);
	}
	public AccountForm accountChangeIdFieldForAccountChange(){
		return accountChangeIdFieldForAccountChange("accountChangeId","");
	}


	public AccountForm nameFieldForAccountChange(String parameterName, String initValue){
		FormField field =  nameFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm nameFieldForAccountChange(String initValue){
		return nameFieldForAccountChange("name",initValue);
	}
	public AccountForm nameFieldForAccountChange(){
		return nameFieldForAccountChange("name","");
	}


	public AccountForm accountIdFieldForAccountChange(String parameterName, String initValue){
		FormField field =  accountIdFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm accountIdFieldForAccountChange(String initValue){
		return accountIdFieldForAccountChange("accountId",initValue);
	}
	public AccountForm accountIdFieldForAccountChange(){
		return accountIdFieldForAccountChange("accountId","");
	}


	public AccountForm previousBalanceFieldForAccountChange(String parameterName, String initValue){
		FormField field =  previousBalanceFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm previousBalanceFieldForAccountChange(String initValue){
		return previousBalanceFieldForAccountChange("previousBalance",initValue);
	}
	public AccountForm previousBalanceFieldForAccountChange(){
		return previousBalanceFieldForAccountChange("previousBalance","");
	}


	public AccountForm typeFieldForAccountChange(String parameterName, String initValue){
		FormField field =  typeFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm typeFieldForAccountChange(String initValue){
		return typeFieldForAccountChange("type",initValue);
	}
	public AccountForm typeFieldForAccountChange(){
		return typeFieldForAccountChange("type","");
	}


	public AccountForm amountFieldForAccountChange(String parameterName, String initValue){
		FormField field =  amountFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm amountFieldForAccountChange(String initValue){
		return amountFieldForAccountChange("amount",initValue);
	}
	public AccountForm amountFieldForAccountChange(){
		return amountFieldForAccountChange("amount","");
	}


	public AccountForm currentBalanceFieldForAccountChange(String parameterName, String initValue){
		FormField field =  currentBalanceFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm currentBalanceFieldForAccountChange(String initValue){
		return currentBalanceFieldForAccountChange("currentBalance",initValue);
	}
	public AccountForm currentBalanceFieldForAccountChange(){
		return currentBalanceFieldForAccountChange("currentBalance","");
	}


	public AccountForm changeRequestIdFieldForAccountChange(String parameterName, String initValue){
		FormField field =  changeRequestIdFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public AccountForm changeRequestIdFieldForAccountChange(String initValue){
		return changeRequestIdFieldForAccountChange("changeRequestId",initValue);
	}
	public AccountForm changeRequestIdFieldForAccountChange(){
		return changeRequestIdFieldForAccountChange("changeRequestId","");
	}

	

	
 	public AccountForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/accountId/");
		this.addFormAction(action);
		return this;
	}

 

	public AccountForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


