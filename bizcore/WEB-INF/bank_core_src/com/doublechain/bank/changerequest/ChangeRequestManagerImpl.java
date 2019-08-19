
package com.doublechain.bank.changerequest;

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

import com.doublechain.bank.namechangeevent.NameChangeEvent;
import com.doublechain.bank.platform.Platform;
import com.doublechain.bank.transaction.Transaction;
import com.doublechain.bank.accountchange.AccountChange;

import com.doublechain.bank.platform.CandidatePlatform;

import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.account.Account;






public class ChangeRequestManagerImpl extends CustomBankCheckerManager implements ChangeRequestManager {
	
	private static final String SERVICE_TYPE = "ChangeRequest";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws ChangeRequestManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new ChangeRequestManagerException(message);

	}
	
	

 	protected ChangeRequest saveChangeRequest(BankUserContext userContext, ChangeRequest changeRequest, String [] tokensExpr) throws Exception{	
 		//return getChangeRequestDAO().save(changeRequest, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveChangeRequest(userContext, changeRequest, tokens);
 	}
 	
 	protected ChangeRequest saveChangeRequestDetail(BankUserContext userContext, ChangeRequest changeRequest) throws Exception{	

 		
 		return saveChangeRequest(userContext, changeRequest, allTokens());
 	}
 	
 	public ChangeRequest loadChangeRequest(BankUserContext userContext, String changeRequestId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().throwExceptionIfHasErrors( ChangeRequestManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,changeRequest, tokens);
 	}
 	
 	
 	 public ChangeRequest searchChangeRequest(BankUserContext userContext, String changeRequestId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().throwExceptionIfHasErrors( ChangeRequestManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,changeRequest, tokens);
 	}
 	
 	

 	protected ChangeRequest present(BankUserContext userContext, ChangeRequest changeRequest, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,changeRequest,tokens);
		
		
		ChangeRequest  changeRequestToPresent = userContext.getDAOGroup().getChangeRequestDAO().present(changeRequest, tokens);
		
		List<BaseEntity> entityListToNaming = changeRequestToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getChangeRequestDAO().alias(entityListToNaming);
		
		return  changeRequestToPresent;
		
		
	}
 
 	
 	
 	public ChangeRequest loadChangeRequestDetail(BankUserContext userContext, String changeRequestId) throws Exception{	
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, allTokens());
 		return present(userContext,changeRequest, allTokens());
		
 	}
 	
 	public Object view(BankUserContext userContext, String changeRequestId) throws Exception{	
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, viewTokens());
 		return present(userContext,changeRequest, allTokens());
		
 	}
 	protected ChangeRequest saveChangeRequest(BankUserContext userContext, ChangeRequest changeRequest, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getChangeRequestDAO().save(changeRequest, tokens);
 	}
 	protected ChangeRequest loadChangeRequest(BankUserContext userContext, String changeRequestId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().throwExceptionIfHasErrors( ChangeRequestManagerException.class);

 
 		return userContext.getDAOGroup().getChangeRequestDAO().load(changeRequestId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(BankUserContext userContext, ChangeRequest changeRequest, Map<String, Object> tokens){
		super.addActions(userContext, changeRequest, tokens);
		
		addAction(userContext, changeRequest, tokens,"@create","createChangeRequest","createChangeRequest/","main","primary");
		addAction(userContext, changeRequest, tokens,"@update","updateChangeRequest","updateChangeRequest/"+changeRequest.getId()+"/","main","primary");
		addAction(userContext, changeRequest, tokens,"@copy","cloneChangeRequest","cloneChangeRequest/"+changeRequest.getId()+"/","main","primary");
		
		addAction(userContext, changeRequest, tokens,"change_request.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+changeRequest.getId()+"/","main","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addTransaction","addTransaction","addTransaction/"+changeRequest.getId()+"/","transactionList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeTransaction","removeTransaction","removeTransaction/"+changeRequest.getId()+"/","transactionList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateTransaction","updateTransaction","updateTransaction/"+changeRequest.getId()+"/","transactionList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyTransactionFrom","copyTransactionFrom","copyTransactionFrom/"+changeRequest.getId()+"/","transactionList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addNameChangeEvent","addNameChangeEvent","addNameChangeEvent/"+changeRequest.getId()+"/","nameChangeEventList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeNameChangeEvent","removeNameChangeEvent","removeNameChangeEvent/"+changeRequest.getId()+"/","nameChangeEventList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateNameChangeEvent","updateNameChangeEvent","updateNameChangeEvent/"+changeRequest.getId()+"/","nameChangeEventList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyNameChangeEventFrom","copyNameChangeEventFrom","copyNameChangeEventFrom/"+changeRequest.getId()+"/","nameChangeEventList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addAccountChange","addAccountChange","addAccountChange/"+changeRequest.getId()+"/","accountChangeList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeAccountChange","removeAccountChange","removeAccountChange/"+changeRequest.getId()+"/","accountChangeList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateAccountChange","updateAccountChange","updateAccountChange/"+changeRequest.getId()+"/","accountChangeList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyAccountChangeFrom","copyAccountChangeFrom","copyAccountChangeFrom/"+changeRequest.getId()+"/","accountChangeList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(BankUserContext userContext, ChangeRequest changeRequest, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public ChangeRequest createChangeRequest(BankUserContext userContext,String name, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfChangeRequest(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);


		ChangeRequest changeRequest=createNewChangeRequest();	

		changeRequest.setName(name);
		changeRequest.setCreateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		changeRequest.setPlatform(platform);
		
		

		changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());
		
		onNewInstanceCreated(userContext, changeRequest);
		return changeRequest;

		
	}
	protected ChangeRequest createNewChangeRequest() 
	{
		
		return new ChangeRequest();		
	}
	
	protected void checkParamsForUpdatingChangeRequest(BankUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkVersionOfChangeRequest( changeRequestVersion);
		

		if(ChangeRequest.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfChangeRequest(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
		
	}
	
	
	
	public ChangeRequest clone(BankUserContext userContext, String fromChangeRequestId) throws Exception{
		
		return userContext.getDAOGroup().getChangeRequestDAO().clone(fromChangeRequestId, this.allTokens());
	}
	
	public ChangeRequest internalSaveChangeRequest(BankUserContext userContext, ChangeRequest changeRequest) throws Exception 
	{
		return internalSaveChangeRequest(userContext, changeRequest, allTokens());

	}
	public ChangeRequest internalSaveChangeRequest(BankUserContext userContext, ChangeRequest changeRequest, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingChangeRequest(userContext, changeRequestId, changeRequestVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(changeRequest){ 
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequest.
			if (changeRequest.isChanged()){
			
			}
			changeRequest = saveChangeRequest(userContext, changeRequest, options);
			return changeRequest;
			
		}

	}
	
	public ChangeRequest updateChangeRequest(BankUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingChangeRequest(userContext, changeRequestId, changeRequestVersion, property, newValueExpr, tokensExpr);
		
		
		
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		if(changeRequest.getVersion() != changeRequestVersion){
			String message = "The target version("+changeRequest.getVersion()+") is not equals to version("+changeRequestVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(changeRequest){ 
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequest.
			
			changeRequest.changeProperty(property, newValueExpr);
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			//return saveChangeRequest(userContext, changeRequest, tokens().done());
		}

	}
	
	public ChangeRequest updateChangeRequestProperty(BankUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingChangeRequest(userContext, changeRequestId, changeRequestVersion, property, newValueExpr, tokensExpr);
		
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		if(changeRequest.getVersion() != changeRequestVersion){
			String message = "The target version("+changeRequest.getVersion()+") is not equals to version("+changeRequestVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(changeRequest){ 
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequest.
			
			changeRequest.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			//return saveChangeRequest(userContext, changeRequest, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected ChangeRequestTokens tokens(){
		return ChangeRequestTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ChangeRequestTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortTransactionListWith("id","desc")
		.sortNameChangeEventListWith("id","desc")
		.sortAccountChangeListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ChangeRequestTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(BankUserContext userContext, String changeRequestId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
 		
 	}
 	public ChangeRequest transferToAnotherPlatform(BankUserContext userContext, String changeRequestId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, changeRequestId,anotherPlatformId);
 
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());	
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			changeRequest.updatePlatform(platform);		
			changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());
			
			return present(userContext,changeRequest, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidatePlatform requestCandidatePlatform(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePlatform result = new CandidatePlatform();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForChangeRequest(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected Platform loadPlatform(BankUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPlatformDAO().load(newPlatformId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(BankUserContext userContext, String changeRequestId, int changeRequestVersion) throws Exception {
		//deleteInternal(userContext, changeRequestId, changeRequestVersion);		
	}
	protected void deleteInternal(BankUserContext userContext,
			String changeRequestId, int changeRequestVersion) throws Exception{
			
		userContext.getDAOGroup().getChangeRequestDAO().delete(changeRequestId, changeRequestVersion);
	}
	
	public ChangeRequest forgetByAll(BankUserContext userContext, String changeRequestId, int changeRequestVersion) throws Exception {
		return forgetByAllInternal(userContext, changeRequestId, changeRequestVersion);		
	}
	protected ChangeRequest forgetByAllInternal(BankUserContext userContext,
			String changeRequestId, int changeRequestVersion) throws Exception{
			
		return userContext.getDAOGroup().getChangeRequestDAO().disconnectFromAll(changeRequestId, changeRequestVersion);
	}
	

	
	public int deleteAll(BankUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ChangeRequestManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(BankUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getChangeRequestDAO().deleteAll();
	}


	//disconnect ChangeRequest with from_account in Transaction
	protected ChangeRequest breakWithTransactionByFromAccount(BankUserContext userContext, String changeRequestId, String fromAccountId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getChangeRequestDAO().planToRemoveTransactionListWithFromAccount(changeRequest, fromAccountId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTransactionList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with to_account in Transaction
	protected ChangeRequest breakWithTransactionByToAccount(BankUserContext userContext, String changeRequestId, String toAccountId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getChangeRequestDAO().planToRemoveTransactionListWithToAccount(changeRequest, toAccountId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTransactionList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with account in NameChangeEvent
	protected ChangeRequest breakWithNameChangeEventByAccount(BankUserContext userContext, String changeRequestId, String accountId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getChangeRequestDAO().planToRemoveNameChangeEventListWithAccount(changeRequest, accountId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withNameChangeEventList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with account in AccountChange
	protected ChangeRequest breakWithAccountChangeByAccount(BankUserContext userContext, String changeRequestId, String accountId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getChangeRequestDAO().planToRemoveAccountChangeListWithAccount(changeRequest, accountId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withAccountChangeList().done());
				return changeRequest;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingTransaction(BankUserContext userContext, String changeRequestId, String name, String fromAccountId, String toAccountId, BigDecimal amount, String type,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);

		
		userContext.getChecker().checkNameOfTransaction(name);
		
		userContext.getChecker().checkFromAccountIdOfTransaction(fromAccountId);
		
		userContext.getChecker().checkToAccountIdOfTransaction(toAccountId);
		
		userContext.getChecker().checkAmountOfTransaction(amount);
		
		userContext.getChecker().checkTypeOfTransaction(type);
	
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	
	}
	public  ChangeRequest addTransaction(BankUserContext userContext, String changeRequestId, String name, String fromAccountId, String toAccountId, BigDecimal amount, String type, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingTransaction(userContext,changeRequestId,name, fromAccountId, toAccountId, amount, type,tokensExpr);
		
		Transaction transaction = createTransaction(userContext,name, fromAccountId, toAccountId, amount, type);
		
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addTransaction( transaction );		
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTransactionList().done());
			
			userContext.getManagerGroup().getTransactionManager().onNewInstanceCreated(userContext, transaction);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTransactionProperties(BankUserContext userContext, String changeRequestId,String id,String name,BigDecimal amount,String type,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkIdOfTransaction(id);
		
		userContext.getChecker().checkNameOfTransaction( name);
		userContext.getChecker().checkAmountOfTransaction( amount);
		userContext.getChecker().checkTypeOfTransaction( type);

		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
		
	}
	public  ChangeRequest updateTransactionProperties(BankUserContext userContext, String changeRequestId, String id,String name,BigDecimal amount,String type, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingTransactionProperties(userContext,changeRequestId,id,name,amount,type,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTransactionListList()
				.searchTransactionListWith(Transaction.ID_PROPERTY, "is", id).done();
		
		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);
		
		if(changeRequestToUpdate.getTransactionList().isEmpty()){
			throw new ChangeRequestManagerException("Transaction is NOT FOUND with id: '"+id+"'");
		}
		
		Transaction item = changeRequestToUpdate.getTransactionList().first();
		
		item.updateName( name );
		item.updateAmount( amount );
		item.updateType( type );

		
		//checkParamsForAddingTransaction(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withTransactionList().done());
		synchronized(changeRequest){ 
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected Transaction createTransaction(BankUserContext userContext, String name, String fromAccountId, String toAccountId, BigDecimal amount, String type) throws Exception{

		Transaction transaction = new Transaction();
		
		
		transaction.setName(name);		
		Account  fromAccount = new Account();
		fromAccount.setId(fromAccountId);		
		transaction.setFromAccount(fromAccount);		
		Account  toAccount = new Account();
		toAccount.setId(toAccountId);		
		transaction.setToAccount(toAccount);		
		transaction.setAmount(amount);		
		transaction.setType(type);		
		transaction.setCurrentStatus("INIT");
	
		
		return transaction;
	
		
	}
	
	protected Transaction createIndexedTransaction(String id, int version){

		Transaction transaction = new Transaction();
		transaction.setId(id);
		transaction.setVersion(version);
		return transaction;			
		
	}
	
	protected void checkParamsForRemovingTransactionList(BankUserContext userContext, String changeRequestId, 
			String transactionIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		for(String transactionIdItem: transactionIds){
			userContext.getChecker().checkIdOfTransaction(transactionIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
		
	}
	public  ChangeRequest removeTransactionList(BankUserContext userContext, String changeRequestId, 
			String transactionIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingTransactionList(userContext, changeRequestId,  transactionIds, tokensExpr);
			
			
			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){ 
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getChangeRequestDAO().planToRemoveTransactionList(changeRequest, transactionIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTransactionList().done());
				deleteRelationListInGraph(userContext, changeRequest.getTransactionList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingTransaction(BankUserContext userContext, String changeRequestId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfChangeRequest( changeRequestId);
		userContext.getChecker().checkIdOfTransaction(transactionId);
		userContext.getChecker().checkVersionOfTransaction(transactionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
	}
	public  ChangeRequest removeTransaction(BankUserContext userContext, String changeRequestId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingTransaction(userContext,changeRequestId, transactionId, transactionVersion,tokensExpr);
		
		Transaction transaction = createIndexedTransaction(transactionId, transactionVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeTransaction( transaction );		
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTransactionList().done());
			deleteRelationInGraph(userContext, transaction);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingTransaction(BankUserContext userContext, String changeRequestId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfChangeRequest( changeRequestId);
		userContext.getChecker().checkIdOfTransaction(transactionId);
		userContext.getChecker().checkVersionOfTransaction(transactionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
	}
	public  ChangeRequest copyTransactionFrom(BankUserContext userContext, String changeRequestId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingTransaction(userContext,changeRequestId, transactionId, transactionVersion,tokensExpr);
		
		Transaction transaction = createIndexedTransaction(transactionId, transactionVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			changeRequest.copyTransactionFrom( transaction );		
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTransactionList().done());
			
			userContext.getManagerGroup().getTransactionManager().onNewInstanceCreated(userContext, (Transaction)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingTransaction(BankUserContext userContext, String changeRequestId, String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkIdOfTransaction(transactionId);
		userContext.getChecker().checkVersionOfTransaction(transactionVersion);
		

		if(Transaction.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfTransaction(parseString(newValueExpr));
		}
		
		if(Transaction.AMOUNT_PROPERTY.equals(property)){
			userContext.getChecker().checkAmountOfTransaction(parseBigDecimal(newValueExpr));
		}
		
		if(Transaction.TYPE_PROPERTY.equals(property)){
			userContext.getChecker().checkTypeOfTransaction(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
	}
	
	public  ChangeRequest updateTransaction(BankUserContext userContext, String changeRequestId, String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingTransaction(userContext, changeRequestId, transactionId, transactionVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withTransactionList().searchTransactionListWith(Transaction.ID_PROPERTY, "eq", transactionId).done();
		
		
		
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);
		
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeTransaction( transaction );	
			//make changes to AcceleraterAccount.
			Transaction transactionIndex = createIndexedTransaction(transactionId, transactionVersion);
		
			Transaction transaction = changeRequest.findTheTransaction(transactionIndex);
			if(transaction == null){
				throw new ChangeRequestManagerException(transaction+" is NOT FOUND" );
			}
			
			transaction.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTransactionList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingNameChangeEvent(BankUserContext userContext, String changeRequestId, String name, String accountId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);

		
		userContext.getChecker().checkNameOfNameChangeEvent(name);
		
		userContext.getChecker().checkAccountIdOfNameChangeEvent(accountId);
	
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	
	}
	public  ChangeRequest addNameChangeEvent(BankUserContext userContext, String changeRequestId, String name, String accountId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingNameChangeEvent(userContext,changeRequestId,name, accountId,tokensExpr);
		
		NameChangeEvent nameChangeEvent = createNameChangeEvent(userContext,name, accountId);
		
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addNameChangeEvent( nameChangeEvent );		
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withNameChangeEventList().done());
			
			userContext.getManagerGroup().getNameChangeEventManager().onNewInstanceCreated(userContext, nameChangeEvent);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingNameChangeEventProperties(BankUserContext userContext, String changeRequestId,String id,String name,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkIdOfNameChangeEvent(id);
		
		userContext.getChecker().checkNameOfNameChangeEvent( name);

		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
		
	}
	public  ChangeRequest updateNameChangeEventProperties(BankUserContext userContext, String changeRequestId, String id,String name, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingNameChangeEventProperties(userContext,changeRequestId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withNameChangeEventListList()
				.searchNameChangeEventListWith(NameChangeEvent.ID_PROPERTY, "is", id).done();
		
		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);
		
		if(changeRequestToUpdate.getNameChangeEventList().isEmpty()){
			throw new ChangeRequestManagerException("NameChangeEvent is NOT FOUND with id: '"+id+"'");
		}
		
		NameChangeEvent item = changeRequestToUpdate.getNameChangeEventList().first();
		
		item.updateName( name );

		
		//checkParamsForAddingNameChangeEvent(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withNameChangeEventList().done());
		synchronized(changeRequest){ 
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected NameChangeEvent createNameChangeEvent(BankUserContext userContext, String name, String accountId) throws Exception{

		NameChangeEvent nameChangeEvent = new NameChangeEvent();
		
		
		nameChangeEvent.setName(name);		
		Account  account = new Account();
		account.setId(accountId);		
		nameChangeEvent.setAccount(account);		
		nameChangeEvent.setCurrentStatus("INIT");
	
		
		return nameChangeEvent;
	
		
	}
	
	protected NameChangeEvent createIndexedNameChangeEvent(String id, int version){

		NameChangeEvent nameChangeEvent = new NameChangeEvent();
		nameChangeEvent.setId(id);
		nameChangeEvent.setVersion(version);
		return nameChangeEvent;			
		
	}
	
	protected void checkParamsForRemovingNameChangeEventList(BankUserContext userContext, String changeRequestId, 
			String nameChangeEventIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		for(String nameChangeEventIdItem: nameChangeEventIds){
			userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
		
	}
	public  ChangeRequest removeNameChangeEventList(BankUserContext userContext, String changeRequestId, 
			String nameChangeEventIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingNameChangeEventList(userContext, changeRequestId,  nameChangeEventIds, tokensExpr);
			
			
			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){ 
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getChangeRequestDAO().planToRemoveNameChangeEventList(changeRequest, nameChangeEventIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withNameChangeEventList().done());
				deleteRelationListInGraph(userContext, changeRequest.getNameChangeEventList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingNameChangeEvent(BankUserContext userContext, String changeRequestId, 
		String nameChangeEventId, int nameChangeEventVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfChangeRequest( changeRequestId);
		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().checkVersionOfNameChangeEvent(nameChangeEventVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
	}
	public  ChangeRequest removeNameChangeEvent(BankUserContext userContext, String changeRequestId, 
		String nameChangeEventId, int nameChangeEventVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingNameChangeEvent(userContext,changeRequestId, nameChangeEventId, nameChangeEventVersion,tokensExpr);
		
		NameChangeEvent nameChangeEvent = createIndexedNameChangeEvent(nameChangeEventId, nameChangeEventVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeNameChangeEvent( nameChangeEvent );		
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withNameChangeEventList().done());
			deleteRelationInGraph(userContext, nameChangeEvent);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingNameChangeEvent(BankUserContext userContext, String changeRequestId, 
		String nameChangeEventId, int nameChangeEventVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfChangeRequest( changeRequestId);
		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().checkVersionOfNameChangeEvent(nameChangeEventVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
	}
	public  ChangeRequest copyNameChangeEventFrom(BankUserContext userContext, String changeRequestId, 
		String nameChangeEventId, int nameChangeEventVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingNameChangeEvent(userContext,changeRequestId, nameChangeEventId, nameChangeEventVersion,tokensExpr);
		
		NameChangeEvent nameChangeEvent = createIndexedNameChangeEvent(nameChangeEventId, nameChangeEventVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			changeRequest.copyNameChangeEventFrom( nameChangeEvent );		
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withNameChangeEventList().done());
			
			userContext.getManagerGroup().getNameChangeEventManager().onNewInstanceCreated(userContext, (NameChangeEvent)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingNameChangeEvent(BankUserContext userContext, String changeRequestId, String nameChangeEventId, int nameChangeEventVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().checkVersionOfNameChangeEvent(nameChangeEventVersion);
		

		if(NameChangeEvent.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfNameChangeEvent(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
	}
	
	public  ChangeRequest updateNameChangeEvent(BankUserContext userContext, String changeRequestId, String nameChangeEventId, int nameChangeEventVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingNameChangeEvent(userContext, changeRequestId, nameChangeEventId, nameChangeEventVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withNameChangeEventList().searchNameChangeEventListWith(NameChangeEvent.ID_PROPERTY, "eq", nameChangeEventId).done();
		
		
		
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);
		
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeNameChangeEvent( nameChangeEvent );	
			//make changes to AcceleraterAccount.
			NameChangeEvent nameChangeEventIndex = createIndexedNameChangeEvent(nameChangeEventId, nameChangeEventVersion);
		
			NameChangeEvent nameChangeEvent = changeRequest.findTheNameChangeEvent(nameChangeEventIndex);
			if(nameChangeEvent == null){
				throw new ChangeRequestManagerException(nameChangeEvent+" is NOT FOUND" );
			}
			
			nameChangeEvent.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withNameChangeEventList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingAccountChange(BankUserContext userContext, String changeRequestId, String name, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, String accountId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);

		
		userContext.getChecker().checkNameOfAccountChange(name);
		
		userContext.getChecker().checkPreviousBalanceOfAccountChange(previousBalance);
		
		userContext.getChecker().checkTypeOfAccountChange(type);
		
		userContext.getChecker().checkAmountOfAccountChange(amount);
		
		userContext.getChecker().checkCurrentBalanceOfAccountChange(currentBalance);
		
		userContext.getChecker().checkAccountIdOfAccountChange(accountId);
	
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	
	}
	public  ChangeRequest addAccountChange(BankUserContext userContext, String changeRequestId, String name, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, String accountId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingAccountChange(userContext,changeRequestId,name, previousBalance, type, amount, currentBalance, accountId,tokensExpr);
		
		AccountChange accountChange = createAccountChange(userContext,name, previousBalance, type, amount, currentBalance, accountId);
		
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addAccountChange( accountChange );		
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withAccountChangeList().done());
			
			userContext.getManagerGroup().getAccountChangeManager().onNewInstanceCreated(userContext, accountChange);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingAccountChangeProperties(BankUserContext userContext, String changeRequestId,String id,String name,BigDecimal previousBalance,String type,BigDecimal amount,BigDecimal currentBalance,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkIdOfAccountChange(id);
		
		userContext.getChecker().checkNameOfAccountChange( name);
		userContext.getChecker().checkPreviousBalanceOfAccountChange( previousBalance);
		userContext.getChecker().checkTypeOfAccountChange( type);
		userContext.getChecker().checkAmountOfAccountChange( amount);
		userContext.getChecker().checkCurrentBalanceOfAccountChange( currentBalance);

		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
		
	}
	public  ChangeRequest updateAccountChangeProperties(BankUserContext userContext, String changeRequestId, String id,String name,BigDecimal previousBalance,String type,BigDecimal amount,BigDecimal currentBalance, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingAccountChangeProperties(userContext,changeRequestId,id,name,previousBalance,type,amount,currentBalance,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withAccountChangeListList()
				.searchAccountChangeListWith(AccountChange.ID_PROPERTY, "is", id).done();
		
		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);
		
		if(changeRequestToUpdate.getAccountChangeList().isEmpty()){
			throw new ChangeRequestManagerException("AccountChange is NOT FOUND with id: '"+id+"'");
		}
		
		AccountChange item = changeRequestToUpdate.getAccountChangeList().first();
		
		item.updateName( name );
		item.updatePreviousBalance( previousBalance );
		item.updateType( type );
		item.updateAmount( amount );
		item.updateCurrentBalance( currentBalance );

		
		//checkParamsForAddingAccountChange(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withAccountChangeList().done());
		synchronized(changeRequest){ 
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected AccountChange createAccountChange(BankUserContext userContext, String name, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, String accountId) throws Exception{

		AccountChange accountChange = new AccountChange();
		
		
		accountChange.setName(name);		
		accountChange.setPreviousBalance(previousBalance);		
		accountChange.setType(type);		
		accountChange.setAmount(amount);		
		accountChange.setCurrentBalance(currentBalance);		
		Account  account = new Account();
		account.setId(accountId);		
		accountChange.setAccount(account);		
		accountChange.setCurrentStatus("INIT");
	
		
		return accountChange;
	
		
	}
	
	protected AccountChange createIndexedAccountChange(String id, int version){

		AccountChange accountChange = new AccountChange();
		accountChange.setId(id);
		accountChange.setVersion(version);
		return accountChange;			
		
	}
	
	protected void checkParamsForRemovingAccountChangeList(BankUserContext userContext, String changeRequestId, 
			String accountChangeIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		for(String accountChangeIdItem: accountChangeIds){
			userContext.getChecker().checkIdOfAccountChange(accountChangeIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
		
	}
	public  ChangeRequest removeAccountChangeList(BankUserContext userContext, String changeRequestId, 
			String accountChangeIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingAccountChangeList(userContext, changeRequestId,  accountChangeIds, tokensExpr);
			
			
			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){ 
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getChangeRequestDAO().planToRemoveAccountChangeList(changeRequest, accountChangeIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withAccountChangeList().done());
				deleteRelationListInGraph(userContext, changeRequest.getAccountChangeList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingAccountChange(BankUserContext userContext, String changeRequestId, 
		String accountChangeId, int accountChangeVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfChangeRequest( changeRequestId);
		userContext.getChecker().checkIdOfAccountChange(accountChangeId);
		userContext.getChecker().checkVersionOfAccountChange(accountChangeVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
	}
	public  ChangeRequest removeAccountChange(BankUserContext userContext, String changeRequestId, 
		String accountChangeId, int accountChangeVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingAccountChange(userContext,changeRequestId, accountChangeId, accountChangeVersion,tokensExpr);
		
		AccountChange accountChange = createIndexedAccountChange(accountChangeId, accountChangeVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeAccountChange( accountChange );		
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withAccountChangeList().done());
			deleteRelationInGraph(userContext, accountChange);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingAccountChange(BankUserContext userContext, String changeRequestId, 
		String accountChangeId, int accountChangeVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfChangeRequest( changeRequestId);
		userContext.getChecker().checkIdOfAccountChange(accountChangeId);
		userContext.getChecker().checkVersionOfAccountChange(accountChangeVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
	}
	public  ChangeRequest copyAccountChangeFrom(BankUserContext userContext, String changeRequestId, 
		String accountChangeId, int accountChangeVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingAccountChange(userContext,changeRequestId, accountChangeId, accountChangeVersion,tokensExpr);
		
		AccountChange accountChange = createIndexedAccountChange(accountChangeId, accountChangeVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			changeRequest.copyAccountChangeFrom( accountChange );		
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withAccountChangeList().done());
			
			userContext.getManagerGroup().getAccountChangeManager().onNewInstanceCreated(userContext, (AccountChange)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingAccountChange(BankUserContext userContext, String changeRequestId, String accountChangeId, int accountChangeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkIdOfAccountChange(accountChangeId);
		userContext.getChecker().checkVersionOfAccountChange(accountChangeVersion);
		

		if(AccountChange.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfAccountChange(parseString(newValueExpr));
		}
		
		if(AccountChange.PREVIOUS_BALANCE_PROPERTY.equals(property)){
			userContext.getChecker().checkPreviousBalanceOfAccountChange(parseBigDecimal(newValueExpr));
		}
		
		if(AccountChange.TYPE_PROPERTY.equals(property)){
			userContext.getChecker().checkTypeOfAccountChange(parseString(newValueExpr));
		}
		
		if(AccountChange.AMOUNT_PROPERTY.equals(property)){
			userContext.getChecker().checkAmountOfAccountChange(parseBigDecimal(newValueExpr));
		}
		
		if(AccountChange.CURRENT_BALANCE_PROPERTY.equals(property)){
			userContext.getChecker().checkCurrentBalanceOfAccountChange(parseBigDecimal(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(ChangeRequestManagerException.class);
	
	}
	
	public  ChangeRequest updateAccountChange(BankUserContext userContext, String changeRequestId, String accountChangeId, int accountChangeVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingAccountChange(userContext, changeRequestId, accountChangeId, accountChangeVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withAccountChangeList().searchAccountChangeListWith(AccountChange.ID_PROPERTY, "eq", accountChangeId).done();
		
		
		
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);
		
		synchronized(changeRequest){ 
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeAccountChange( accountChange );	
			//make changes to AcceleraterAccount.
			AccountChange accountChangeIndex = createIndexedAccountChange(accountChangeId, accountChangeVersion);
		
			AccountChange accountChange = changeRequest.findTheAccountChange(accountChangeIndex);
			if(accountChange == null){
				throw new ChangeRequestManagerException(accountChange+" is NOT FOUND" );
			}
			
			accountChange.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withAccountChangeList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(BankUserContext userContext, ChangeRequest newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


