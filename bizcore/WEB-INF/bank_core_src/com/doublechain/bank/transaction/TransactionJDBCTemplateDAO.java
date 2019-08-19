
package com.doublechain.bank.transaction;

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


import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.account.Account;

import com.doublechain.bank.changerequest.ChangeRequestDAO;
import com.doublechain.bank.account.AccountDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class TransactionJDBCTemplateDAO extends BankBaseDAOImpl implements TransactionDAO{
 
 	
 	private  ChangeRequestDAO  changeRequestDAO;
 	public void setChangeRequestDAO(ChangeRequestDAO changeRequestDAO){
	 	this.changeRequestDAO = changeRequestDAO;
 	}
 	public ChangeRequestDAO getChangeRequestDAO(){
	 	return this.changeRequestDAO;
 	}
 
 	
 	private  AccountDAO  accountDAO;
 	public void setAccountDAO(AccountDAO accountDAO){
	 	this.accountDAO = accountDAO;
 	}
 	public AccountDAO getAccountDAO(){
	 	return this.accountDAO;
 	}


			
		

	
	/*
	protected Transaction load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalTransaction(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Transaction load(String id,Map<String,Object> options) throws Exception{
		return loadInternalTransaction(TransactionTable.withId(id), options);
	}
	
	
	
	public Transaction save(Transaction transaction,Map<String,Object> options){
		
		String methodName="save(Transaction transaction,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(transaction, methodName, "transaction");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalTransaction(transaction,options);
	}
	public Transaction clone(String transactionId, Map<String,Object> options) throws Exception{
	
		return clone(TransactionTable.withId(transactionId),options);
	}
	
	protected Transaction clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String transactionId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Transaction newTransaction = loadInternalTransaction(accessKey, options);
		newTransaction.setVersion(0);
		
		

		
		saveInternalTransaction(newTransaction,options);
		
		return newTransaction;
	}
	
	
	
	

	protected void throwIfHasException(String transactionId,int version,int count) throws Exception{
		if (count == 1) {
			throw new TransactionVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new TransactionNotFoundException(
					"The " + this.getTableName() + "(" + transactionId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String transactionId, int version) throws Exception{
	
		String methodName="delete(String transactionId, int version)";
		assertMethodArgumentNotNull(transactionId, methodName, "transactionId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{transactionId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(transactionId,version);
		}
		
	
	}
	
	
	
	
	

	public Transaction disconnectFromAll(String transactionId, int version) throws Exception{
	
		
		Transaction transaction = loadInternalTransaction(TransactionTable.withId(transactionId), emptyOptions());
		transaction.clearFromAll();
		this.saveTransaction(transaction);
		return transaction;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return TransactionTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "transaction";
	}
	@Override
	protected String getBeanName() {
		
		return "transaction";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return TransactionTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractFromAccountEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TransactionTokens.FROMACCOUNT);
 	}

 	protected boolean isSaveFromAccountEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TransactionTokens.FROMACCOUNT);
 	}
 	

 	
  

 	protected boolean isExtractToAccountEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TransactionTokens.TOACCOUNT);
 	}

 	protected boolean isSaveToAccountEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TransactionTokens.TOACCOUNT);
 	}
 	

 	
  

 	protected boolean isExtractChangeRequestEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TransactionTokens.CHANGEREQUEST);
 	}

 	protected boolean isSaveChangeRequestEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TransactionTokens.CHANGEREQUEST);
 	}
 	

 	
 
		

	

	protected TransactionMapper getTransactionMapper(){
		return new TransactionMapper();
	}

	
	
	protected Transaction extractTransaction(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Transaction transaction = loadSingleObject(accessKey, getTransactionMapper());
			return transaction;
		}catch(EmptyResultDataAccessException e){
			throw new TransactionNotFoundException("Transaction("+accessKey+") is not found!");
		}

	}

	
	

	protected Transaction loadInternalTransaction(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Transaction transaction = extractTransaction(accessKey, loadOptions);
 	
 		if(isExtractFromAccountEnabled(loadOptions)){
	 		extractFromAccount(transaction, loadOptions);
 		}
  	
 		if(isExtractToAccountEnabled(loadOptions)){
	 		extractToAccount(transaction, loadOptions);
 		}
  	
 		if(isExtractChangeRequestEnabled(loadOptions)){
	 		extractChangeRequest(transaction, loadOptions);
 		}
 
		
		return transaction;
		
	}

	 

 	protected Transaction extractFromAccount(Transaction transaction, Map<String,Object> options) throws Exception{

		if(transaction.getFromAccount() == null){
			return transaction;
		}
		String fromAccountId = transaction.getFromAccount().getId();
		if( fromAccountId == null){
			return transaction;
		}
		Account fromAccount = getAccountDAO().load(fromAccountId,options);
		if(fromAccount != null){
			transaction.setFromAccount(fromAccount);
		}
		
 		
 		return transaction;
 	}
 		
  

 	protected Transaction extractToAccount(Transaction transaction, Map<String,Object> options) throws Exception{

		if(transaction.getToAccount() == null){
			return transaction;
		}
		String toAccountId = transaction.getToAccount().getId();
		if( toAccountId == null){
			return transaction;
		}
		Account toAccount = getAccountDAO().load(toAccountId,options);
		if(toAccount != null){
			transaction.setToAccount(toAccount);
		}
		
 		
 		return transaction;
 	}
 		
  

 	protected Transaction extractChangeRequest(Transaction transaction, Map<String,Object> options) throws Exception{

		if(transaction.getChangeRequest() == null){
			return transaction;
		}
		String changeRequestId = transaction.getChangeRequest().getId();
		if( changeRequestId == null){
			return transaction;
		}
		ChangeRequest changeRequest = getChangeRequestDAO().load(changeRequestId,options);
		if(changeRequest != null){
			transaction.setChangeRequest(changeRequest);
		}
		
 		
 		return transaction;
 	}
 		
 
		
		
  	
 	public SmartList<Transaction> findTransactionByFromAccount(String accountId,Map<String,Object> options){
 	
  		SmartList<Transaction> resultList = queryWith(TransactionTable.COLUMN_FROM_ACCOUNT, accountId, options, getTransactionMapper());
		// analyzeTransactionByFromAccount(resultList, accountId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Transaction> findTransactionByFromAccount(String accountId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Transaction> resultList =  queryWithRange(TransactionTable.COLUMN_FROM_ACCOUNT, accountId, options, getTransactionMapper(), start, count);
 		//analyzeTransactionByFromAccount(resultList, accountId, options);
 		return resultList;
 		
 	}
 	public void analyzeTransactionByFromAccount(SmartList<Transaction> resultList, String accountId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Transaction.FROM_ACCOUNT_PROPERTY, accountId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTransactionByFromAccount(String accountId,Map<String,Object> options){

 		return countWith(TransactionTable.COLUMN_FROM_ACCOUNT, accountId, options);
 	}
 	@Override
	public Map<String, Integer> countTransactionByFromAccountIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TransactionTable.COLUMN_FROM_ACCOUNT, ids, options);
	}
 	
  	
 	public SmartList<Transaction> findTransactionByToAccount(String accountId,Map<String,Object> options){
 	
  		SmartList<Transaction> resultList = queryWith(TransactionTable.COLUMN_TO_ACCOUNT, accountId, options, getTransactionMapper());
		// analyzeTransactionByToAccount(resultList, accountId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Transaction> findTransactionByToAccount(String accountId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Transaction> resultList =  queryWithRange(TransactionTable.COLUMN_TO_ACCOUNT, accountId, options, getTransactionMapper(), start, count);
 		//analyzeTransactionByToAccount(resultList, accountId, options);
 		return resultList;
 		
 	}
 	public void analyzeTransactionByToAccount(SmartList<Transaction> resultList, String accountId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Transaction.TO_ACCOUNT_PROPERTY, accountId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTransactionByToAccount(String accountId,Map<String,Object> options){

 		return countWith(TransactionTable.COLUMN_TO_ACCOUNT, accountId, options);
 	}
 	@Override
	public Map<String, Integer> countTransactionByToAccountIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TransactionTable.COLUMN_TO_ACCOUNT, ids, options);
	}
 	
  	
 	public SmartList<Transaction> findTransactionByChangeRequest(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<Transaction> resultList = queryWith(TransactionTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getTransactionMapper());
		// analyzeTransactionByChangeRequest(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Transaction> findTransactionByChangeRequest(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Transaction> resultList =  queryWithRange(TransactionTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getTransactionMapper(), start, count);
 		//analyzeTransactionByChangeRequest(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeTransactionByChangeRequest(SmartList<Transaction> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTransactionByChangeRequest(String changeRequestId,Map<String,Object> options){

 		return countWith(TransactionTable.COLUMN_CHANGE_REQUEST, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countTransactionByChangeRequestIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TransactionTable.COLUMN_CHANGE_REQUEST, ids, options);
	}
 	
 	
		
		
		

	

	protected Transaction saveTransaction(Transaction  transaction){
		
		if(!transaction.isChanged()){
			return transaction;
		}
		
		
		String SQL=this.getSaveTransactionSQL(transaction);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveTransactionParameters(transaction);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		transaction.incVersion();
		return transaction;
	
	}
	public SmartList<Transaction> saveTransactionList(SmartList<Transaction> transactionList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitTransactionList(transactionList);
		
		batchTransactionCreate((List<Transaction>)lists[CREATE_LIST_INDEX]);
		
		batchTransactionUpdate((List<Transaction>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Transaction transaction:transactionList){
			if(transaction.isChanged()){
				transaction.incVersion();
			}
			
		
		}
		
		
		return transactionList;
	}

	public SmartList<Transaction> removeTransactionList(SmartList<Transaction> transactionList,Map<String,Object> options){
		
		
		super.removeList(transactionList, options);
		
		return transactionList;
		
		
	}
	
	protected List<Object[]> prepareTransactionBatchCreateArgs(List<Transaction> transactionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Transaction transaction:transactionList ){
			Object [] parameters = prepareTransactionCreateParameters(transaction);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareTransactionBatchUpdateArgs(List<Transaction> transactionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Transaction transaction:transactionList ){
			if(!transaction.isChanged()){
				continue;
			}
			Object [] parameters = prepareTransactionUpdateParameters(transaction);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchTransactionCreate(List<Transaction> transactionList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareTransactionBatchCreateArgs(transactionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchTransactionUpdate(List<Transaction> transactionList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareTransactionBatchUpdateArgs(transactionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitTransactionList(List<Transaction> transactionList){
		
		List<Transaction> transactionCreateList=new ArrayList<Transaction>();
		List<Transaction> transactionUpdateList=new ArrayList<Transaction>();
		
		for(Transaction transaction: transactionList){
			if(isUpdateRequest(transaction)){
				transactionUpdateList.add( transaction);
				continue;
			}
			transactionCreateList.add(transaction);
		}
		
		return new Object[]{transactionCreateList,transactionUpdateList};
	}
	
	protected boolean isUpdateRequest(Transaction transaction){
 		return transaction.getVersion() > 0;
 	}
 	protected String getSaveTransactionSQL(Transaction transaction){
 		if(isUpdateRequest(transaction)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveTransactionParameters(Transaction transaction){
 		if(isUpdateRequest(transaction) ){
 			return prepareTransactionUpdateParameters(transaction);
 		}
 		return prepareTransactionCreateParameters(transaction);
 	}
 	protected Object[] prepareTransactionUpdateParameters(Transaction transaction){
 		Object[] parameters = new Object[9];
 
 		parameters[0] = transaction.getName(); 	
 		if(transaction.getFromAccount() != null){
 			parameters[1] = transaction.getFromAccount().getId();
 		}
  	
 		if(transaction.getToAccount() != null){
 			parameters[2] = transaction.getToAccount().getId();
 		}
 
 		parameters[3] = transaction.getAmount();
 		parameters[4] = transaction.getType(); 	
 		if(transaction.getChangeRequest() != null){
 			parameters[5] = transaction.getChangeRequest().getId();
 		}
 		
 		parameters[6] = transaction.nextVersion();
 		parameters[7] = transaction.getId();
 		parameters[8] = transaction.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareTransactionCreateParameters(Transaction transaction){
		Object[] parameters = new Object[7];
		String newTransactionId=getNextId();
		transaction.setId(newTransactionId);
		parameters[0] =  transaction.getId();
 
 		parameters[1] = transaction.getName(); 	
 		if(transaction.getFromAccount() != null){
 			parameters[2] = transaction.getFromAccount().getId();
 		
 		}
 		 	
 		if(transaction.getToAccount() != null){
 			parameters[3] = transaction.getToAccount().getId();
 		
 		}
 		
 		parameters[4] = transaction.getAmount();
 		parameters[5] = transaction.getType(); 	
 		if(transaction.getChangeRequest() != null){
 			parameters[6] = transaction.getChangeRequest().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Transaction saveInternalTransaction(Transaction transaction, Map<String,Object> options){
		
		saveTransaction(transaction);
 	
 		if(isSaveFromAccountEnabled(options)){
	 		saveFromAccount(transaction, options);
 		}
  	
 		if(isSaveToAccountEnabled(options)){
	 		saveToAccount(transaction, options);
 		}
  	
 		if(isSaveChangeRequestEnabled(options)){
	 		saveChangeRequest(transaction, options);
 		}
 
		
		return transaction;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Transaction saveFromAccount(Transaction transaction, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(transaction.getFromAccount() == null){
 			return transaction;//do nothing when it is null
 		}
 		
 		getAccountDAO().save(transaction.getFromAccount(),options);
 		return transaction;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Transaction saveToAccount(Transaction transaction, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(transaction.getToAccount() == null){
 			return transaction;//do nothing when it is null
 		}
 		
 		getAccountDAO().save(transaction.getToAccount(),options);
 		return transaction;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Transaction saveChangeRequest(Transaction transaction, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(transaction.getChangeRequest() == null){
 			return transaction;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(transaction.getChangeRequest(),options);
 		return transaction;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public Transaction present(Transaction transaction,Map<String, Object> options){
	

		return transaction;
	
	}
		

	

	protected String getTableName(){
		return TransactionTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Transaction> transactionList) {		
		this.enhanceListInternal(transactionList, this.getTransactionMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Transaction> transactionList = ownerEntity.collectRefsWithType(Transaction.INTERNAL_TYPE);
		this.enhanceList(transactionList);
		
	}
	
	@Override
	public SmartList<Transaction> findTransactionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getTransactionMapper());

	}
	@Override
	public int countTransactionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countTransactionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Transaction> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getTransactionMapper());
	}
	
	

}


