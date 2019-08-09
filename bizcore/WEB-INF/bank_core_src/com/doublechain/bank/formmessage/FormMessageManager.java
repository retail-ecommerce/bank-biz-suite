
package com.doublechain.bank.formmessage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BankUserContext;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;

public interface FormMessageManager{

		

	public FormMessage createFormMessage(BankUserContext userContext, String title, String formId, String level) throws Exception;	
	public FormMessage updateFormMessage(BankUserContext userContext,String formMessageId, int formMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormMessage loadFormMessage(BankUserContext userContext, String formMessageId, String [] tokensExpr) throws Exception;
	public FormMessage internalSaveFormMessage(BankUserContext userContext, FormMessage formMessage) throws Exception;
	public FormMessage internalSaveFormMessage(BankUserContext userContext, FormMessage formMessage,Map<String,Object>option) throws Exception;
	
	public FormMessage transferToAnotherForm(BankUserContext userContext, String formMessageId, String anotherFormId)  throws Exception;
 

	public void delete(BankUserContext userContext, String formMessageId, int version) throws Exception;
	public int deleteAll(BankUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(BankUserContext userContext, FormMessage newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


