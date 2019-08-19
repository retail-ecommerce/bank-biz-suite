package com.doublechain.bank.namechangeevent;
import com.doublechain.bank.BaseForm;
import com.doublechain.bank.genericform.GenericForm;
import com.doublechain.bank.formfield.FormField;
import com.doublechain.bank.formaction.FormAction;
import com.doublechain.bank.formmessage.FormMessage;
import com.doublechain.bank.formfieldmessage.FormFieldMessage;



public class NameChangeEventForm extends BaseForm {
	
	
	public NameChangeEventForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public NameChangeEventForm nameChangeEventIdField(String parameterName, String initValue){
		FormField field = idFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public NameChangeEventForm nameChangeEventIdField(String initValue){
		return nameChangeEventIdField("nameChangeEventId",initValue);
	}
	public NameChangeEventForm nameChangeEventIdField(){
		return nameChangeEventIdField("nameChangeEventId","");
	}


	public NameChangeEventForm nameField(String parameterName, String initValue){
		FormField field = nameFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public NameChangeEventForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public NameChangeEventForm nameField(){
		return nameField("name","");
	}


	public NameChangeEventForm accountIdField(String parameterName, String initValue){
		FormField field = accountIdFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public NameChangeEventForm accountIdField(String initValue){
		return accountIdField("accountId",initValue);
	}
	public NameChangeEventForm accountIdField(){
		return accountIdField("accountId","");
	}


	public NameChangeEventForm changeRequestIdField(String parameterName, String initValue){
		FormField field = changeRequestIdFromNameChangeEvent(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public NameChangeEventForm changeRequestIdField(String initValue){
		return changeRequestIdField("changeRequestId",initValue);
	}
	public NameChangeEventForm changeRequestIdField(){
		return changeRequestIdField("changeRequestId","");
	}

	
	


	public NameChangeEventForm accountIdFieldOfAccount(String parameterName, String initValue){
		FormField field =  idFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm accountIdFieldOfAccount(String initValue){
		return accountIdFieldOfAccount("accountId",initValue);
	}
	public NameChangeEventForm accountIdFieldOfAccount(){
		return accountIdFieldOfAccount("accountId","");
	}


	public NameChangeEventForm nameFieldOfAccount(String parameterName, String initValue){
		FormField field =  nameFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm nameFieldOfAccount(String initValue){
		return nameFieldOfAccount("name",initValue);
	}
	public NameChangeEventForm nameFieldOfAccount(){
		return nameFieldOfAccount("name","");
	}


	public NameChangeEventForm balanceFieldOfAccount(String parameterName, String initValue){
		FormField field =  balanceFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm balanceFieldOfAccount(String initValue){
		return balanceFieldOfAccount("balance",initValue);
	}
	public NameChangeEventForm balanceFieldOfAccount(){
		return balanceFieldOfAccount("balance","");
	}


	public NameChangeEventForm createTimeFieldOfAccount(String parameterName, String initValue){
		FormField field =  createTimeFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm createTimeFieldOfAccount(String initValue){
		return createTimeFieldOfAccount("createTime",initValue);
	}
	public NameChangeEventForm createTimeFieldOfAccount(){
		return createTimeFieldOfAccount("createTime","");
	}


	public NameChangeEventForm updateTimeFieldOfAccount(String parameterName, String initValue){
		FormField field =  updateTimeFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm updateTimeFieldOfAccount(String initValue){
		return updateTimeFieldOfAccount("updateTime",initValue);
	}
	public NameChangeEventForm updateTimeFieldOfAccount(){
		return updateTimeFieldOfAccount("updateTime","");
	}


	public NameChangeEventForm platformIdFieldOfAccount(String parameterName, String initValue){
		FormField field =  platformIdFromAccount(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm platformIdFieldOfAccount(String initValue){
		return platformIdFieldOfAccount("platformId",initValue);
	}
	public NameChangeEventForm platformIdFieldOfAccount(){
		return platformIdFieldOfAccount("platformId","");
	}


	public NameChangeEventForm changeRequestIdFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  idFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm changeRequestIdFieldOfChangeRequest(String initValue){
		return changeRequestIdFieldOfChangeRequest("changeRequestId",initValue);
	}
	public NameChangeEventForm changeRequestIdFieldOfChangeRequest(){
		return changeRequestIdFieldOfChangeRequest("changeRequestId","");
	}


	public NameChangeEventForm nameFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  nameFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm nameFieldOfChangeRequest(String initValue){
		return nameFieldOfChangeRequest("name",initValue);
	}
	public NameChangeEventForm nameFieldOfChangeRequest(){
		return nameFieldOfChangeRequest("name","");
	}


	public NameChangeEventForm createTimeFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  createTimeFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm createTimeFieldOfChangeRequest(String initValue){
		return createTimeFieldOfChangeRequest("createTime",initValue);
	}
	public NameChangeEventForm createTimeFieldOfChangeRequest(){
		return createTimeFieldOfChangeRequest("createTime","");
	}


	public NameChangeEventForm platformIdFieldOfChangeRequest(String parameterName, String initValue){
		FormField field =  platformIdFromChangeRequest(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public NameChangeEventForm platformIdFieldOfChangeRequest(String initValue){
		return platformIdFieldOfChangeRequest("platformId",initValue);
	}
	public NameChangeEventForm platformIdFieldOfChangeRequest(){
		return platformIdFieldOfChangeRequest("platformId","");
	}

	


	

	
 	public NameChangeEventForm transferToAnotherAccountAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherAccount/nameChangeEventId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public NameChangeEventForm transferToAnotherChangeRequestAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherChangeRequest/nameChangeEventId/");
		this.addFormAction(action);
		return this;
	}

 

	public NameChangeEventForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


