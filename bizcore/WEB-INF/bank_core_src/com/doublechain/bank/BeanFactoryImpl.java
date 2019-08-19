
package com.doublechain.bank;
import java.util.Map;

import com.doublechain.bank.platform.Platform;
import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.transaction.Transaction;
import com.doublechain.bank.namechangeevent.NameChangeEvent;
import com.doublechain.bank.account.Account;
import com.doublechain.bank.accountchange.AccountChange;
import com.doublechain.bank.userdomain.UserDomain;
import com.doublechain.bank.userwhitelist.UserWhiteList;
import com.doublechain.bank.secuser.SecUser;
import com.doublechain.bank.secuserblocking.SecUserBlocking;
import com.doublechain.bank.userapp.UserApp;
import com.doublechain.bank.listaccess.ListAccess;
import com.doublechain.bank.objectaccess.ObjectAccess;
import com.doublechain.bank.loginhistory.LoginHistory;
import com.doublechain.bank.genericform.GenericForm;
import com.doublechain.bank.formmessage.FormMessage;
import com.doublechain.bank.formfieldmessage.FormFieldMessage;
import com.doublechain.bank.formfield.FormField;
import com.doublechain.bank.formaction.FormAction;

public class BeanFactoryImpl{


	public Platform createPlatform(Map<String,Object> options){
		return new Platform();
	}


	public ChangeRequest createChangeRequest(Map<String,Object> options){
		return new ChangeRequest();
	}


	public Transaction createTransaction(Map<String,Object> options){
		return new Transaction();
	}


	public NameChangeEvent createNameChangeEvent(Map<String,Object> options){
		return new NameChangeEvent();
	}


	public Account createAccount(Map<String,Object> options){
		return new Account();
	}


	public AccountChange createAccountChange(Map<String,Object> options){
		return new AccountChange();
	}


	public UserDomain createUserDomain(Map<String,Object> options){
		return new UserDomain();
	}


	public UserWhiteList createUserWhiteList(Map<String,Object> options){
		return new UserWhiteList();
	}


	public SecUser createSecUser(Map<String,Object> options){
		return new SecUser();
	}


	public SecUserBlocking createSecUserBlocking(Map<String,Object> options){
		return new SecUserBlocking();
	}


	public UserApp createUserApp(Map<String,Object> options){
		return new UserApp();
	}


	public ListAccess createListAccess(Map<String,Object> options){
		return new ListAccess();
	}


	public ObjectAccess createObjectAccess(Map<String,Object> options){
		return new ObjectAccess();
	}


	public LoginHistory createLoginHistory(Map<String,Object> options){
		return new LoginHistory();
	}


	public GenericForm createGenericForm(Map<String,Object> options){
		return new GenericForm();
	}


	public FormMessage createFormMessage(Map<String,Object> options){
		return new FormMessage();
	}


	public FormFieldMessage createFormFieldMessage(Map<String,Object> options){
		return new FormFieldMessage();
	}


	public FormField createFormField(Map<String,Object> options){
		return new FormField();
	}


	public FormAction createFormAction(Map<String,Object> options){
		return new FormAction();
	}





}







