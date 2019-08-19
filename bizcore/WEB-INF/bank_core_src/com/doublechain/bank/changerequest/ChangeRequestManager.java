
package com.doublechain.bank.changerequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BankUserContext;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;

public interface ChangeRequestManager{

		

	public ChangeRequest createChangeRequest(BankUserContext userContext, String name, String platformId) throws Exception;	
	public ChangeRequest updateChangeRequest(BankUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ChangeRequest loadChangeRequest(BankUserContext userContext, String changeRequestId, String [] tokensExpr) throws Exception;
	public ChangeRequest internalSaveChangeRequest(BankUserContext userContext, ChangeRequest changeRequest) throws Exception;
	public ChangeRequest internalSaveChangeRequest(BankUserContext userContext, ChangeRequest changeRequest,Map<String,Object>option) throws Exception;
	
	public ChangeRequest transferToAnotherPlatform(BankUserContext userContext, String changeRequestId, String anotherPlatformId)  throws Exception;
 

	public void delete(BankUserContext userContext, String changeRequestId, int version) throws Exception;
	public int deleteAll(BankUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(BankUserContext userContext, ChangeRequest newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  TransactionManager getTransactionManager(BankUserContext userContext, String changeRequestId, String name, String fromAccountId, String toAccountId, BigDecimal amount, String type ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addTransaction(BankUserContext userContext, String changeRequestId, String name, String fromAccountId, String toAccountId, BigDecimal amount, String type , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeTransaction(BankUserContext userContext, String changeRequestId, String transactionId, int transactionVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateTransaction(BankUserContext userContext, String changeRequestId, String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  NameChangeEventManager getNameChangeEventManager(BankUserContext userContext, String changeRequestId, String name, String accountId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addNameChangeEvent(BankUserContext userContext, String changeRequestId, String name, String accountId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeNameChangeEvent(BankUserContext userContext, String changeRequestId, String nameChangeEventId, int nameChangeEventVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateNameChangeEvent(BankUserContext userContext, String changeRequestId, String nameChangeEventId, int nameChangeEventVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  AccountChangeManager getAccountChangeManager(BankUserContext userContext, String changeRequestId, String name, String accountId, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addAccountChange(BankUserContext userContext, String changeRequestId, String name, String accountId, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeAccountChange(BankUserContext userContext, String changeRequestId, String accountChangeId, int accountChangeVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateAccountChange(BankUserContext userContext, String changeRequestId, String accountChangeId, int accountChangeVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


