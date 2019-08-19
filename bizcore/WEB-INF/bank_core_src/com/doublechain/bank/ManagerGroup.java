package com.doublechain.bank;


import com.doublechain.bank.platform.PlatformManager;

import com.doublechain.bank.changerequest.ChangeRequestManager;

import com.doublechain.bank.transaction.TransactionManager;

import com.doublechain.bank.namechangeevent.NameChangeEventManager;

import com.doublechain.bank.account.AccountManager;

import com.doublechain.bank.accountchange.AccountChangeManager;

import com.doublechain.bank.userdomain.UserDomainManager;

import com.doublechain.bank.userwhitelist.UserWhiteListManager;

import com.doublechain.bank.secuser.SecUserManager;

import com.doublechain.bank.secuserblocking.SecUserBlockingManager;

import com.doublechain.bank.userapp.UserAppManager;

import com.doublechain.bank.listaccess.ListAccessManager;

import com.doublechain.bank.objectaccess.ObjectAccessManager;

import com.doublechain.bank.loginhistory.LoginHistoryManager;

import com.doublechain.bank.genericform.GenericFormManager;

import com.doublechain.bank.formmessage.FormMessageManager;

import com.doublechain.bank.formfieldmessage.FormFieldMessageManager;

import com.doublechain.bank.formfield.FormFieldManager;

import com.doublechain.bank.formaction.FormActionManager;


public class ManagerGroup {

	protected PlatformManager platformManager;

	protected ChangeRequestManager changeRequestManager;

	protected TransactionManager transactionManager;

	protected NameChangeEventManager nameChangeEventManager;

	protected AccountManager accountManager;

	protected AccountChangeManager accountChangeManager;

	protected UserDomainManager userDomainManager;

	protected UserWhiteListManager userWhiteListManager;

	protected SecUserManager secUserManager;

	protected SecUserBlockingManager secUserBlockingManager;

	protected UserAppManager userAppManager;

	protected ListAccessManager listAccessManager;

	protected ObjectAccessManager objectAccessManager;

	protected LoginHistoryManager loginHistoryManager;

	protected GenericFormManager genericFormManager;

	protected FormMessageManager formMessageManager;

	protected FormFieldMessageManager formFieldMessageManager;

	protected FormFieldManager formFieldManager;

	protected FormActionManager formActionManager;

	

	public PlatformManager getPlatformManager(){
		return this.platformManager;
	}
	public void setPlatformManager(PlatformManager manager){
		this.platformManager = manager;
	}


	public ChangeRequestManager getChangeRequestManager(){
		return this.changeRequestManager;
	}
	public void setChangeRequestManager(ChangeRequestManager manager){
		this.changeRequestManager = manager;
	}


	public TransactionManager getTransactionManager(){
		return this.transactionManager;
	}
	public void setTransactionManager(TransactionManager manager){
		this.transactionManager = manager;
	}


	public NameChangeEventManager getNameChangeEventManager(){
		return this.nameChangeEventManager;
	}
	public void setNameChangeEventManager(NameChangeEventManager manager){
		this.nameChangeEventManager = manager;
	}


	public AccountManager getAccountManager(){
		return this.accountManager;
	}
	public void setAccountManager(AccountManager manager){
		this.accountManager = manager;
	}


	public AccountChangeManager getAccountChangeManager(){
		return this.accountChangeManager;
	}
	public void setAccountChangeManager(AccountChangeManager manager){
		this.accountChangeManager = manager;
	}


	public UserDomainManager getUserDomainManager(){
		return this.userDomainManager;
	}
	public void setUserDomainManager(UserDomainManager manager){
		this.userDomainManager = manager;
	}


	public UserWhiteListManager getUserWhiteListManager(){
		return this.userWhiteListManager;
	}
	public void setUserWhiteListManager(UserWhiteListManager manager){
		this.userWhiteListManager = manager;
	}


	public SecUserManager getSecUserManager(){
		return this.secUserManager;
	}
	public void setSecUserManager(SecUserManager manager){
		this.secUserManager = manager;
	}


	public SecUserBlockingManager getSecUserBlockingManager(){
		return this.secUserBlockingManager;
	}
	public void setSecUserBlockingManager(SecUserBlockingManager manager){
		this.secUserBlockingManager = manager;
	}


	public UserAppManager getUserAppManager(){
		return this.userAppManager;
	}
	public void setUserAppManager(UserAppManager manager){
		this.userAppManager = manager;
	}


	public ListAccessManager getListAccessManager(){
		return this.listAccessManager;
	}
	public void setListAccessManager(ListAccessManager manager){
		this.listAccessManager = manager;
	}


	public ObjectAccessManager getObjectAccessManager(){
		return this.objectAccessManager;
	}
	public void setObjectAccessManager(ObjectAccessManager manager){
		this.objectAccessManager = manager;
	}


	public LoginHistoryManager getLoginHistoryManager(){
		return this.loginHistoryManager;
	}
	public void setLoginHistoryManager(LoginHistoryManager manager){
		this.loginHistoryManager = manager;
	}


	public GenericFormManager getGenericFormManager(){
		return this.genericFormManager;
	}
	public void setGenericFormManager(GenericFormManager manager){
		this.genericFormManager = manager;
	}


	public FormMessageManager getFormMessageManager(){
		return this.formMessageManager;
	}
	public void setFormMessageManager(FormMessageManager manager){
		this.formMessageManager = manager;
	}


	public FormFieldMessageManager getFormFieldMessageManager(){
		return this.formFieldMessageManager;
	}
	public void setFormFieldMessageManager(FormFieldMessageManager manager){
		this.formFieldMessageManager = manager;
	}


	public FormFieldManager getFormFieldManager(){
		return this.formFieldManager;
	}
	public void setFormFieldManager(FormFieldManager manager){
		this.formFieldManager = manager;
	}


	public FormActionManager getFormActionManager(){
		return this.formActionManager;
	}
	public void setFormActionManager(FormActionManager manager){
		this.formActionManager = manager;
	}


}






