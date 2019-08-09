
package com.doublechain.bank.accountchange;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BankUserContext;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;

public interface AccountChangeManager{

		

	public AccountChange createAccountChange(BankUserContext userContext, String name, String accountId, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, String changeRequestId) throws Exception;	
	public AccountChange updateAccountChange(BankUserContext userContext,String accountChangeId, int accountChangeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public AccountChange loadAccountChange(BankUserContext userContext, String accountChangeId, String [] tokensExpr) throws Exception;
	public AccountChange internalSaveAccountChange(BankUserContext userContext, AccountChange accountChange) throws Exception;
	public AccountChange internalSaveAccountChange(BankUserContext userContext, AccountChange accountChange,Map<String,Object>option) throws Exception;
	
	public AccountChange transferToAnotherAccount(BankUserContext userContext, String accountChangeId, String anotherAccountId)  throws Exception;
 	public AccountChange transferToAnotherChangeRequest(BankUserContext userContext, String accountChangeId, String anotherChangeRequestId)  throws Exception;
 

	public void delete(BankUserContext userContext, String accountChangeId, int version) throws Exception;
	public int deleteAll(BankUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(BankUserContext userContext, AccountChange newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


