
package com.doublechain.bank.account;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import com.doublechain.bank.BankBaseDAOImpl;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;
import com.doublechain.bank.AccessKey;
import com.doublechain.bank.DateKey;
import com.doublechain.bank.StatsInfo;
import com.doublechain.bank.StatsItem;

import com.doublechain.bank.MultipleAccessKey;
import com.doublechain.bank.BankUserContext;


import com.doublechain.bank.namechangeevent.NameChangeEvent;
import com.doublechain.bank.platform.Platform;
import com.doublechain.bank.transaction.Transaction;
import com.doublechain.bank.accountchange.AccountChange;

import com.doublechain.bank.transaction.TransactionDAO;
import com.doublechain.bank.namechangeevent.NameChangeEventDAO;
import com.doublechain.bank.platform.PlatformDAO;
import com.doublechain.bank.accountchange.AccountChangeDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class AccountJDBCTemplateDAO extends BankBaseDAOImpl implements AccountDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  TransactionDAO  transactionDAO;
 	public void setTransactionDAO(TransactionDAO pTransactionDAO){
 	
 		if(pTransactionDAO == null){
 			throw new IllegalStateException("Do not try to set transactionDAO to null.");
 		}
	 	this.transactionDAO = pTransactionDAO;
 	}
 	public TransactionDAO getTransactionDAO(){
 		if(this.transactionDAO == null){
 			throw new IllegalStateException("The transactionDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.transactionDAO;
 	}	
 	
			
		
	
  	private  NameChangeEventDAO  nameChangeEventDAO;
 	public void setNameChangeEventDAO(NameChangeEventDAO pNameChangeEventDAO){
 	
 		if(pNameChangeEventDAO == null){
 			throw new IllegalStateException("Do not try to set nameChangeEventDAO to null.");
 		}
	 	this.nameChangeEventDAO = pNameChangeEventDAO;
 	}
 	public NameChangeEventDAO getNameChangeEventDAO(){
 		if(this.nameChangeEventDAO == null){
 			throw new IllegalStateException("The nameChangeEventDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.nameChangeEventDAO;
 	}	
 	
			
		
	
  	private  AccountChangeDAO  accountChangeDAO;
 	public void setAccountChangeDAO(AccountChangeDAO pAccountChangeDAO){
 	
 		if(pAccountChangeDAO == null){
 			throw new IllegalStateException("Do not try to set accountChangeDAO to null.");
 		}
	 	this.accountChangeDAO = pAccountChangeDAO;
 	}
 	public AccountChangeDAO getAccountChangeDAO(){
 		if(this.accountChangeDAO == null){
 			throw new IllegalStateException("The accountChangeDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.accountChangeDAO;
 	}	
 	
			
		

	
	/*
	protected Account load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalAccount(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Account load(String id,Map<String,Object> options) throws Exception{
		return loadInternalAccount(AccountTable.withId(id), options);
	}
	
	
	
	public Account loadByName(String name,Map<String,Object> options) throws Exception{
		return loadInternalAccount(AccountTable.withName( name), options);
	}
	
	
	public Account save(Account account,Map<String,Object> options){
		
		String methodName="save(Account account,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(account, methodName, "account");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalAccount(account,options);
	}
	public Account clone(String accountId, Map<String,Object> options) throws Exception{
	
		return clone(AccountTable.withId(accountId),options);
	}
	
	protected Account clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String accountId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Account newAccount = loadInternalAccount(accessKey, options);
		newAccount.setVersion(0);
		
		
 		
 		if(isSaveTransactionListAsFromAccountEnabled(options)){
 			for(Transaction item: newAccount.getTransactionListAsFromAccount()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveTransactionListAsToAccountEnabled(options)){
 			for(Transaction item: newAccount.getTransactionListAsToAccount()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveNameChangeEventListEnabled(options)){
 			for(NameChangeEvent item: newAccount.getNameChangeEventList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveAccountChangeListEnabled(options)){
 			for(AccountChange item: newAccount.getAccountChangeList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalAccount(newAccount,options);
		
		return newAccount;
	}
	
	
	
	public Account cloneByName(String name,Map<String,Object> options) throws Exception{
		return clone(AccountTable.withName( name), options);
	}
	
	
	

	protected void throwIfHasException(String accountId,int version,int count) throws Exception{
		if (count == 1) {
			throw new AccountVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new AccountNotFoundException(
					"The " + this.getTableName() + "(" + accountId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String accountId, int version) throws Exception{
	
		String methodName="delete(String accountId, int version)";
		assertMethodArgumentNotNull(accountId, methodName, "accountId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{accountId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(accountId,version);
		}
		
	
	}
	
	
	
	
	

	public Account disconnectFromAll(String accountId, int version) throws Exception{
	
		
		Account account = loadInternalAccount(AccountTable.withId(accountId), emptyOptions());
		account.clearFromAll();
		this.saveAccount(account);
		return account;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return AccountTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "account";
	}
	@Override
	protected String getBeanName() {
		
		return "account";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return AccountTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, AccountTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, AccountTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractTransactionListAsFromAccountEnabled(Map<String,Object> options){		
 		return checkOptions(options,AccountTokens.TRANSACTION_LIST_AS_FROM_ACCOUNT);
 	}
 	protected boolean isAnalyzeTransactionListAsFromAccountEnabled(Map<String,Object> options){		 		
 		return AccountTokens.of(options).analyzeTransactionListAsFromAccountEnabled();
 	}
	
	protected boolean isSaveTransactionListAsFromAccountEnabled(Map<String,Object> options){
		return checkOptions(options, AccountTokens.TRANSACTION_LIST_AS_FROM_ACCOUNT);
		
 	}
 	
		
	
	protected boolean isExtractTransactionListAsToAccountEnabled(Map<String,Object> options){		
 		return checkOptions(options,AccountTokens.TRANSACTION_LIST_AS_TO_ACCOUNT);
 	}
 	protected boolean isAnalyzeTransactionListAsToAccountEnabled(Map<String,Object> options){		 		
 		return AccountTokens.of(options).analyzeTransactionListAsToAccountEnabled();
 	}
	
	protected boolean isSaveTransactionListAsToAccountEnabled(Map<String,Object> options){
		return checkOptions(options, AccountTokens.TRANSACTION_LIST_AS_TO_ACCOUNT);
		
 	}
 	
		
	
	protected boolean isExtractNameChangeEventListEnabled(Map<String,Object> options){		
 		return checkOptions(options,AccountTokens.NAME_CHANGE_EVENT_LIST);
 	}
 	protected boolean isAnalyzeNameChangeEventListEnabled(Map<String,Object> options){		 		
 		return AccountTokens.of(options).analyzeNameChangeEventListEnabled();
 	}
	
	protected boolean isSaveNameChangeEventListEnabled(Map<String,Object> options){
		return checkOptions(options, AccountTokens.NAME_CHANGE_EVENT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractAccountChangeListEnabled(Map<String,Object> options){		
 		return checkOptions(options,AccountTokens.ACCOUNT_CHANGE_LIST);
 	}
 	protected boolean isAnalyzeAccountChangeListEnabled(Map<String,Object> options){		 		
 		return AccountTokens.of(options).analyzeAccountChangeListEnabled();
 	}
	
	protected boolean isSaveAccountChangeListEnabled(Map<String,Object> options){
		return checkOptions(options, AccountTokens.ACCOUNT_CHANGE_LIST);
		
 	}
 	
		

	

	protected AccountMapper getAccountMapper(){
		return new AccountMapper();
	}

	
	
	protected Account extractAccount(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Account account = loadSingleObject(accessKey, getAccountMapper());
			return account;
		}catch(EmptyResultDataAccessException e){
			throw new AccountNotFoundException("Account("+accessKey+") is not found!");
		}

	}

	
	

	protected Account loadInternalAccount(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Account account = extractAccount(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(account, loadOptions);
 		}
 
		
		if(isExtractTransactionListAsFromAccountEnabled(loadOptions)){
	 		extractTransactionListAsFromAccount(account, loadOptions);
 		}	
 		if(isAnalyzeTransactionListAsFromAccountEnabled(loadOptions)){
	 		analyzeTransactionListAsFromAccount(account, loadOptions);
 		}
 		
		
		if(isExtractTransactionListAsToAccountEnabled(loadOptions)){
	 		extractTransactionListAsToAccount(account, loadOptions);
 		}	
 		if(isAnalyzeTransactionListAsToAccountEnabled(loadOptions)){
	 		analyzeTransactionListAsToAccount(account, loadOptions);
 		}
 		
		
		if(isExtractNameChangeEventListEnabled(loadOptions)){
	 		extractNameChangeEventList(account, loadOptions);
 		}	
 		if(isAnalyzeNameChangeEventListEnabled(loadOptions)){
	 		analyzeNameChangeEventList(account, loadOptions);
 		}
 		
		
		if(isExtractAccountChangeListEnabled(loadOptions)){
	 		extractAccountChangeList(account, loadOptions);
 		}	
 		if(isAnalyzeAccountChangeListEnabled(loadOptions)){
	 		analyzeAccountChangeList(account, loadOptions);
 		}
 		
		
		return account;
		
	}

	 

 	protected Account extractPlatform(Account account, Map<String,Object> options) throws Exception{

		if(account.getPlatform() == null){
			return account;
		}
		String platformId = account.getPlatform().getId();
		if( platformId == null){
			return account;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			account.setPlatform(platform);
		}
		
 		
 		return account;
 	}
 		
 
		
	protected void enhanceTransactionListAsFromAccount(SmartList<Transaction> transactionListAsFromAccount,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Account extractTransactionListAsFromAccount(Account account, Map<String,Object> options){
		
		
		if(account == null){
			return null;
		}
		if(account.getId() == null){
			return account;
		}
		convertListOptions(options,"transactionListAsFromAccount","transactionList");

		
		
		SmartList<Transaction> transactionListAsFromAccount = getTransactionDAO().findTransactionByFromAccount(account.getId(),options);
		if(transactionListAsFromAccount != null){
			enhanceTransactionListAsFromAccount(transactionListAsFromAccount,options);
			account.setTransactionListAsFromAccount(transactionListAsFromAccount);
		}
		
		return account;
	
	}	
	
	protected Account analyzeTransactionListAsFromAccount(Account account, Map<String,Object> options){
		
		
		if(account == null){
			return null;
		}
		if(account.getId() == null){
			return account;
		}
		convertListOptions(options,"transactionListAsFromAccount","transactionList");

		
		
		SmartList<Transaction> transactionListAsFromAccount = account.getTransactionListAsFromAccount();
		if(transactionListAsFromAccount != null){
			getTransactionDAO().analyzeTransactionByFromAccount(transactionListAsFromAccount, account.getId(), options);
			
		}
		
		return account;
	
	}	
	
		
	protected void enhanceTransactionListAsToAccount(SmartList<Transaction> transactionListAsToAccount,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Account extractTransactionListAsToAccount(Account account, Map<String,Object> options){
		
		
		if(account == null){
			return null;
		}
		if(account.getId() == null){
			return account;
		}
		convertListOptions(options,"transactionListAsToAccount","transactionList");

		
		
		SmartList<Transaction> transactionListAsToAccount = getTransactionDAO().findTransactionByToAccount(account.getId(),options);
		if(transactionListAsToAccount != null){
			enhanceTransactionListAsToAccount(transactionListAsToAccount,options);
			account.setTransactionListAsToAccount(transactionListAsToAccount);
		}
		
		return account;
	
	}	
	
	protected Account analyzeTransactionListAsToAccount(Account account, Map<String,Object> options){
		
		
		if(account == null){
			return null;
		}
		if(account.getId() == null){
			return account;
		}
		convertListOptions(options,"transactionListAsToAccount","transactionList");

		
		
		SmartList<Transaction> transactionListAsToAccount = account.getTransactionListAsToAccount();
		if(transactionListAsToAccount != null){
			getTransactionDAO().analyzeTransactionByFromAccount(transactionListAsToAccount, account.getId(), options);
			
		}
		
		return account;
	
	}	
	
		
	protected void enhanceNameChangeEventList(SmartList<NameChangeEvent> nameChangeEventList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Account extractNameChangeEventList(Account account, Map<String,Object> options){
		
		
		if(account == null){
			return null;
		}
		if(account.getId() == null){
			return account;
		}

		
		
		SmartList<NameChangeEvent> nameChangeEventList = getNameChangeEventDAO().findNameChangeEventByAccount(account.getId(),options);
		if(nameChangeEventList != null){
			enhanceNameChangeEventList(nameChangeEventList,options);
			account.setNameChangeEventList(nameChangeEventList);
		}
		
		return account;
	
	}	
	
	protected Account analyzeNameChangeEventList(Account account, Map<String,Object> options){
		
		
		if(account == null){
			return null;
		}
		if(account.getId() == null){
			return account;
		}

		
		
		SmartList<NameChangeEvent> nameChangeEventList = account.getNameChangeEventList();
		if(nameChangeEventList != null){
			getNameChangeEventDAO().analyzeNameChangeEventByAccount(nameChangeEventList, account.getId(), options);
			
		}
		
		return account;
	
	}	
	
		
	protected void enhanceAccountChangeList(SmartList<AccountChange> accountChangeList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Account extractAccountChangeList(Account account, Map<String,Object> options){
		
		
		if(account == null){
			return null;
		}
		if(account.getId() == null){
			return account;
		}

		
		
		SmartList<AccountChange> accountChangeList = getAccountChangeDAO().findAccountChangeByAccount(account.getId(),options);
		if(accountChangeList != null){
			enhanceAccountChangeList(accountChangeList,options);
			account.setAccountChangeList(accountChangeList);
		}
		
		return account;
	
	}	
	
	protected Account analyzeAccountChangeList(Account account, Map<String,Object> options){
		
		
		if(account == null){
			return null;
		}
		if(account.getId() == null){
			return account;
		}

		
		
		SmartList<AccountChange> accountChangeList = account.getAccountChangeList();
		if(accountChangeList != null){
			getAccountChangeDAO().analyzeAccountChangeByAccount(accountChangeList, account.getId(), options);
			
		}
		
		return account;
	
	}	
	
		
		
  	
 	public SmartList<Account> findAccountByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Account> resultList = queryWith(AccountTable.COLUMN_PLATFORM, platformId, options, getAccountMapper());
		// analyzeAccountByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Account> findAccountByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Account> resultList =  queryWithRange(AccountTable.COLUMN_PLATFORM, platformId, options, getAccountMapper(), start, count);
 		//analyzeAccountByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeAccountByPlatform(SmartList<Account> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Account.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Account.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("账户");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Account.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Account.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countAccountByPlatform(String platformId,Map<String,Object> options){

 		return countWith(AccountTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countAccountByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(AccountTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected Account saveAccount(Account  account){
		
		if(!account.isChanged()){
			return account;
		}
		
		
		String SQL=this.getSaveAccountSQL(account);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveAccountParameters(account);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		account.incVersion();
		return account;
	
	}
	public SmartList<Account> saveAccountList(SmartList<Account> accountList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitAccountList(accountList);
		
		batchAccountCreate((List<Account>)lists[CREATE_LIST_INDEX]);
		
		batchAccountUpdate((List<Account>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Account account:accountList){
			if(account.isChanged()){
				account.incVersion();
			}
			
		
		}
		
		
		return accountList;
	}

	public SmartList<Account> removeAccountList(SmartList<Account> accountList,Map<String,Object> options){
		
		
		super.removeList(accountList, options);
		
		return accountList;
		
		
	}
	
	protected List<Object[]> prepareAccountBatchCreateArgs(List<Account> accountList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Account account:accountList ){
			Object [] parameters = prepareAccountCreateParameters(account);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareAccountBatchUpdateArgs(List<Account> accountList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Account account:accountList ){
			if(!account.isChanged()){
				continue;
			}
			Object [] parameters = prepareAccountUpdateParameters(account);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchAccountCreate(List<Account> accountList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareAccountBatchCreateArgs(accountList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchAccountUpdate(List<Account> accountList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareAccountBatchUpdateArgs(accountList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitAccountList(List<Account> accountList){
		
		List<Account> accountCreateList=new ArrayList<Account>();
		List<Account> accountUpdateList=new ArrayList<Account>();
		
		for(Account account: accountList){
			if(isUpdateRequest(account)){
				accountUpdateList.add( account);
				continue;
			}
			accountCreateList.add(account);
		}
		
		return new Object[]{accountCreateList,accountUpdateList};
	}
	
	protected boolean isUpdateRequest(Account account){
 		return account.getVersion() > 0;
 	}
 	protected String getSaveAccountSQL(Account account){
 		if(isUpdateRequest(account)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveAccountParameters(Account account){
 		if(isUpdateRequest(account) ){
 			return prepareAccountUpdateParameters(account);
 		}
 		return prepareAccountCreateParameters(account);
 	}
 	protected Object[] prepareAccountUpdateParameters(Account account){
 		Object[] parameters = new Object[8];
 
 		parameters[0] = account.getName();
 		parameters[1] = account.getBalance();
 		parameters[2] = account.getCreateTime();
 		parameters[3] = account.getUpdateTime(); 	
 		if(account.getPlatform() != null){
 			parameters[4] = account.getPlatform().getId();
 		}
 		
 		parameters[5] = account.nextVersion();
 		parameters[6] = account.getId();
 		parameters[7] = account.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareAccountCreateParameters(Account account){
		Object[] parameters = new Object[6];
		String newAccountId=getNextId();
		account.setId(newAccountId);
		parameters[0] =  account.getId();
 
 		parameters[1] = account.getName();
 		parameters[2] = account.getBalance();
 		parameters[3] = account.getCreateTime();
 		parameters[4] = account.getUpdateTime(); 	
 		if(account.getPlatform() != null){
 			parameters[5] = account.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Account saveInternalAccount(Account account, Map<String,Object> options){
		
		saveAccount(account);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(account, options);
 		}
 
		
		if(isSaveTransactionListAsFromAccountEnabled(options)){
	 		saveTransactionListAsFromAccount(account, options);
	 		//removeTransactionListAsFromAccount(account, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveTransactionListAsToAccountEnabled(options)){
	 		saveTransactionListAsToAccount(account, options);
	 		//removeTransactionListAsToAccount(account, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveNameChangeEventListEnabled(options)){
	 		saveNameChangeEventList(account, options);
	 		//removeNameChangeEventList(account, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveAccountChangeListEnabled(options)){
	 		saveAccountChangeList(account, options);
	 		//removeAccountChangeList(account, options);
	 		//Not delete the record
	 		
 		}		
		
		return account;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Account savePlatform(Account account, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(account.getPlatform() == null){
 			return account;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(account.getPlatform(),options);
 		return account;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Account planToRemoveTransactionListAsFromAccount(Account account, String transactionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.FROM_ACCOUNT_PROPERTY, account.getId());
		key.put(Transaction.ID_PROPERTY, transactionIds);
		
		SmartList<Transaction> externalTransactionList = getTransactionDAO().
				findTransactionWithKey(key, options);
		if(externalTransactionList == null){
			return account;
		}
		if(externalTransactionList.isEmpty()){
			return account;
		}
		
		for(Transaction transactionItem: externalTransactionList){

			transactionItem.clearFromAll();
		}
		
		
		SmartList<Transaction> transactionListAsFromAccount = account.getTransactionListAsFromAccount();		
		transactionListAsFromAccount.addAllToRemoveList(externalTransactionList);
		return account;	
	
	}


	//disconnect Account with change_request in Transaction
	public Account planToRemoveTransactionListAsFromAccountWithChangeRequest(Account account, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.FROM_ACCOUNT_PROPERTY, account.getId());
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<Transaction> externalTransactionList = getTransactionDAO().
				findTransactionWithKey(key, options);
		if(externalTransactionList == null){
			return account;
		}
		if(externalTransactionList.isEmpty()){
			return account;
		}
		
		for(Transaction transactionItem: externalTransactionList){
			transactionItem.clearChangeRequest();
			transactionItem.clearFromAccount();
			
		}
		
		
		SmartList<Transaction> transactionList = account.getTransactionListAsFromAccount();		
		transactionList.addAllToRemoveList(externalTransactionList);
		return account;
	}
	
	public int countTransactionListAsFromAccountWithChangeRequest(String accountId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.FROM_ACCOUNT_PROPERTY, accountId);
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getTransactionDAO().countTransactionWithKey(key, options);
		return count;
	}
	
	public Account planToRemoveTransactionListAsToAccount(Account account, String transactionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.FROM_ACCOUNT_PROPERTY, account.getId());
		key.put(Transaction.ID_PROPERTY, transactionIds);
		
		SmartList<Transaction> externalTransactionList = getTransactionDAO().
				findTransactionWithKey(key, options);
		if(externalTransactionList == null){
			return account;
		}
		if(externalTransactionList.isEmpty()){
			return account;
		}
		
		for(Transaction transactionItem: externalTransactionList){

			transactionItem.clearFromAll();
		}
		
		
		SmartList<Transaction> transactionListAsToAccount = account.getTransactionListAsToAccount();		
		transactionListAsToAccount.addAllToRemoveList(externalTransactionList);
		return account;	
	
	}


	//disconnect Account with change_request in Transaction
	public Account planToRemoveTransactionListAsToAccountWithChangeRequest(Account account, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.FROM_ACCOUNT_PROPERTY, account.getId());
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<Transaction> externalTransactionList = getTransactionDAO().
				findTransactionWithKey(key, options);
		if(externalTransactionList == null){
			return account;
		}
		if(externalTransactionList.isEmpty()){
			return account;
		}
		
		for(Transaction transactionItem: externalTransactionList){
			transactionItem.clearChangeRequest();
			transactionItem.clearFromAccount();
			
		}
		
		
		SmartList<Transaction> transactionList = account.getTransactionListAsFromAccount();		
		transactionList.addAllToRemoveList(externalTransactionList);
		return account;
	}
	
	public int countTransactionListAsToAccountWithChangeRequest(String accountId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.FROM_ACCOUNT_PROPERTY, accountId);
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getTransactionDAO().countTransactionWithKey(key, options);
		return count;
	}
	
	public Account planToRemoveNameChangeEventList(Account account, String nameChangeEventIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(NameChangeEvent.ACCOUNT_PROPERTY, account.getId());
		key.put(NameChangeEvent.ID_PROPERTY, nameChangeEventIds);
		
		SmartList<NameChangeEvent> externalNameChangeEventList = getNameChangeEventDAO().
				findNameChangeEventWithKey(key, options);
		if(externalNameChangeEventList == null){
			return account;
		}
		if(externalNameChangeEventList.isEmpty()){
			return account;
		}
		
		for(NameChangeEvent nameChangeEventItem: externalNameChangeEventList){

			nameChangeEventItem.clearFromAll();
		}
		
		
		SmartList<NameChangeEvent> nameChangeEventList = account.getNameChangeEventList();		
		nameChangeEventList.addAllToRemoveList(externalNameChangeEventList);
		return account;	
	
	}


	//disconnect Account with change_request in NameChangeEvent
	public Account planToRemoveNameChangeEventListWithChangeRequest(Account account, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(NameChangeEvent.ACCOUNT_PROPERTY, account.getId());
		key.put(NameChangeEvent.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<NameChangeEvent> externalNameChangeEventList = getNameChangeEventDAO().
				findNameChangeEventWithKey(key, options);
		if(externalNameChangeEventList == null){
			return account;
		}
		if(externalNameChangeEventList.isEmpty()){
			return account;
		}
		
		for(NameChangeEvent nameChangeEventItem: externalNameChangeEventList){
			nameChangeEventItem.clearChangeRequest();
			nameChangeEventItem.clearAccount();
			
		}
		
		
		SmartList<NameChangeEvent> nameChangeEventList = account.getNameChangeEventList();		
		nameChangeEventList.addAllToRemoveList(externalNameChangeEventList);
		return account;
	}
	
	public int countNameChangeEventListWithChangeRequest(String accountId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(NameChangeEvent.ACCOUNT_PROPERTY, accountId);
		key.put(NameChangeEvent.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getNameChangeEventDAO().countNameChangeEventWithKey(key, options);
		return count;
	}
	
	public Account planToRemoveAccountChangeList(Account account, String accountChangeIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(AccountChange.ACCOUNT_PROPERTY, account.getId());
		key.put(AccountChange.ID_PROPERTY, accountChangeIds);
		
		SmartList<AccountChange> externalAccountChangeList = getAccountChangeDAO().
				findAccountChangeWithKey(key, options);
		if(externalAccountChangeList == null){
			return account;
		}
		if(externalAccountChangeList.isEmpty()){
			return account;
		}
		
		for(AccountChange accountChangeItem: externalAccountChangeList){

			accountChangeItem.clearFromAll();
		}
		
		
		SmartList<AccountChange> accountChangeList = account.getAccountChangeList();		
		accountChangeList.addAllToRemoveList(externalAccountChangeList);
		return account;	
	
	}


	//disconnect Account with change_request in AccountChange
	public Account planToRemoveAccountChangeListWithChangeRequest(Account account, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(AccountChange.ACCOUNT_PROPERTY, account.getId());
		key.put(AccountChange.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<AccountChange> externalAccountChangeList = getAccountChangeDAO().
				findAccountChangeWithKey(key, options);
		if(externalAccountChangeList == null){
			return account;
		}
		if(externalAccountChangeList.isEmpty()){
			return account;
		}
		
		for(AccountChange accountChangeItem: externalAccountChangeList){
			accountChangeItem.clearChangeRequest();
			accountChangeItem.clearAccount();
			
		}
		
		
		SmartList<AccountChange> accountChangeList = account.getAccountChangeList();		
		accountChangeList.addAllToRemoveList(externalAccountChangeList);
		return account;
	}
	
	public int countAccountChangeListWithChangeRequest(String accountId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(AccountChange.ACCOUNT_PROPERTY, accountId);
		key.put(AccountChange.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getAccountChangeDAO().countAccountChangeWithKey(key, options);
		return count;
	}
	

		
	protected Account saveTransactionListAsFromAccount(Account account, Map<String,Object> options){
		
		
		
		
		SmartList<Transaction> transactionListAsFromAccount = account.getTransactionListAsFromAccount();
		if(transactionListAsFromAccount == null){
			//null list means nothing
			return account;
		}
		SmartList<Transaction> mergedUpdateTransactionListAsFromAccount = new SmartList<Transaction>();
		
		
		mergedUpdateTransactionListAsFromAccount.addAll(transactionListAsFromAccount); 
		if(transactionListAsFromAccount.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateTransactionListAsFromAccount.addAll(transactionListAsFromAccount.getToRemoveList());
			transactionListAsFromAccount.removeAll(transactionListAsFromAccount.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getTransactionDAO().saveTransactionList(mergedUpdateTransactionListAsFromAccount,options);
		
		if(transactionListAsFromAccount.getToRemoveList() != null){
			transactionListAsFromAccount.removeAll(transactionListAsFromAccount.getToRemoveList());
		}
		
		
		return account;
	
	}
	
	protected Account removeTransactionListAsFromAccount(Account account, Map<String,Object> options){
	
	
		SmartList<Transaction> transactionListAsFromAccount = account.getTransactionListAsFromAccount();
		if(transactionListAsFromAccount == null){
			return account;
		}	
	
		SmartList<Transaction> toRemoveTransactionListAsFromAccount = transactionListAsFromAccount.getToRemoveList();
		
		if(toRemoveTransactionListAsFromAccount == null){
			return account;
		}
		if(toRemoveTransactionListAsFromAccount.isEmpty()){
			return account;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTransactionDAO().removeTransactionList(toRemoveTransactionListAsFromAccount,options);
		
		return account;
	
	}
	
	

 	
 	
	
	
	
		
	protected Account saveTransactionListAsToAccount(Account account, Map<String,Object> options){
		
		
		
		
		SmartList<Transaction> transactionListAsToAccount = account.getTransactionListAsToAccount();
		if(transactionListAsToAccount == null){
			//null list means nothing
			return account;
		}
		SmartList<Transaction> mergedUpdateTransactionListAsToAccount = new SmartList<Transaction>();
		
		
		mergedUpdateTransactionListAsToAccount.addAll(transactionListAsToAccount); 
		if(transactionListAsToAccount.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateTransactionListAsToAccount.addAll(transactionListAsToAccount.getToRemoveList());
			transactionListAsToAccount.removeAll(transactionListAsToAccount.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getTransactionDAO().saveTransactionList(mergedUpdateTransactionListAsToAccount,options);
		
		if(transactionListAsToAccount.getToRemoveList() != null){
			transactionListAsToAccount.removeAll(transactionListAsToAccount.getToRemoveList());
		}
		
		
		return account;
	
	}
	
	protected Account removeTransactionListAsToAccount(Account account, Map<String,Object> options){
	
	
		SmartList<Transaction> transactionListAsToAccount = account.getTransactionListAsToAccount();
		if(transactionListAsToAccount == null){
			return account;
		}	
	
		SmartList<Transaction> toRemoveTransactionListAsToAccount = transactionListAsToAccount.getToRemoveList();
		
		if(toRemoveTransactionListAsToAccount == null){
			return account;
		}
		if(toRemoveTransactionListAsToAccount.isEmpty()){
			return account;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTransactionDAO().removeTransactionList(toRemoveTransactionListAsToAccount,options);
		
		return account;
	
	}
	
	

 	
 	
	
	
	
		
	protected Account saveNameChangeEventList(Account account, Map<String,Object> options){
		
		
		
		
		SmartList<NameChangeEvent> nameChangeEventList = account.getNameChangeEventList();
		if(nameChangeEventList == null){
			//null list means nothing
			return account;
		}
		SmartList<NameChangeEvent> mergedUpdateNameChangeEventList = new SmartList<NameChangeEvent>();
		
		
		mergedUpdateNameChangeEventList.addAll(nameChangeEventList); 
		if(nameChangeEventList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateNameChangeEventList.addAll(nameChangeEventList.getToRemoveList());
			nameChangeEventList.removeAll(nameChangeEventList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getNameChangeEventDAO().saveNameChangeEventList(mergedUpdateNameChangeEventList,options);
		
		if(nameChangeEventList.getToRemoveList() != null){
			nameChangeEventList.removeAll(nameChangeEventList.getToRemoveList());
		}
		
		
		return account;
	
	}
	
	protected Account removeNameChangeEventList(Account account, Map<String,Object> options){
	
	
		SmartList<NameChangeEvent> nameChangeEventList = account.getNameChangeEventList();
		if(nameChangeEventList == null){
			return account;
		}	
	
		SmartList<NameChangeEvent> toRemoveNameChangeEventList = nameChangeEventList.getToRemoveList();
		
		if(toRemoveNameChangeEventList == null){
			return account;
		}
		if(toRemoveNameChangeEventList.isEmpty()){
			return account;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getNameChangeEventDAO().removeNameChangeEventList(toRemoveNameChangeEventList,options);
		
		return account;
	
	}
	
	

 	
 	
	
	
	
		
	protected Account saveAccountChangeList(Account account, Map<String,Object> options){
		
		
		
		
		SmartList<AccountChange> accountChangeList = account.getAccountChangeList();
		if(accountChangeList == null){
			//null list means nothing
			return account;
		}
		SmartList<AccountChange> mergedUpdateAccountChangeList = new SmartList<AccountChange>();
		
		
		mergedUpdateAccountChangeList.addAll(accountChangeList); 
		if(accountChangeList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateAccountChangeList.addAll(accountChangeList.getToRemoveList());
			accountChangeList.removeAll(accountChangeList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getAccountChangeDAO().saveAccountChangeList(mergedUpdateAccountChangeList,options);
		
		if(accountChangeList.getToRemoveList() != null){
			accountChangeList.removeAll(accountChangeList.getToRemoveList());
		}
		
		
		return account;
	
	}
	
	protected Account removeAccountChangeList(Account account, Map<String,Object> options){
	
	
		SmartList<AccountChange> accountChangeList = account.getAccountChangeList();
		if(accountChangeList == null){
			return account;
		}	
	
		SmartList<AccountChange> toRemoveAccountChangeList = accountChangeList.getToRemoveList();
		
		if(toRemoveAccountChangeList == null){
			return account;
		}
		if(toRemoveAccountChangeList.isEmpty()){
			return account;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getAccountChangeDAO().removeAccountChangeList(toRemoveAccountChangeList,options);
		
		return account;
	
	}
	
	

 	
 	
	
	
	
		

	public Account present(Account account,Map<String, Object> options){
	
		presentTransactionListAsFromAccount(account,options);
		presentTransactionListAsToAccount(account,options);
		presentNameChangeEventList(account,options);
		presentAccountChangeList(account,options);

		return account;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Account presentTransactionListAsFromAccount(
			Account account,
			Map<String, Object> options) {

		SmartList<Transaction> transactionListAsFromAccount = account.getTransactionListAsFromAccount();		
				SmartList<Transaction> newList= presentSubList(account.getId(),
				transactionListAsFromAccount,
				options,
				getTransactionDAO()::countTransactionByFromAccount,
				getTransactionDAO()::findTransactionByFromAccount
				);

		
		account.setTransactionListAsFromAccount(newList);
		

		return account;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Account presentTransactionListAsToAccount(
			Account account,
			Map<String, Object> options) {

		SmartList<Transaction> transactionListAsToAccount = account.getTransactionListAsToAccount();		
				SmartList<Transaction> newList= presentSubList(account.getId(),
				transactionListAsToAccount,
				options,
				getTransactionDAO()::countTransactionByToAccount,
				getTransactionDAO()::findTransactionByToAccount
				);

		
		account.setTransactionListAsToAccount(newList);
		

		return account;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Account presentNameChangeEventList(
			Account account,
			Map<String, Object> options) {

		SmartList<NameChangeEvent> nameChangeEventList = account.getNameChangeEventList();		
				SmartList<NameChangeEvent> newList= presentSubList(account.getId(),
				nameChangeEventList,
				options,
				getNameChangeEventDAO()::countNameChangeEventByAccount,
				getNameChangeEventDAO()::findNameChangeEventByAccount
				);

		
		account.setNameChangeEventList(newList);
		

		return account;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Account presentAccountChangeList(
			Account account,
			Map<String, Object> options) {

		SmartList<AccountChange> accountChangeList = account.getAccountChangeList();		
				SmartList<AccountChange> newList= presentSubList(account.getId(),
				accountChangeList,
				options,
				getAccountChangeDAO()::countAccountChangeByAccount,
				getAccountChangeDAO()::findAccountChangeByAccount
				);

		
		account.setAccountChangeList(newList);
		

		return account;
	}			
		

	
    public SmartList<Account> requestCandidateAccountForTransactionAsFromAccount(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(AccountTable.COLUMN_NAME, filterKey, pageNo, pageSize, getAccountMapper());
    }
		
    public SmartList<Account> requestCandidateAccountForTransactionAsToAccount(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(AccountTable.COLUMN_NAME, filterKey, pageNo, pageSize, getAccountMapper());
    }
		
    public SmartList<Account> requestCandidateAccountForNameChangeEvent(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(AccountTable.COLUMN_NAME, filterKey, pageNo, pageSize, getAccountMapper());
    }
		
    public SmartList<Account> requestCandidateAccountForAccountChange(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(AccountTable.COLUMN_NAME, filterKey, pageNo, pageSize, getAccountMapper());
    }
		

	protected String getTableName(){
		return AccountTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Account> accountList) {		
		this.enhanceListInternal(accountList, this.getAccountMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Transaction的fromAccount的TransactionListAsFromAccount
	public SmartList<Transaction> loadOurTransactionListAsFromAccount(BankUserContext userContext, List<Account> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.FROM_ACCOUNT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Transaction> loadedObjs = userContext.getDAOGroup().getTransactionDAO().findTransactionWithKey(key, options);
		Map<String, List<Transaction>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getFromAccount().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Transaction> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Transaction> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setTransactionListAsFromAccount(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Transaction的toAccount的TransactionListAsToAccount
	public SmartList<Transaction> loadOurTransactionListAsToAccount(BankUserContext userContext, List<Account> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.TO_ACCOUNT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Transaction> loadedObjs = userContext.getDAOGroup().getTransactionDAO().findTransactionWithKey(key, options);
		Map<String, List<Transaction>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getToAccount().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Transaction> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Transaction> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setTransactionListAsToAccount(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:NameChangeEvent的account的NameChangeEventList
	public SmartList<NameChangeEvent> loadOurNameChangeEventList(BankUserContext userContext, List<Account> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(NameChangeEvent.ACCOUNT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<NameChangeEvent> loadedObjs = userContext.getDAOGroup().getNameChangeEventDAO().findNameChangeEventWithKey(key, options);
		Map<String, List<NameChangeEvent>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getAccount().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<NameChangeEvent> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<NameChangeEvent> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setNameChangeEventList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:AccountChange的account的AccountChangeList
	public SmartList<AccountChange> loadOurAccountChangeList(BankUserContext userContext, List<Account> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(AccountChange.ACCOUNT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<AccountChange> loadedObjs = userContext.getDAOGroup().getAccountChangeDAO().findAccountChangeWithKey(key, options);
		Map<String, List<AccountChange>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getAccount().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<AccountChange> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<AccountChange> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setAccountChangeList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Account> accountList = ownerEntity.collectRefsWithType(Account.INTERNAL_TYPE);
		this.enhanceList(accountList);
		
	}
	
	@Override
	public SmartList<Account> findAccountWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getAccountMapper());

	}
	@Override
	public int countAccountWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countAccountWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Account> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getAccountMapper());
	}
	
	

}


