package com.doublechain.bank;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;

public class BankObjectChecker extends BankChecker{
	public BankChecker checkPlatformAsObject(BaseEntity platformAsBaseEntity){

		checkIdOfPlatform((String)platformAsBaseEntity.propertyOf("id"));
		checkNameOfPlatform((String)platformAsBaseEntity.propertyOf("name"));
		checkVersionOfPlatform((int)platformAsBaseEntity.propertyOf("version"));
		checkChangeRequestListOfPlatform((List<BaseEntity>)platformAsBaseEntity.propertyOf("changeRequestList"));
		checkAccountListOfPlatform((List<BaseEntity>)platformAsBaseEntity.propertyOf("accountList"));
		return this;

	}

	public BankChecker checkChangeRequestAsObject(BaseEntity changeRequestAsBaseEntity){

		checkIdOfChangeRequest((String)changeRequestAsBaseEntity.propertyOf("id"));
		checkNameOfChangeRequest((String)changeRequestAsBaseEntity.propertyOf("name"));
		checkPlatformOfChangeRequest((BaseEntity)changeRequestAsBaseEntity.propertyOf("platform"));
		checkVersionOfChangeRequest((int)changeRequestAsBaseEntity.propertyOf("version"));
		checkTransactionListOfChangeRequest((List<BaseEntity>)changeRequestAsBaseEntity.propertyOf("transactionList"));
		checkAccountChangeListOfChangeRequest((List<BaseEntity>)changeRequestAsBaseEntity.propertyOf("accountChangeList"));
		return this;

	}

	public BankChecker checkTransactionAsObject(BaseEntity transactionAsBaseEntity){

		checkIdOfTransaction((String)transactionAsBaseEntity.propertyOf("id"));
		checkNameOfTransaction((String)transactionAsBaseEntity.propertyOf("name"));
		checkFromAccountOfTransaction((BaseEntity)transactionAsBaseEntity.propertyOf("fromAccount"));
		checkToAccountOfTransaction((BaseEntity)transactionAsBaseEntity.propertyOf("toAccount"));
		checkAmountOfTransaction((BigDecimal)transactionAsBaseEntity.propertyOf("amount"));
		checkTypeOfTransaction((String)transactionAsBaseEntity.propertyOf("type"));
		checkChangeRequestOfTransaction((BaseEntity)transactionAsBaseEntity.propertyOf("changeRequest"));
		checkVersionOfTransaction((int)transactionAsBaseEntity.propertyOf("version"));
		return this;

	}

	public BankChecker checkAccountAsObject(BaseEntity accountAsBaseEntity){

		checkIdOfAccount((String)accountAsBaseEntity.propertyOf("id"));
		checkNameOfAccount((String)accountAsBaseEntity.propertyOf("name"));
		checkBalanceOfAccount((BigDecimal)accountAsBaseEntity.propertyOf("balance"));
		checkPlatformOfAccount((BaseEntity)accountAsBaseEntity.propertyOf("platform"));
		checkVersionOfAccount((int)accountAsBaseEntity.propertyOf("version"));
		checkTransactionListAsFromAccountOfAccount((List<BaseEntity>)accountAsBaseEntity.propertyOf("transactionListAsFromAccount"));
		checkTransactionListAsToAccountOfAccount((List<BaseEntity>)accountAsBaseEntity.propertyOf("transactionListAsToAccount"));
		checkAccountChangeListOfAccount((List<BaseEntity>)accountAsBaseEntity.propertyOf("accountChangeList"));
		return this;

	}

	public BankChecker checkAccountChangeAsObject(BaseEntity accountChangeAsBaseEntity){

		checkIdOfAccountChange((String)accountChangeAsBaseEntity.propertyOf("id"));
		checkNameOfAccountChange((String)accountChangeAsBaseEntity.propertyOf("name"));
		checkAccountOfAccountChange((BaseEntity)accountChangeAsBaseEntity.propertyOf("account"));
		checkPreviousBalanceOfAccountChange((BigDecimal)accountChangeAsBaseEntity.propertyOf("previousBalance"));
		checkTypeOfAccountChange((String)accountChangeAsBaseEntity.propertyOf("type"));
		checkAmountOfAccountChange((BigDecimal)accountChangeAsBaseEntity.propertyOf("amount"));
		checkCurrentBalanceOfAccountChange((BigDecimal)accountChangeAsBaseEntity.propertyOf("currentBalance"));
		checkChangeRequestOfAccountChange((BaseEntity)accountChangeAsBaseEntity.propertyOf("changeRequest"));
		checkVersionOfAccountChange((int)accountChangeAsBaseEntity.propertyOf("version"));
		return this;

	}

