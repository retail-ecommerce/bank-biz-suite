
package com.doublechain.bank.changerequest;

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


public class ChangeRequestJDBCTemplateDAO extends BankBaseDAOImpl implements ChangeRequestDAO{
 
 	
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
	protected ChangeRequest load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalChangeRequest(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public ChangeRequest load(String id,Map<String,Object> options) throws Exception{
		return loadInternalChangeRequest(ChangeRequestTable.withId(id), options);
	}
	
	
	
	public ChangeRequest save(ChangeRequest changeRequest,Map<String,Object> options){
		
		String methodName="save(ChangeRequest changeRequest,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(changeRequest, methodName, "changeRequest");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalChangeRequest(changeRequest,options);
	}
	public ChangeRequest clone(String changeRequestId, Map<String,Object> options) throws Exception{
	
		return clone(ChangeRequestTable.withId(changeRequestId),options);
	}
	
	protected ChangeRequest clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String changeRequestId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		ChangeRequest newChangeRequest = loadInternalChangeRequest(accessKey, options);
		newChangeRequest.setVersion(0);
		
		
 		
 		if(isSaveTransactionListEnabled(options)){
 			for(Transaction item: newChangeRequest.getTransactionList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveNameChangeEventListEnabled(options)){
 			for(NameChangeEvent item: newChangeRequest.getNameChangeEventList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveAccountChangeListEnabled(options)){
 			for(AccountChange item: newChangeRequest.getAccountChangeList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalChangeRequest(newChangeRequest,options);
		
		return newChangeRequest;
	}
	
	
	
	

	protected void throwIfHasException(String changeRequestId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ChangeRequestVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ChangeRequestNotFoundException(
					"The " + this.getTableName() + "(" + changeRequestId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String changeRequestId, int version) throws Exception{
	
		String methodName="delete(String changeRequestId, int version)";
		assertMethodArgumentNotNull(changeRequestId, methodName, "changeRequestId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{changeRequestId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(changeRequestId,version);
		}
		
	
	}
	
	
	
	
	

	public ChangeRequest disconnectFromAll(String changeRequestId, int version) throws Exception{
	
		
		ChangeRequest changeRequest = loadInternalChangeRequest(ChangeRequestTable.withId(changeRequestId), emptyOptions());
		changeRequest.clearFromAll();
		this.saveChangeRequest(changeRequest);
		return changeRequest;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ChangeRequestTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "change_request";
	}
	@Override
	protected String getBeanName() {
		
		return "changeRequest";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ChangeRequestTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ChangeRequestTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ChangeRequestTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractTransactionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.TRANSACTION_LIST);
 	}
 	protected boolean isAnalyzeTransactionListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeTransactionListEnabled();
 	}
	
	protected boolean isSaveTransactionListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.TRANSACTION_LIST);
		
 	}
 	
		
	
	protected boolean isExtractNameChangeEventListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.NAME_CHANGE_EVENT_LIST);
 	}
 	protected boolean isAnalyzeNameChangeEventListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeNameChangeEventListEnabled();
 	}
	
	protected boolean isSaveNameChangeEventListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.NAME_CHANGE_EVENT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractAccountChangeListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.ACCOUNT_CHANGE_LIST);
 	}
 	protected boolean isAnalyzeAccountChangeListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeAccountChangeListEnabled();
 	}
	
	protected boolean isSaveAccountChangeListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.ACCOUNT_CHANGE_LIST);
		
 	}
 	
		

	

	protected ChangeRequestMapper getChangeRequestMapper(){
		return new ChangeRequestMapper();
	}

	
	
	protected ChangeRequest extractChangeRequest(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			ChangeRequest changeRequest = loadSingleObject(accessKey, getChangeRequestMapper());
			return changeRequest;
		}catch(EmptyResultDataAccessException e){
			throw new ChangeRequestNotFoundException("ChangeRequest("+accessKey+") is not found!");
		}

	}

	
	

	protected ChangeRequest loadInternalChangeRequest(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		ChangeRequest changeRequest = extractChangeRequest(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(changeRequest, loadOptions);
 		}
 
		
		if(isExtractTransactionListEnabled(loadOptions)){
	 		extractTransactionList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeTransactionListEnabled(loadOptions)){
	 		analyzeTransactionList(changeRequest, loadOptions);
 		}
 		
		
		if(isExtractNameChangeEventListEnabled(loadOptions)){
	 		extractNameChangeEventList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeNameChangeEventListEnabled(loadOptions)){
	 		analyzeNameChangeEventList(changeRequest, loadOptions);
 		}
 		
		
		if(isExtractAccountChangeListEnabled(loadOptions)){
	 		extractAccountChangeList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeAccountChangeListEnabled(loadOptions)){
	 		analyzeAccountChangeList(changeRequest, loadOptions);
 		}
 		
		
		return changeRequest;
		
	}

	 

 	protected ChangeRequest extractPlatform(ChangeRequest changeRequest, Map<String,Object> options) throws Exception{

		if(changeRequest.getPlatform() == null){
			return changeRequest;
		}
		String platformId = changeRequest.getPlatform().getId();
		if( platformId == null){
			return changeRequest;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			changeRequest.setPlatform(platform);
		}
		
 		
 		return changeRequest;
 	}
 		
 
		
	protected void enhanceTransactionList(SmartList<Transaction> transactionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractTransactionList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<Transaction> transactionList = getTransactionDAO().findTransactionByChangeRequest(changeRequest.getId(),options);
		if(transactionList != null){
			enhanceTransactionList(transactionList,options);
			changeRequest.setTransactionList(transactionList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeTransactionList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<Transaction> transactionList = changeRequest.getTransactionList();
		if(transactionList != null){
			getTransactionDAO().analyzeTransactionByChangeRequest(transactionList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
	protected void enhanceNameChangeEventList(SmartList<NameChangeEvent> nameChangeEventList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractNameChangeEventList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<NameChangeEvent> nameChangeEventList = getNameChangeEventDAO().findNameChangeEventByChangeRequest(changeRequest.getId(),options);
		if(nameChangeEventList != null){
			enhanceNameChangeEventList(nameChangeEventList,options);
			changeRequest.setNameChangeEventList(nameChangeEventList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeNameChangeEventList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<NameChangeEvent> nameChangeEventList = changeRequest.getNameChangeEventList();
		if(nameChangeEventList != null){
			getNameChangeEventDAO().analyzeNameChangeEventByChangeRequest(nameChangeEventList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
	protected void enhanceAccountChangeList(SmartList<AccountChange> accountChangeList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractAccountChangeList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<AccountChange> accountChangeList = getAccountChangeDAO().findAccountChangeByChangeRequest(changeRequest.getId(),options);
		if(accountChangeList != null){
			enhanceAccountChangeList(accountChangeList,options);
			changeRequest.setAccountChangeList(accountChangeList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeAccountChangeList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<AccountChange> accountChangeList = changeRequest.getAccountChangeList();
		if(accountChangeList != null){
			getAccountChangeDAO().analyzeAccountChangeByChangeRequest(accountChangeList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
		
  	
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<ChangeRequest> resultList = queryWith(ChangeRequestTable.COLUMN_PLATFORM, platformId, options, getChangeRequestMapper());
		// analyzeChangeRequestByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ChangeRequest> resultList =  queryWithRange(ChangeRequestTable.COLUMN_PLATFORM, platformId, options, getChangeRequestMapper(), start, count);
 		//analyzeChangeRequestByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeChangeRequestByPlatform(SmartList<ChangeRequest> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ChangeRequest.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//ChangeRequest.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("变更请求");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(ChangeRequest.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ChangeRequest.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countChangeRequestByPlatform(String platformId,Map<String,Object> options){

 		return countWith(ChangeRequestTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countChangeRequestByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ChangeRequestTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected ChangeRequest saveChangeRequest(ChangeRequest  changeRequest){
		
		if(!changeRequest.isChanged()){
			return changeRequest;
		}
		
		
		String SQL=this.getSaveChangeRequestSQL(changeRequest);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveChangeRequestParameters(changeRequest);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		changeRequest.incVersion();
		return changeRequest;
	
	}
	public SmartList<ChangeRequest> saveChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitChangeRequestList(changeRequestList);
		
		batchChangeRequestCreate((List<ChangeRequest>)lists[CREATE_LIST_INDEX]);
		
		batchChangeRequestUpdate((List<ChangeRequest>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(ChangeRequest changeRequest:changeRequestList){
			if(changeRequest.isChanged()){
				changeRequest.incVersion();
			}
			
		
		}
		
		
		return changeRequestList;
	}

	public SmartList<ChangeRequest> removeChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options){
		
		
		super.removeList(changeRequestList, options);
		
		return changeRequestList;
		
		
	}
	
	protected List<Object[]> prepareChangeRequestBatchCreateArgs(List<ChangeRequest> changeRequestList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ChangeRequest changeRequest:changeRequestList ){
			Object [] parameters = prepareChangeRequestCreateParameters(changeRequest);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareChangeRequestBatchUpdateArgs(List<ChangeRequest> changeRequestList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ChangeRequest changeRequest:changeRequestList ){
			if(!changeRequest.isChanged()){
				continue;
			}
			Object [] parameters = prepareChangeRequestUpdateParameters(changeRequest);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchChangeRequestCreate(List<ChangeRequest> changeRequestList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareChangeRequestBatchCreateArgs(changeRequestList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchChangeRequestUpdate(List<ChangeRequest> changeRequestList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareChangeRequestBatchUpdateArgs(changeRequestList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitChangeRequestList(List<ChangeRequest> changeRequestList){
		
		List<ChangeRequest> changeRequestCreateList=new ArrayList<ChangeRequest>();
		List<ChangeRequest> changeRequestUpdateList=new ArrayList<ChangeRequest>();
		
		for(ChangeRequest changeRequest: changeRequestList){
			if(isUpdateRequest(changeRequest)){
				changeRequestUpdateList.add( changeRequest);
				continue;
			}
			changeRequestCreateList.add(changeRequest);
		}
		
		return new Object[]{changeRequestCreateList,changeRequestUpdateList};
	}
	
	protected boolean isUpdateRequest(ChangeRequest changeRequest){
 		return changeRequest.getVersion() > 0;
 	}
 	protected String getSaveChangeRequestSQL(ChangeRequest changeRequest){
 		if(isUpdateRequest(changeRequest)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveChangeRequestParameters(ChangeRequest changeRequest){
 		if(isUpdateRequest(changeRequest) ){
 			return prepareChangeRequestUpdateParameters(changeRequest);
 		}
 		return prepareChangeRequestCreateParameters(changeRequest);
 	}
 	protected Object[] prepareChangeRequestUpdateParameters(ChangeRequest changeRequest){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = changeRequest.getName();
 		parameters[1] = changeRequest.getCreateTime(); 	
 		if(changeRequest.getPlatform() != null){
 			parameters[2] = changeRequest.getPlatform().getId();
 		}
 		
 		parameters[3] = changeRequest.nextVersion();
 		parameters[4] = changeRequest.getId();
 		parameters[5] = changeRequest.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareChangeRequestCreateParameters(ChangeRequest changeRequest){
		Object[] parameters = new Object[4];
		String newChangeRequestId=getNextId();
		changeRequest.setId(newChangeRequestId);
		parameters[0] =  changeRequest.getId();
 
 		parameters[1] = changeRequest.getName();
 		parameters[2] = changeRequest.getCreateTime(); 	
 		if(changeRequest.getPlatform() != null){
 			parameters[3] = changeRequest.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected ChangeRequest saveInternalChangeRequest(ChangeRequest changeRequest, Map<String,Object> options){
		
		saveChangeRequest(changeRequest);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(changeRequest, options);
 		}
 
		
		if(isSaveTransactionListEnabled(options)){
	 		saveTransactionList(changeRequest, options);
	 		//removeTransactionList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveNameChangeEventListEnabled(options)){
	 		saveNameChangeEventList(changeRequest, options);
	 		//removeNameChangeEventList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveAccountChangeListEnabled(options)){
	 		saveAccountChangeList(changeRequest, options);
	 		//removeAccountChangeList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		return changeRequest;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected ChangeRequest savePlatform(ChangeRequest changeRequest, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(changeRequest.getPlatform() == null){
 			return changeRequest;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(changeRequest.getPlatform(),options);
 		return changeRequest;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public ChangeRequest planToRemoveTransactionList(ChangeRequest changeRequest, String transactionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequest.getId());
		key.put(Transaction.ID_PROPERTY, transactionIds);
		
		SmartList<Transaction> externalTransactionList = getTransactionDAO().
				findTransactionWithKey(key, options);
		if(externalTransactionList == null){
			return changeRequest;
		}
		if(externalTransactionList.isEmpty()){
			return changeRequest;
		}
		
		for(Transaction transactionItem: externalTransactionList){

			transactionItem.clearFromAll();
		}
		
		
		SmartList<Transaction> transactionList = changeRequest.getTransactionList();		
		transactionList.addAllToRemoveList(externalTransactionList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with from_account in Transaction
	public ChangeRequest planToRemoveTransactionListWithFromAccount(ChangeRequest changeRequest, String fromAccountId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequest.getId());
		key.put(Transaction.FROM_ACCOUNT_PROPERTY, fromAccountId);
		
		SmartList<Transaction> externalTransactionList = getTransactionDAO().
				findTransactionWithKey(key, options);
		if(externalTransactionList == null){
			return changeRequest;
		}
		if(externalTransactionList.isEmpty()){
			return changeRequest;
		}
		
		for(Transaction transactionItem: externalTransactionList){
			transactionItem.clearFromAccount();
			transactionItem.clearChangeRequest();
			
		}
		
		
		SmartList<Transaction> transactionList = changeRequest.getTransactionList();		
		transactionList.addAllToRemoveList(externalTransactionList);
		return changeRequest;
	}
	
	public int countTransactionListWithFromAccount(String changeRequestId, String fromAccountId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequestId);
		key.put(Transaction.FROM_ACCOUNT_PROPERTY, fromAccountId);
		
		int count = getTransactionDAO().countTransactionWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with to_account in Transaction
	public ChangeRequest planToRemoveTransactionListWithToAccount(ChangeRequest changeRequest, String toAccountId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequest.getId());
		key.put(Transaction.TO_ACCOUNT_PROPERTY, toAccountId);
		
		SmartList<Transaction> externalTransactionList = getTransactionDAO().
				findTransactionWithKey(key, options);
		if(externalTransactionList == null){
			return changeRequest;
		}
		if(externalTransactionList.isEmpty()){
			return changeRequest;
		}
		
		for(Transaction transactionItem: externalTransactionList){
			transactionItem.clearToAccount();
			transactionItem.clearChangeRequest();
			
		}
		
		
		SmartList<Transaction> transactionList = changeRequest.getTransactionList();		
		transactionList.addAllToRemoveList(externalTransactionList);
		return changeRequest;
	}
	
	public int countTransactionListWithToAccount(String changeRequestId, String toAccountId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, changeRequestId);
		key.put(Transaction.TO_ACCOUNT_PROPERTY, toAccountId);
		
		int count = getTransactionDAO().countTransactionWithKey(key, options);
		return count;
	}
	
	public ChangeRequest planToRemoveNameChangeEventList(ChangeRequest changeRequest, String nameChangeEventIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(NameChangeEvent.CHANGE_REQUEST_PROPERTY, changeRequest.getId());
		key.put(NameChangeEvent.ID_PROPERTY, nameChangeEventIds);
		
		SmartList<NameChangeEvent> externalNameChangeEventList = getNameChangeEventDAO().
				findNameChangeEventWithKey(key, options);
		if(externalNameChangeEventList == null){
			return changeRequest;
		}
		if(externalNameChangeEventList.isEmpty()){
			return changeRequest;
		}
		
		for(NameChangeEvent nameChangeEventItem: externalNameChangeEventList){

			nameChangeEventItem.clearFromAll();
		}
		
		
		SmartList<NameChangeEvent> nameChangeEventList = changeRequest.getNameChangeEventList();		
		nameChangeEventList.addAllToRemoveList(externalNameChangeEventList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with account in NameChangeEvent
	public ChangeRequest planToRemoveNameChangeEventListWithAccount(ChangeRequest changeRequest, String accountId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(NameChangeEvent.CHANGE_REQUEST_PROPERTY, changeRequest.getId());
		key.put(NameChangeEvent.ACCOUNT_PROPERTY, accountId);
		
		SmartList<NameChangeEvent> externalNameChangeEventList = getNameChangeEventDAO().
				findNameChangeEventWithKey(key, options);
		if(externalNameChangeEventList == null){
			return changeRequest;
		}
		if(externalNameChangeEventList.isEmpty()){
			return changeRequest;
		}
		
		for(NameChangeEvent nameChangeEventItem: externalNameChangeEventList){
			nameChangeEventItem.clearAccount();
			nameChangeEventItem.clearChangeRequest();
			
		}
		
		
		SmartList<NameChangeEvent> nameChangeEventList = changeRequest.getNameChangeEventList();		
		nameChangeEventList.addAllToRemoveList(externalNameChangeEventList);
		return changeRequest;
	}
	
	public int countNameChangeEventListWithAccount(String changeRequestId, String accountId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(NameChangeEvent.CHANGE_REQUEST_PROPERTY, changeRequestId);
		key.put(NameChangeEvent.ACCOUNT_PROPERTY, accountId);
		
		int count = getNameChangeEventDAO().countNameChangeEventWithKey(key, options);
		return count;
	}
	
	public ChangeRequest planToRemoveAccountChangeList(ChangeRequest changeRequest, String accountChangeIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(AccountChange.CHANGE_REQUEST_PROPERTY, changeRequest.getId());
		key.put(AccountChange.ID_PROPERTY, accountChangeIds);
		
		SmartList<AccountChange> externalAccountChangeList = getAccountChangeDAO().
				findAccountChangeWithKey(key, options);
		if(externalAccountChangeList == null){
			return changeRequest;
		}
		if(externalAccountChangeList.isEmpty()){
			return changeRequest;
		}
		
		for(AccountChange accountChangeItem: externalAccountChangeList){

			accountChangeItem.clearFromAll();
		}
		
		
		SmartList<AccountChange> accountChangeList = changeRequest.getAccountChangeList();		
		accountChangeList.addAllToRemoveList(externalAccountChangeList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with account in AccountChange
	public ChangeRequest planToRemoveAccountChangeListWithAccount(ChangeRequest changeRequest, String accountId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(AccountChange.CHANGE_REQUEST_PROPERTY, changeRequest.getId());
		key.put(AccountChange.ACCOUNT_PROPERTY, accountId);
		
		SmartList<AccountChange> externalAccountChangeList = getAccountChangeDAO().
				findAccountChangeWithKey(key, options);
		if(externalAccountChangeList == null){
			return changeRequest;
		}
		if(externalAccountChangeList.isEmpty()){
			return changeRequest;
		}
		
		for(AccountChange accountChangeItem: externalAccountChangeList){
			accountChangeItem.clearAccount();
			accountChangeItem.clearChangeRequest();
			
		}
		
		
		SmartList<AccountChange> accountChangeList = changeRequest.getAccountChangeList();		
		accountChangeList.addAllToRemoveList(externalAccountChangeList);
		return changeRequest;
	}
	
	public int countAccountChangeListWithAccount(String changeRequestId, String accountId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(AccountChange.CHANGE_REQUEST_PROPERTY, changeRequestId);
		key.put(AccountChange.ACCOUNT_PROPERTY, accountId);
		
		int count = getAccountChangeDAO().countAccountChangeWithKey(key, options);
		return count;
	}
	

		
	protected ChangeRequest saveTransactionList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<Transaction> transactionList = changeRequest.getTransactionList();
		if(transactionList == null){
			//null list means nothing
			return changeRequest;
		}
		SmartList<Transaction> mergedUpdateTransactionList = new SmartList<Transaction>();
		
		
		mergedUpdateTransactionList.addAll(transactionList); 
		if(transactionList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateTransactionList.addAll(transactionList.getToRemoveList());
			transactionList.removeAll(transactionList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getTransactionDAO().saveTransactionList(mergedUpdateTransactionList,options);
		
		if(transactionList.getToRemoveList() != null){
			transactionList.removeAll(transactionList.getToRemoveList());
		}
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeTransactionList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<Transaction> transactionList = changeRequest.getTransactionList();
		if(transactionList == null){
			return changeRequest;
		}	
	
		SmartList<Transaction> toRemoveTransactionList = transactionList.getToRemoveList();
		
		if(toRemoveTransactionList == null){
			return changeRequest;
		}
		if(toRemoveTransactionList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTransactionDAO().removeTransactionList(toRemoveTransactionList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		
	protected ChangeRequest saveNameChangeEventList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<NameChangeEvent> nameChangeEventList = changeRequest.getNameChangeEventList();
		if(nameChangeEventList == null){
			//null list means nothing
			return changeRequest;
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
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeNameChangeEventList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<NameChangeEvent> nameChangeEventList = changeRequest.getNameChangeEventList();
		if(nameChangeEventList == null){
			return changeRequest;
		}	
	
		SmartList<NameChangeEvent> toRemoveNameChangeEventList = nameChangeEventList.getToRemoveList();
		
		if(toRemoveNameChangeEventList == null){
			return changeRequest;
		}
		if(toRemoveNameChangeEventList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getNameChangeEventDAO().removeNameChangeEventList(toRemoveNameChangeEventList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		
	protected ChangeRequest saveAccountChangeList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<AccountChange> accountChangeList = changeRequest.getAccountChangeList();
		if(accountChangeList == null){
			//null list means nothing
			return changeRequest;
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
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeAccountChangeList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<AccountChange> accountChangeList = changeRequest.getAccountChangeList();
		if(accountChangeList == null){
			return changeRequest;
		}	
	
		SmartList<AccountChange> toRemoveAccountChangeList = accountChangeList.getToRemoveList();
		
		if(toRemoveAccountChangeList == null){
			return changeRequest;
		}
		if(toRemoveAccountChangeList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getAccountChangeDAO().removeAccountChangeList(toRemoveAccountChangeList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		

	public ChangeRequest present(ChangeRequest changeRequest,Map<String, Object> options){
	
		presentTransactionList(changeRequest,options);
		presentNameChangeEventList(changeRequest,options);
		presentAccountChangeList(changeRequest,options);

		return changeRequest;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentTransactionList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<Transaction> transactionList = changeRequest.getTransactionList();		
				SmartList<Transaction> newList= presentSubList(changeRequest.getId(),
				transactionList,
				options,
				getTransactionDAO()::countTransactionByChangeRequest,
				getTransactionDAO()::findTransactionByChangeRequest
				);

		
		changeRequest.setTransactionList(newList);
		

		return changeRequest;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentNameChangeEventList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<NameChangeEvent> nameChangeEventList = changeRequest.getNameChangeEventList();		
				SmartList<NameChangeEvent> newList= presentSubList(changeRequest.getId(),
				nameChangeEventList,
				options,
				getNameChangeEventDAO()::countNameChangeEventByChangeRequest,
				getNameChangeEventDAO()::findNameChangeEventByChangeRequest
				);

		
		changeRequest.setNameChangeEventList(newList);
		

		return changeRequest;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentAccountChangeList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<AccountChange> accountChangeList = changeRequest.getAccountChangeList();		
				SmartList<AccountChange> newList= presentSubList(changeRequest.getId(),
				accountChangeList,
				options,
				getAccountChangeDAO()::countAccountChangeByChangeRequest,
				getAccountChangeDAO()::findAccountChangeByChangeRequest
				);

		
		changeRequest.setAccountChangeList(newList);
		

		return changeRequest;
	}			
		

	
    public SmartList<ChangeRequest> requestCandidateChangeRequestForTransaction(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		
    public SmartList<ChangeRequest> requestCandidateChangeRequestForNameChangeEvent(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		
    public SmartList<ChangeRequest> requestCandidateChangeRequestForAccountChange(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		

	protected String getTableName(){
		return ChangeRequestTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<ChangeRequest> changeRequestList) {		
		this.enhanceListInternal(changeRequestList, this.getChangeRequestMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Transaction的changeRequest的TransactionList
	public SmartList<Transaction> loadOurTransactionList(BankUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Transaction.CHANGE_REQUEST_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Transaction> loadedObjs = userContext.getDAOGroup().getTransactionDAO().findTransactionWithKey(key, options);
		Map<String, List<Transaction>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getChangeRequest().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Transaction> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Transaction> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setTransactionList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:NameChangeEvent的changeRequest的NameChangeEventList
	public SmartList<NameChangeEvent> loadOurNameChangeEventList(BankUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(NameChangeEvent.CHANGE_REQUEST_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<NameChangeEvent> loadedObjs = userContext.getDAOGroup().getNameChangeEventDAO().findNameChangeEventWithKey(key, options);
		Map<String, List<NameChangeEvent>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getChangeRequest().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:AccountChange的changeRequest的AccountChangeList
	public SmartList<AccountChange> loadOurAccountChangeList(BankUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(AccountChange.CHANGE_REQUEST_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<AccountChange> loadedObjs = userContext.getDAOGroup().getAccountChangeDAO().findAccountChangeWithKey(key, options);
		Map<String, List<AccountChange>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getChangeRequest().getId()));
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
		List<ChangeRequest> changeRequestList = ownerEntity.collectRefsWithType(ChangeRequest.INTERNAL_TYPE);
		this.enhanceList(changeRequestList);
		
	}
	
	@Override
	public SmartList<ChangeRequest> findChangeRequestWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getChangeRequestMapper());

	}
	@Override
	public int countChangeRequestWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countChangeRequestWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<ChangeRequest> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getChangeRequestMapper());
	}
	
	

}


