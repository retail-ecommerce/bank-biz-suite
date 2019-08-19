package com.doublechain.bank;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
public class BankObjectChecker extends BankChecker{

	Set<BaseEntity> checkedObjectSet;
	
	protected void markAsChecked(BaseEntity baseEntity) {
		if(checkedObjectSet==null) {
			checkedObjectSet =  new HashSet<BaseEntity>();
		}
		checkedObjectSet.add(baseEntity);
		
		
	}
	
	protected boolean isChecked(BaseEntity baseEntity) {
		if(checkedObjectSet==null) {
			return false;
			
		}
		return checkedObjectSet.contains(baseEntity);
	}
	@FunctionalInterface
	public interface CheckerParameterFunction<P1,R> {
		R apply(P1 valueToCheck);
	}
	
	public <T> BankChecker commonObjectPropertyCheck(BaseEntity target, String propertyName, CheckerParameterFunction<T,BankChecker> checkerFunction) {
		
		pushPosition(propertyName);
		T valueToCheck=(T)target.propertyOf(propertyName);
		checkerFunction.apply(valueToCheck);
		popPosition();
		
		return this;
	}
	public  BankChecker commonObjectElementCheck(BaseEntity target, String propertyName, CheckerParameterFunction<BaseEntity,BankChecker> checkerFunction) {
		
		pushPosition(propertyName);
		checkerFunction.apply(target);
		popPosition();
		return this;
	}
	protected String wrapArrayIndex(int andIncrement) {
		return "["+andIncrement+"]";
	}
	// use like commonObjectPropertyCheck(changeRequestAsBaseEntity,"name",this::checkNameOfChangeRequest);

