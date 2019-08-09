
package com.doublechain.bank.accountchange;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;
import com.doublechain.bank.MultipleAccessKey;
import com.doublechain.bank.BankUserContext;

import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.account.Account;

import com.doublechain.bank.changerequest.ChangeRequestDAO;
import com.doublechain.bank.account.AccountDAO;


public interface AccountChangeDAO{

	
	public AccountChange load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<AccountChange> accountChangeList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public AccountChange present(AccountChange accountChange,Map<String,Object> options) throws Exception;
	public AccountChange clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public AccountChange save(AccountChange accountChange,Map<String,Object> options);
	public SmartList<AccountChange> saveAccountChangeList(SmartList<AccountChange> accountChangeList,Map<String,Object> options);
	public SmartList<AccountChange> removeAccountChangeList(SmartList<AccountChange> accountChangeList,Map<String,Object> options);
	public SmartList<AccountChange> findAccountChangeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countAccountChangeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countAccountChangeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String accountChangeId, int version) throws Exception;
	public AccountChange disconnectFromAll(String accountChangeId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<AccountChange> queryList(String sql, Object ... parmeters);
 
 	public SmartList<AccountChange> findAccountChangeByAccount(String accountId, Map<String,Object> options);
 	public int countAccountChangeByAccount(String accountId, Map<String,Object> options);
 	public Map<String, Integer> countAccountChangeByAccountIds(String[] ids, Map<String,Object> options);
 	public SmartList<AccountChange> findAccountChangeByAccount(String accountId, int start, int count, Map<String,Object> options);
 	public void analyzeAccountChangeByAccount(SmartList<AccountChange> resultList, String accountId, Map<String,Object> options);

 
  
 	public SmartList<AccountChange> findAccountChangeByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public int countAccountChangeByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countAccountChangeByChangeRequestIds(String[] ids, Map<String,Object> options);
 	public SmartList<AccountChange> findAccountChangeByChangeRequest(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeAccountChangeByChangeRequest(SmartList<AccountChange> resultList, String changeRequestId, Map<String,Object> options);

 
 
}