	public BankChecker checkUserDomainAsObject(BaseEntity userDomainAsBaseEntity){

		checkIdOfUserDomain((String)userDomainAsBaseEntity.propertyOf("id"));
		checkNameOfUserDomain((String)userDomainAsBaseEntity.propertyOf("name"));
		checkVersionOfUserDomain((int)userDomainAsBaseEntity.propertyOf("version"));
		checkUserWhiteListListOfUserDomain((List<BaseEntity>)userDomainAsBaseEntity.propertyOf("userWhiteListList"));
		checkSecUserListOfUserDomain((List<BaseEntity>)userDomainAsBaseEntity.propertyOf("secUserList"));
		return this;

	}

	public BankChecker checkUserWhiteListAsObject(BaseEntity userWhiteListAsBaseEntity){

		checkIdOfUserWhiteList((String)userWhiteListAsBaseEntity.propertyOf("id"));
		checkUserIdentityOfUserWhiteList((String)userWhiteListAsBaseEntity.propertyOf("userIdentity"));
		checkUserSpecialFunctionsOfUserWhiteList((String)userWhiteListAsBaseEntity.propertyOf("userSpecialFunctions"));
		checkDomainOfUserWhiteList((BaseEntity)userWhiteListAsBaseEntity.propertyOf("domain"));
		checkVersionOfUserWhiteList((int)userWhiteListAsBaseEntity.propertyOf("version"));
		return this;

	}

	public BankChecker checkSecUserAsObject(BaseEntity secUserAsBaseEntity){

		checkIdOfSecUser((String)secUserAsBaseEntity.propertyOf("id"));
		checkLoginOfSecUser((String)secUserAsBaseEntity.propertyOf("login"));
		checkMobileOfSecUser((String)secUserAsBaseEntity.propertyOf("mobile"));
		checkEmailOfSecUser((String)secUserAsBaseEntity.propertyOf("email"));
		checkPwdOfSecUser((String)secUserAsBaseEntity.propertyOf("pwd"));
		checkWeixinOpenidOfSecUser((String)secUserAsBaseEntity.propertyOf("weixinOpenid"));
		checkWeixinAppidOfSecUser((String)secUserAsBaseEntity.propertyOf("weixinAppid"));
		checkAccessTokenOfSecUser((String)secUserAsBaseEntity.propertyOf("accessToken"));
		checkVerificationCodeOfSecUser((int)secUserAsBaseEntity.propertyOf("verificationCode"));
		checkVerificationCodeExpireOfSecUser((DateTime)secUserAsBaseEntity.propertyOf("verificationCodeExpire"));
		checkLastLoginTimeOfSecUser((DateTime)secUserAsBaseEntity.propertyOf("lastLoginTime"));
		checkDomainOfSecUser((BaseEntity)secUserAsBaseEntity.propertyOf("domain"));
		checkVersionOfSecUser((int)secUserAsBaseEntity.propertyOf("version"));
		checkUserAppListOfSecUser((List<BaseEntity>)secUserAsBaseEntity.propertyOf("userAppList"));
		checkLoginHistoryListOfSecUser((List<BaseEntity>)secUserAsBaseEntity.propertyOf("loginHistoryList"));
		return this;

	}

