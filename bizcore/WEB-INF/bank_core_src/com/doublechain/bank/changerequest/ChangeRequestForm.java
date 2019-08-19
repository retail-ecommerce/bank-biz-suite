package com.doublechain.bank.changerequest;
import com.doublechain.bank.BaseForm;
import com.doublechain.bank.genericform.GenericForm;
import com.doublechain.bank.formfield.FormField;
import com.doublechain.bank.formaction.FormAction;
import com.doublechain.bank.formmessage.FormMessage;
import com.doublechain.bank.formfieldmessage.FormFieldMessage;



public class ChangeRequestForm extends BaseForm {
	
	
	public ChangeRequestForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public ChangeRequestForm changeRequestIdField(String parameterName, String initValue){
		FormField field = idFromChangeRequest(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm changeRequestIdField(String initValue){
		return changeRequestIdField("changeRequestId",initValue);
	}
	public ChangeRequestForm changeRequestIdField(){
		return changeRequestIdField("changeRequestId","");
	}


	public ChangeRequestForm nameField(String parameterName, String initValue){
		FormField field = nameFromChangeRequest(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public ChangeRequestForm nameField(){
		return nameField("name","");
	}


	public ChangeRequestForm createTimeField(String parameterName, String initValue){
		FormField field = createTimeFromChangeRequest(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm createTimeField(String initValue){
		return createTimeField("createTime",initValue);
	}
	public ChangeRequestForm createTimeField(){
		return createTimeField("createTime","");
	}


	public ChangeRequestForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromChangeRequest(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public ChangeRequestForm platformIdField(){
		return platformIdField("platformId","");
	}

	
	


	public ChangeRequestForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ChangeRequestForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public ChangeRequestForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public ChangeRequestForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ChangeRequestForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public ChangeRequestForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public ChangeRequestForm foundedFieldOfPlatform(String parameterName, String initValue){
		FormField field =  foundedFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ChangeRequestForm foundedFieldOfPlatform(String initValue){
		return foundedFieldOfPlatform("founded",initValue);
	}
	public ChangeRequestForm foundedFieldOfPlatform(){
		return foundedFieldOfPlatform("founded","");
	}

	



	public ChangeRequestForm transactionIdFieldForTransaction(String parameterName, String initValue){
		FormField field =  idFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm transactionIdFieldForTransaction(String initValue){
		return transactionIdFieldForTransaction("transactionId",initValue);
	}
	public ChangeRequestForm transactionIdFieldForTransaction(){
		return transactionIdFieldForTransaction("transactionId","");
	}


	public ChangeRequestForm nameFieldForTransaction(String parameterName, String initValue){
		FormField field =  nameFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm nameFieldForTransaction(String initValue){
		return nameFieldForTransaction("name",initValue);
	}
	public ChangeRequestForm nameFieldForTransaction(){
		return nameFieldForTransaction("name","");
	}


	public ChangeRequestForm fromAccountIdFieldForTransaction(String parameterName, String initValue){
		FormField field =  fromAccountIdFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm fromAccountIdFieldForTransaction(String initValue){
		return fromAccountIdFieldForTransaction("fromAccountId",initValue);
	}
	public ChangeRequestForm fromAccountIdFieldForTransaction(){
		return fromAccountIdFieldForTransaction("fromAccountId","");
	}


	public ChangeRequestForm toAccountIdFieldForTransaction(String parameterName, String initValue){
		FormField field =  toAccountIdFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm toAccountIdFieldForTransaction(String initValue){
		return toAccountIdFieldForTransaction("toAccountId",initValue);
	}
	public ChangeRequestForm toAccountIdFieldForTransaction(){
		return toAccountIdFieldForTransaction("toAccountId","");
	}


	public ChangeRequestForm amountFieldForTransaction(String parameterName, String initValue){
		FormField field =  amountFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm amountFieldForTransaction(String initValue){
		return amountFieldForTransaction("amount",initValue);
	}
	public ChangeRequestForm amountFieldForTransaction(){
		return amountFieldForTransaction("amount","");
	}


	public ChangeRequestForm typeFieldForTransaction(String parameterName, String initValue){
		FormField field =  typeFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm typeFieldForTransaction(String initValue){
		return typeFieldForTransaction("type",initValue);
	}
	public ChangeRequestForm typeFieldForTransaction(){
		return typeFieldForTransaction("type","");
	}


	public ChangeRequestForm changeRequestIdFieldForTransaction(String parameterName, String initValue){
		FormField field =  changeRequestIdFromTransaction(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm changeRequestIdFieldForTransaction(String initValue){
		return changeRequestIdFieldForTransaction("changeRequestId",initValue);
	}
	public ChangeRequestForm changeRequestIdFieldForTransaction(){
		return changeRequestIdFieldForTransaction("changeRequestId","");
	}


	public ChangeRequestForm nameChangeEventIdFieldForNameChangeEvent(String parameterName, String initValue){
		FormField field =  idFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm nameChangeEventIdFieldForNameChangeEvent(String initValue){
		return nameChangeEventIdFieldForNameChangeEvent("nameChangeEventId",initValue);
	}
	public ChangeRequestForm nameChangeEventIdFieldForNameChangeEvent(){
		return nameChangeEventIdFieldForNameChangeEvent("nameChangeEventId","");
	}


	public ChangeRequestForm nameFieldForNameChangeEvent(String parameterName, String initValue){
		FormField field =  nameFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm nameFieldForNameChangeEvent(String initValue){
		return nameFieldForNameChangeEvent("name",initValue);
	}
	public ChangeRequestForm nameFieldForNameChangeEvent(){
		return nameFieldForNameChangeEvent("name","");
	}


	public ChangeRequestForm accountIdFieldForNameChangeEvent(String parameterName, String initValue){
		FormField field =  accountIdFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm accountIdFieldForNameChangeEvent(String initValue){
		return accountIdFieldForNameChangeEvent("accountId",initValue);
	}
	public ChangeRequestForm accountIdFieldForNameChangeEvent(){
		return accountIdFieldForNameChangeEvent("accountId","");
	}


	public ChangeRequestForm changeRequestIdFieldForNameChangeEvent(String parameterName, String initValue){
		FormField field =  changeRequestIdFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm changeRequestIdFieldForNameChangeEvent(String initValue){
		return changeRequestIdFieldForNameChangeEvent("changeRequestId",initValue);
	}
	public ChangeRequestForm changeRequestIdFieldForNameChangeEvent(){
		return changeRequestIdFieldForNameChangeEvent("changeRequestId","");
	}


	public ChangeRequestForm accountChangeIdFieldForAccountChange(String parameterName, String initValue){
		FormField field =  idFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm accountChangeIdFieldForAccountChange(String initValue){
		return accountChangeIdFieldForAccountChange("accountChangeId",initValue);
	}
	public ChangeRequestForm accountChangeIdFieldForAccountChange(){
		return accountChangeIdFieldForAccountChange("accountChangeId","");
	}


	public ChangeRequestForm nameFieldForAccountChange(String parameterName, String initValue){
		FormField field =  nameFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm nameFieldForAccountChange(String initValue){
		return nameFieldForAccountChange("name",initValue);
	}
	public ChangeRequestForm nameFieldForAccountChange(){
		return nameFieldForAccountChange("name","");
	}


	public ChangeRequestForm accountIdFieldForAccountChange(String parameterName, String initValue){
		FormField field =  accountIdFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm accountIdFieldForAccountChange(String initValue){
		return accountIdFieldForAccountChange("accountId",initValue);
	}
	public ChangeRequestForm accountIdFieldForAccountChange(){
		return accountIdFieldForAccountChange("accountId","");
	}


	public ChangeRequestForm previousBalanceFieldForAccountChange(String parameterName, String initValue){
		FormField field =  previousBalanceFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm previousBalanceFieldForAccountChange(String initValue){
		return previousBalanceFieldForAccountChange("previousBalance",initValue);
	}
	public ChangeRequestForm previousBalanceFieldForAccountChange(){
		return previousBalanceFieldForAccountChange("previousBalance","");
	}


	public ChangeRequestForm typeFieldForAccountChange(String parameterName, String initValue){
		FormField field =  typeFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm typeFieldForAccountChange(String initValue){
		return typeFieldForAccountChange("type",initValue);
	}
	public ChangeRequestForm typeFieldForAccountChange(){
		return typeFieldForAccountChange("type","");
	}


	public ChangeRequestForm amountFieldForAccountChange(String parameterName, String initValue){
		FormField field =  amountFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm amountFieldForAccountChange(String initValue){
		return amountFieldForAccountChange("amount",initValue);
	}
	public ChangeRequestForm amountFieldForAccountChange(){
		return amountFieldForAccountChange("amount","");
	}


	public ChangeRequestForm currentBalanceFieldForAccountChange(String parameterName, String initValue){
		FormField field =  currentBalanceFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm currentBalanceFieldForAccountChange(String initValue){
		return currentBalanceFieldForAccountChange("currentBalance",initValue);
	}
	public ChangeRequestForm currentBalanceFieldForAccountChange(){
		return currentBalanceFieldForAccountChange("currentBalance","");
	}


	public ChangeRequestForm changeRequestIdFieldForAccountChange(String parameterName, String initValue){
		FormField field =  changeRequestIdFromAccountChange(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ChangeRequestForm changeRequestIdFieldForAccountChange(String initValue){
		return changeRequestIdFieldForAccountChange("changeRequestId",initValue);
	}
	public ChangeRequestForm changeRequestIdFieldForAccountChange(){
		return changeRequestIdFieldForAccountChange("changeRequestId","");
	}

	

	
 	public ChangeRequestForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/changeRequestId/");
		this.addFormAction(action);
		return this;
	}

 

	public ChangeRequestForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


