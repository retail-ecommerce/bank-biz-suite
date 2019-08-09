
package com.doublechain.bank.formfield;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BankUserContext;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;

public interface FormFieldManager{

		

	public FormField createFormField(BankUserContext userContext, String label, String localeKey, String parameterName, String type, String formId, String placeholder, String defaultValue, String description, String fieldGroup, String minimumValue, String maximumValue, boolean required, boolean disabled, boolean customRendering, String candidateValues, String suggestValues) throws Exception;	
	public FormField updateFormField(BankUserContext userContext,String formFieldId, int formFieldVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormField loadFormField(BankUserContext userContext, String formFieldId, String [] tokensExpr) throws Exception;
	public FormField internalSaveFormField(BankUserContext userContext, FormField formField) throws Exception;
	public FormField internalSaveFormField(BankUserContext userContext, FormField formField,Map<String,Object>option) throws Exception;
	
	public FormField transferToAnotherForm(BankUserContext userContext, String formFieldId, String anotherFormId)  throws Exception;
 

	public void delete(BankUserContext userContext, String formFieldId, int version) throws Exception;
	public int deleteAll(BankUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(BankUserContext userContext, FormField newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