	public BankChecker checkSecUserBlockingAsObject(BaseEntity secUserBlockingAsBaseEntity){

		checkIdOfSecUserBlocking((String)secUserBlockingAsBaseEntity.propertyOf("id"));
		checkWhoOfSecUserBlocking((String)secUserBlockingAsBaseEntity.propertyOf("who"));
		checkCommentsOfSecUserBlocking((String)secUserBlockingAsBaseEntity.propertyOf("comments"));
		checkVersionOfSecUserBlocking((int)secUserBlockingAsBaseEntity.propertyOf("version"));
		checkSecUserListOfSecUserBlocking((List<BaseEntity>)secUserBlockingAsBaseEntity.propertyOf("secUserList"));
		return this;

	}

	public BankChecker checkUserAppAsObject(BaseEntity userAppAsBaseEntity){

		checkIdOfUserApp((String)userAppAsBaseEntity.propertyOf("id"));
		checkTitleOfUserApp((String)userAppAsBaseEntity.propertyOf("title"));
		checkSecUserOfUserApp((BaseEntity)userAppAsBaseEntity.propertyOf("secUser"));
		checkAppIconOfUserApp((String)userAppAsBaseEntity.propertyOf("appIcon"));
		checkFullAccessOfUserApp((boolean)userAppAsBaseEntity.propertyOf("fullAccess"));
		checkPermissionOfUserApp((String)userAppAsBaseEntity.propertyOf("permission"));
		checkObjectTypeOfUserApp((String)userAppAsBaseEntity.propertyOf("objectType"));
		checkObjectIdOfUserApp((String)userAppAsBaseEntity.propertyOf("objectId"));
		checkLocationOfUserApp((String)userAppAsBaseEntity.propertyOf("location"));
		checkVersionOfUserApp((int)userAppAsBaseEntity.propertyOf("version"));
		checkListAccessListOfUserApp((List<BaseEntity>)userAppAsBaseEntity.propertyOf("listAccessList"));
		checkObjectAccessListOfUserApp((List<BaseEntity>)userAppAsBaseEntity.propertyOf("objectAccessList"));
		return this;

	}

	public BankChecker checkListAccessAsObject(BaseEntity listAccessAsBaseEntity){

		checkIdOfListAccess((String)listAccessAsBaseEntity.propertyOf("id"));
		checkNameOfListAccess((String)listAccessAsBaseEntity.propertyOf("name"));
		checkInternalNameOfListAccess((String)listAccessAsBaseEntity.propertyOf("internalName"));
		checkReadPermissionOfListAccess((boolean)listAccessAsBaseEntity.propertyOf("readPermission"));
		checkCreatePermissionOfListAccess((boolean)listAccessAsBaseEntity.propertyOf("createPermission"));
		checkDeletePermissionOfListAccess((boolean)listAccessAsBaseEntity.propertyOf("deletePermission"));
		checkUpdatePermissionOfListAccess((boolean)listAccessAsBaseEntity.propertyOf("updatePermission"));
		checkExecutionPermissionOfListAccess((boolean)listAccessAsBaseEntity.propertyOf("executionPermission"));
		checkAppOfListAccess((BaseEntity)listAccessAsBaseEntity.propertyOf("app"));
		checkVersionOfListAccess((int)listAccessAsBaseEntity.propertyOf("version"));
		return this;

	}

