
package com.doublechain.bank.transaction;
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


public interface TransactionDAO{

	
	public Transaction load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Transaction> transactionList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Transaction present(Transaction transaction,Map<String,Object> options) throws Exception;
	public Transaction clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Transaction save(Transaction transaction,Map<String,Object> options);
	public SmartList<Transaction> saveTransactionList(SmartList<Transaction> transactionList,Map<String,Object> options);
	public SmartList<Transaction> removeTransactionList(SmartList<Transaction> transactionList,Map<String,Object> options);
	public SmartList<Transaction> findTransactionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countTransactionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countTransactionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String transactionId, int version) throws Exception;
	public Transaction disconnectFromAll(String transactionId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<Transaction> queryList(String sql, Object ... parmeters);
 
 	public SmartList<Transaction> findTransactionByFromAccount(String accountId, Map<String,Object> options);
 	public int countTransactionByFromAccount(String accountId, Map<String,Object> options);
 	public Map<String, Integer> countTransactionByFromAccountIds(String[] ids, Map<String,Object> options);
 	public SmartList<Transaction> findTransactionByFromAccount(String accountId, int start, int count, Map<String,Object> options);
 	public void analyzeTransactionByFromAccount(SmartList<Transaction> resultList, String accountId, Map<String,Object> options);

 
  
 	public SmartList<Transaction> findTransactionByToAccount(String accountId, Map<String,Object> options);
 	public int countTransactionByToAccount(String accountId, Map<String,Object> options);
 	public Map<String, Integer> countTransactionByToAccountIds(String[] ids, Map<String,Object> options);
 	public SmartList<Transaction> findTransactionByToAccount(String accountId, int start, int count, Map<String,Object> options);
 	public void analyzeTransactionByToAccount(SmartList<Transaction> resultList, String accountId, Map<String,Object> options);

 
  
 	public SmartList<Transaction> findTransactionByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public int countTransactionByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countTransactionByChangeRequestIds(String[] ids, Map<String,Object> options);
 	public SmartList<Transaction> findTransactionByChangeRequest(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeTransactionByChangeRequest(SmartList<Transaction> resultList, String changeRequestId, Map<String,Object> options);

 
 
}


