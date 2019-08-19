
package com.doublechain.bank.namechangeevent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BankUserContext;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;

public interface NameChangeEventManager{

		

	public NameChangeEvent createNameChangeEvent(BankUserContext userContext, String name, String accountId) throws Exception;	
	public NameChangeEvent updateNameChangeEvent(BankUserContext userContext,String nameChangeEventId, int nameChangeEventVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public NameChangeEvent loadNameChangeEvent(BankUserContext userContext, String nameChangeEventId, String [] tokensExpr) throws Exception;
	public NameChangeEvent internalSaveNameChangeEvent(BankUserContext userContext, NameChangeEvent nameChangeEvent) throws Exception;
	public NameChangeEvent internalSaveNameChangeEvent(BankUserContext userContext, NameChangeEvent nameChangeEvent,Map<String,Object>option) throws Exception;
	
	public NameChangeEvent transferToAnotherAccount(BankUserContext userContext, String nameChangeEventId, String anotherAccountId)  throws Exception;
 	public NameChangeEvent requestChange(BankUserContext userContext, String nameChangeEventId, String name, String platformId
)  throws Exception;


	public void delete(BankUserContext userContext, String nameChangeEventId, int version) throws Exception;
	public int deleteAll(BankUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(BankUserContext userContext, NameChangeEvent newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


