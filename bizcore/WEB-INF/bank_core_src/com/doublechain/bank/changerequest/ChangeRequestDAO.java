
package com.doublechain.bank.changerequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;
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


public interface ChangeRequestDAO{

	
	public ChangeRequest load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<ChangeRequest> changeRequestList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public ChangeRequest present(ChangeRequest changeRequest,Map<String,Object> options) throws Exception;
	public ChangeRequest clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public ChangeRequest save(ChangeRequest changeRequest,Map<String,Object> options);
	public SmartList<ChangeRequest> saveChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options);
	public SmartList<ChangeRequest> removeChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options);
	public SmartList<ChangeRequest> findChangeRequestWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countChangeRequestWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countChangeRequestWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String changeRequestId, int version) throws Exception;
	public ChangeRequest disconnectFromAll(String changeRequestId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public TransactionDAO getTransactionDAO();
		
	public NameChangeEventDAO getNameChangeEventDAO();
		
	public AccountChangeDAO getAccountChangeDAO();
		
	
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForTransaction(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForNameChangeEvent(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForAccountChange(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public ChangeRequest planToRemoveTransactionList(ChangeRequest changeRequest, String transactionIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with from_account in Transaction
	public ChangeRequest planToRemoveTransactionListWithFromAccount(ChangeRequest changeRequest, String fromAccountId, Map<String,Object> options)throws Exception;
	public int countTransactionListWithFromAccount(String changeRequestId, String fromAccountId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with to_account in Transaction
	public ChangeRequest planToRemoveTransactionListWithToAccount(ChangeRequest changeRequest, String toAccountId, Map<String,Object> options)throws Exception;
	public int countTransactionListWithToAccount(String changeRequestId, String toAccountId, Map<String,Object> options)throws Exception;
	
	public ChangeRequest planToRemoveNameChangeEventList(ChangeRequest changeRequest, String nameChangeEventIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with account in NameChangeEvent
	public ChangeRequest planToRemoveNameChangeEventListWithAccount(ChangeRequest changeRequest, String accountId, Map<String,Object> options)throws Exception;
	public int countNameChangeEventListWithAccount(String changeRequestId, String accountId, Map<String,Object> options)throws Exception;
	
	public ChangeRequest planToRemoveAccountChangeList(ChangeRequest changeRequest, String accountChangeIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with account in AccountChange
	public ChangeRequest planToRemoveAccountChangeListWithAccount(ChangeRequest changeRequest, String accountId, Map<String,Object> options)throws Exception;
	public int countAccountChangeListWithAccount(String changeRequestId, String accountId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<ChangeRequest> queryList(String sql, Object ... parmeters);
 
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId, Map<String,Object> options);
 	public int countChangeRequestByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countChangeRequestByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeChangeRequestByPlatform(SmartList<ChangeRequest> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Transaction的changeRequest的TransactionList
	public SmartList<Transaction> loadOurTransactionList(BankUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:NameChangeEvent的changeRequest的NameChangeEventList
	public SmartList<NameChangeEvent> loadOurNameChangeEventList(BankUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:AccountChange的changeRequest的AccountChangeList
	public SmartList<AccountChange> loadOurAccountChangeList(BankUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
}


