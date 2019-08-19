
package com.doublechain.bank.account;

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






public class AccountManagerImpl extends CustomBankCheckerManager implements AccountManager {
	
	private static final String SERVICE_TYPE = "Account";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws AccountManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new AccountManagerException(message);

	}
	
	

 	protected Account saveAccount(BankUserContext userContext, Account account, String [] tokensExpr) throws Exception{	
 		//return getAccountDAO().save(account, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveAccount(userContext, account, tokens);
 	}
 	
 	protected Account saveAccountDetail(BankUserContext userContext, Account account) throws Exception{	

 		
 		return saveAccount(userContext, account, allTokens());
 	}
 	
 	public Account loadAccount(BankUserContext userContext, String accountId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().throwExceptionIfHasErrors( AccountManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Account account = loadAccount( userContext, accountId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,account, tokens);
 	}
 	
 	
 	 public Account searchAccount(BankUserContext userContext, String accountId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().throwExceptionIfHasErrors( AccountManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Account account = loadAccount( userContext, accountId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,account, tokens);
 	}
 	
 	

 	protected Account present(BankUserContext userContext, Account account, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,account,tokens);
		
		
		Account  accountToPresent = userContext.getDAOGroup().getAccountDAO().present(account, tokens);
		
		List<BaseEntity> entityListToNaming = accountToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getAccountDAO().alias(entityListToNaming);
		
		return  accountToPresent;
		
		
	}
 
 	
 	
 	public Account loadAccountDetail(BankUserContext userContext, String accountId) throws Exception{	
 		Account account = loadAccount( userContext, accountId, allTokens());
 		return present(userContext,account, allTokens());
		
 	}
 	
 	public Object view(BankUserContext userContext, String accountId) throws Exception{	
 		Account account = loadAccount( userContext, accountId, viewTokens());
 		return present(userContext,account, allTokens());
		
 	}
 	protected Account saveAccount(BankUserContext userContext, Account account, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getAccountDAO().save(account, tokens);
 	}
 	protected Account loadAccount(BankUserContext userContext, String accountId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().throwExceptionIfHasErrors( AccountManagerException.class);

 
 		return userContext.getDAOGroup().getAccountDAO().load(accountId, tokens);
 	}

	
	

	public Account loadAccountWithName(BankUserContext userContext, String name, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getAccountDAO().loadByName(name, tokens);
 	}

	 


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(BankUserContext userContext, Account account, Map<String, Object> tokens){
		super.addActions(userContext, account, tokens);
		
		addAction(userContext, account, tokens,"@create","createAccount","createAccount/","main","primary");
		addAction(userContext, account, tokens,"@update","updateAccount","updateAccount/"+account.getId()+"/","main","primary");
		addAction(userContext, account, tokens,"@copy","cloneAccount","cloneAccount/"+account.getId()+"/","main","primary");
		
		addAction(userContext, account, tokens,"account.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+account.getId()+"/","main","primary");
		addAction(userContext, account, tokens,"account.addTransaction","addTransaction","addTransaction/"+account.getId()+"/","transactionListAsFromAccount","primary");
		addAction(userContext, account, tokens,"account.removeTransaction","removeTransaction","removeTransaction/"+account.getId()+"/","transactionListAsFromAccount","primary");
		addAction(userContext, account, tokens,"account.updateTransaction","updateTransaction","updateTransaction/"+account.getId()+"/","transactionListAsFromAccount","primary");
		addAction(userContext, account, tokens,"account.copyTransactionFrom","copyTransactionFrom","copyTransactionFrom/"+account.getId()+"/","transactionListAsFromAccount","primary");
		addAction(userContext, account, tokens,"account.addTransaction","addTransaction","addTransaction/"+account.getId()+"/","transactionListAsToAccount","primary");
		addAction(userContext, account, tokens,"account.removeTransaction","removeTransaction","removeTransaction/"+account.getId()+"/","transactionListAsToAccount","primary");
		addAction(userContext, account, tokens,"account.updateTransaction","updateTransaction","updateTransaction/"+account.getId()+"/","transactionListAsToAccount","primary");
		addAction(userContext, account, tokens,"account.copyTransactionFrom","copyTransactionFrom","copyTransactionFrom/"+account.getId()+"/","transactionListAsToAccount","primary");
		addAction(userContext, account, tokens,"account.addNameChangeEvent","addNameChangeEvent","addNameChangeEvent/"+account.getId()+"/","nameChangeEventList","primary");
		addAction(userContext, account, tokens,"account.removeNameChangeEvent","removeNameChangeEvent","removeNameChangeEvent/"+account.getId()+"/","nameChangeEventList","primary");
		addAction(userContext, account, tokens,"account.updateNameChangeEvent","updateNameChangeEvent","updateNameChangeEvent/"+account.getId()+"/","nameChangeEventList","primary");
		addAction(userContext, account, tokens,"account.copyNameChangeEventFrom","copyNameChangeEventFrom","copyNameChangeEventFrom/"+account.getId()+"/","nameChangeEventList","primary");
		addAction(userContext, account, tokens,"account.addAccountChange","addAccountChange","addAccountChange/"+account.getId()+"/","accountChangeList","primary");
		addAction(userContext, account, tokens,"account.removeAccountChange","removeAccountChange","removeAccountChange/"+account.getId()+"/","accountChangeList","primary");
		addAction(userContext, account, tokens,"account.updateAccountChange","updateAccountChange","updateAccountChange/"+account.getId()+"/","accountChangeList","primary");
		addAction(userContext, account, tokens,"account.copyAccountChangeFrom","copyAccountChangeFrom","copyAccountChangeFrom/"+account.getId()+"/","accountChangeList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(BankUserContext userContext, Account account, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public Account createAccount(BankUserContext userContext,String name, BigDecimal balance, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfAccount(name);
		userContext.getChecker().checkBalanceOfAccount(balance);
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);


		Account account=createNewAccount();	

		account.setName(name);
		account.setBalance(balance);
		account.setCreateTime(userContext.now());
		account.setUpdateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		account.setPlatform(platform);
		
		

		account = saveAccount(userContext, account, emptyOptions());
		
		onNewInstanceCreated(userContext, account);
		return account;

		
	}
	protected Account createNewAccount() 
	{
		
		return new Account();		
	}
	
	protected void checkParamsForUpdatingAccount(BankUserContext userContext,String accountId, int accountVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().checkVersionOfAccount( accountVersion);
		

		if(Account.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfAccount(parseString(newValueExpr));
		}
		if(Account.BALANCE_PROPERTY.equals(property)){
			userContext.getChecker().checkBalanceOfAccount(parseBigDecimal(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
		
	}
	
	
	
	public Account clone(BankUserContext userContext, String fromAccountId) throws Exception{
		
		return userContext.getDAOGroup().getAccountDAO().clone(fromAccountId, this.allTokens());
	}
	
	public Account internalSaveAccount(BankUserContext userContext, Account account) throws Exception 
	{
		return internalSaveAccount(userContext, account, allTokens());

	}
	public Account internalSaveAccount(BankUserContext userContext, Account account, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingAccount(userContext, accountId, accountVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(account){ 
			//will be good when the account loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Account.
			if (account.isChanged()){
			account.updateUpdateTime(userContext.now());
			}
			account = saveAccount(userContext, account, options);
			return account;
			
		}

	}
	
	public Account updateAccount(BankUserContext userContext,String accountId, int accountVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingAccount(userContext, accountId, accountVersion, property, newValueExpr, tokensExpr);
		
		
		
		Account account = loadAccount(userContext, accountId, allTokens());
		if(account.getVersion() != accountVersion){
			String message = "The target version("+account.getVersion()+") is not equals to version("+accountVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(account){ 
			//will be good when the account loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Account.
			account.updateUpdateTime(userContext.now());
			account.changeProperty(property, newValueExpr);
			account = saveAccount(userContext, account, tokens().done());
			return present(userContext,account, mergedAllTokens(tokensExpr));
			//return saveAccount(userContext, account, tokens().done());
		}

	}
	
	public Account updateAccountProperty(BankUserContext userContext,String accountId, int accountVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingAccount(userContext, accountId, accountVersion, property, newValueExpr, tokensExpr);
		
		Account account = loadAccount(userContext, accountId, allTokens());
		if(account.getVersion() != accountVersion){
			String message = "The target version("+account.getVersion()+") is not equals to version("+accountVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(account){ 
			//will be good when the account loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Account.
			
			account.changeProperty(property, newValueExpr);
			account.updateUpdateTime(userContext.now());
			account = saveAccount(userContext, account, tokens().done());
			return present(userContext,account, mergedAllTokens(tokensExpr));
			//return saveAccount(userContext, account, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected AccountTokens tokens(){
		return AccountTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return AccountTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortTransactionListAsFromAccountWith("id","desc")
		.sortTransactionListAsToAccountWith("id","desc")
		.sortNameChangeEventListWith("id","desc")
		.sortAccountChangeListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return AccountTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(BankUserContext userContext, String accountId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfAccount(accountId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
 		
 	}
 	public Account transferToAnotherPlatform(BankUserContext userContext, String accountId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, accountId,anotherPlatformId);
 
		Account account = loadAccount(userContext, accountId, allTokens());	
		synchronized(account){
			//will be good when the account loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			account.updatePlatform(platform);		
			account = saveAccount(userContext, account, emptyOptions());
			
			return present(userContext,account, allTokens());
			
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
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForAccount(userContext,ownerClass, id, filterKey, pageNo, pageSize);
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

	public void delete(BankUserContext userContext, String accountId, int accountVersion) throws Exception {
		//deleteInternal(userContext, accountId, accountVersion);		
	}
	protected void deleteInternal(BankUserContext userContext,
			String accountId, int accountVersion) throws Exception{
			
		userContext.getDAOGroup().getAccountDAO().delete(accountId, accountVersion);
	}
	
	public Account forgetByAll(BankUserContext userContext, String accountId, int accountVersion) throws Exception {
		return forgetByAllInternal(userContext, accountId, accountVersion);		
	}
	protected Account forgetByAllInternal(BankUserContext userContext,
			String accountId, int accountVersion) throws Exception{
			
		return userContext.getDAOGroup().getAccountDAO().disconnectFromAll(accountId, accountVersion);
	}
	

	
	public int deleteAll(BankUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new AccountManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(BankUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getAccountDAO().deleteAll();
	}


	//disconnect Account with change_request in Transaction
	protected Account breakWithTransactionAsFromAccountByChangeRequest(BankUserContext userContext, String accountId, String changeRequestId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Account account = loadAccount(userContext, accountId, allTokens());

			synchronized(account){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getAccountDAO().planToRemoveTransactionListAsFromAccountWithChangeRequest(account, changeRequestId, this.emptyOptions());

				account = saveAccount(userContext, account, tokens().withTransactionListAsFromAccount().done());
				return account;
			}
	}
	//disconnect Account with change_request in Transaction
	protected Account breakWithTransactionAsToAccountByChangeRequest(BankUserContext userContext, String accountId, String changeRequestId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Account account = loadAccount(userContext, accountId, allTokens());

			synchronized(account){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getAccountDAO().planToRemoveTransactionListAsToAccountWithChangeRequest(account, changeRequestId, this.emptyOptions());

				account = saveAccount(userContext, account, tokens().withTransactionListAsToAccount().done());
				return account;
			}
	}
	//disconnect Account with change_request in NameChangeEvent
	protected Account breakWithNameChangeEventByChangeRequest(BankUserContext userContext, String accountId, String changeRequestId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Account account = loadAccount(userContext, accountId, allTokens());

			synchronized(account){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getAccountDAO().planToRemoveNameChangeEventListWithChangeRequest(account, changeRequestId, this.emptyOptions());

				account = saveAccount(userContext, account, tokens().withNameChangeEventList().done());
				return account;
			}
	}
	//disconnect Account with change_request in AccountChange
	protected Account breakWithAccountChangeByChangeRequest(BankUserContext userContext, String accountId, String changeRequestId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Account account = loadAccount(userContext, accountId, allTokens());

			synchronized(account){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getAccountDAO().planToRemoveAccountChangeListWithChangeRequest(account, changeRequestId, this.emptyOptions());

				account = saveAccount(userContext, account, tokens().withAccountChangeList().done());
				return account;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingTransactionAsFromAccount(BankUserContext userContext, String accountId, String name, BigDecimal amount, String type, String changeRequestId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfAccount(accountId);

		
		userContext.getChecker().checkNameOfTransaction(name);
		
		userContext.getChecker().checkAmountOfTransaction(amount);
		
		userContext.getChecker().checkTypeOfTransaction(type);
		
		userContext.getChecker().checkChangeRequestIdOfTransaction(changeRequestId);
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);

	
	}
	public  Account addTransactionAsFromAccount(BankUserContext userContext, String accountId, String name, BigDecimal amount, String type, String changeRequestId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingTransactionAsFromAccount(userContext,accountId,name, amount, type, changeRequestId,tokensExpr);
		
		Transaction transaction = createTransactionAsFromAccount(userContext,name, amount, type, changeRequestId);
		
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			account.addTransactionAsFromAccount( transaction );		
			account = saveAccount(userContext, account, tokens().withTransactionListAsFromAccount().done());
			
			userContext.getManagerGroup().getTransactionManager().onNewInstanceCreated(userContext, transaction);
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTransactionAsFromAccountProperties(BankUserContext userContext, String accountId,String id,String name,BigDecimal amount,String type,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().checkIdOfTransaction(id);
		
		userContext.getChecker().checkNameOfTransaction( name);
		userContext.getChecker().checkAmountOfTransaction( amount);
		userContext.getChecker().checkTypeOfTransaction( type);

		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
		
	}
	public  Account updateTransactionAsFromAccountProperties(BankUserContext userContext, String accountId, String id,String name,BigDecimal amount,String type, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingTransactionAsFromAccountProperties(userContext,accountId,id,name,amount,type,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTransactionListAsFromAccountList()
				.searchTransactionListAsFromAccountWith(Transaction.ID_PROPERTY, "is", id).done();
		
		Account accountToUpdate = loadAccount(userContext, accountId, options);
		
		if(accountToUpdate.getTransactionListAsFromAccount().isEmpty()){
			throw new AccountManagerException("Transaction is NOT FOUND with id: '"+id+"'");
		}
		
		Transaction item = accountToUpdate.getTransactionListAsFromAccount().first();
		
		item.updateName( name );
		item.updateAmount( amount );
		item.updateType( type );

		
		//checkParamsForAddingTransactionAsFromAccount(userContext,accountId,name, code, used,tokensExpr);
		Account account = saveAccount(userContext, accountToUpdate, tokens().withTransactionListAsFromAccount().done());
		synchronized(account){ 
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected Transaction createTransactionAsFromAccount(BankUserContext userContext, String name, BigDecimal amount, String type, String changeRequestId) throws Exception{

		Transaction transaction = new Transaction();
		
		
		transaction.setName(name);		
		transaction.setAmount(amount);		
		transaction.setType(type);		
		ChangeRequest  changeRequest = new ChangeRequest();
		changeRequest.setId(changeRequestId);		
		transaction.setChangeRequest(changeRequest);
	
		
		return transaction;
	
		
	}
	
	protected Transaction createIndexedTransactionAsFromAccount(String id, int version){

		Transaction transaction = new Transaction();
		transaction.setId(id);
		transaction.setVersion(version);
		return transaction;			
		
	}
	
	protected void checkParamsForRemovingTransactionListAsFromAccount(BankUserContext userContext, String accountId, 
			String transactionIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfAccount(accountId);
		for(String transactionIdItem: transactionIds){
			userContext.getChecker().checkIdOfTransaction(transactionIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
		
	}
	public  Account removeTransactionListAsFromAccount(BankUserContext userContext, String accountId, 
			String transactionIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingTransactionListAsFromAccount(userContext, accountId,  transactionIds, tokensExpr);
			
			
			Account account = loadAccount(userContext, accountId, allTokens());
			synchronized(account){ 
				//Will be good when the account loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getAccountDAO().planToRemoveTransactionListAsFromAccount(account, transactionIds, allTokens());
				account = saveAccount(userContext, account, tokens().withTransactionListAsFromAccount().done());
				deleteRelationListInGraph(userContext, account.getTransactionListAsFromAccount());
				return present(userContext,account, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingTransactionAsFromAccount(BankUserContext userContext, String accountId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfAccount( accountId);
		userContext.getChecker().checkIdOfTransaction(transactionId);
		userContext.getChecker().checkVersionOfTransaction(transactionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	public  Account removeTransactionAsFromAccount(BankUserContext userContext, String accountId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingTransactionAsFromAccount(userContext,accountId, transactionId, transactionVersion,tokensExpr);
		
		Transaction transaction = createIndexedTransactionAsFromAccount(transactionId, transactionVersion);
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			account.removeTransactionAsFromAccount( transaction );		
			account = saveAccount(userContext, account, tokens().withTransactionListAsFromAccount().done());
			deleteRelationInGraph(userContext, transaction);
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingTransactionAsFromAccount(BankUserContext userContext, String accountId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfAccount( accountId);
		userContext.getChecker().checkIdOfTransaction(transactionId);
		userContext.getChecker().checkVersionOfTransaction(transactionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	public  Account copyTransactionAsFromAccountFrom(BankUserContext userContext, String accountId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingTransactionAsFromAccount(userContext,accountId, transactionId, transactionVersion,tokensExpr);
		
		Transaction transactionAsFromAccount = createIndexedTransactionAsFromAccount(transactionId, transactionVersion);
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			account.copyTransactionAsFromAccountFrom( transactionAsFromAccount );		
			account = saveAccount(userContext, account, tokens().withTransactionListAsFromAccount().done());
			
			userContext.getManagerGroup().getTransactionManager().onNewInstanceCreated(userContext, (Transaction)account.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingTransactionAsFromAccount(BankUserContext userContext, String accountId, String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfAccount(accountId);
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
		
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	
	public  Account updateTransactionAsFromAccount(BankUserContext userContext, String accountId, String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingTransactionAsFromAccount(userContext, accountId, transactionId, transactionVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withTransactionListAsFromAccount().searchTransactionListAsFromAccountWith(Transaction.ID_PROPERTY, "eq", transactionId).done();
		
		
		
		Account account = loadAccount(userContext, accountId, loadTokens);
		
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//account.removeTransactionAsFromAccount( transaction );	
			//make changes to AcceleraterAccount.
			Transaction transactionIndex = createIndexedTransactionAsFromAccount(transactionId, transactionVersion);
		
			Transaction transactionAsFromAccount = account.findTheTransactionAsFromAccount(transactionIndex);
			if(transactionAsFromAccount == null){
				throw new AccountManagerException(transactionAsFromAccount+" is NOT FOUND" );
			}
			
			transactionAsFromAccount.changeProperty(property, newValueExpr);
			
			account = saveAccount(userContext, account, tokens().withTransactionListAsFromAccount().done());
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingTransactionAsToAccount(BankUserContext userContext, String accountId, String name, BigDecimal amount, String type, String changeRequestId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfAccount(accountId);

		
		userContext.getChecker().checkNameOfTransaction(name);
		
		userContext.getChecker().checkAmountOfTransaction(amount);
		
		userContext.getChecker().checkTypeOfTransaction(type);
		
		userContext.getChecker().checkChangeRequestIdOfTransaction(changeRequestId);
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);

	
	}
	public  Account addTransactionAsToAccount(BankUserContext userContext, String accountId, String name, BigDecimal amount, String type, String changeRequestId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingTransactionAsToAccount(userContext,accountId,name, amount, type, changeRequestId,tokensExpr);
		
		Transaction transaction = createTransactionAsToAccount(userContext,name, amount, type, changeRequestId);
		
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			account.addTransactionAsToAccount( transaction );		
			account = saveAccount(userContext, account, tokens().withTransactionListAsToAccount().done());
			
			userContext.getManagerGroup().getTransactionManager().onNewInstanceCreated(userContext, transaction);
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTransactionAsToAccountProperties(BankUserContext userContext, String accountId,String id,String name,BigDecimal amount,String type,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().checkIdOfTransaction(id);
		
		userContext.getChecker().checkNameOfTransaction( name);
		userContext.getChecker().checkAmountOfTransaction( amount);
		userContext.getChecker().checkTypeOfTransaction( type);

		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
		
	}
	public  Account updateTransactionAsToAccountProperties(BankUserContext userContext, String accountId, String id,String name,BigDecimal amount,String type, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingTransactionAsToAccountProperties(userContext,accountId,id,name,amount,type,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTransactionListAsToAccountList()
				.searchTransactionListAsToAccountWith(Transaction.ID_PROPERTY, "is", id).done();
		
		Account accountToUpdate = loadAccount(userContext, accountId, options);
		
		if(accountToUpdate.getTransactionListAsToAccount().isEmpty()){
			throw new AccountManagerException("Transaction is NOT FOUND with id: '"+id+"'");
		}
		
		Transaction item = accountToUpdate.getTransactionListAsToAccount().first();
		
		item.updateName( name );
		item.updateAmount( amount );
		item.updateType( type );

		
		//checkParamsForAddingTransactionAsToAccount(userContext,accountId,name, code, used,tokensExpr);
		Account account = saveAccount(userContext, accountToUpdate, tokens().withTransactionListAsToAccount().done());
		synchronized(account){ 
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected Transaction createTransactionAsToAccount(BankUserContext userContext, String name, BigDecimal amount, String type, String changeRequestId) throws Exception{

		Transaction transaction = new Transaction();
		
		
		transaction.setName(name);		
		transaction.setAmount(amount);		
		transaction.setType(type);		
		ChangeRequest  changeRequest = new ChangeRequest();
		changeRequest.setId(changeRequestId);		
		transaction.setChangeRequest(changeRequest);
	
		
		return transaction;
	
		
	}
	
	protected Transaction createIndexedTransactionAsToAccount(String id, int version){

		Transaction transaction = new Transaction();
		transaction.setId(id);
		transaction.setVersion(version);
		return transaction;			
		
	}
	
	protected void checkParamsForRemovingTransactionListAsToAccount(BankUserContext userContext, String accountId, 
			String transactionIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfAccount(accountId);
		for(String transactionIdItem: transactionIds){
			userContext.getChecker().checkIdOfTransaction(transactionIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
		
	}
	public  Account removeTransactionListAsToAccount(BankUserContext userContext, String accountId, 
			String transactionIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingTransactionListAsToAccount(userContext, accountId,  transactionIds, tokensExpr);
			
			
			Account account = loadAccount(userContext, accountId, allTokens());
			synchronized(account){ 
				//Will be good when the account loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getAccountDAO().planToRemoveTransactionListAsToAccount(account, transactionIds, allTokens());
				account = saveAccount(userContext, account, tokens().withTransactionListAsToAccount().done());
				deleteRelationListInGraph(userContext, account.getTransactionListAsToAccount());
				return present(userContext,account, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingTransactionAsToAccount(BankUserContext userContext, String accountId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfAccount( accountId);
		userContext.getChecker().checkIdOfTransaction(transactionId);
		userContext.getChecker().checkVersionOfTransaction(transactionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	public  Account removeTransactionAsToAccount(BankUserContext userContext, String accountId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingTransactionAsToAccount(userContext,accountId, transactionId, transactionVersion,tokensExpr);
		
		Transaction transaction = createIndexedTransactionAsToAccount(transactionId, transactionVersion);
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			account.removeTransactionAsToAccount( transaction );		
			account = saveAccount(userContext, account, tokens().withTransactionListAsToAccount().done());
			deleteRelationInGraph(userContext, transaction);
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingTransactionAsToAccount(BankUserContext userContext, String accountId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfAccount( accountId);
		userContext.getChecker().checkIdOfTransaction(transactionId);
		userContext.getChecker().checkVersionOfTransaction(transactionVersion);
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	public  Account copyTransactionAsToAccountFrom(BankUserContext userContext, String accountId, 
		String transactionId, int transactionVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingTransactionAsToAccount(userContext,accountId, transactionId, transactionVersion,tokensExpr);
		
		Transaction transactionAsToAccount = createIndexedTransactionAsToAccount(transactionId, transactionVersion);
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			account.copyTransactionAsToAccountFrom( transactionAsToAccount );		
			account = saveAccount(userContext, account, tokens().withTransactionListAsToAccount().done());
			
			userContext.getManagerGroup().getTransactionManager().onNewInstanceCreated(userContext, (Transaction)account.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingTransactionAsToAccount(BankUserContext userContext, String accountId, String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfAccount(accountId);
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
		
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	
	public  Account updateTransactionAsToAccount(BankUserContext userContext, String accountId, String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingTransactionAsToAccount(userContext, accountId, transactionId, transactionVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withTransactionListAsToAccount().searchTransactionListAsToAccountWith(Transaction.ID_PROPERTY, "eq", transactionId).done();
		
		
		
		Account account = loadAccount(userContext, accountId, loadTokens);
		
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//account.removeTransactionAsToAccount( transaction );	
			//make changes to AcceleraterAccount.
			Transaction transactionIndex = createIndexedTransactionAsToAccount(transactionId, transactionVersion);
		
			Transaction transactionAsToAccount = account.findTheTransactionAsToAccount(transactionIndex);
			if(transactionAsToAccount == null){
				throw new AccountManagerException(transactionAsToAccount+" is NOT FOUND" );
			}
			
			transactionAsToAccount.changeProperty(property, newValueExpr);
			
			account = saveAccount(userContext, account, tokens().withTransactionListAsToAccount().done());
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingNameChangeEvent(BankUserContext userContext, String accountId, String name, String changeRequestId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfAccount(accountId);

		
		userContext.getChecker().checkNameOfNameChangeEvent(name);
		
		userContext.getChecker().checkChangeRequestIdOfNameChangeEvent(changeRequestId);
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);

	
	}
	public  Account addNameChangeEvent(BankUserContext userContext, String accountId, String name, String changeRequestId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingNameChangeEvent(userContext,accountId,name, changeRequestId,tokensExpr);
		
		NameChangeEvent nameChangeEvent = createNameChangeEvent(userContext,name, changeRequestId);
		
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			account.addNameChangeEvent( nameChangeEvent );		
			account = saveAccount(userContext, account, tokens().withNameChangeEventList().done());
			
			userContext.getManagerGroup().getNameChangeEventManager().onNewInstanceCreated(userContext, nameChangeEvent);
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingNameChangeEventProperties(BankUserContext userContext, String accountId,String id,String name,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().checkIdOfNameChangeEvent(id);
		
		userContext.getChecker().checkNameOfNameChangeEvent( name);

		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
		
	}
	public  Account updateNameChangeEventProperties(BankUserContext userContext, String accountId, String id,String name, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingNameChangeEventProperties(userContext,accountId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withNameChangeEventListList()
				.searchNameChangeEventListWith(NameChangeEvent.ID_PROPERTY, "is", id).done();
		
		Account accountToUpdate = loadAccount(userContext, accountId, options);
		
		if(accountToUpdate.getNameChangeEventList().isEmpty()){
			throw new AccountManagerException("NameChangeEvent is NOT FOUND with id: '"+id+"'");
		}
		
		NameChangeEvent item = accountToUpdate.getNameChangeEventList().first();
		
		item.updateName( name );

		
		//checkParamsForAddingNameChangeEvent(userContext,accountId,name, code, used,tokensExpr);
		Account account = saveAccount(userContext, accountToUpdate, tokens().withNameChangeEventList().done());
		synchronized(account){ 
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected NameChangeEvent createNameChangeEvent(BankUserContext userContext, String name, String changeRequestId) throws Exception{

		NameChangeEvent nameChangeEvent = new NameChangeEvent();
		
		
		nameChangeEvent.setName(name);		
		ChangeRequest  changeRequest = new ChangeRequest();
		changeRequest.setId(changeRequestId);		
		nameChangeEvent.setChangeRequest(changeRequest);
	
		
		return nameChangeEvent;
	
		
	}
	
	protected NameChangeEvent createIndexedNameChangeEvent(String id, int version){

		NameChangeEvent nameChangeEvent = new NameChangeEvent();
		nameChangeEvent.setId(id);
		nameChangeEvent.setVersion(version);
		return nameChangeEvent;			
		
	}
	
	protected void checkParamsForRemovingNameChangeEventList(BankUserContext userContext, String accountId, 
			String nameChangeEventIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfAccount(accountId);
		for(String nameChangeEventIdItem: nameChangeEventIds){
			userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
		
	}
	public  Account removeNameChangeEventList(BankUserContext userContext, String accountId, 
			String nameChangeEventIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingNameChangeEventList(userContext, accountId,  nameChangeEventIds, tokensExpr);
			
			
			Account account = loadAccount(userContext, accountId, allTokens());
			synchronized(account){ 
				//Will be good when the account loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getAccountDAO().planToRemoveNameChangeEventList(account, nameChangeEventIds, allTokens());
				account = saveAccount(userContext, account, tokens().withNameChangeEventList().done());
				deleteRelationListInGraph(userContext, account.getNameChangeEventList());
				return present(userContext,account, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingNameChangeEvent(BankUserContext userContext, String accountId, 
		String nameChangeEventId, int nameChangeEventVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfAccount( accountId);
		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().checkVersionOfNameChangeEvent(nameChangeEventVersion);
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	public  Account removeNameChangeEvent(BankUserContext userContext, String accountId, 
		String nameChangeEventId, int nameChangeEventVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingNameChangeEvent(userContext,accountId, nameChangeEventId, nameChangeEventVersion,tokensExpr);
		
		NameChangeEvent nameChangeEvent = createIndexedNameChangeEvent(nameChangeEventId, nameChangeEventVersion);
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			account.removeNameChangeEvent( nameChangeEvent );		
			account = saveAccount(userContext, account, tokens().withNameChangeEventList().done());
			deleteRelationInGraph(userContext, nameChangeEvent);
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingNameChangeEvent(BankUserContext userContext, String accountId, 
		String nameChangeEventId, int nameChangeEventVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfAccount( accountId);
		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().checkVersionOfNameChangeEvent(nameChangeEventVersion);
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	public  Account copyNameChangeEventFrom(BankUserContext userContext, String accountId, 
		String nameChangeEventId, int nameChangeEventVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingNameChangeEvent(userContext,accountId, nameChangeEventId, nameChangeEventVersion,tokensExpr);
		
		NameChangeEvent nameChangeEvent = createIndexedNameChangeEvent(nameChangeEventId, nameChangeEventVersion);
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			account.copyNameChangeEventFrom( nameChangeEvent );		
			account = saveAccount(userContext, account, tokens().withNameChangeEventList().done());
			
			userContext.getManagerGroup().getNameChangeEventManager().onNewInstanceCreated(userContext, (NameChangeEvent)account.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingNameChangeEvent(BankUserContext userContext, String accountId, String nameChangeEventId, int nameChangeEventVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().checkIdOfNameChangeEvent(nameChangeEventId);
		userContext.getChecker().checkVersionOfNameChangeEvent(nameChangeEventVersion);
		

		if(NameChangeEvent.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfNameChangeEvent(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	
	public  Account updateNameChangeEvent(BankUserContext userContext, String accountId, String nameChangeEventId, int nameChangeEventVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingNameChangeEvent(userContext, accountId, nameChangeEventId, nameChangeEventVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withNameChangeEventList().searchNameChangeEventListWith(NameChangeEvent.ID_PROPERTY, "eq", nameChangeEventId).done();
		
		
		
		Account account = loadAccount(userContext, accountId, loadTokens);
		
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//account.removeNameChangeEvent( nameChangeEvent );	
			//make changes to AcceleraterAccount.
			NameChangeEvent nameChangeEventIndex = createIndexedNameChangeEvent(nameChangeEventId, nameChangeEventVersion);
		
			NameChangeEvent nameChangeEvent = account.findTheNameChangeEvent(nameChangeEventIndex);
			if(nameChangeEvent == null){
				throw new AccountManagerException(nameChangeEvent+" is NOT FOUND" );
			}
			
			nameChangeEvent.changeProperty(property, newValueExpr);
			
			account = saveAccount(userContext, account, tokens().withNameChangeEventList().done());
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingAccountChange(BankUserContext userContext, String accountId, String name, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, String changeRequestId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfAccount(accountId);

		
		userContext.getChecker().checkNameOfAccountChange(name);
		
		userContext.getChecker().checkPreviousBalanceOfAccountChange(previousBalance);
		
		userContext.getChecker().checkTypeOfAccountChange(type);
		
		userContext.getChecker().checkAmountOfAccountChange(amount);
		
		userContext.getChecker().checkCurrentBalanceOfAccountChange(currentBalance);
		
		userContext.getChecker().checkChangeRequestIdOfAccountChange(changeRequestId);
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);

	
	}
	public  Account addAccountChange(BankUserContext userContext, String accountId, String name, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, String changeRequestId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingAccountChange(userContext,accountId,name, previousBalance, type, amount, currentBalance, changeRequestId,tokensExpr);
		
		AccountChange accountChange = createAccountChange(userContext,name, previousBalance, type, amount, currentBalance, changeRequestId);
		
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			account.addAccountChange( accountChange );		
			account = saveAccount(userContext, account, tokens().withAccountChangeList().done());
			
			userContext.getManagerGroup().getAccountChangeManager().onNewInstanceCreated(userContext, accountChange);
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingAccountChangeProperties(BankUserContext userContext, String accountId,String id,String name,BigDecimal previousBalance,String type,BigDecimal amount,BigDecimal currentBalance,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().checkIdOfAccountChange(id);
		
		userContext.getChecker().checkNameOfAccountChange( name);
		userContext.getChecker().checkPreviousBalanceOfAccountChange( previousBalance);
		userContext.getChecker().checkTypeOfAccountChange( type);
		userContext.getChecker().checkAmountOfAccountChange( amount);
		userContext.getChecker().checkCurrentBalanceOfAccountChange( currentBalance);

		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
		
	}
	public  Account updateAccountChangeProperties(BankUserContext userContext, String accountId, String id,String name,BigDecimal previousBalance,String type,BigDecimal amount,BigDecimal currentBalance, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingAccountChangeProperties(userContext,accountId,id,name,previousBalance,type,amount,currentBalance,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withAccountChangeListList()
				.searchAccountChangeListWith(AccountChange.ID_PROPERTY, "is", id).done();
		
		Account accountToUpdate = loadAccount(userContext, accountId, options);
		
		if(accountToUpdate.getAccountChangeList().isEmpty()){
			throw new AccountManagerException("AccountChange is NOT FOUND with id: '"+id+"'");
		}
		
		AccountChange item = accountToUpdate.getAccountChangeList().first();
		
		item.updateName( name );
		item.updatePreviousBalance( previousBalance );
		item.updateType( type );
		item.updateAmount( amount );
		item.updateCurrentBalance( currentBalance );

		
		//checkParamsForAddingAccountChange(userContext,accountId,name, code, used,tokensExpr);
		Account account = saveAccount(userContext, accountToUpdate, tokens().withAccountChangeList().done());
		synchronized(account){ 
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected AccountChange createAccountChange(BankUserContext userContext, String name, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, String changeRequestId) throws Exception{

		AccountChange accountChange = new AccountChange();
		
		
		accountChange.setName(name);		
		accountChange.setPreviousBalance(previousBalance);		
		accountChange.setType(type);		
		accountChange.setAmount(amount);		
		accountChange.setCurrentBalance(currentBalance);		
		ChangeRequest  changeRequest = new ChangeRequest();
		changeRequest.setId(changeRequestId);		
		accountChange.setChangeRequest(changeRequest);
	
		
		return accountChange;
	
		
	}
	
	protected AccountChange createIndexedAccountChange(String id, int version){

		AccountChange accountChange = new AccountChange();
		accountChange.setId(id);
		accountChange.setVersion(version);
		return accountChange;			
		
	}
	
	protected void checkParamsForRemovingAccountChangeList(BankUserContext userContext, String accountId, 
			String accountChangeIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfAccount(accountId);
		for(String accountChangeIdItem: accountChangeIds){
			userContext.getChecker().checkIdOfAccountChange(accountChangeIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
		
	}
	public  Account removeAccountChangeList(BankUserContext userContext, String accountId, 
			String accountChangeIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingAccountChangeList(userContext, accountId,  accountChangeIds, tokensExpr);
			
			
			Account account = loadAccount(userContext, accountId, allTokens());
			synchronized(account){ 
				//Will be good when the account loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getAccountDAO().planToRemoveAccountChangeList(account, accountChangeIds, allTokens());
				account = saveAccount(userContext, account, tokens().withAccountChangeList().done());
				deleteRelationListInGraph(userContext, account.getAccountChangeList());
				return present(userContext,account, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingAccountChange(BankUserContext userContext, String accountId, 
		String accountChangeId, int accountChangeVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfAccount( accountId);
		userContext.getChecker().checkIdOfAccountChange(accountChangeId);
		userContext.getChecker().checkVersionOfAccountChange(accountChangeVersion);
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	public  Account removeAccountChange(BankUserContext userContext, String accountId, 
		String accountChangeId, int accountChangeVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingAccountChange(userContext,accountId, accountChangeId, accountChangeVersion,tokensExpr);
		
		AccountChange accountChange = createIndexedAccountChange(accountChangeId, accountChangeVersion);
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			account.removeAccountChange( accountChange );		
			account = saveAccount(userContext, account, tokens().withAccountChangeList().done());
			deleteRelationInGraph(userContext, accountChange);
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingAccountChange(BankUserContext userContext, String accountId, 
		String accountChangeId, int accountChangeVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfAccount( accountId);
		userContext.getChecker().checkIdOfAccountChange(accountChangeId);
		userContext.getChecker().checkVersionOfAccountChange(accountChangeVersion);
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	public  Account copyAccountChangeFrom(BankUserContext userContext, String accountId, 
		String accountChangeId, int accountChangeVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingAccountChange(userContext,accountId, accountChangeId, accountChangeVersion,tokensExpr);
		
		AccountChange accountChange = createIndexedAccountChange(accountChangeId, accountChangeVersion);
		Account account = loadAccount(userContext, accountId, allTokens());
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			account.copyAccountChangeFrom( accountChange );		
			account = saveAccount(userContext, account, tokens().withAccountChangeList().done());
			
			userContext.getManagerGroup().getAccountChangeManager().onNewInstanceCreated(userContext, (AccountChange)account.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingAccountChange(BankUserContext userContext, String accountId, String accountChangeId, int accountChangeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfAccount(accountId);
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
		
	
		userContext.getChecker().throwExceptionIfHasErrors(AccountManagerException.class);
	
	}
	
	public  Account updateAccountChange(BankUserContext userContext, String accountId, String accountChangeId, int accountChangeVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingAccountChange(userContext, accountId, accountChangeId, accountChangeVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withAccountChangeList().searchAccountChangeListWith(AccountChange.ID_PROPERTY, "eq", accountChangeId).done();
		
		
		
		Account account = loadAccount(userContext, accountId, loadTokens);
		
		synchronized(account){ 
			//Will be good when the account loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//account.removeAccountChange( accountChange );	
			//make changes to AcceleraterAccount.
			AccountChange accountChangeIndex = createIndexedAccountChange(accountChangeId, accountChangeVersion);
		
			AccountChange accountChange = account.findTheAccountChange(accountChangeIndex);
			if(accountChange == null){
				throw new AccountManagerException(accountChange+" is NOT FOUND" );
			}
			
			accountChange.changeProperty(property, newValueExpr);
			
			account = saveAccount(userContext, account, tokens().withAccountChangeList().done());
			return present(userContext,account, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(BankUserContext userContext, Account newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


