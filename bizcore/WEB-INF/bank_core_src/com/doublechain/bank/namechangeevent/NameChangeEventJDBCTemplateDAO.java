
package com.doublechain.bank.namechangeevent;

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


public class NameChangeEventJDBCTemplateDAO extends BankBaseDAOImpl implements NameChangeEventDAO{
 
 	
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
	protected NameChangeEvent load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalNameChangeEvent(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public NameChangeEvent load(String id,Map<String,Object> options) throws Exception{
		return loadInternalNameChangeEvent(NameChangeEventTable.withId(id), options);
	}
	
	
	
	public NameChangeEvent save(NameChangeEvent nameChangeEvent,Map<String,Object> options){
		
		String methodName="save(NameChangeEvent nameChangeEvent,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(nameChangeEvent, methodName, "nameChangeEvent");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalNameChangeEvent(nameChangeEvent,options);
	}
	public NameChangeEvent clone(String nameChangeEventId, Map<String,Object> options) throws Exception{
	
		return clone(NameChangeEventTable.withId(nameChangeEventId),options);
	}
	
	protected NameChangeEvent clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String nameChangeEventId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		NameChangeEvent newNameChangeEvent = loadInternalNameChangeEvent(accessKey, options);
		newNameChangeEvent.setVersion(0);
		
		

		
		saveInternalNameChangeEvent(newNameChangeEvent,options);
		
		return newNameChangeEvent;
	}
	
	
	
	

