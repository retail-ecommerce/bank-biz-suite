
package com.doublechain.bank.account;
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


public interface AccountDAO{

	
	public Account load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Account> accountList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Account loadByName(String name,Map<String,Object> options) throws Exception;
	
	
	public Account present(Account account,Map<String,Object> options) throws Exception;
	public Account clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Account cloneByName(String name,Map<String,Object> options) throws Exception;
	
	
	public Account save(Account account,Map<String,Object> options);
	public SmartList<Account> saveAccountList(SmartList<Account> accountList,Map<String,Object> options);
	public SmartList<Account> removeAccountList(SmartList<Account> accountList,Map<String,Object> options);
	public SmartList<Account> findAccountWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countAccountWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countAccountWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String accountId, int version) throws Exception;
	public Account disconnectFromAll(String accountId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public TransactionDAO getTransactionDAO();
		
	public NameChangeEventDAO getNameChangeEventDAO();
		
	public AccountChangeDAO getAccountChangeDAO();
		
	
 	public SmartList<Account> requestCandidateAccountForTransactionAsFromAccount(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Account> requestCandidateAccountForTransactionAsToAccount(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Account> requestCandidateAccountForNameChangeEvent(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Account> requestCandidateAccountForAccountChange(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Account planToRemoveTransactionListAsFromAccount(Account account, String transactionIds[], Map<String,Object> options)throws Exception;


	//disconnect Account with change_request in Transaction
	public Account planToRemoveTransactionListAsFromAccountWithChangeRequest(Account account, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countTransactionListAsFromAccountWithChangeRequest(String accountId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	public Account planToRemoveTransactionListAsToAccount(Account account, String transactionIds[], Map<String,Object> options)throws Exception;


	//disconnect Account with change_request in Transaction
	public Account planToRemoveTransactionListAsToAccountWithChangeRequest(Account account, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countTransactionListAsToAccountWithChangeRequest(String accountId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	public Account planToRemoveNameChangeEventList(Account account, String nameChangeEventIds[], Map<String,Object> options)throws Exception;


	//disconnect Account with change_request in NameChangeEvent
	public Account planToRemoveNameChangeEventListWithChangeRequest(Account account, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countNameChangeEventListWithChangeRequest(String accountId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	public Account planToRemoveAccountChangeList(Account account, String accountChangeIds[], Map<String,Object> options)throws Exception;


	//disconnect Account with change_request in AccountChange
	public Account planToRemoveAccountChangeListWithChangeRequest(Account account, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countAccountChangeListWithChangeRequest(String accountId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Account> queryList(String sql, Object ... parmeters);
 
 	public SmartList<Account> findAccountByPlatform(String platformId, Map<String,Object> options);
 	public int countAccountByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countAccountByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Account> findAccountByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeAccountByPlatform(SmartList<Account> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Transaction的fromAccount的TransactionListAsFromAccount
	public SmartList<Transaction> loadOurTransactionListAsFromAccount(BankUserContext userContext, List<Account> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Transaction的toAccount的TransactionListAsToAccount
	public SmartList<Transaction> loadOurTransactionListAsToAccount(BankUserContext userContext, List<Account> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:NameChangeEvent的account的NameChangeEventList
	public SmartList<NameChangeEvent> loadOurNameChangeEventList(BankUserContext userContext, List<Account> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:AccountChange的account的AccountChangeList
	public SmartList<AccountChange> loadOurAccountChangeList(BankUserContext userContext, List<Account> us, Map<String,Object> options) throws Exception;
	
}