	public BankChecker checkObjectAccessAsObject(BaseEntity objectAccessAsBaseEntity){

		checkIdOfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("id"));
		checkNameOfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("name"));
		checkObjectTypeOfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("objectType"));
		checkList1OfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("list1"));
		checkList2OfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("list2"));
		checkList3OfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("list3"));
		checkList4OfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("list4"));
		checkList5OfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("list5"));
		checkList6OfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("list6"));
		checkList7OfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("list7"));
		checkList8OfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("list8"));
		checkList9OfObjectAccess((String)objectAccessAsBaseEntity.propertyOf("list9"));
		checkAppOfObjectAccess((BaseEntity)objectAccessAsBaseEntity.propertyOf("app"));
		checkVersionOfObjectAccess((int)objectAccessAsBaseEntity.propertyOf("version"));
		return this;

	}

	public BankChecker checkLoginHistoryAsObject(BaseEntity loginHistoryAsBaseEntity){

		checkIdOfLoginHistory((String)loginHistoryAsBaseEntity.propertyOf("id"));
		checkFromIpOfLoginHistory((String)loginHistoryAsBaseEntity.propertyOf("fromIp"));
		checkDescriptionOfLoginHistory((String)loginHistoryAsBaseEntity.propertyOf("description"));
		checkSecUserOfLoginHistory((BaseEntity)loginHistoryAsBaseEntity.propertyOf("secUser"));
		checkVersionOfLoginHistory((int)loginHistoryAsBaseEntity.propertyOf("version"));
		return this;

	}

	public BankChecker checkGenericFormAsObject(BaseEntity genericFormAsBaseEntity){

		checkIdOfGenericForm((String)genericFormAsBaseEntity.propertyOf("id"));
		checkTitleOfGenericForm((String)genericFormAsBaseEntity.propertyOf("title"));
		checkDescriptionOfGenericForm((String)genericFormAsBaseEntity.propertyOf("description"));
		checkVersionOfGenericForm((int)genericFormAsBaseEntity.propertyOf("version"));
		checkFormMessageListOfGenericForm((List<BaseEntity>)genericFormAsBaseEntity.propertyOf("formMessageList"));
		checkFormFieldMessageListOfGenericForm((List<BaseEntity>)genericFormAsBaseEntity.propertyOf("formFieldMessageList"));
		checkFormFieldListOfGenericForm((List<BaseEntity>)genericFormAsBaseEntity.propertyOf("formFieldList"));
		checkFormActionListOfGenericForm((List<BaseEntity>)genericFormAsBaseEntity.propertyOf("formActionList"));
		return this;

	}

	public BankChecker checkFormMessageAsObject(BaseEntity formMessageAsBaseEntity){

		checkIdOfFormMessage((String)formMessageAsBaseEntity.propertyOf("id"));
		checkTitleOfFormMessage((String)formMessageAsBaseEntity.propertyOf("title"));
		checkFormOfFormMessage((BaseEntity)formMessageAsBaseEntity.propertyOf("form"));
		checkLevelOfFormMessage((String)formMessageAsBaseEntity.propertyOf("level"));
		checkVersionOfFormMessage((int)formMessageAsBaseEntity.propertyOf("version"));
		return this;

	}

	public BankChecker checkFormFieldMessageAsObject(BaseEntity formFieldMessageAsBaseEntity){

		checkIdOfFormFieldMessage((String)formFieldMessageAsBaseEntity.propertyOf("id"));
		checkTitleOfFormFieldMessage((String)formFieldMessageAsBaseEntity.propertyOf("title"));
		checkParameterNameOfFormFieldMessage((String)formFieldMessageAsBaseEntity.propertyOf("parameterName"));
		checkFormOfFormFieldMessage((BaseEntity)formFieldMessageAsBaseEntity.propertyOf("form"));
		checkLevelOfFormFieldMessage((String)formFieldMessageAsBaseEntity.propertyOf("level"));
		checkVersionOfFormFieldMessage((int)formFieldMessageAsBaseEntity.propertyOf("version"));
		return this;

	}

	public BankChecker checkFormFieldAsObject(BaseEntity formFieldAsBaseEntity){

		checkIdOfFormField((String)formFieldAsBaseEntity.propertyOf("id"));
		checkLabelOfFormField((String)formFieldAsBaseEntity.propertyOf("label"));
		checkLocaleKeyOfFormField((String)formFieldAsBaseEntity.propertyOf("localeKey"));
		checkParameterNameOfFormField((String)formFieldAsBaseEntity.propertyOf("parameterName"));
		checkTypeOfFormField((String)formFieldAsBaseEntity.propertyOf("type"));
		checkFormOfFormField((BaseEntity)formFieldAsBaseEntity.propertyOf("form"));
		checkPlaceholderOfFormField((String)formFieldAsBaseEntity.propertyOf("placeholder"));
		checkDefaultValueOfFormField((String)formFieldAsBaseEntity.propertyOf("defaultValue"));
		checkDescriptionOfFormField((String)formFieldAsBaseEntity.propertyOf("description"));
		checkFieldGroupOfFormField((String)formFieldAsBaseEntity.propertyOf("fieldGroup"));
		checkMinimumValueOfFormField((String)formFieldAsBaseEntity.propertyOf("minimumValue"));
		checkMaximumValueOfFormField((String)formFieldAsBaseEntity.propertyOf("maximumValue"));
		checkRequiredOfFormField((boolean)formFieldAsBaseEntity.propertyOf("required"));
		checkDisabledOfFormField((boolean)formFieldAsBaseEntity.propertyOf("disabled"));
		checkCustomRenderingOfFormField((boolean)formFieldAsBaseEntity.propertyOf("customRendering"));
		checkCandidateValuesOfFormField((String)formFieldAsBaseEntity.propertyOf("candidateValues"));
		checkSuggestValuesOfFormField((String)formFieldAsBaseEntity.propertyOf("suggestValues"));
		checkVersionOfFormField((int)formFieldAsBaseEntity.propertyOf("version"));
		return this;

	}

	public BankChecker checkFormActionAsObject(BaseEntity formActionAsBaseEntity){

		checkIdOfFormAction((String)formActionAsBaseEntity.propertyOf("id"));
		checkLabelOfFormAction((String)formActionAsBaseEntity.propertyOf("label"));
		checkLocaleKeyOfFormAction((String)formActionAsBaseEntity.propertyOf("localeKey"));
		checkActionKeyOfFormAction((String)formActionAsBaseEntity.propertyOf("actionKey"));
		checkLevelOfFormAction((String)formActionAsBaseEntity.propertyOf("level"));
		checkUrlOfFormAction((String)formActionAsBaseEntity.propertyOf("url"));
		checkFormOfFormAction((BaseEntity)formActionAsBaseEntity.propertyOf("form"));
		checkVersionOfFormAction((int)formActionAsBaseEntity.propertyOf("version"));
		return this;

	}


	public BankChecker checkChangeRequestListOfPlatform(List<BaseEntity> changeRequestList){
		changeRequestList.stream().map(changeRequest->this.checkChangeRequestAsObject(changeRequest));
		return this;
	}

	public BankChecker checkAccountListOfPlatform(List<BaseEntity> accountList){
		accountList.stream().map(account->this.checkAccountAsObject(account));
		return this;
	}

	public BankChecker checkTransactionListOfChangeRequest(List<BaseEntity> transactionList){
		transactionList.stream().map(transaction->this.checkTransactionAsObject(transaction));
		return this;
	}

	public BankChecker checkAccountChangeListOfChangeRequest(List<BaseEntity> accountChangeList){
		accountChangeList.stream().map(accountChange->this.checkAccountChangeAsObject(accountChange));
		return this;
	}

	public static final String PLATFORM_OF_CHANGE_REQUEST = "change_request.platform";


	public BankChecker checkPlatformOfChangeRequest(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_CHANGE_REQUEST);
			return this;
		}
		checkPlatformAsObject(platformAsBaseEntity);
		return this;
	}


	public static final String FROM_ACCOUNT_OF_TRANSACTION = "transaction.from_account";


	public BankChecker checkFromAccountOfTransaction(BaseEntity fromAccountAsBaseEntity){

		if(fromAccountAsBaseEntity == null){
			checkBaseEntityReference(fromAccountAsBaseEntity,true,FROM_ACCOUNT_OF_TRANSACTION);
			return this;
		}
		checkAccountAsObject(fromAccountAsBaseEntity);
		return this;
	}


	public static final String TO_ACCOUNT_OF_TRANSACTION = "transaction.to_account";


	public BankChecker checkToAccountOfTransaction(BaseEntity toAccountAsBaseEntity){

		if(toAccountAsBaseEntity == null){
			checkBaseEntityReference(toAccountAsBaseEntity,true,TO_ACCOUNT_OF_TRANSACTION);
			return this;
		}
		checkAccountAsObject(toAccountAsBaseEntity);
		return this;
	}


	public static final String CHANGE_REQUEST_OF_TRANSACTION = "transaction.change_request";


	public BankChecker checkChangeRequestOfTransaction(BaseEntity changeRequestAsBaseEntity){

		if(changeRequestAsBaseEntity == null){
			checkBaseEntityReference(changeRequestAsBaseEntity,true,CHANGE_REQUEST_OF_TRANSACTION);
			return this;
		}
		checkChangeRequestAsObject(changeRequestAsBaseEntity);
		return this;
	}


	public BankChecker checkTransactionListAsFromAccountOfAccount(List<BaseEntity> transactionListAsFromAccount){
		transactionListAsFromAccount.stream().map(transaction->this.checkTransactionAsObject(transaction));
		return this;
	}

	public BankChecker checkTransactionListAsToAccountOfAccount(List<BaseEntity> transactionListAsToAccount){
		transactionListAsToAccount.stream().map(transaction->this.checkTransactionAsObject(transaction));
		return this;
	}

	public BankChecker checkAccountChangeListOfAccount(List<BaseEntity> accountChangeList){
		accountChangeList.stream().map(accountChange->this.checkAccountChangeAsObject(accountChange));
		return this;
	}

	public static final String PLATFORM_OF_ACCOUNT = "account.platform";


	public BankChecker checkPlatformOfAccount(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_ACCOUNT);
			return this;
		}
		checkPlatformAsObject(platformAsBaseEntity);
		return this;
	}


	public static final String ACCOUNT_OF_ACCOUNT_CHANGE = "account_change.account";


	public BankChecker checkAccountOfAccountChange(BaseEntity accountAsBaseEntity){

		if(accountAsBaseEntity == null){
			checkBaseEntityReference(accountAsBaseEntity,true,ACCOUNT_OF_ACCOUNT_CHANGE);
			return this;
		}
		checkAccountAsObject(accountAsBaseEntity);
		return this;
	}


	public static final String CHANGE_REQUEST_OF_ACCOUNT_CHANGE = "account_change.change_request";


	public BankChecker checkChangeRequestOfAccountChange(BaseEntity changeRequestAsBaseEntity){

		if(changeRequestAsBaseEntity == null){
			checkBaseEntityReference(changeRequestAsBaseEntity,true,CHANGE_REQUEST_OF_ACCOUNT_CHANGE);
			return this;
		}
		checkChangeRequestAsObject(changeRequestAsBaseEntity);
		return this;
	}


	public BankChecker checkUserWhiteListListOfUserDomain(List<BaseEntity> userWhiteListList){
		userWhiteListList.stream().map(userWhiteList->this.checkUserWhiteListAsObject(userWhiteList));
		return this;
	}

	public BankChecker checkSecUserListOfUserDomain(List<BaseEntity> secUserList){
		secUserList.stream().map(secUser->this.checkSecUserAsObject(secUser));
		return this;
	}

	public static final String DOMAIN_OF_USER_WHITE_LIST = "user_white_list.domain";


	public BankChecker checkDomainOfUserWhiteList(BaseEntity domainAsBaseEntity){

		if(domainAsBaseEntity == null){
			checkBaseEntityReference(domainAsBaseEntity,true,DOMAIN_OF_USER_WHITE_LIST);
			return this;
		}
		checkUserDomainAsObject(domainAsBaseEntity);
		return this;
	}


	public BankChecker checkUserAppListOfSecUser(List<BaseEntity> userAppList){
		userAppList.stream().map(userApp->this.checkUserAppAsObject(userApp));
		return this;
	}

	public BankChecker checkLoginHistoryListOfSecUser(List<BaseEntity> loginHistoryList){
		loginHistoryList.stream().map(loginHistory->this.checkLoginHistoryAsObject(loginHistory));
		return this;
	}

	public static final String DOMAIN_OF_SEC_USER = "sec_user.domain";


	public BankChecker checkDomainOfSecUser(BaseEntity domainAsBaseEntity){

		if(domainAsBaseEntity == null){
			checkBaseEntityReference(domainAsBaseEntity,true,DOMAIN_OF_SEC_USER);
			return this;
		}
		checkUserDomainAsObject(domainAsBaseEntity);
		return this;
	}


	public static final String BLOCKING_OF_SEC_USER = "sec_user.blocking";


	public BankChecker checkBlockingOfSecUser(BaseEntity blockingAsBaseEntity){

		if(blockingAsBaseEntity == null){
			checkBaseEntityReference(blockingAsBaseEntity,true,BLOCKING_OF_SEC_USER);
			return this;
		}
		checkSecUserBlockingAsObject(blockingAsBaseEntity);
		return this;
	}


	public BankChecker checkSecUserListOfSecUserBlocking(List<BaseEntity> secUserList){
		secUserList.stream().map(secUser->this.checkSecUserAsObject(secUser));
		return this;
	}

	public BankChecker checkListAccessListOfUserApp(List<BaseEntity> listAccessList){
		listAccessList.stream().map(listAccess->this.checkListAccessAsObject(listAccess));
		return this;
	}

	public BankChecker checkObjectAccessListOfUserApp(List<BaseEntity> objectAccessList){
		objectAccessList.stream().map(objectAccess->this.checkObjectAccessAsObject(objectAccess));
		return this;
	}

	public static final String SEC_USER_OF_USER_APP = "user_app.sec_user";


	public BankChecker checkSecUserOfUserApp(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_USER_APP);
			return this;
		}
		checkSecUserAsObject(secUserAsBaseEntity);
		return this;
	}


	public static final String APP_OF_LIST_ACCESS = "list_access.app";


	public BankChecker checkAppOfListAccess(BaseEntity appAsBaseEntity){

		if(appAsBaseEntity == null){
			checkBaseEntityReference(appAsBaseEntity,true,APP_OF_LIST_ACCESS);
			return this;
		}
		checkUserAppAsObject(appAsBaseEntity);
		return this;
	}


	public static final String APP_OF_OBJECT_ACCESS = "object_access.app";


	public BankChecker checkAppOfObjectAccess(BaseEntity appAsBaseEntity){

		if(appAsBaseEntity == null){
			checkBaseEntityReference(appAsBaseEntity,true,APP_OF_OBJECT_ACCESS);
			return this;
		}
		checkUserAppAsObject(appAsBaseEntity);
		return this;
	}


	public static final String SEC_USER_OF_LOGIN_HISTORY = "login_history.sec_user";


	public BankChecker checkSecUserOfLoginHistory(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_LOGIN_HISTORY);
			return this;
		}
		checkSecUserAsObject(secUserAsBaseEntity);
		return this;
	}


	public BankChecker checkFormMessageListOfGenericForm(List<BaseEntity> formMessageList){
		formMessageList.stream().map(formMessage->this.checkFormMessageAsObject(formMessage));
		return this;
	}

	public BankChecker checkFormFieldMessageListOfGenericForm(List<BaseEntity> formFieldMessageList){
		formFieldMessageList.stream().map(formFieldMessage->this.checkFormFieldMessageAsObject(formFieldMessage));
		return this;
	}

	public BankChecker checkFormFieldListOfGenericForm(List<BaseEntity> formFieldList){
		formFieldList.stream().map(formField->this.checkFormFieldAsObject(formField));
		return this;
	}

	public BankChecker checkFormActionListOfGenericForm(List<BaseEntity> formActionList){
		formActionList.stream().map(formAction->this.checkFormActionAsObject(formAction));
		return this;
	}

	public static final String FORM_OF_FORM_MESSAGE = "form_message.form";


	public BankChecker checkFormOfFormMessage(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_MESSAGE);
			return this;
		}
		checkGenericFormAsObject(formAsBaseEntity);
		return this;
	}


	public static final String FORM_OF_FORM_FIELD_MESSAGE = "form_field_message.form";


	public BankChecker checkFormOfFormFieldMessage(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_FIELD_MESSAGE);
			return this;
		}
		checkGenericFormAsObject(formAsBaseEntity);
		return this;
	}


	public static final String FORM_OF_FORM_FIELD = "form_field.form";


	public BankChecker checkFormOfFormField(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_FIELD);
			return this;
		}
		checkGenericFormAsObject(formAsBaseEntity);
		return this;
	}


	public static final String FORM_OF_FORM_ACTION = "form_action.form";


	public BankChecker checkFormOfFormAction(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_ACTION);
			return this;
		}
		checkGenericFormAsObject(formAsBaseEntity);
		return this;
	}


}

