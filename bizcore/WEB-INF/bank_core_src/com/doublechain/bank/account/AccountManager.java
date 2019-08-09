
package com.doublechain.bank.account;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BankUserContext;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;

public interface AccountManager{

		
	

	public Account loadAccountWithName(BankUserContext userContext, String name, Map<String,Object>tokens) throws Exception;

	 

	public Account createAccount(BankUserContext userContext, String name, BigDecimal balance, String platformId) throws Exception;	
	public Account updateAccount(BankUserContext userContext,String accountId, int accountVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Account loadAccount(BankUserContext userContext, String accountId, String [] tokensExpr) throws Exception;
	public Account internalSaveAccount(BankUserContext userContext, Account account) throws Exception;
	public Account internalSaveAccount(BankUserContext userContext, Account account,Map<String,Object>option) throws Exception;
	
	public Account transferToAnotherPlatform(BankUserContext userContext, String accountId, String anotherPlatformId)  throws Exception;
 

	public void delete(BankUserContext userContext, String accountId, int version) throws Exception;
	public int deleteAll(BankUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(BankUserContext userContext, Account newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  TransactionManager getTransactionManager(BankUserContext userContext, String accountId, String name, BigDecimal amount, String type, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  Account addTransactionAsFromAccount(BankUserContext userContext, String accountId, String name, BigDecimal amount, String type, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  Account removeTransactionAsFromAccount(BankUserContext userContext, String accountId, String transactionId, int transactionVersion,String [] tokensExpr)  throws Exception;
	public  Account updateTransactionAsFromAccount(BankUserContext userContext, String accountId, String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  TransactionManager getTransactionManager(BankUserContext userContext, String accountId, String name, BigDecimal amount, String type, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  Account addTransactionAsToAccount(BankUserContext userContext, String accountId, String name, BigDecimal amount, String type, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  Account removeTransactionAsToAccount(BankUserContext userContext, String accountId, String transactionId, int transactionVersion,String [] tokensExpr)  throws Exception;
	public  Account updateTransactionAsToAccount(BankUserContext userContext, String accountId, String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  AccountChangeManager getAccountChangeManager(BankUserContext userContext, String accountId, String name, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  Account addAccountChange(BankUserContext userContext, String accountId, String name, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  Account removeAccountChange(BankUserContext userContext, String accountId, String accountChangeId, int accountChangeVersion,String [] tokensExpr)  throws Exception;
	public  Account updateAccountChange(BankUserContext userContext, String accountId, String accountChangeId, int accountChangeVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


