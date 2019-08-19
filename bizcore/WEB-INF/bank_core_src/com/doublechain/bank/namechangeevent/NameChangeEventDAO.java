
package com.doublechain.bank.namechangeevent;
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


public interface NameChangeEventDAO{

	
	public NameChangeEvent load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<NameChangeEvent> nameChangeEventList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public NameChangeEvent present(NameChangeEvent nameChangeEvent,Map<String,Object> options) throws Exception;
	public NameChangeEvent clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public NameChangeEvent save(NameChangeEvent nameChangeEvent,Map<String,Object> options);
	public SmartList<NameChangeEvent> saveNameChangeEventList(SmartList<NameChangeEvent> nameChangeEventList,Map<String,Object> options);
	public SmartList<NameChangeEvent> removeNameChangeEventList(SmartList<NameChangeEvent> nameChangeEventList,Map<String,Object> options);
	public SmartList<NameChangeEvent> findNameChangeEventWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countNameChangeEventWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countNameChangeEventWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String nameChangeEventId, int version) throws Exception;
	public NameChangeEvent disconnectFromAll(String nameChangeEventId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<NameChangeEvent> queryList(String sql, Object ... parmeters);
 
 	public SmartList<NameChangeEvent> findNameChangeEventByAccount(String accountId, Map<String,Object> options);
 	public int countNameChangeEventByAccount(String accountId, Map<String,Object> options);
 	public Map<String, Integer> countNameChangeEventByAccountIds(String[] ids, Map<String,Object> options);
 	public SmartList<NameChangeEvent> findNameChangeEventByAccount(String accountId, int start, int count, Map<String,Object> options);
 	public void analyzeNameChangeEventByAccount(SmartList<NameChangeEvent> resultList, String accountId, Map<String,Object> options);

 
  
 	public SmartList<NameChangeEvent> findNameChangeEventByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public int countNameChangeEventByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countNameChangeEventByChangeRequestIds(String[] ids, Map<String,Object> options);
 	public SmartList<NameChangeEvent> findNameChangeEventByChangeRequest(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeNameChangeEventByChangeRequest(SmartList<NameChangeEvent> resultList, String changeRequestId, Map<String,Object> options);

 
 
}


