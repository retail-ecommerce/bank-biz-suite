
package com.doublechain.bank.genericform;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;
import com.doublechain.bank.MultipleAccessKey;
import com.doublechain.bank.BankUserContext;

import com.doublechain.bank.formaction.FormAction;
import com.doublechain.bank.formfield.FormField;
import com.doublechain.bank.formfieldmessage.FormFieldMessage;
import com.doublechain.bank.formmessage.FormMessage;

import com.doublechain.bank.formfieldmessage.FormFieldMessageDAO;
import com.doublechain.bank.formfield.FormFieldDAO;
import com.doublechain.bank.formmessage.FormMessageDAO;
import com.doublechain.bank.formaction.FormActionDAO;


public interface GenericFormDAO{

	
	public GenericForm load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<GenericForm> genericFormList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public GenericForm present(GenericForm genericForm,Map<String,Object> options) throws Exception;
	public GenericForm clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public GenericForm save(GenericForm genericForm,Map<String,Object> options);
	public SmartList<GenericForm> saveGenericFormList(SmartList<GenericForm> genericFormList,Map<String,Object> options);
	public SmartList<GenericForm> removeGenericFormList(SmartList<GenericForm> genericFormList,Map<String,Object> options);
	public SmartList<GenericForm> findGenericFormWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countGenericFormWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countGenericFormWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String genericFormId, int version) throws Exception;
	public GenericForm disconnectFromAll(String genericFormId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public FormMessageDAO getFormMessageDAO();
		
	public FormFieldMessageDAO getFormFieldMessageDAO();
		
	public FormFieldDAO getFormFieldDAO();
		
	public FormActionDAO getFormActionDAO();
		
	
 	public SmartList<GenericForm> requestCandidateGenericFormForFormMessage(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<GenericForm> requestCandidateGenericFormForFormFieldMessage(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<GenericForm> requestCandidateGenericFormForFormField(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<GenericForm> requestCandidateGenericFormForFormAction(BankUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public GenericForm planToRemoveFormMessageList(GenericForm genericForm, String formMessageIds[], Map<String,Object> options)throws Exception;


	public GenericForm planToRemoveFormFieldMessageList(GenericForm genericForm, String formFieldMessageIds[], Map<String,Object> options)throws Exception;


	public GenericForm planToRemoveFormFieldList(GenericForm genericForm, String formFieldIds[], Map<String,Object> options)throws Exception;


	public GenericForm planToRemoveFormActionList(GenericForm genericForm, String formActionIds[], Map<String,Object> options)throws Exception;


	
	public SmartList<GenericForm> queryList(String sql, Object ... parmeters);

	// 需要一个加载引用我的对象的enhance方法:FormMessage的form的FormMessageList
	public SmartList<FormMessage> loadOurFormMessageList(BankUserContext userContext, List<GenericForm> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:FormFieldMessage的form的FormFieldMessageList
	public SmartList<FormFieldMessage> loadOurFormFieldMessageList(BankUserContext userContext, List<GenericForm> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:FormField的form的FormFieldList
	public SmartList<FormField> loadOurFormFieldList(BankUserContext userContext, List<GenericForm> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:FormAction的form的FormActionList
	public SmartList<FormAction> loadOurFormActionList(BankUserContext userContext, List<GenericForm> us, Map<String,Object> options) throws Exception;
	
}


