
package com.doublechain.bank.accountchange;

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


public class AccountChangeJDBCTemplateDAO extends BankBaseDAOImpl implements AccountChangeDAO{
 
 	
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
	protected AccountChange load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalAccountChange(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public AccountChange load(String id,Map<String,Object> options) throws Exception{
		return loadInternalAccountChange(AccountChangeTable.withId(id), options);
	}
	
	
	
	public AccountChange save(AccountChange accountChange,Map<String,Object> options){
		
		String methodName="save(AccountChange accountChange,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accountChange, methodName, "accountChange");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalAccountChange(accountChange,options);
	}
	public AccountChange clone(String accountChangeId, Map<String,Object> options) throws Exception{
	
		return clone(AccountChangeTable.withId(accountChangeId),options);
	}
	
	protected AccountChange clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String accountChangeId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		AccountChange newAccountChange = loadInternalAccountChange(accessKey, options);
		newAccountChange.setVersion(0);
		
		

		
		saveInternalAccountChange(newAccountChange,options);
		
		return newAccountChange;
	}
	
	
	
	

	protected void throwIfHasException(String accountChangeId,int version,int count) throws Exception{
		if (count == 1) {
			throw new AccountChangeVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new AccountChangeNotFoundException(
					"The " + this.getTableName() + "(" + accountChangeId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String accountChangeId, int version) throws Exception{
	
		String methodName="delete(String accountChangeId, int version)";
		assertMethodArgumentNotNull(accountChangeId, methodName, "accountChangeId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{accountChangeId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(accountChangeId,version);
		}
		
	
	}
	
	
	
	
	

	public AccountChange disconnectFromAll(String accountChangeId, int version) throws Exception{
	
		
		AccountChange accountChange = loadInternalAccountChange(AccountChangeTable.withId(accountChangeId), emptyOptions());
		accountChange.clearFromAll();
		this.saveAccountChange(accountChange);
		return accountChange;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return AccountChangeTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "account_change";
	}
	@Override
	protected String getBeanName() {
		
		return "accountChange";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return AccountChangeTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractAccountEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, AccountChangeTokens.ACCOUNT);
 	}

 	protected boolean isSaveAccountEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, AccountChangeTokens.ACCOUNT);
 	}
 	

 	
  

 	protected boolean isExtractChangeRequestEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, AccountChangeTokens.CHANGEREQUEST);
 	}

 	protected boolean isSaveChangeRequestEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, AccountChangeTokens.CHANGEREQUEST);
 	}
 	

 	
 
		

	

	protected AccountChangeMapper getAccountChangeMapper(){
		return new AccountChangeMapper();
	}

	
	
	protected AccountChange extractAccountChange(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			AccountChange accountChange = loadSingleObject(accessKey, getAccountChangeMapper());
			return accountChange;
		}catch(EmptyResultDataAccessException e){
			throw new AccountChangeNotFoundException("AccountChange("+accessKey+") is not found!");
		}

	}

	
	

	protected AccountChange loadInternalAccountChange(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		AccountChange accountChange = extractAccountChange(accessKey, loadOptions);
 	
 		if(isExtractAccountEnabled(loadOptions)){
	 		extractAccount(accountChange, loadOptions);
 		}
  	
 		if(isExtractChangeRequestEnabled(loadOptions)){
	 		extractChangeRequest(accountChange, loadOptions);
 		}
 
		
		return accountChange;
		
	}

	 

 	protected AccountChange extractAccount(AccountChange accountChange, Map<String,Object> options) throws Exception{

		if(accountChange.getAccount() == null){
			return accountChange;
		}
		String accountId = accountChange.getAccount().getId();
		if( accountId == null){
			return accountChange;
		}
		Account account = getAccountDAO().load(accountId,options);
		if(account != null){
			accountChange.setAccount(account);
		}
		
 		
 		return accountChange;
 	}
 		
  

 	protected AccountChange extractChangeRequest(AccountChange accountChange, Map<String,Object> options) throws Exception{

		if(accountChange.getChangeRequest() == null){
			return accountChange;
		}
		String changeRequestId = accountChange.getChangeRequest().getId();
		if( changeRequestId == null){
			return accountChange;
		}
		ChangeRequest changeRequest = getChangeRequestDAO().load(changeRequestId,options);
		if(changeRequest != null){
			accountChange.setChangeRequest(changeRequest);
		}
		
 		
 		return accountChange;
 	}
 		
 
		
		
  	
 	public SmartList<AccountChange> findAccountChangeByAccount(String accountId,Map<String,Object> options){
 	
  		SmartList<AccountChange> resultList = queryWith(AccountChangeTable.COLUMN_ACCOUNT, accountId, options, getAccountChangeMapper());
		// analyzeAccountChangeByAccount(resultList, accountId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<AccountChange> findAccountChangeByAccount(String accountId, int start, int count,Map<String,Object> options){
 		
 		SmartList<AccountChange> resultList =  queryWithRange(AccountChangeTable.COLUMN_ACCOUNT, accountId, options, getAccountChangeMapper(), start, count);
 		//analyzeAccountChangeByAccount(resultList, accountId, options);
 		return resultList;
 		
 	}
 	public void analyzeAccountChangeByAccount(SmartList<AccountChange> resultList, String accountId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(AccountChange.ACCOUNT_PROPERTY, accountId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countAccountChangeByAccount(String accountId,Map<String,Object> options){

 		return countWith(AccountChangeTable.COLUMN_ACCOUNT, accountId, options);
 	}
 	@Override
	public Map<String, Integer> countAccountChangeByAccountIds(String[] ids, Map<String, Object> options) {
		return countWithIds(AccountChangeTable.COLUMN_ACCOUNT, ids, options);
	}
 	
  	
 	public SmartList<AccountChange> findAccountChangeByChangeRequest(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<AccountChange> resultList = queryWith(AccountChangeTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getAccountChangeMapper());
		// analyzeAccountChangeByChangeRequest(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<AccountChange> findAccountChangeByChangeRequest(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<AccountChange> resultList =  queryWithRange(AccountChangeTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getAccountChangeMapper(), start, count);
 		//analyzeAccountChangeByChangeRequest(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeAccountChangeByChangeRequest(SmartList<AccountChange> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(AccountChange.CHANGE_REQUEST_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countAccountChangeByChangeRequest(String changeRequestId,Map<String,Object> options){

 		return countWith(AccountChangeTable.COLUMN_CHANGE_REQUEST, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countAccountChangeByChangeRequestIds(String[] ids, Map<String, Object> options) {
		return countWithIds(AccountChangeTable.COLUMN_CHANGE_REQUEST, ids, options);
	}
 	
 	
		
		
		

	

	protected AccountChange saveAccountChange(AccountChange  accountChange){
		
		if(!accountChange.isChanged()){
			return accountChange;
		}
		
		
		String SQL=this.getSaveAccountChangeSQL(accountChange);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveAccountChangeParameters(accountChange);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		accountChange.incVersion();
		return accountChange;
	
	}
	public SmartList<AccountChange> saveAccountChangeList(SmartList<AccountChange> accountChangeList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitAccountChangeList(accountChangeList);
		
		batchAccountChangeCreate((List<AccountChange>)lists[CREATE_LIST_INDEX]);
		
		batchAccountChangeUpdate((List<AccountChange>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(AccountChange accountChange:accountChangeList){
			if(accountChange.isChanged()){
				accountChange.incVersion();
			}
			
		
		}
		
		
		return accountChangeList;
	}

	public SmartList<AccountChange> removeAccountChangeList(SmartList<AccountChange> accountChangeList,Map<String,Object> options){
		
		
		super.removeList(accountChangeList, options);
		
		return accountChangeList;
		
		
	}
	
	protected List<Object[]> prepareAccountChangeBatchCreateArgs(List<AccountChange> accountChangeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(AccountChange accountChange:accountChangeList ){
			Object [] parameters = prepareAccountChangeCreateParameters(accountChange);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareAccountChangeBatchUpdateArgs(List<AccountChange> accountChangeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(AccountChange accountChange:accountChangeList ){
			if(!accountChange.isChanged()){
				continue;
			}
			Object [] parameters = prepareAccountChangeUpdateParameters(accountChange);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchAccountChangeCreate(List<AccountChange> accountChangeList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareAccountChangeBatchCreateArgs(accountChangeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchAccountChangeUpdate(List<AccountChange> accountChangeList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareAccountChangeBatchUpdateArgs(accountChangeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitAccountChangeList(List<AccountChange> accountChangeList){
		
		List<AccountChange> accountChangeCreateList=new ArrayList<AccountChange>();
		List<AccountChange> accountChangeUpdateList=new ArrayList<AccountChange>();
		
		for(AccountChange accountChange: accountChangeList){
			if(isUpdateRequest(accountChange)){
				accountChangeUpdateList.add( accountChange);
				continue;
			}
			accountChangeCreateList.add(accountChange);
		}
		
		return new Object[]{accountChangeCreateList,accountChangeUpdateList};
	}
	
	protected boolean isUpdateRequest(AccountChange accountChange){
 		return accountChange.getVersion() > 0;
 	}
 	protected String getSaveAccountChangeSQL(AccountChange accountChange){
 		if(isUpdateRequest(accountChange)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveAccountChangeParameters(AccountChange accountChange){
 		if(isUpdateRequest(accountChange) ){
 			return prepareAccountChangeUpdateParameters(accountChange);
 		}
 		return prepareAccountChangeCreateParameters(accountChange);
 	}
 	protected Object[] prepareAccountChangeUpdateParameters(AccountChange accountChange){
 		Object[] parameters = new Object[11];
 
 		parameters[0] = accountChange.getName();
 		parameters[1] = accountChange.getPreviousBalance();
 		parameters[2] = accountChange.getType();
 		parameters[3] = accountChange.getAmount();
 		parameters[4] = accountChange.getCurrentBalance(); 	
 		if(accountChange.getAccount() != null){
 			parameters[5] = accountChange.getAccount().getId();
 		}
  	
 		if(accountChange.getChangeRequest() != null){
 			parameters[6] = accountChange.getChangeRequest().getId();
 		}
 
 		parameters[7] = accountChange.getCurrentStatus();		
 		parameters[8] = accountChange.nextVersion();
 		parameters[9] = accountChange.getId();
 		parameters[10] = accountChange.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareAccountChangeCreateParameters(AccountChange accountChange){
		Object[] parameters = new Object[9];
		String newAccountChangeId=getNextId();
		accountChange.setId(newAccountChangeId);
		parameters[0] =  accountChange.getId();
 
 		parameters[1] = accountChange.getName();
 		parameters[2] = accountChange.getPreviousBalance();
 		parameters[3] = accountChange.getType();
 		parameters[4] = accountChange.getAmount();
 		parameters[5] = accountChange.getCurrentBalance(); 	
 		if(accountChange.getAccount() != null){
 			parameters[6] = accountChange.getAccount().getId();
 		
 		}
 		 	
 		if(accountChange.getChangeRequest() != null){
 			parameters[7] = accountChange.getChangeRequest().getId();
 		
 		}
 		
 		parameters[8] = accountChange.getCurrentStatus();		
 				
 		return parameters;
 	}
 	
	protected AccountChange saveInternalAccountChange(AccountChange accountChange, Map<String,Object> options){
		
		saveAccountChange(accountChange);
 	
 		if(isSaveAccountEnabled(options)){
	 		saveAccount(accountChange, options);
 		}
  	
 		if(isSaveChangeRequestEnabled(options)){
	 		saveChangeRequest(accountChange, options);
 		}
 
		
		return accountChange;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected AccountChange saveAccount(AccountChange accountChange, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(accountChange.getAccount() == null){
 			return accountChange;//do nothing when it is null
 		}
 		
 		getAccountDAO().save(accountChange.getAccount(),options);
 		return accountChange;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected AccountChange saveChangeRequest(AccountChange accountChange, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(accountChange.getChangeRequest() == null){
 			return accountChange;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(accountChange.getChangeRequest(),options);
 		return accountChange;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public AccountChange present(AccountChange accountChange,Map<String, Object> options){
	

		return accountChange;
	
	}
		

	

	protected String getTableName(){
		return AccountChangeTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<AccountChange> accountChangeList) {		
		this.enhanceListInternal(accountChangeList, this.getAccountChangeMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<AccountChange> accountChangeList = ownerEntity.collectRefsWithType(AccountChange.INTERNAL_TYPE);
		this.enhanceList(accountChangeList);
		
	}
	
	@Override
	public SmartList<AccountChange> findAccountChangeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getAccountChangeMapper());

	}
	@Override
	public int countAccountChangeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countAccountChangeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<AccountChange> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getAccountChangeMapper());
	}
	
	

}


