
package com.doublechain.bank.namechangeevent;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BaseEntity;


import com.doublechain.bank.Message;
import com.doublechain.bank.SmartList;
import com.doublechain.bank.MultipleAccessKey;

import com.doublechain.bank.BankUserContext;
//import com.doublechain.bank.BaseManagerImpl;
import com.doublechain.bank.BankCheckerManager;
import com.doublechain.bank.CustomBankCheckerManager;

import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.account.Account;

import com.doublechain.bank.changerequest.CandidateChangeRequest;
import com.doublechain.bank.account.CandidateAccount;







public class NameChangeEventManagerImpl extends CustomBankCheckerManager implements NameChangeEventManager {
	
	private static final String SERVICE_TYPE = "NameChangeEvent";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws NameChangeEventManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new NameChangeEventManagerException(message);

	}
	
	

 	protected NameChangeEvent saveNameChangeEvent(BankUserContext userContext, NameChangeEvent nameChangeEvent, String [] tokensExpr) throws Exception{	
 		//return getNameChangeEventDAO().save(nameChangeEvent, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveNameChangeEvent(userContext, nameChangeEvent, tokens);
 	}
 	
 	protected NameChangeEvent saveNameChangeEventDetail(BankUserContext userContext, NameChangeEvent nameChangeEvent) throws Exception{	

 		
 		return saveNameChangeEvent(userContext, nameChangeEvent, allTokens());
 	}
 	
 	public NameChangeEvent loadNameChangeEvent(BankUserContext userContext, String nameChangeEventId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().throwExceptionIfHasErrors( NameChangeEventManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		NameChangeEvent nameChangeEvent = loadNameChangeEvent( userContext, nameChangeEventId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,nameChangeEvent, tokens);
 	}
 	
 	
 	 public NameChangeEvent searchNameChangeEvent(BankUserContext userContext, String nameChangeEventId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().throwExceptionIfHasErrors( NameChangeEventManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		NameChangeEvent nameChangeEvent = loadNameChangeEvent( userContext, nameChangeEventId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,nameChangeEvent, tokens);
 	}
 	
 	

 	protected NameChangeEvent present(BankUserContext userContext, NameChangeEvent nameChangeEvent, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,nameChangeEvent,tokens);
		
		
		NameChangeEvent  nameChangeEventToPresent = userContext.getDAOGroup().getNameChangeEventDAO().present(nameChangeEvent, tokens);
		
		List<BaseEntity> entityListToNaming = nameChangeEventToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getNameChangeEventDAO().alias(entityListToNaming);
		
		return  nameChangeEventToPresent;
		
		
	}
 
 	
 	
 	public NameChangeEvent loadNameChangeEventDetail(BankUserContext userContext, String nameChangeEventId) throws Exception{	
 		NameChangeEvent nameChangeEvent = loadNameChangeEvent( userContext, nameChangeEventId, allTokens());
 		return present(userContext,nameChangeEvent, allTokens());
		
 	}
 	
 	public Object view(BankUserContext userContext, String nameChangeEventId) throws Exception{	
 		NameChangeEvent nameChangeEvent = loadNameChangeEvent( userContext, nameChangeEventId, viewTokens());
 		return present(userContext,nameChangeEvent, allTokens());
		
 	}
 	protected NameChangeEvent saveNameChangeEvent(BankUserContext userContext, NameChangeEvent nameChangeEvent, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getNameChangeEventDAO().save(nameChangeEvent, tokens);
 	}
 	protected NameChangeEvent loadNameChangeEvent(BankUserContext userContext, String nameChangeEventId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().throwExceptionIfHasErrors( NameChangeEventManagerException.class);

 
 		return userContext.getDAOGroup().getNameChangeEventDAO().load(nameChangeEventId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(BankUserContext userContext, NameChangeEvent nameChangeEvent, Map<String, Object> tokens){
		super.addActions(userContext, nameChangeEvent, tokens);
		
		addAction(userContext, nameChangeEvent, tokens,"@create","createNameChangeEvent","createNameChangeEvent/","main","primary");
		addAction(userContext, nameChangeEvent, tokens,"@update","updateNameChangeEvent","updateNameChangeEvent/"+nameChangeEvent.getId()+"/","main","primary");
		addAction(userContext, nameChangeEvent, tokens,"@copy","cloneNameChangeEvent","cloneNameChangeEvent/"+nameChangeEvent.getId()+"/","main","primary");
		
		addAction(userContext, nameChangeEvent, tokens,"name_change_event.transfer_to_account","transferToAnotherAccount","transferToAnotherAccount/"+nameChangeEvent.getId()+"/","main","primary");
		addAction(userContext, nameChangeEvent, tokens,"name_change_event.transfer_to_change_request","transferToAnotherChangeRequest","transferToAnotherChangeRequest/"+nameChangeEvent.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(BankUserContext userContext, NameChangeEvent nameChangeEvent, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public NameChangeEvent createNameChangeEvent(BankUserContext userContext,String name, String accountId, String changeRequestId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfNameChangeEvent(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(NameChangeEventManagerException.class);


		NameChangeEvent nameChangeEvent=createNewNameChangeEvent();	

		nameChangeEvent.setName(name);
			
		Account account = loadAccount(userContext, accountId,emptyOptions());
		nameChangeEvent.setAccount(account);
		
		
			
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId,emptyOptions());
		nameChangeEvent.setChangeRequest(changeRequest);
		
		

		nameChangeEvent = saveNameChangeEvent(userContext, nameChangeEvent, emptyOptions());
		
		onNewInstanceCreated(userContext, nameChangeEvent);
		return nameChangeEvent;

		
	}
	protected NameChangeEvent createNewNameChangeEvent() 
	{
		
		return new NameChangeEvent();		
	}
	
	protected void checkParamsForUpdatingNameChangeEvent(BankUserContext userContext,String nameChangeEventId, int nameChangeEventVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().checkVersionOfNameChangeEvent( nameChangeEventVersion);
		

		if(NameChangeEvent.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfNameChangeEvent(parseString(newValueExpr));
		}		

				

		
	
		userContext.getChecker().throwExceptionIfHasErrors(NameChangeEventManagerException.class);
	
		
	}
	
	
	
	public NameChangeEvent clone(BankUserContext userContext, String fromNameChangeEventId) throws Exception{
		
		return userContext.getDAOGroup().getNameChangeEventDAO().clone(fromNameChangeEventId, this.allTokens());
	}
	
	public NameChangeEvent internalSaveNameChangeEvent(BankUserContext userContext, NameChangeEvent nameChangeEvent) throws Exception 
	{
		return internalSaveNameChangeEvent(userContext, nameChangeEvent, allTokens());

	}
	public NameChangeEvent internalSaveNameChangeEvent(BankUserContext userContext, NameChangeEvent nameChangeEvent, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingNameChangeEvent(userContext, nameChangeEventId, nameChangeEventVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(nameChangeEvent){ 
			//will be good when the nameChangeEvent loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to NameChangeEvent.
			if (nameChangeEvent.isChanged()){
			
			}
			nameChangeEvent = saveNameChangeEvent(userContext, nameChangeEvent, options);
			return nameChangeEvent;
			
		}

	}
	
	public NameChangeEvent updateNameChangeEvent(BankUserContext userContext,String nameChangeEventId, int nameChangeEventVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingNameChangeEvent(userContext, nameChangeEventId, nameChangeEventVersion, property, newValueExpr, tokensExpr);
		
		
		
		NameChangeEvent nameChangeEvent = loadNameChangeEvent(userContext, nameChangeEventId, allTokens());
		if(nameChangeEvent.getVersion() != nameChangeEventVersion){
			String message = "The target version("+nameChangeEvent.getVersion()+") is not equals to version("+nameChangeEventVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(nameChangeEvent){ 
			//will be good when the nameChangeEvent loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to NameChangeEvent.
			
			nameChangeEvent.changeProperty(property, newValueExpr);
			nameChangeEvent = saveNameChangeEvent(userContext, nameChangeEvent, tokens().done());
			return present(userContext,nameChangeEvent, mergedAllTokens(tokensExpr));
			//return saveNameChangeEvent(userContext, nameChangeEvent, tokens().done());
		}

	}
	
	public NameChangeEvent updateNameChangeEventProperty(BankUserContext userContext,String nameChangeEventId, int nameChangeEventVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingNameChangeEvent(userContext, nameChangeEventId, nameChangeEventVersion, property, newValueExpr, tokensExpr);
		
		NameChangeEvent nameChangeEvent = loadNameChangeEvent(userContext, nameChangeEventId, allTokens());
		if(nameChangeEvent.getVersion() != nameChangeEventVersion){
			String message = "The target version("+nameChangeEvent.getVersion()+") is not equals to version("+nameChangeEventVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(nameChangeEvent){ 
			//will be good when the nameChangeEvent loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to NameChangeEvent.
			
			nameChangeEvent.changeProperty(property, newValueExpr);
			
			nameChangeEvent = saveNameChangeEvent(userContext, nameChangeEvent, tokens().done());
			return present(userContext,nameChangeEvent, mergedAllTokens(tokensExpr));
			//return saveNameChangeEvent(userContext, nameChangeEvent, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected NameChangeEventTokens tokens(){
		return NameChangeEventTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return NameChangeEventTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return NameChangeEventTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherAccount(BankUserContext userContext, String nameChangeEventId, String anotherAccountId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
 		userContext.getChecker().checkIdOfAccount(anotherAccountId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(NameChangeEventManagerException.class);
 		
 	}
 	public NameChangeEvent transferToAnotherAccount(BankUserContext userContext, String nameChangeEventId, String anotherAccountId) throws Exception
 	{
 		checkParamsForTransferingAnotherAccount(userContext, nameChangeEventId,anotherAccountId);
 
		NameChangeEvent nameChangeEvent = loadNameChangeEvent(userContext, nameChangeEventId, allTokens());	
		synchronized(nameChangeEvent){
			//will be good when the nameChangeEvent loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Account account = loadAccount(userContext, anotherAccountId, emptyOptions());		
			nameChangeEvent.updateAccount(account);		
			nameChangeEvent = saveNameChangeEvent(userContext, nameChangeEvent, emptyOptions());
			
			return present(userContext,nameChangeEvent, allTokens());
			
		}

 	}
 	
	

	protected void checkParamsForTransferingAnotherAccountWithName(BankUserContext userContext, String nameChangeEventId, String anotherName) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
 		userContext.getChecker().checkNameOfAccount( anotherName);
 		userContext.getChecker().throwExceptionIfHasErrors(NameChangeEventManagerException.class);
 		
 	}

 	public NameChangeEvent transferToAnotherAccountWithName(BankUserContext userContext, String nameChangeEventId, String anotherName) throws Exception
 	{
 		checkParamsForTransferingAnotherAccountWithName(userContext, nameChangeEventId,anotherName);
 		NameChangeEvent nameChangeEvent = loadNameChangeEvent(userContext, nameChangeEventId, allTokens());	
		synchronized(nameChangeEvent){
			//will be good when the nameChangeEvent loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Account account = loadAccountWithName(userContext, anotherName, emptyOptions());		
			nameChangeEvent.updateAccount(account);		
			nameChangeEvent = saveNameChangeEvent(userContext, nameChangeEvent, emptyOptions());
			
			return present(userContext,nameChangeEvent, allTokens());
			
		}
 	}	

	  	
 	
 	
	public CandidateAccount requestCandidateAccount(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateAccount result = new CandidateAccount();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Account> candidateList = userContext.getDAOGroup().getAccountDAO().requestCandidateAccountForNameChangeEvent(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherChangeRequest(BankUserContext userContext, String nameChangeEventId, String anotherChangeRequestId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
 		userContext.getChecker().checkIdOfChangeRequest(anotherChangeRequestId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(NameChangeEventManagerException.class);
 		
 	}
 	public NameChangeEvent transferToAnotherChangeRequest(BankUserContext userContext, String nameChangeEventId, String anotherChangeRequestId) throws Exception
 	{
 		checkParamsForTransferingAnotherChangeRequest(userContext, nameChangeEventId,anotherChangeRequestId);
 
		NameChangeEvent nameChangeEvent = loadNameChangeEvent(userContext, nameChangeEventId, allTokens());	
		synchronized(nameChangeEvent){
			//will be good when the nameChangeEvent loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest changeRequest = loadChangeRequest(userContext, anotherChangeRequestId, emptyOptions());		
			nameChangeEvent.updateChangeRequest(changeRequest);		
			nameChangeEvent = saveNameChangeEvent(userContext, nameChangeEvent, emptyOptions());
			
			return present(userContext,nameChangeEvent, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateChangeRequest requestCandidateChangeRequest(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateChangeRequest result = new CandidateChangeRequest();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ChangeRequest> candidateList = userContext.getDAOGroup().getChangeRequestDAO().requestCandidateChangeRequestForNameChangeEvent(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected ChangeRequest loadChangeRequest(BankUserContext userContext, String newChangeRequestId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getChangeRequestDAO().load(newChangeRequestId, options);
 	}
 	
 	
 	
	
	 	
 	protected Account loadAccount(BankUserContext userContext, String newAccountId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getAccountDAO().load(newAccountId, options);
 	}
 	
 	protected Account loadAccountWithName(BankUserContext userContext, String newName, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getAccountDAO().loadByName(newName, options);
 	}
 	
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(BankUserContext userContext, String nameChangeEventId, int nameChangeEventVersion) throws Exception {
		//deleteInternal(userContext, nameChangeEventId, nameChangeEventVersion);		
	}
	protected void deleteInternal(BankUserContext userContext,
			String nameChangeEventId, int nameChangeEventVersion) throws Exception{
			
		userContext.getDAOGroup().getNameChangeEventDAO().delete(nameChangeEventId, nameChangeEventVersion);
	}
	
	public NameChangeEvent forgetByAll(BankUserContext userContext, String nameChangeEventId, int nameChangeEventVersion) throws Exception {
		return forgetByAllInternal(userContext, nameChangeEventId, nameChangeEventVersion);		
	}
	protected NameChangeEvent forgetByAllInternal(BankUserContext userContext,
			String nameChangeEventId, int nameChangeEventVersion) throws Exception{
			
		return userContext.getDAOGroup().getNameChangeEventDAO().disconnectFromAll(nameChangeEventId, nameChangeEventVersion);
	}
	

	
	public int deleteAll(BankUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new NameChangeEventManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(BankUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getNameChangeEventDAO().deleteAll();
	}


	
	
	
	
	

	public void onNewInstanceCreated(BankUserContext userContext, NameChangeEvent newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


