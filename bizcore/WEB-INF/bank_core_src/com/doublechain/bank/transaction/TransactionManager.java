
package com.doublechain.bank.transaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BankUserContext;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;

public interface TransactionManager{

		

	public Transaction createTransaction(BankUserContext userContext, String name, String fromAccountId, String toAccountId, BigDecimal amount, String type) throws Exception;	
	public Transaction updateTransaction(BankUserContext userContext,String transactionId, int transactionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Transaction loadTransaction(BankUserContext userContext, String transactionId, String [] tokensExpr) throws Exception;
	public Transaction internalSaveTransaction(BankUserContext userContext, Transaction transaction) throws Exception;
	public Transaction internalSaveTransaction(BankUserContext userContext, Transaction transaction,Map<String,Object>option) throws Exception;
	
	public Transaction transferToAnotherFromAccount(BankUserContext userContext, String transactionId, String anotherFromAccountId)  throws Exception;
 	public Transaction transferToAnotherToAccount(BankUserContext userContext, String transactionId, String anotherToAccountId)  throws Exception;
 	public Transaction requestChange(BankUserContext userContext, String transactionId, String name, String platformId
)  throws Exception;


	public void delete(BankUserContext userContext, String transactionId, int version) throws Exception;
	public int deleteAll(BankUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(BankUserContext userContext, Transaction newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


