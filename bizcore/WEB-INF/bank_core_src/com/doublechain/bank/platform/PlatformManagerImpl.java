
package com.doublechain.bank.platform;

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


import com.doublechain.bank.platform.Platform;






public class PlatformManagerImpl extends CustomBankCheckerManager implements PlatformManager {
	
	private static final String SERVICE_TYPE = "Platform";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws PlatformManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new PlatformManagerException(message);

	}
	
	

 	protected Platform savePlatform(BankUserContext userContext, Platform platform, String [] tokensExpr) throws Exception{	
 		//return getPlatformDAO().save(platform, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePlatform(userContext, platform, tokens);
 	}
 	
 	protected Platform savePlatformDetail(BankUserContext userContext, Platform platform) throws Exception{	

 		
 		return savePlatform(userContext, platform, allTokens());
 	}
 	
 	public Platform loadPlatform(BankUserContext userContext, String platformId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	
 	 public Platform searchPlatform(BankUserContext userContext, String platformId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	

 	protected Platform present(BankUserContext userContext, Platform platform, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,platform,tokens);
		
		
		Platform  platformToPresent = userContext.getDAOGroup().getPlatformDAO().present(platform, tokens);
		
		List<BaseEntity> entityListToNaming = platformToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getPlatformDAO().alias(entityListToNaming);
		
		return  platformToPresent;
		
		
	}
 
 	
 	
 	public Platform loadPlatformDetail(BankUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, allTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	
 	public Object view(BankUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, viewTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	protected Platform savePlatform(BankUserContext userContext, Platform platform, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getPlatformDAO().save(platform, tokens);
 	}
 	protected Platform loadPlatform(BankUserContext userContext, String platformId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 
 		return userContext.getDAOGroup().getPlatformDAO().load(platformId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(BankUserContext userContext, Platform platform, Map<String, Object> tokens){
		super.addActions(userContext, platform, tokens);
		
		addAction(userContext, platform, tokens,"@create","createPlatform","createPlatform/","main","primary");
		addAction(userContext, platform, tokens,"@update","updatePlatform","updatePlatform/"+platform.getId()+"/","main","primary");
		addAction(userContext, platform, tokens,"@copy","clonePlatform","clonePlatform/"+platform.getId()+"/","main","primary");
		
		addAction(userContext, platform, tokens,"platform.addChangeRequest","addChangeRequest","addChangeRequest/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.removeChangeRequest","removeChangeRequest","removeChangeRequest/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.updateChangeRequest","updateChangeRequest","updateChangeRequest/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.copyChangeRequestFrom","copyChangeRequestFrom","copyChangeRequestFrom/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.addAccount","addAccount","addAccount/"+platform.getId()+"/","accountList","primary");
		addAction(userContext, platform, tokens,"platform.removeAccount","removeAccount","removeAccount/"+platform.getId()+"/","accountList","primary");
		addAction(userContext, platform, tokens,"platform.updateAccount","updateAccount","updateAccount/"+platform.getId()+"/","accountList","primary");
		addAction(userContext, platform, tokens,"platform.copyAccountFrom","copyAccountFrom","copyAccountFrom/"+platform.getId()+"/","accountList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(BankUserContext userContext, Platform platform, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public Platform createPlatform(BankUserContext userContext,String name) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfPlatform(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);


		Platform platform=createNewPlatform();	

		platform.setName(name);
		platform.setFounded(userContext.now());

		platform = savePlatform(userContext, platform, emptyOptions());
		
		onNewInstanceCreated(userContext, platform);
		return platform;

		
	}
	protected Platform createNewPlatform() 
	{
		
		return new Platform();		
	}
	
	protected void checkParamsForUpdatingPlatform(BankUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkVersionOfPlatform( platformVersion);
		

		if(Platform.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPlatform(parseString(newValueExpr));
		}
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
		
	}
	
	
	
	public Platform clone(BankUserContext userContext, String fromPlatformId) throws Exception{
		
		return userContext.getDAOGroup().getPlatformDAO().clone(fromPlatformId, this.allTokens());
	}
	
	public Platform internalSavePlatform(BankUserContext userContext, Platform platform) throws Exception 
	{
		return internalSavePlatform(userContext, platform, allTokens());

	}
	public Platform internalSavePlatform(BankUserContext userContext, Platform platform, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			if (platform.isChanged()){
			
			}
			platform = savePlatform(userContext, platform, options);
			return platform;
			
		}

	}
	
	public Platform updatePlatform(BankUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			platform.changeProperty(property, newValueExpr);
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	
	public Platform updatePlatformProperty(BankUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			platform.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected PlatformTokens tokens(){
		return PlatformTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PlatformTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortChangeRequestListWith("id","desc")
		.sortAccountListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PlatformTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(BankUserContext userContext, String platformId, int platformVersion) throws Exception {
		//deleteInternal(userContext, platformId, platformVersion);		
	}
	protected void deleteInternal(BankUserContext userContext,
			String platformId, int platformVersion) throws Exception{
			
		userContext.getDAOGroup().getPlatformDAO().delete(platformId, platformVersion);
	}
	
	public Platform forgetByAll(BankUserContext userContext, String platformId, int platformVersion) throws Exception {
		return forgetByAllInternal(userContext, platformId, platformVersion);		
	}
	protected Platform forgetByAllInternal(BankUserContext userContext,
			String platformId, int platformVersion) throws Exception{
			
		return userContext.getDAOGroup().getPlatformDAO().disconnectFromAll(platformId, platformVersion);
	}
	

	
	public int deleteAll(BankUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PlatformManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(BankUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getPlatformDAO().deleteAll();
	}


	
	
	
	
	

	protected void checkParamsForAddingChangeRequest(BankUserContext userContext, String platformId, String name,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkNameOfChangeRequest(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addChangeRequest(BankUserContext userContext, String platformId, String name, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingChangeRequest(userContext,platformId,name,tokensExpr);
		
		ChangeRequest changeRequest = createChangeRequest(userContext,name);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addChangeRequest( changeRequest );		
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			
			userContext.getManagerGroup().getChangeRequestManager().onNewInstanceCreated(userContext, changeRequest);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingChangeRequestProperties(BankUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfChangeRequest(id);
		
		userContext.getChecker().checkNameOfChangeRequest( name);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updateChangeRequestProperties(BankUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingChangeRequestProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withChangeRequestListList()
				.searchChangeRequestListWith(ChangeRequest.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getChangeRequestList().isEmpty()){
			throw new PlatformManagerException("ChangeRequest is NOT FOUND with id: '"+id+"'");
		}
		
		ChangeRequest item = platformToUpdate.getChangeRequestList().first();
		
		item.updateName( name );

		
		//checkParamsForAddingChangeRequest(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withChangeRequestList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected ChangeRequest createChangeRequest(BankUserContext userContext, String name) throws Exception{

		ChangeRequest changeRequest = new ChangeRequest();
		
		
		changeRequest.setName(name);		
		changeRequest.setCreateTime(userContext.now());
	
		
		return changeRequest;
	
		
	}
	
	protected ChangeRequest createIndexedChangeRequest(String id, int version){

		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(id);
		changeRequest.setVersion(version);
		return changeRequest;			
		
	}
	
	protected void checkParamsForRemovingChangeRequestList(BankUserContext userContext, String platformId, 
			String changeRequestIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String changeRequestIdItem: changeRequestIds){
			userContext.getChecker().checkIdOfChangeRequest(changeRequestIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removeChangeRequestList(BankUserContext userContext, String platformId, 
			String changeRequestIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingChangeRequestList(userContext, platformId,  changeRequestIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemoveChangeRequestList(platform, changeRequestIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
				deleteRelationListInGraph(userContext, platform.getChangeRequestList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingChangeRequest(BankUserContext userContext, String platformId, 
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkVersionOfChangeRequest(changeRequestVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removeChangeRequest(BankUserContext userContext, String platformId, 
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingChangeRequest(userContext,platformId, changeRequestId, changeRequestVersion,tokensExpr);
		
		ChangeRequest changeRequest = createIndexedChangeRequest(changeRequestId, changeRequestVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeChangeRequest( changeRequest );		
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			deleteRelationInGraph(userContext, changeRequest);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingChangeRequest(BankUserContext userContext, String platformId, 
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkVersionOfChangeRequest(changeRequestVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copyChangeRequestFrom(BankUserContext userContext, String platformId, 
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingChangeRequest(userContext,platformId, changeRequestId, changeRequestVersion,tokensExpr);
		
		ChangeRequest changeRequest = createIndexedChangeRequest(changeRequestId, changeRequestVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			platform.copyChangeRequestFrom( changeRequest );		
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			
			userContext.getManagerGroup().getChangeRequestManager().onNewInstanceCreated(userContext, (ChangeRequest)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingChangeRequest(BankUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfChangeRequest(changeRequestId);
		userContext.getChecker().checkVersionOfChangeRequest(changeRequestVersion);
		

		if(ChangeRequest.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfChangeRequest(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updateChangeRequest(BankUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingChangeRequest(userContext, platformId, changeRequestId, changeRequestVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withChangeRequestList().searchChangeRequestListWith(ChangeRequest.ID_PROPERTY, "eq", changeRequestId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeChangeRequest( changeRequest );	
			//make changes to AcceleraterAccount.
			ChangeRequest changeRequestIndex = createIndexedChangeRequest(changeRequestId, changeRequestVersion);
		
			ChangeRequest changeRequest = platform.findTheChangeRequest(changeRequestIndex);
			if(changeRequest == null){
				throw new PlatformManagerException(changeRequest+" is NOT FOUND" );
			}
			
			changeRequest.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingAccount(BankUserContext userContext, String platformId, String name, BigDecimal balance,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkNameOfAccount(name);
		
		userContext.getChecker().checkBalanceOfAccount(balance);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addAccount(BankUserContext userContext, String platformId, String name, BigDecimal balance, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingAccount(userContext,platformId,name, balance,tokensExpr);
		
		Account account = createAccount(userContext,name, balance);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addAccount( account );		
			platform = savePlatform(userContext, platform, tokens().withAccountList().done());
			
			userContext.getManagerGroup().getAccountManager().onNewInstanceCreated(userContext, account);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingAccountProperties(BankUserContext userContext, String platformId,String id,String name,BigDecimal balance,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfAccount(id);
		
		userContext.getChecker().checkNameOfAccount( name);
		userContext.getChecker().checkBalanceOfAccount( balance);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updateAccountProperties(BankUserContext userContext, String platformId, String id,String name,BigDecimal balance, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingAccountProperties(userContext,platformId,id,name,balance,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withAccountListList()
				.searchAccountListWith(Account.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getAccountList().isEmpty()){
			throw new PlatformManagerException("Account is NOT FOUND with id: '"+id+"'");
		}
		
		Account item = platformToUpdate.getAccountList().first();
		
		item.updateName( name );
		item.updateBalance( balance );

		
		//checkParamsForAddingAccount(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withAccountList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected Account createAccount(BankUserContext userContext, String name, BigDecimal balance) throws Exception{

		Account account = new Account();
		
		
		account.setName(name);		
		account.setBalance(balance);		
		account.setCreateTime(userContext.now());		
		account.setUpdateTime(userContext.now());
	
		
		return account;
	
		
	}
	
	protected Account createIndexedAccount(String id, int version){

		Account account = new Account();
		account.setId(id);
		account.setVersion(version);
		return account;			
		
	}
	
	protected void checkParamsForRemovingAccountList(BankUserContext userContext, String platformId, 
			String accountIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String accountIdItem: accountIds){
			userContext.getChecker().checkIdOfAccount(accountIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removeAccountList(BankUserContext userContext, String platformId, 
			String accountIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingAccountList(userContext, platformId,  accountIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemoveAccountList(platform, accountIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withAccountList().done());
				deleteRelationListInGraph(userContext, platform.getAccountList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingAccount(BankUserContext userContext, String platformId, 
		String accountId, int accountVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().checkVersionOfAccount(accountVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removeAccount(BankUserContext userContext, String platformId, 
		String accountId, int accountVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingAccount(userContext,platformId, accountId, accountVersion,tokensExpr);
		
		Account account = createIndexedAccount(accountId, accountVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeAccount( account );		
			platform = savePlatform(userContext, platform, tokens().withAccountList().done());
			deleteRelationInGraph(userContext, account);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingAccount(BankUserContext userContext, String platformId, 
		String accountId, int accountVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().checkVersionOfAccount(accountVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copyAccountFrom(BankUserContext userContext, String platformId, 
		String accountId, int accountVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingAccount(userContext,platformId, accountId, accountVersion,tokensExpr);
		
		Account account = createIndexedAccount(accountId, accountVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			account.updateUpdateTime(userContext.now());
			
			platform.copyAccountFrom( account );		
			platform = savePlatform(userContext, platform, tokens().withAccountList().done());
			
			userContext.getManagerGroup().getAccountManager().onNewInstanceCreated(userContext, (Account)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingAccount(BankUserContext userContext, String platformId, String accountId, int accountVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfAccount(accountId);
		userContext.getChecker().checkVersionOfAccount(accountVersion);
		

		if(Account.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfAccount(parseString(newValueExpr));
		}
		
		if(Account.BALANCE_PROPERTY.equals(property)){
			userContext.getChecker().checkBalanceOfAccount(parseBigDecimal(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updateAccount(BankUserContext userContext, String platformId, String accountId, int accountVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingAccount(userContext, platformId, accountId, accountVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withAccountList().searchAccountListWith(Account.ID_PROPERTY, "eq", accountId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeAccount( account );	
			//make changes to AcceleraterAccount.
			Account accountIndex = createIndexedAccount(accountId, accountVersion);
		
			Account account = platform.findTheAccount(accountIndex);
			if(account == null){
				throw new PlatformManagerException(account+" is NOT FOUND" );
			}
			
			account.changeProperty(property, newValueExpr);
			account.updateUpdateTime(userContext.now());
			platform = savePlatform(userContext, platform, tokens().withAccountList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(BankUserContext userContext, Platform newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