	protected void throwIfHasException(String nameChangeEventId,int version,int count) throws Exception{
		if (count == 1) {
			throw new NameChangeEventVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new NameChangeEventNotFoundException(
					"The " + this.getTableName() + "(" + nameChangeEventId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String nameChangeEventId, int version) throws Exception{
	
		String methodName="delete(String nameChangeEventId, int version)";
		assertMethodArgumentNotNull(nameChangeEventId, methodName, "nameChangeEventId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{nameChangeEventId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(nameChangeEventId,version);
		}
		
	
	}
	
	
	
	
	

	public NameChangeEvent disconnectFromAll(String nameChangeEventId, int version) throws Exception{
	
		
		NameChangeEvent nameChangeEvent = loadInternalNameChangeEvent(NameChangeEventTable.withId(nameChangeEventId), emptyOptions());
		nameChangeEvent.clearFromAll();
		this.saveNameChangeEvent(nameChangeEvent);
		return nameChangeEvent;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return NameChangeEventTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "name_change_event";
	}
	@Override
	protected String getBeanName() {
		
		return "nameChangeEvent";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return NameChangeEventTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractAccountEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, NameChangeEventTokens.ACCOUNT);
 	}

 	protected boolean isSaveAccountEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, NameChangeEventTokens.ACCOUNT);
 	}
 	

 	
  

 	protected boolean isExtractChangeRequestEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, NameChangeEventTokens.CHANGEREQUEST);
 	}

 	protected boolean isSaveChangeRequestEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, NameChangeEventTokens.CHANGEREQUEST);
 	}
 	

 	
 
		

	

	protected NameChangeEventMapper getNameChangeEventMapper(){
		return new NameChangeEventMapper();
	}

	
	
	protected NameChangeEvent extractNameChangeEvent(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			NameChangeEvent nameChangeEvent = loadSingleObject(accessKey, getNameChangeEventMapper());
			return nameChangeEvent;
		}catch(EmptyResultDataAccessException e){
			throw new NameChangeEventNotFoundException("NameChangeEvent("+accessKey+") is not found!");
		}

	}

	
	

	protected NameChangeEvent loadInternalNameChangeEvent(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		NameChangeEvent nameChangeEvent = extractNameChangeEvent(accessKey, loadOptions);
 	
 		if(isExtractAccountEnabled(loadOptions)){
	 		extractAccount(nameChangeEvent, loadOptions);
 		}
  	
 		if(isExtractChangeRequestEnabled(loadOptions)){
	 		extractChangeRequest(nameChangeEvent, loadOptions);
 		}
 
		
		return nameChangeEvent;
		
	}

	 

 	protected NameChangeEvent extractAccount(NameChangeEvent nameChangeEvent, Map<String,Object> options) throws Exception{

		if(nameChangeEvent.getAccount() == null){
			return nameChangeEvent;
		}
		String accountId = nameChangeEvent.getAccount().getId();
		if( accountId == null){
			return nameChangeEvent;
		}
		Account account = getAccountDAO().load(accountId,options);
		if(account != null){
			nameChangeEvent.setAccount(account);
		}
		
 		
 		return nameChangeEvent;
 	}
 		
  

 	protected NameChangeEvent extractChangeRequest(NameChangeEvent nameChangeEvent, Map<String,Object> options) throws Exception{

		if(nameChangeEvent.getChangeRequest() == null){
			return nameChangeEvent;
		}
		String changeRequestId = nameChangeEvent.getChangeRequest().getId();
		if( changeRequestId == null){
			return nameChangeEvent;
		}
		ChangeRequest changeRequest = getChangeRequestDAO().load(changeRequestId,options);
		if(changeRequest != null){
			nameChangeEvent.setChangeRequest(changeRequest);
		}
		
 		
 		return nameChangeEvent;
 	}
 		
 
		
		
  	
 	public SmartList<NameChangeEvent> findNameChangeEventByAccount(String accountId,Map<String,Object> options){
 	
  		SmartList<NameChangeEvent> resultList = queryWith(NameChangeEventTable.COLUMN_ACCOUNT, accountId, options, getNameChangeEventMapper());
		// analyzeNameChangeEventByAccount(resultList, accountId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<NameChangeEvent> findNameChangeEventByAccount(String accountId, int start, int count,Map<String,Object> options){
 		
 		SmartList<NameChangeEvent> resultList =  queryWithRange(NameChangeEventTable.COLUMN_ACCOUNT, accountId, options, getNameChangeEventMapper(), start, count);
 		//analyzeNameChangeEventByAccount(resultList, accountId, options);
 		return resultList;
 		
 	}
 	public void analyzeNameChangeEventByAccount(SmartList<NameChangeEvent> resultList, String accountId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(NameChangeEvent.ACCOUNT_PROPERTY, accountId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countNameChangeEventByAccount(String accountId,Map<String,Object> options){

 		return countWith(NameChangeEventTable.COLUMN_ACCOUNT, accountId, options);
 	}
 	@Override
	public Map<String, Integer> countNameChangeEventByAccountIds(String[] ids, Map<String, Object> options) {
		return countWithIds(NameChangeEventTable.COLUMN_ACCOUNT, ids, options);
	}
 	
  	
 	public SmartList<NameChangeEvent> findNameChangeEventByChangeRequest(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<NameChangeEvent> resultList = queryWith(NameChangeEventTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getNameChangeEventMapper());
		// analyzeNameChangeEventByChangeRequest(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<NameChangeEvent> findNameChangeEventByChangeRequest(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<NameChangeEvent> resultList =  queryWithRange(NameChangeEventTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getNameChangeEventMapper(), start, count);
 		//analyzeNameChangeEventByChangeRequest(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeNameChangeEventByChangeRequest(SmartList<NameChangeEvent> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(NameChangeEvent.CHANGE_REQUEST_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countNameChangeEventByChangeRequest(String changeRequestId,Map<String,Object> options){

 		return countWith(NameChangeEventTable.COLUMN_CHANGE_REQUEST, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countNameChangeEventByChangeRequestIds(String[] ids, Map<String, Object> options) {
		return countWithIds(NameChangeEventTable.COLUMN_CHANGE_REQUEST, ids, options);
	}
 	
 	
		
		
		

	

	protected NameChangeEvent saveNameChangeEvent(NameChangeEvent  nameChangeEvent){
		
		if(!nameChangeEvent.isChanged()){
			return nameChangeEvent;
		}
		
		
		String SQL=this.getSaveNameChangeEventSQL(nameChangeEvent);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveNameChangeEventParameters(nameChangeEvent);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		nameChangeEvent.incVersion();
		return nameChangeEvent;
	
	}
	public SmartList<NameChangeEvent> saveNameChangeEventList(SmartList<NameChangeEvent> nameChangeEventList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitNameChangeEventList(nameChangeEventList);
		
		batchNameChangeEventCreate((List<NameChangeEvent>)lists[CREATE_LIST_INDEX]);
		
		batchNameChangeEventUpdate((List<NameChangeEvent>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(NameChangeEvent nameChangeEvent:nameChangeEventList){
			if(nameChangeEvent.isChanged()){
				nameChangeEvent.incVersion();
			}
			
		
		}
		
		
		return nameChangeEventList;
	}

	public SmartList<NameChangeEvent> removeNameChangeEventList(SmartList<NameChangeEvent> nameChangeEventList,Map<String,Object> options){
		
		
		super.removeList(nameChangeEventList, options);
		
		return nameChangeEventList;
		
		
	}
	
	protected List<Object[]> prepareNameChangeEventBatchCreateArgs(List<NameChangeEvent> nameChangeEventList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(NameChangeEvent nameChangeEvent:nameChangeEventList ){
			Object [] parameters = prepareNameChangeEventCreateParameters(nameChangeEvent);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareNameChangeEventBatchUpdateArgs(List<NameChangeEvent> nameChangeEventList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(NameChangeEvent nameChangeEvent:nameChangeEventList ){
			if(!nameChangeEvent.isChanged()){
				continue;
			}
			Object [] parameters = prepareNameChangeEventUpdateParameters(nameChangeEvent);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchNameChangeEventCreate(List<NameChangeEvent> nameChangeEventList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareNameChangeEventBatchCreateArgs(nameChangeEventList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchNameChangeEventUpdate(List<NameChangeEvent> nameChangeEventList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareNameChangeEventBatchUpdateArgs(nameChangeEventList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitNameChangeEventList(List<NameChangeEvent> nameChangeEventList){
		
		List<NameChangeEvent> nameChangeEventCreateList=new ArrayList<NameChangeEvent>();
		List<NameChangeEvent> nameChangeEventUpdateList=new ArrayList<NameChangeEvent>();
		
		for(NameChangeEvent nameChangeEvent: nameChangeEventList){
			if(isUpdateRequest(nameChangeEvent)){
				nameChangeEventUpdateList.add( nameChangeEvent);
				continue;
			}
			nameChangeEventCreateList.add(nameChangeEvent);
		}
		
		return new Object[]{nameChangeEventCreateList,nameChangeEventUpdateList};
	}
	
	protected boolean isUpdateRequest(NameChangeEvent nameChangeEvent){
 		return nameChangeEvent.getVersion() > 0;
 	}
 	protected String getSaveNameChangeEventSQL(NameChangeEvent nameChangeEvent){
 		if(isUpdateRequest(nameChangeEvent)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveNameChangeEventParameters(NameChangeEvent nameChangeEvent){
 		if(isUpdateRequest(nameChangeEvent) ){
 			return prepareNameChangeEventUpdateParameters(nameChangeEvent);
 		}
 		return prepareNameChangeEventCreateParameters(nameChangeEvent);
 	}
 	protected Object[] prepareNameChangeEventUpdateParameters(NameChangeEvent nameChangeEvent){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = nameChangeEvent.getName(); 	
 		if(nameChangeEvent.getAccount() != null){
 			parameters[1] = nameChangeEvent.getAccount().getId();
 		}
  	
 		if(nameChangeEvent.getChangeRequest() != null){
 			parameters[2] = nameChangeEvent.getChangeRequest().getId();
 		}
 		
 		parameters[3] = nameChangeEvent.nextVersion();
 		parameters[4] = nameChangeEvent.getId();
 		parameters[5] = nameChangeEvent.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareNameChangeEventCreateParameters(NameChangeEvent nameChangeEvent){
		Object[] parameters = new Object[4];
		String newNameChangeEventId=getNextId();
		nameChangeEvent.setId(newNameChangeEventId);
		parameters[0] =  nameChangeEvent.getId();
 
 		parameters[1] = nameChangeEvent.getName(); 	
 		if(nameChangeEvent.getAccount() != null){
 			parameters[2] = nameChangeEvent.getAccount().getId();
 		
 		}
 		 	
 		if(nameChangeEvent.getChangeRequest() != null){
 			parameters[3] = nameChangeEvent.getChangeRequest().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected NameChangeEvent saveInternalNameChangeEvent(NameChangeEvent nameChangeEvent, Map<String,Object> options){
		
		saveNameChangeEvent(nameChangeEvent);
 	
 		if(isSaveAccountEnabled(options)){
	 		saveAccount(nameChangeEvent, options);
 		}
  	
 		if(isSaveChangeRequestEnabled(options)){
	 		saveChangeRequest(nameChangeEvent, options);
 		}
 
		
		return nameChangeEvent;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected NameChangeEvent saveAccount(NameChangeEvent nameChangeEvent, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(nameChangeEvent.getAccount() == null){
 			return nameChangeEvent;//do nothing when it is null
 		}
 		
 		getAccountDAO().save(nameChangeEvent.getAccount(),options);
 		return nameChangeEvent;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected NameChangeEvent saveChangeRequest(NameChangeEvent nameChangeEvent, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(nameChangeEvent.getChangeRequest() == null){
 			return nameChangeEvent;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(nameChangeEvent.getChangeRequest(),options);
 		return nameChangeEvent;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public NameChangeEvent present(NameChangeEvent nameChangeEvent,Map<String, Object> options){
	

		return nameChangeEvent;
	
	}
		

	

	protected String getTableName(){
		return NameChangeEventTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<NameChangeEvent> nameChangeEventList) {		
		this.enhanceListInternal(nameChangeEventList, this.getNameChangeEventMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<NameChangeEvent> nameChangeEventList = ownerEntity.collectRefsWithType(NameChangeEvent.INTERNAL_TYPE);
		this.enhanceList(nameChangeEventList);
		
	}
	
	@Override
	public SmartList<NameChangeEvent> findNameChangeEventWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getNameChangeEventMapper());

	}
	@Override
	public int countNameChangeEventWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countNameChangeEventWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<NameChangeEvent> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getNameChangeEventMapper());
	}
	
	

}


