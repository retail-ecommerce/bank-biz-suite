
package com.doublechain.bank.platform;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BankUserContext;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;

public interface PlatformManager{

		

	public Platform createPlatform(BankUserContext userContext, String name, String founder, String description) throws Exception;	
	public Platform updatePlatform(BankUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Platform loadPlatform(BankUserContext userContext, String platformId, String [] tokensExpr) throws Exception;
	public Platform internalSavePlatform(BankUserContext userContext, Platform platform) throws Exception;
	public Platform internalSavePlatform(BankUserContext userContext, Platform platform,Map<String,Object>option) throws Exception;
	


	public void delete(BankUserContext userContext, String platformId, int version) throws Exception;
	public int deleteAll(BankUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(BankUserContext userContext, Platform newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ChangeRequestManager getChangeRequestManager(BankUserContext userContext, String platformId, String name ,String [] tokensExpr)  throws Exception;
	
	public  Platform addChangeRequest(BankUserContext userContext, String platformId, String name , String [] tokensExpr)  throws Exception;
	public  Platform removeChangeRequest(BankUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateChangeRequest(BankUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  AccountManager getAccountManager(BankUserContext userContext, String platformId, String name, BigDecimal balance ,String [] tokensExpr)  throws Exception;
	
	public  Platform addAccount(BankUserContext userContext, String platformId, String name, BigDecimal balance , String [] tokensExpr)  throws Exception;
	public  Platform removeAccount(BankUserContext userContext, String platformId, String accountId, int accountVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateAccount(BankUserContext userContext, String platformId, String accountId, int accountVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