	public BankObjectChecker checkPlatformAsObject(BaseEntity platformAsBaseEntity){

		if( isChecked(platformAsBaseEntity) ){
			return this;
		}
		markAsChecked(platformAsBaseEntity);
		if( platformAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(platformAsBaseEntity,"id",this::checkIdOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"name",this::checkNameOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"version",this::checkVersionOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"changeRequestList",this::checkChangeRequestListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"accountList",this::checkAccountListOfPlatform);
		return this;

	}

	public BankObjectChecker checkChangeRequestAsObject(BaseEntity changeRequestAsBaseEntity){

		if( isChecked(changeRequestAsBaseEntity) ){
			return this;
		}
		markAsChecked(changeRequestAsBaseEntity);
		if( changeRequestAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(changeRequestAsBaseEntity,"id",this::checkIdOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"name",this::checkNameOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"platform",this::checkPlatformOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"version",this::checkVersionOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"transactionList",this::checkTransactionListOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"nameChangeEventList",this::checkNameChangeEventListOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"accountChangeList",this::checkAccountChangeListOfChangeRequest);
		return this;

	}

	public BankObjectChecker checkTransactionAsObject(BaseEntity transactionAsBaseEntity){

		if( isChecked(transactionAsBaseEntity) ){
			return this;
		}
		markAsChecked(transactionAsBaseEntity);
		if( transactionAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(transactionAsBaseEntity,"id",this::checkIdOfTransaction);
		commonObjectPropertyCheck(transactionAsBaseEntity,"name",this::checkNameOfTransaction);
		commonObjectPropertyCheck(transactionAsBaseEntity,"fromAccount",this::checkFromAccountOfTransaction);
		commonObjectPropertyCheck(transactionAsBaseEntity,"toAccount",this::checkToAccountOfTransaction);
		commonObjectPropertyCheck(transactionAsBaseEntity,"amount",this::checkAmountOfTransaction);
		commonObjectPropertyCheck(transactionAsBaseEntity,"type",this::checkTypeOfTransaction);
		commonObjectPropertyCheck(transactionAsBaseEntity,"changeRequest",this::checkChangeRequestOfTransaction);
		commonObjectPropertyCheck(transactionAsBaseEntity,"version",this::checkVersionOfTransaction);
		return this;

	}

	public BankObjectChecker checkNameChangeEventAsObject(BaseEntity nameChangeEventAsBaseEntity){

		if( isChecked(nameChangeEventAsBaseEntity) ){
			return this;
		}
		markAsChecked(nameChangeEventAsBaseEntity);
		if( nameChangeEventAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(nameChangeEventAsBaseEntity,"id",this::checkIdOfNameChangeEvent);
		commonObjectPropertyCheck(nameChangeEventAsBaseEntity,"name",this::checkNameOfNameChangeEvent);
		commonObjectPropertyCheck(nameChangeEventAsBaseEntity,"account",this::checkAccountOfNameChangeEvent);
		commonObjectPropertyCheck(nameChangeEventAsBaseEntity,"changeRequest",this::checkChangeRequestOfNameChangeEvent);
		commonObjectPropertyCheck(nameChangeEventAsBaseEntity,"version",this::checkVersionOfNameChangeEvent);
		return this;

	}

	public BankObjectChecker checkAccountAsObject(BaseEntity accountAsBaseEntity){

		if( isChecked(accountAsBaseEntity) ){
			return this;
		}
		markAsChecked(accountAsBaseEntity);
		if( accountAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(accountAsBaseEntity,"id",this::checkIdOfAccount);
		commonObjectPropertyCheck(accountAsBaseEntity,"name",this::checkNameOfAccount);
		commonObjectPropertyCheck(accountAsBaseEntity,"balance",this::checkBalanceOfAccount);
		commonObjectPropertyCheck(accountAsBaseEntity,"platform",this::checkPlatformOfAccount);
		commonObjectPropertyCheck(accountAsBaseEntity,"version",this::checkVersionOfAccount);
		commonObjectPropertyCheck(accountAsBaseEntity,"transactionListAsFromAccount",this::checkTransactionListAsFromAccountOfAccount);
		commonObjectPropertyCheck(accountAsBaseEntity,"transactionListAsToAccount",this::checkTransactionListAsToAccountOfAccount);
		commonObjectPropertyCheck(accountAsBaseEntity,"nameChangeEventList",this::checkNameChangeEventListOfAccount);
		commonObjectPropertyCheck(accountAsBaseEntity,"accountChangeList",this::checkAccountChangeListOfAccount);
		return this;

	}

	public BankObjectChecker checkAccountChangeAsObject(BaseEntity accountChangeAsBaseEntity){

		if( isChecked(accountChangeAsBaseEntity) ){
			return this;
		}
		markAsChecked(accountChangeAsBaseEntity);
		if( accountChangeAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(accountChangeAsBaseEntity,"id",this::checkIdOfAccountChange);
		commonObjectPropertyCheck(accountChangeAsBaseEntity,"name",this::checkNameOfAccountChange);
		commonObjectPropertyCheck(accountChangeAsBaseEntity,"account",this::checkAccountOfAccountChange);
		commonObjectPropertyCheck(accountChangeAsBaseEntity,"previousBalance",this::checkPreviousBalanceOfAccountChange);
		commonObjectPropertyCheck(accountChangeAsBaseEntity,"type",this::checkTypeOfAccountChange);
		commonObjectPropertyCheck(accountChangeAsBaseEntity,"amount",this::checkAmountOfAccountChange);
		commonObjectPropertyCheck(accountChangeAsBaseEntity,"currentBalance",this::checkCurrentBalanceOfAccountChange);
		commonObjectPropertyCheck(accountChangeAsBaseEntity,"changeRequest",this::checkChangeRequestOfAccountChange);
		commonObjectPropertyCheck(accountChangeAsBaseEntity,"version",this::checkVersionOfAccountChange);
		return this;

	}

	public BankObjectChecker checkUserDomainAsObject(BaseEntity userDomainAsBaseEntity){

		if( isChecked(userDomainAsBaseEntity) ){
			return this;
		}
		markAsChecked(userDomainAsBaseEntity);
		if( userDomainAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(userDomainAsBaseEntity,"id",this::checkIdOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"name",this::checkNameOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"version",this::checkVersionOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"userWhiteListList",this::checkUserWhiteListListOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"secUserList",this::checkSecUserListOfUserDomain);
		return this;

	}

	public BankObjectChecker checkUserWhiteListAsObject(BaseEntity userWhiteListAsBaseEntity){

		if( isChecked(userWhiteListAsBaseEntity) ){
			return this;
		}
		markAsChecked(userWhiteListAsBaseEntity);
		if( userWhiteListAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(userWhiteListAsBaseEntity,"id",this::checkIdOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"userIdentity",this::checkUserIdentityOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"userSpecialFunctions",this::checkUserSpecialFunctionsOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"domain",this::checkDomainOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"version",this::checkVersionOfUserWhiteList);
		return this;

	}

	public BankObjectChecker checkSecUserAsObject(BaseEntity secUserAsBaseEntity){

		if( isChecked(secUserAsBaseEntity) ){
			return this;
		}
		markAsChecked(secUserAsBaseEntity);
		if( secUserAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(secUserAsBaseEntity,"id",this::checkIdOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"login",this::checkLoginOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"mobile",this::checkMobileOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"email",this::checkEmailOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"pwd",this::checkPwdOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"weixinOpenid",this::checkWeixinOpenidOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"weixinAppid",this::checkWeixinAppidOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"accessToken",this::checkAccessTokenOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"verificationCode",this::checkVerificationCodeOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"verificationCodeExpire",this::checkVerificationCodeExpireOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"lastLoginTime",this::checkLastLoginTimeOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"domain",this::checkDomainOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"version",this::checkVersionOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"userAppList",this::checkUserAppListOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"loginHistoryList",this::checkLoginHistoryListOfSecUser);
		return this;

	}

	public BankObjectChecker checkSecUserBlockingAsObject(BaseEntity secUserBlockingAsBaseEntity){

		if( isChecked(secUserBlockingAsBaseEntity) ){
			return this;
		}
		markAsChecked(secUserBlockingAsBaseEntity);
		if( secUserBlockingAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(secUserBlockingAsBaseEntity,"id",this::checkIdOfSecUserBlocking);
		commonObjectPropertyCheck(secUserBlockingAsBaseEntity,"who",this::checkWhoOfSecUserBlocking);
		commonObjectPropertyCheck(secUserBlockingAsBaseEntity,"comments",this::checkCommentsOfSecUserBlocking);
		commonObjectPropertyCheck(secUserBlockingAsBaseEntity,"version",this::checkVersionOfSecUserBlocking);
		commonObjectPropertyCheck(secUserBlockingAsBaseEntity,"secUserList",this::checkSecUserListOfSecUserBlocking);
		return this;

	}

	public BankObjectChecker checkUserAppAsObject(BaseEntity userAppAsBaseEntity){

		if( isChecked(userAppAsBaseEntity) ){
			return this;
		}
		markAsChecked(userAppAsBaseEntity);
		if( userAppAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(userAppAsBaseEntity,"id",this::checkIdOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"title",this::checkTitleOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"secUser",this::checkSecUserOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"appIcon",this::checkAppIconOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"fullAccess",this::checkFullAccessOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"permission",this::checkPermissionOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"objectType",this::checkObjectTypeOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"objectId",this::checkObjectIdOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"location",this::checkLocationOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"version",this::checkVersionOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"listAccessList",this::checkListAccessListOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"objectAccessList",this::checkObjectAccessListOfUserApp);
		return this;

	}

	public BankObjectChecker checkListAccessAsObject(BaseEntity listAccessAsBaseEntity){

		if( isChecked(listAccessAsBaseEntity) ){
			return this;
		}
		markAsChecked(listAccessAsBaseEntity);
		if( listAccessAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(listAccessAsBaseEntity,"id",this::checkIdOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"name",this::checkNameOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"internalName",this::checkInternalNameOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"readPermission",this::checkReadPermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"createPermission",this::checkCreatePermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"deletePermission",this::checkDeletePermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"updatePermission",this::checkUpdatePermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"executionPermission",this::checkExecutionPermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"app",this::checkAppOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"version",this::checkVersionOfListAccess);
		return this;

	}

	public BankObjectChecker checkObjectAccessAsObject(BaseEntity objectAccessAsBaseEntity){

		if( isChecked(objectAccessAsBaseEntity) ){
			return this;
		}
		markAsChecked(objectAccessAsBaseEntity);
		if( objectAccessAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(objectAccessAsBaseEntity,"id",this::checkIdOfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"name",this::checkNameOfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"objectType",this::checkObjectTypeOfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list1",this::checkList1OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list2",this::checkList2OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list3",this::checkList3OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list4",this::checkList4OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list5",this::checkList5OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list6",this::checkList6OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list7",this::checkList7OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list8",this::checkList8OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list9",this::checkList9OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"app",this::checkAppOfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"version",this::checkVersionOfObjectAccess);
		return this;

	}

	public BankObjectChecker checkLoginHistoryAsObject(BaseEntity loginHistoryAsBaseEntity){

		if( isChecked(loginHistoryAsBaseEntity) ){
			return this;
		}
		markAsChecked(loginHistoryAsBaseEntity);
		if( loginHistoryAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(loginHistoryAsBaseEntity,"id",this::checkIdOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"fromIp",this::checkFromIpOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"description",this::checkDescriptionOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"secUser",this::checkSecUserOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"version",this::checkVersionOfLoginHistory);
		return this;

	}

	public BankObjectChecker checkGenericFormAsObject(BaseEntity genericFormAsBaseEntity){

		if( isChecked(genericFormAsBaseEntity) ){
			return this;
		}
		markAsChecked(genericFormAsBaseEntity);
		if( genericFormAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(genericFormAsBaseEntity,"id",this::checkIdOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"title",this::checkTitleOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"description",this::checkDescriptionOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"version",this::checkVersionOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"formMessageList",this::checkFormMessageListOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"formFieldMessageList",this::checkFormFieldMessageListOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"formFieldList",this::checkFormFieldListOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"formActionList",this::checkFormActionListOfGenericForm);
		return this;

	}

	public BankObjectChecker checkFormMessageAsObject(BaseEntity formMessageAsBaseEntity){

		if( isChecked(formMessageAsBaseEntity) ){
			return this;
		}
		markAsChecked(formMessageAsBaseEntity);
		if( formMessageAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(formMessageAsBaseEntity,"id",this::checkIdOfFormMessage);
		commonObjectPropertyCheck(formMessageAsBaseEntity,"title",this::checkTitleOfFormMessage);
		commonObjectPropertyCheck(formMessageAsBaseEntity,"form",this::checkFormOfFormMessage);
		commonObjectPropertyCheck(formMessageAsBaseEntity,"level",this::checkLevelOfFormMessage);
		commonObjectPropertyCheck(formMessageAsBaseEntity,"version",this::checkVersionOfFormMessage);
		return this;

	}

	public BankObjectChecker checkFormFieldMessageAsObject(BaseEntity formFieldMessageAsBaseEntity){

		if( isChecked(formFieldMessageAsBaseEntity) ){
			return this;
		}
		markAsChecked(formFieldMessageAsBaseEntity);
		if( formFieldMessageAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"id",this::checkIdOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"title",this::checkTitleOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"parameterName",this::checkParameterNameOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"form",this::checkFormOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"level",this::checkLevelOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"version",this::checkVersionOfFormFieldMessage);
		return this;

	}

	public BankObjectChecker checkFormFieldAsObject(BaseEntity formFieldAsBaseEntity){

		if( isChecked(formFieldAsBaseEntity) ){
			return this;
		}
		markAsChecked(formFieldAsBaseEntity);
		if( formFieldAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(formFieldAsBaseEntity,"id",this::checkIdOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"label",this::checkLabelOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"localeKey",this::checkLocaleKeyOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"parameterName",this::checkParameterNameOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"type",this::checkTypeOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"form",this::checkFormOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"placeholder",this::checkPlaceholderOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"defaultValue",this::checkDefaultValueOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"description",this::checkDescriptionOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"fieldGroup",this::checkFieldGroupOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"minimumValue",this::checkMinimumValueOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"maximumValue",this::checkMaximumValueOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"required",this::checkRequiredOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"disabled",this::checkDisabledOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"customRendering",this::checkCustomRenderingOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"candidateValues",this::checkCandidateValuesOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"suggestValues",this::checkSuggestValuesOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"version",this::checkVersionOfFormField);
		return this;

	}

	public BankObjectChecker checkFormActionAsObject(BaseEntity formActionAsBaseEntity){

		if( isChecked(formActionAsBaseEntity) ){
			return this;
		}
		markAsChecked(formActionAsBaseEntity);
		if( formActionAsBaseEntity.getVersion() > 0 ) 
			commonObjectPropertyCheck(formActionAsBaseEntity,"id",this::checkIdOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"label",this::checkLabelOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"localeKey",this::checkLocaleKeyOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"actionKey",this::checkActionKeyOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"level",this::checkLevelOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"url",this::checkUrlOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"form",this::checkFormOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"version",this::checkVersionOfFormAction);
		return this;

	}


	public BankObjectChecker checkChangeRequestListOfPlatform(List<BaseEntity> changeRequestList){
		AtomicInteger index = new AtomicInteger();
		changeRequestList.stream().forEach(changeRequest->
			commonObjectElementCheck(changeRequest,wrapArrayIndex(index.getAndIncrement()),this::checkChangeRequestAsObject));
		return this;
	}

	public BankObjectChecker checkAccountListOfPlatform(List<BaseEntity> accountList){
		AtomicInteger index = new AtomicInteger();
		accountList.stream().forEach(account->
			commonObjectElementCheck(account,wrapArrayIndex(index.getAndIncrement()),this::checkAccountAsObject));
		return this;
	}

	public BankObjectChecker checkTransactionListOfChangeRequest(List<BaseEntity> transactionList){
		AtomicInteger index = new AtomicInteger();
		transactionList.stream().forEach(transaction->
			commonObjectElementCheck(transaction,wrapArrayIndex(index.getAndIncrement()),this::checkTransactionAsObject));
		return this;
	}

	public BankObjectChecker checkNameChangeEventListOfChangeRequest(List<BaseEntity> nameChangeEventList){
		AtomicInteger index = new AtomicInteger();
		nameChangeEventList.stream().forEach(nameChangeEvent->
			commonObjectElementCheck(nameChangeEvent,wrapArrayIndex(index.getAndIncrement()),this::checkNameChangeEventAsObject));
		return this;
	}

	public BankObjectChecker checkAccountChangeListOfChangeRequest(List<BaseEntity> accountChangeList){
		AtomicInteger index = new AtomicInteger();
		accountChangeList.stream().forEach(accountChange->
			commonObjectElementCheck(accountChange,wrapArrayIndex(index.getAndIncrement()),this::checkAccountChangeAsObject));
		return this;
	}

	public static final String PLATFORM_OF_CHANGE_REQUEST = "change_request.platform";


	public BankObjectChecker checkPlatformOfChangeRequest(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_CHANGE_REQUEST);
			return this;
		}
		checkPlatformAsObject(platformAsBaseEntity);
		return this;
	}


	public static final String FROM_ACCOUNT_OF_TRANSACTION = "transaction.from_account";


	public BankObjectChecker checkFromAccountOfTransaction(BaseEntity fromAccountAsBaseEntity){

		if(fromAccountAsBaseEntity == null){
			checkBaseEntityReference(fromAccountAsBaseEntity,true,FROM_ACCOUNT_OF_TRANSACTION);
			return this;
		}
		checkAccountAsObject(fromAccountAsBaseEntity);
		return this;
	}


	public static final String TO_ACCOUNT_OF_TRANSACTION = "transaction.to_account";


	public BankObjectChecker checkToAccountOfTransaction(BaseEntity toAccountAsBaseEntity){

		if(toAccountAsBaseEntity == null){
			checkBaseEntityReference(toAccountAsBaseEntity,true,TO_ACCOUNT_OF_TRANSACTION);
			return this;
		}
		checkAccountAsObject(toAccountAsBaseEntity);
		return this;
	}


	public static final String CHANGE_REQUEST_OF_TRANSACTION = "transaction.change_request";


	public BankObjectChecker checkChangeRequestOfTransaction(BaseEntity changeRequestAsBaseEntity){

		if(changeRequestAsBaseEntity == null){
			checkBaseEntityReference(changeRequestAsBaseEntity,true,CHANGE_REQUEST_OF_TRANSACTION);
			return this;
		}
		checkChangeRequestAsObject(changeRequestAsBaseEntity);
		return this;
	}


	public static final String ACCOUNT_OF_NAME_CHANGE_EVENT = "name_change_event.account";


	public BankObjectChecker checkAccountOfNameChangeEvent(BaseEntity accountAsBaseEntity){

		if(accountAsBaseEntity == null){
			checkBaseEntityReference(accountAsBaseEntity,true,ACCOUNT_OF_NAME_CHANGE_EVENT);
			return this;
		}
		checkAccountAsObject(accountAsBaseEntity);
		return this;
	}


	public static final String CHANGE_REQUEST_OF_NAME_CHANGE_EVENT = "name_change_event.change_request";


	public BankObjectChecker checkChangeRequestOfNameChangeEvent(BaseEntity changeRequestAsBaseEntity){

		if(changeRequestAsBaseEntity == null){
			checkBaseEntityReference(changeRequestAsBaseEntity,true,CHANGE_REQUEST_OF_NAME_CHANGE_EVENT);
			return this;
		}
		checkChangeRequestAsObject(changeRequestAsBaseEntity);
		return this;
	}


	public BankObjectChecker checkTransactionListAsFromAccountOfAccount(List<BaseEntity> transactionListAsFromAccount){
		AtomicInteger index = new AtomicInteger();
		transactionListAsFromAccount.stream().forEach(transaction->
			commonObjectElementCheck(transaction,wrapArrayIndex(index.getAndIncrement()),this::checkTransactionAsObject));
		return this;
	}

	public BankObjectChecker checkTransactionListAsToAccountOfAccount(List<BaseEntity> transactionListAsToAccount){
		AtomicInteger index = new AtomicInteger();
		transactionListAsToAccount.stream().forEach(transaction->
			commonObjectElementCheck(transaction,wrapArrayIndex(index.getAndIncrement()),this::checkTransactionAsObject));
		return this;
	}

	public BankObjectChecker checkNameChangeEventListOfAccount(List<BaseEntity> nameChangeEventList){
		AtomicInteger index = new AtomicInteger();
		nameChangeEventList.stream().forEach(nameChangeEvent->
			commonObjectElementCheck(nameChangeEvent,wrapArrayIndex(index.getAndIncrement()),this::checkNameChangeEventAsObject));
		return this;
	}

	public BankObjectChecker checkAccountChangeListOfAccount(List<BaseEntity> accountChangeList){
		AtomicInteger index = new AtomicInteger();
		accountChangeList.stream().forEach(accountChange->
			commonObjectElementCheck(accountChange,wrapArrayIndex(index.getAndIncrement()),this::checkAccountChangeAsObject));
		return this;
	}

	public static final String PLATFORM_OF_ACCOUNT = "account.platform";


	public BankObjectChecker checkPlatformOfAccount(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_ACCOUNT);
			return this;
		}
		checkPlatformAsObject(platformAsBaseEntity);
		return this;
	}


	public static final String ACCOUNT_OF_ACCOUNT_CHANGE = "account_change.account";


	public BankObjectChecker checkAccountOfAccountChange(BaseEntity accountAsBaseEntity){

		if(accountAsBaseEntity == null){
			checkBaseEntityReference(accountAsBaseEntity,true,ACCOUNT_OF_ACCOUNT_CHANGE);
			return this;
		}
		checkAccountAsObject(accountAsBaseEntity);
		return this;
	}


	public static final String CHANGE_REQUEST_OF_ACCOUNT_CHANGE = "account_change.change_request";


	public BankObjectChecker checkChangeRequestOfAccountChange(BaseEntity changeRequestAsBaseEntity){

		if(changeRequestAsBaseEntity == null){
			checkBaseEntityReference(changeRequestAsBaseEntity,true,CHANGE_REQUEST_OF_ACCOUNT_CHANGE);
			return this;
		}
		checkChangeRequestAsObject(changeRequestAsBaseEntity);
		return this;
	}


	public BankObjectChecker checkUserWhiteListListOfUserDomain(List<BaseEntity> userWhiteListList){
		AtomicInteger index = new AtomicInteger();
		userWhiteListList.stream().forEach(userWhiteList->
			commonObjectElementCheck(userWhiteList,wrapArrayIndex(index.getAndIncrement()),this::checkUserWhiteListAsObject));
		return this;
	}

	public BankObjectChecker checkSecUserListOfUserDomain(List<BaseEntity> secUserList){
		AtomicInteger index = new AtomicInteger();
		secUserList.stream().forEach(secUser->
			commonObjectElementCheck(secUser,wrapArrayIndex(index.getAndIncrement()),this::checkSecUserAsObject));
		return this;
	}

	public static final String DOMAIN_OF_USER_WHITE_LIST = "user_white_list.domain";


	public BankObjectChecker checkDomainOfUserWhiteList(BaseEntity domainAsBaseEntity){

		if(domainAsBaseEntity == null){
			checkBaseEntityReference(domainAsBaseEntity,true,DOMAIN_OF_USER_WHITE_LIST);
			return this;
		}
		checkUserDomainAsObject(domainAsBaseEntity);
		return this;
	}


	public BankObjectChecker checkUserAppListOfSecUser(List<BaseEntity> userAppList){
		AtomicInteger index = new AtomicInteger();
		userAppList.stream().forEach(userApp->
			commonObjectElementCheck(userApp,wrapArrayIndex(index.getAndIncrement()),this::checkUserAppAsObject));
		return this;
	}

	public BankObjectChecker checkLoginHistoryListOfSecUser(List<BaseEntity> loginHistoryList){
		AtomicInteger index = new AtomicInteger();
		loginHistoryList.stream().forEach(loginHistory->
			commonObjectElementCheck(loginHistory,wrapArrayIndex(index.getAndIncrement()),this::checkLoginHistoryAsObject));
		return this;
	}

	public static final String DOMAIN_OF_SEC_USER = "sec_user.domain";


	public BankObjectChecker checkDomainOfSecUser(BaseEntity domainAsBaseEntity){

		if(domainAsBaseEntity == null){
			checkBaseEntityReference(domainAsBaseEntity,true,DOMAIN_OF_SEC_USER);
			return this;
		}
		checkUserDomainAsObject(domainAsBaseEntity);
		return this;
	}


	public static final String BLOCKING_OF_SEC_USER = "sec_user.blocking";


	public BankObjectChecker checkBlockingOfSecUser(BaseEntity blockingAsBaseEntity){

		if(blockingAsBaseEntity == null){
			checkBaseEntityReference(blockingAsBaseEntity,true,BLOCKING_OF_SEC_USER);
			return this;
		}
		checkSecUserBlockingAsObject(blockingAsBaseEntity);
		return this;
	}


	public BankObjectChecker checkSecUserListOfSecUserBlocking(List<BaseEntity> secUserList){
		AtomicInteger index = new AtomicInteger();
		secUserList.stream().forEach(secUser->
			commonObjectElementCheck(secUser,wrapArrayIndex(index.getAndIncrement()),this::checkSecUserAsObject));
		return this;
	}

	public BankObjectChecker checkListAccessListOfUserApp(List<BaseEntity> listAccessList){
		AtomicInteger index = new AtomicInteger();
		listAccessList.stream().forEach(listAccess->
			commonObjectElementCheck(listAccess,wrapArrayIndex(index.getAndIncrement()),this::checkListAccessAsObject));
		return this;
	}

	public BankObjectChecker checkObjectAccessListOfUserApp(List<BaseEntity> objectAccessList){
		AtomicInteger index = new AtomicInteger();
		objectAccessList.stream().forEach(objectAccess->
			commonObjectElementCheck(objectAccess,wrapArrayIndex(index.getAndIncrement()),this::checkObjectAccessAsObject));
		return this;
	}

	public static final String SEC_USER_OF_USER_APP = "user_app.sec_user";


	public BankObjectChecker checkSecUserOfUserApp(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_USER_APP);
			return this;
		}
		checkSecUserAsObject(secUserAsBaseEntity);
		return this;
	}


	public static final String APP_OF_LIST_ACCESS = "list_access.app";


	public BankObjectChecker checkAppOfListAccess(BaseEntity appAsBaseEntity){

		if(appAsBaseEntity == null){
			checkBaseEntityReference(appAsBaseEntity,true,APP_OF_LIST_ACCESS);
			return this;
		}
		checkUserAppAsObject(appAsBaseEntity);
		return this;
	}


	public static final String APP_OF_OBJECT_ACCESS = "object_access.app";


	public BankObjectChecker checkAppOfObjectAccess(BaseEntity appAsBaseEntity){

		if(appAsBaseEntity == null){
			checkBaseEntityReference(appAsBaseEntity,true,APP_OF_OBJECT_ACCESS);
			return this;
		}
		checkUserAppAsObject(appAsBaseEntity);
		return this;
	}


	public static final String SEC_USER_OF_LOGIN_HISTORY = "login_history.sec_user";


	public BankObjectChecker checkSecUserOfLoginHistory(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_LOGIN_HISTORY);
			return this;
		}
		checkSecUserAsObject(secUserAsBaseEntity);
		return this;
	}


	public BankObjectChecker checkFormMessageListOfGenericForm(List<BaseEntity> formMessageList){
		AtomicInteger index = new AtomicInteger();
		formMessageList.stream().forEach(formMessage->
			commonObjectElementCheck(formMessage,wrapArrayIndex(index.getAndIncrement()),this::checkFormMessageAsObject));
		return this;
	}

	public BankObjectChecker checkFormFieldMessageListOfGenericForm(List<BaseEntity> formFieldMessageList){
		AtomicInteger index = new AtomicInteger();
		formFieldMessageList.stream().forEach(formFieldMessage->
			commonObjectElementCheck(formFieldMessage,wrapArrayIndex(index.getAndIncrement()),this::checkFormFieldMessageAsObject));
		return this;
	}

	public BankObjectChecker checkFormFieldListOfGenericForm(List<BaseEntity> formFieldList){
		AtomicInteger index = new AtomicInteger();
		formFieldList.stream().forEach(formField->
			commonObjectElementCheck(formField,wrapArrayIndex(index.getAndIncrement()),this::checkFormFieldAsObject));
		return this;
	}

	public BankObjectChecker checkFormActionListOfGenericForm(List<BaseEntity> formActionList){
		AtomicInteger index = new AtomicInteger();
		formActionList.stream().forEach(formAction->
			commonObjectElementCheck(formAction,wrapArrayIndex(index.getAndIncrement()),this::checkFormActionAsObject));
		return this;
	}

	public static final String FORM_OF_FORM_MESSAGE = "form_message.form";


	public BankObjectChecker checkFormOfFormMessage(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_MESSAGE);
			return this;
		}
		checkGenericFormAsObject(formAsBaseEntity);
		return this;
	}


	public static final String FORM_OF_FORM_FIELD_MESSAGE = "form_field_message.form";


	public BankObjectChecker checkFormOfFormFieldMessage(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_FIELD_MESSAGE);
			return this;
		}
		checkGenericFormAsObject(formAsBaseEntity);
		return this;
	}


	public static final String FORM_OF_FORM_FIELD = "form_field.form";


	public BankObjectChecker checkFormOfFormField(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_FIELD);
			return this;
		}
		checkGenericFormAsObject(formAsBaseEntity);
		return this;
	}


	public static final String FORM_OF_FORM_ACTION = "form_action.form";


	public BankObjectChecker checkFormOfFormAction(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_ACTION);
			return this;
		}
		checkGenericFormAsObject(formAsBaseEntity);
		return this;
	}


}

