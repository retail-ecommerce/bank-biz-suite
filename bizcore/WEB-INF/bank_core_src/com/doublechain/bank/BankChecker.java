
package com.doublechain.bank;
import java.text.MessageFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
public class BankChecker extends BaseChecker{

	
	public BankChecker() {
		this.messageList = new ArrayList<Message>();
	}
	

	public static final String  ID_OF_PLATFORM ="platform.id";
	public BankChecker checkIdOfPlatform(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  NAME_OF_PLATFORM ="platform.name";
	public BankChecker checkNameOfPlatform(String name)
	{
		
	 	checkStringLengthRange(name,3, 24,NAME_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PLATFORM ="platform.version";
	public BankChecker checkVersionOfPlatform(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  ID_OF_CHANGE_REQUEST ="change_request.id";
	public BankChecker checkIdOfChangeRequest(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CHANGE_REQUEST ="change_request.name";
	public BankChecker checkNameOfChangeRequest(String name)
	{
		
	 	checkStringLengthRange(name,1, 8,NAME_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_CHANGE_REQUEST ="change_request.platform";
	public BankChecker checkPlatformIdOfChangeRequest(String platformId)
	{
		
	 	checkIdOfChangeRequest(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CHANGE_REQUEST ="change_request.version";
	public BankChecker checkVersionOfChangeRequest(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  ID_OF_TRANSACTION ="transaction.id";
	public BankChecker checkIdOfTransaction(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_TRANSACTION ); 		
		
		return this;
	}	

	public static final String  NAME_OF_TRANSACTION ="transaction.name";
	public BankChecker checkNameOfTransaction(String name)
	{
		
	 	checkStringLengthRange(name,2, 16,NAME_OF_TRANSACTION ); 		
		
		return this;
	}	

	public static final String  FROM_ACCOUNT_OF_TRANSACTION ="transaction.from_account";
	public BankChecker checkFromAccountIdOfTransaction(String fromAccountId)
	{
		
	 	checkIdOfTransaction(fromAccountId ); 		
		
		return this;
	}	

	public static final String  TO_ACCOUNT_OF_TRANSACTION ="transaction.to_account";
	public BankChecker checkToAccountIdOfTransaction(String toAccountId)
	{
		
	 	checkIdOfTransaction(toAccountId ); 		
		
		return this;
	}	

	public static final String  AMOUNT_OF_TRANSACTION ="transaction.amount";
	public BankChecker checkAmountOfTransaction(BigDecimal amount)
	{
		
	 	checkMoneyAmount(amount,0.00, 123.00,AMOUNT_OF_TRANSACTION ); 		
		
		return this;
	}	

	public static final String  TYPE_OF_TRANSACTION ="transaction.type";
	public BankChecker checkTypeOfTransaction(String type)
	{
		
	 	checkStringLengthRange(type,1, 8,TYPE_OF_TRANSACTION ); 		
		
		return this;
	}	

	public static final String  CHANGE_REQUEST_OF_TRANSACTION ="transaction.change_request";
	public BankChecker checkChangeRequestIdOfTransaction(String changeRequestId)
	{
		
	 	checkIdOfTransaction(changeRequestId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_TRANSACTION ="transaction.version";
	public BankChecker checkVersionOfTransaction(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_TRANSACTION ); 		
		
		return this;
	}	

	public static final String  ID_OF_NAME_CHANGE_EVENT ="name_change_event.id";
	public BankChecker checkIdOfNameChangeEvent(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_NAME_CHANGE_EVENT ); 		
		
		return this;
	}	

	public static final String  NAME_OF_NAME_CHANGE_EVENT ="name_change_event.name";
	public BankChecker checkNameOfNameChangeEvent(String name)
	{
		
	 	checkStringLengthRange(name,3, 32,NAME_OF_NAME_CHANGE_EVENT ); 		
		
		return this;
	}	

	public static final String  ACCOUNT_OF_NAME_CHANGE_EVENT ="name_change_event.account";
	public BankChecker checkAccountIdOfNameChangeEvent(String accountId)
	{
		
	 	checkIdOfNameChangeEvent(accountId ); 		
		
		return this;
	}	

	public static final String  CHANGE_REQUEST_OF_NAME_CHANGE_EVENT ="name_change_event.change_request";
	public BankChecker checkChangeRequestIdOfNameChangeEvent(String changeRequestId)
	{
		
	 	checkIdOfNameChangeEvent(changeRequestId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_NAME_CHANGE_EVENT ="name_change_event.version";
	public BankChecker checkVersionOfNameChangeEvent(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_NAME_CHANGE_EVENT ); 		
		
		return this;
	}	

	public static final String  ID_OF_ACCOUNT ="account.id";
	public BankChecker checkIdOfAccount(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_ACCOUNT ); 		
		
		return this;
	}	

	public static final String  NAME_OF_ACCOUNT ="account.name";
	public BankChecker checkNameOfAccount(String name)
	{
		
	 	checkStringLengthRange(name,2, 16,NAME_OF_ACCOUNT ); 		
		
		return this;
	}	

	public static final String  BALANCE_OF_ACCOUNT ="account.balance";
	public BankChecker checkBalanceOfAccount(BigDecimal balance)
	{
		
	 	checkMoneyAmount(balance,0.00, 123.00,BALANCE_OF_ACCOUNT ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_ACCOUNT ="account.platform";
	public BankChecker checkPlatformIdOfAccount(String platformId)
	{
		
	 	checkIdOfAccount(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_ACCOUNT ="account.version";
	public BankChecker checkVersionOfAccount(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_ACCOUNT ); 		
		
		return this;
	}	

	public static final String  ID_OF_ACCOUNT_CHANGE ="account_change.id";
	public BankChecker checkIdOfAccountChange(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_ACCOUNT_CHANGE ); 		
		
		return this;
	}	

	public static final String  NAME_OF_ACCOUNT_CHANGE ="account_change.name";
	public BankChecker checkNameOfAccountChange(String name)
	{
		
	 	checkStringLengthRange(name,2, 16,NAME_OF_ACCOUNT_CHANGE ); 		
		
		return this;
	}	

	public static final String  ACCOUNT_OF_ACCOUNT_CHANGE ="account_change.account";
	public BankChecker checkAccountIdOfAccountChange(String accountId)
	{
		
	 	checkIdOfAccountChange(accountId ); 		
		
		return this;
	}	

	public static final String  PREVIOUS_BALANCE_OF_ACCOUNT_CHANGE ="account_change.previous_balance";
	public BankChecker checkPreviousBalanceOfAccountChange(BigDecimal previousBalance)
	{
		
	 	checkMoneyAmount(previousBalance,0.00, 1312.00,PREVIOUS_BALANCE_OF_ACCOUNT_CHANGE ); 		
		
		return this;
	}	

	public static final String  TYPE_OF_ACCOUNT_CHANGE ="account_change.type";
	public BankChecker checkTypeOfAccountChange(String type)
	{
		
	 	checkStringLengthRange(type,1, 8,TYPE_OF_ACCOUNT_CHANGE ); 		
		
		return this;
	}	

	public static final String  AMOUNT_OF_ACCOUNT_CHANGE ="account_change.amount";
	public BankChecker checkAmountOfAccountChange(BigDecimal amount)
	{
		
	 	checkMoneyAmount(amount,0.00, 123.00,AMOUNT_OF_ACCOUNT_CHANGE ); 		
		
		return this;
	}	

	public static final String  CURRENT_BALANCE_OF_ACCOUNT_CHANGE ="account_change.current_balance";
	public BankChecker checkCurrentBalanceOfAccountChange(BigDecimal currentBalance)
	{
		
	 	checkMoneyAmount(currentBalance,0.00, 1312.00,CURRENT_BALANCE_OF_ACCOUNT_CHANGE ); 		
		
		return this;
	}	

	public static final String  CHANGE_REQUEST_OF_ACCOUNT_CHANGE ="account_change.change_request";
	public BankChecker checkChangeRequestIdOfAccountChange(String changeRequestId)
	{
		
	 	checkIdOfAccountChange(changeRequestId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_ACCOUNT_CHANGE ="account_change.version";
	public BankChecker checkVersionOfAccountChange(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_ACCOUNT_CHANGE ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_DOMAIN ="user_domain.id";
	public BankChecker checkIdOfUserDomain(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_DOMAIN ); 		
		
		return this;
	}	

	public static final String  NAME_OF_USER_DOMAIN ="user_domain.name";
	public BankChecker checkNameOfUserDomain(String name)
	{
		
	 	checkStringLengthRange(name,2, 16,NAME_OF_USER_DOMAIN ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_DOMAIN ="user_domain.version";
	public BankChecker checkVersionOfUserDomain(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_DOMAIN ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_WHITE_LIST ="user_white_list.id";
	public BankChecker checkIdOfUserWhiteList(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  USER_IDENTITY_OF_USER_WHITE_LIST ="user_white_list.user_identity";
	public BankChecker checkUserIdentityOfUserWhiteList(String userIdentity)
	{
		
	 	checkStringLengthRange(userIdentity,1, 40,USER_IDENTITY_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  USER_SPECIAL_FUNCTIONS_OF_USER_WHITE_LIST ="user_white_list.user_special_functions";
	public BankChecker checkUserSpecialFunctionsOfUserWhiteList(String userSpecialFunctions)
	{
		
	 	checkStringLengthRange(userSpecialFunctions,1, 200,USER_SPECIAL_FUNCTIONS_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  DOMAIN_OF_USER_WHITE_LIST ="user_white_list.domain";
	public BankChecker checkDomainIdOfUserWhiteList(String domainId)
	{
		
	 	checkIdOfUserWhiteList(domainId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_WHITE_LIST ="user_white_list.version";
	public BankChecker checkVersionOfUserWhiteList(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  ID_OF_SEC_USER ="sec_user.id";
	public BankChecker checkIdOfSecUser(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  LOGIN_OF_SEC_USER ="sec_user.login";
	public BankChecker checkLoginOfSecUser(String login)
	{
		
	 	checkStringLengthRange(login,2, 20,LOGIN_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  MOBILE_OF_SEC_USER ="sec_user.mobile";
	public BankChecker checkMobileOfSecUser(String mobile)
	{
		
	 	checkChinaMobilePhone(mobile,0, 11,MOBILE_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  EMAIL_OF_SEC_USER ="sec_user.email";
	public BankChecker checkEmailOfSecUser(String email)
	{
		
	 	checkStringLengthRange(email,0, 76,EMAIL_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  PWD_OF_SEC_USER ="sec_user.pwd";
	public BankChecker checkPwdOfSecUser(String pwd)
	{
		
	 	checkPassword(pwd,3, 28,PWD_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  WEIXIN_OPENID_OF_SEC_USER ="sec_user.weixin_openid";
	public BankChecker checkWeixinOpenidOfSecUser(String weixinOpenid)
	{
		
	 	checkStringLengthRange(weixinOpenid,0, 128,WEIXIN_OPENID_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  WEIXIN_APPID_OF_SEC_USER ="sec_user.weixin_appid";
	public BankChecker checkWeixinAppidOfSecUser(String weixinAppid)
	{
		
	 	checkStringLengthRange(weixinAppid,0, 128,WEIXIN_APPID_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  ACCESS_TOKEN_OF_SEC_USER ="sec_user.access_token";
	public BankChecker checkAccessTokenOfSecUser(String accessToken)
	{
		
	 	checkStringLengthRange(accessToken,0, 128,ACCESS_TOKEN_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  VERIFICATION_CODE_OF_SEC_USER ="sec_user.verification_code";
	public BankChecker checkVerificationCodeOfSecUser(int verificationCode)
	{
		
	 	checkIntegerRange(verificationCode,0, 9999999,VERIFICATION_CODE_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  VERIFICATION_CODE_EXPIRE_OF_SEC_USER ="sec_user.verification_code_expire";
	public BankChecker checkVerificationCodeExpireOfSecUser(DateTime verificationCodeExpire)
	{
		
	 	checkDateTime(verificationCodeExpire,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2099-12-31T09:09:09"),VERIFICATION_CODE_EXPIRE_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  LAST_LOGIN_TIME_OF_SEC_USER ="sec_user.last_login_time";
	public BankChecker checkLastLoginTimeOfSecUser(DateTime lastLoginTime)
	{
		
	 	checkDateTime(lastLoginTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2099-12-31T09:09:09"),LAST_LOGIN_TIME_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  DOMAIN_OF_SEC_USER ="sec_user.domain";
	public BankChecker checkDomainIdOfSecUser(String domainId)
	{
		
	 	checkIdOfSecUser(domainId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_SEC_USER ="sec_user.version";
	public BankChecker checkVersionOfSecUser(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  ID_OF_SEC_USER_BLOCKING ="sec_user_blocking.id";
	public BankChecker checkIdOfSecUserBlocking(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_SEC_USER_BLOCKING ); 		
		
		return this;
	}	

	public static final String  WHO_OF_SEC_USER_BLOCKING ="sec_user_blocking.who";
	public BankChecker checkWhoOfSecUserBlocking(String who)
	{
		
	 	checkStringLengthRange(who,4, 52,WHO_OF_SEC_USER_BLOCKING ); 		
		
		return this;
	}	

	public static final String  COMMENTS_OF_SEC_USER_BLOCKING ="sec_user_blocking.comments";
	public BankChecker checkCommentsOfSecUserBlocking(String comments)
	{
		
	 	checkStringLengthRange(comments,7, 96,COMMENTS_OF_SEC_USER_BLOCKING ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_SEC_USER_BLOCKING ="sec_user_blocking.version";
	public BankChecker checkVersionOfSecUserBlocking(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_SEC_USER_BLOCKING ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_APP ="user_app.id";
	public BankChecker checkIdOfUserApp(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_USER_APP ="user_app.title";
	public BankChecker checkTitleOfUserApp(String title)
	{
		
	 	checkStringLengthRange(title,1, 300,TITLE_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_USER_APP ="user_app.sec_user";
	public BankChecker checkSecUserIdOfUserApp(String secUserId)
	{
		
	 	checkIdOfUserApp(secUserId ); 		
		
		return this;
	}	

	public static final String  APP_ICON_OF_USER_APP ="user_app.app_icon";
	public BankChecker checkAppIconOfUserApp(String appIcon)
	{
		
	 	checkStringLengthRange(appIcon,2, 36,APP_ICON_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  FULL_ACCESS_OF_USER_APP ="user_app.full_access";
	public BankChecker checkFullAccessOfUserApp(boolean fullAccess)
	{
		
	 	checkBooleanRange(fullAccess,0, true,FULL_ACCESS_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  PERMISSION_OF_USER_APP ="user_app.permission";
	public BankChecker checkPermissionOfUserApp(String permission)
	{
		
	 	checkStringLengthRange(permission,2, 16,PERMISSION_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  OBJECT_TYPE_OF_USER_APP ="user_app.object_type";
	public BankChecker checkObjectTypeOfUserApp(String objectType)
	{
		
	 	checkStringLengthRange(objectType,1, 100,OBJECT_TYPE_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  OBJECT_ID_OF_USER_APP ="user_app.object_id";
	public BankChecker checkObjectIdOfUserApp(String objectId)
	{
		
	 	checkStringLengthRange(objectId,4, 40,OBJECT_ID_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  LOCATION_OF_USER_APP ="user_app.location";
	public BankChecker checkLocationOfUserApp(String location)
	{
		
	 	checkStringLengthRange(location,4, 48,LOCATION_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_APP ="user_app.version";
	public BankChecker checkVersionOfUserApp(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  ID_OF_LIST_ACCESS ="list_access.id";
	public BankChecker checkIdOfListAccess(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  NAME_OF_LIST_ACCESS ="list_access.name";
	public BankChecker checkNameOfListAccess(String name)
	{
		
	 	checkStringLengthRange(name,1, 200,NAME_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  INTERNAL_NAME_OF_LIST_ACCESS ="list_access.internal_name";
	public BankChecker checkInternalNameOfListAccess(String internalName)
	{
		
	 	checkStringLengthRange(internalName,1, 200,INTERNAL_NAME_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  READ_PERMISSION_OF_LIST_ACCESS ="list_access.read_permission";
	public BankChecker checkReadPermissionOfListAccess(boolean readPermission)
	{
		
	 	checkBooleanRange(readPermission,0, true,READ_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  CREATE_PERMISSION_OF_LIST_ACCESS ="list_access.create_permission";
	public BankChecker checkCreatePermissionOfListAccess(boolean createPermission)
	{
		
	 	checkBooleanRange(createPermission,0, true,CREATE_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  DELETE_PERMISSION_OF_LIST_ACCESS ="list_access.delete_permission";
	public BankChecker checkDeletePermissionOfListAccess(boolean deletePermission)
	{
		
	 	checkBooleanRange(deletePermission,0, true,DELETE_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  UPDATE_PERMISSION_OF_LIST_ACCESS ="list_access.update_permission";
	public BankChecker checkUpdatePermissionOfListAccess(boolean updatePermission)
	{
		
	 	checkBooleanRange(updatePermission,0, true,UPDATE_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  EXECUTION_PERMISSION_OF_LIST_ACCESS ="list_access.execution_permission";
	public BankChecker checkExecutionPermissionOfListAccess(boolean executionPermission)
	{
		
	 	checkBooleanRange(executionPermission,0, true,EXECUTION_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  APP_OF_LIST_ACCESS ="list_access.app";
	public BankChecker checkAppIdOfListAccess(String appId)
	{
		
	 	checkIdOfListAccess(appId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_LIST_ACCESS ="list_access.version";
	public BankChecker checkVersionOfListAccess(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  ID_OF_OBJECT_ACCESS ="object_access.id";
	public BankChecker checkIdOfObjectAccess(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  NAME_OF_OBJECT_ACCESS ="object_access.name";
	public BankChecker checkNameOfObjectAccess(String name)
	{
		
	 	checkStringLengthRange(name,2, 28,NAME_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  OBJECT_TYPE_OF_OBJECT_ACCESS ="object_access.object_type";
	public BankChecker checkObjectTypeOfObjectAccess(String objectType)
	{
		
	 	checkStringLengthRange(objectType,5, 112,OBJECT_TYPE_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST1_OF_OBJECT_ACCESS ="object_access.list1";
	public BankChecker checkList1OfObjectAccess(String list1)
	{
		
	 	checkStringLengthRange(list1,5, 80,LIST1_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST2_OF_OBJECT_ACCESS ="object_access.list2";
	public BankChecker checkList2OfObjectAccess(String list2)
	{
		
	 	checkStringLengthRange(list2,5, 80,LIST2_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST3_OF_OBJECT_ACCESS ="object_access.list3";
	public BankChecker checkList3OfObjectAccess(String list3)
	{
		
	 	checkStringLengthRange(list3,5, 80,LIST3_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST4_OF_OBJECT_ACCESS ="object_access.list4";
	public BankChecker checkList4OfObjectAccess(String list4)
	{
		
	 	checkStringLengthRange(list4,5, 80,LIST4_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST5_OF_OBJECT_ACCESS ="object_access.list5";
	public BankChecker checkList5OfObjectAccess(String list5)
	{
		
	 	checkStringLengthRange(list5,5, 80,LIST5_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST6_OF_OBJECT_ACCESS ="object_access.list6";
	public BankChecker checkList6OfObjectAccess(String list6)
	{
		
	 	checkStringLengthRange(list6,5, 80,LIST6_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST7_OF_OBJECT_ACCESS ="object_access.list7";
	public BankChecker checkList7OfObjectAccess(String list7)
	{
		
	 	checkStringLengthRange(list7,5, 80,LIST7_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST8_OF_OBJECT_ACCESS ="object_access.list8";
	public BankChecker checkList8OfObjectAccess(String list8)
	{
		
	 	checkStringLengthRange(list8,5, 80,LIST8_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST9_OF_OBJECT_ACCESS ="object_access.list9";
	public BankChecker checkList9OfObjectAccess(String list9)
	{
		
	 	checkStringLengthRange(list9,5, 80,LIST9_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  APP_OF_OBJECT_ACCESS ="object_access.app";
	public BankChecker checkAppIdOfObjectAccess(String appId)
	{
		
	 	checkIdOfObjectAccess(appId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_OBJECT_ACCESS ="object_access.version";
	public BankChecker checkVersionOfObjectAccess(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  ID_OF_LOGIN_HISTORY ="login_history.id";
	public BankChecker checkIdOfLoginHistory(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  FROM_IP_OF_LOGIN_HISTORY ="login_history.from_ip";
	public BankChecker checkFromIpOfLoginHistory(String fromIp)
	{
		
	 	checkStringLengthRange(fromIp,5, 44,FROM_IP_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  DESCRIPTION_OF_LOGIN_HISTORY ="login_history.description";
	public BankChecker checkDescriptionOfLoginHistory(String description)
	{
		
	 	checkStringLengthRange(description,2, 16,DESCRIPTION_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_LOGIN_HISTORY ="login_history.sec_user";
	public BankChecker checkSecUserIdOfLoginHistory(String secUserId)
	{
		
	 	checkIdOfLoginHistory(secUserId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_LOGIN_HISTORY ="login_history.version";
	public BankChecker checkVersionOfLoginHistory(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  ID_OF_GENERIC_FORM ="generic_form.id";
	public BankChecker checkIdOfGenericForm(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_GENERIC_FORM ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_GENERIC_FORM ="generic_form.title";
	public BankChecker checkTitleOfGenericForm(String title)
	{
		
	 	checkStringLengthRange(title,2, 20,TITLE_OF_GENERIC_FORM ); 		
		
		return this;
	}	

	public static final String  DESCRIPTION_OF_GENERIC_FORM ="generic_form.description";
	public BankChecker checkDescriptionOfGenericForm(String description)
	{
		
	 	checkStringLengthRange(description,4, 48,DESCRIPTION_OF_GENERIC_FORM ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_GENERIC_FORM ="generic_form.version";
	public BankChecker checkVersionOfGenericForm(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_GENERIC_FORM ); 		
		
		return this;
	}	

	public static final String  ID_OF_FORM_MESSAGE ="form_message.id";
	public BankChecker checkIdOfFormMessage(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_FORM_MESSAGE ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_FORM_MESSAGE ="form_message.title";
	public BankChecker checkTitleOfFormMessage(String title)
	{
		
	 	checkStringLengthRange(title,2, 24,TITLE_OF_FORM_MESSAGE ); 		
		
		return this;
	}	

	public static final String  FORM_OF_FORM_MESSAGE ="form_message.form";
	public BankChecker checkFormIdOfFormMessage(String formId)
	{
		
	 	checkIdOfFormMessage(formId ); 		
		
		return this;
	}	

	public static final String  LEVEL_OF_FORM_MESSAGE ="form_message.level";
	public BankChecker checkLevelOfFormMessage(String level)
	{
		
	 	checkStringLengthRange(level,2, 28,LEVEL_OF_FORM_MESSAGE ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_FORM_MESSAGE ="form_message.version";
	public BankChecker checkVersionOfFormMessage(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_FORM_MESSAGE ); 		
		
		return this;
	}	

	public static final String  ID_OF_FORM_FIELD_MESSAGE ="form_field_message.id";
	public BankChecker checkIdOfFormFieldMessage(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_FORM_FIELD_MESSAGE ="form_field_message.title";
	public BankChecker checkTitleOfFormFieldMessage(String title)
	{
		
	 	checkStringLengthRange(title,2, 16,TITLE_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  PARAMETER_NAME_OF_FORM_FIELD_MESSAGE ="form_field_message.parameter_name";
	public BankChecker checkParameterNameOfFormFieldMessage(String parameterName)
	{
		
	 	checkStringLengthRange(parameterName,2, 16,PARAMETER_NAME_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  FORM_OF_FORM_FIELD_MESSAGE ="form_field_message.form";
	public BankChecker checkFormIdOfFormFieldMessage(String formId)
	{
		
	 	checkIdOfFormFieldMessage(formId ); 		
		
		return this;
	}	

	public static final String  LEVEL_OF_FORM_FIELD_MESSAGE ="form_field_message.level";
	public BankChecker checkLevelOfFormFieldMessage(String level)
	{
		
	 	checkStringLengthRange(level,2, 28,LEVEL_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_FORM_FIELD_MESSAGE ="form_field_message.version";
	public BankChecker checkVersionOfFormFieldMessage(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  ID_OF_FORM_FIELD ="form_field.id";
	public BankChecker checkIdOfFormField(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  LABEL_OF_FORM_FIELD ="form_field.label";
	public BankChecker checkLabelOfFormField(String label)
	{
		
	 	checkStringLengthRange(label,1, 12,LABEL_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  LOCALE_KEY_OF_FORM_FIELD ="form_field.locale_key";
	public BankChecker checkLocaleKeyOfFormField(String localeKey)
	{
		
	 	checkStringLengthRange(localeKey,1, 44,LOCALE_KEY_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  PARAMETER_NAME_OF_FORM_FIELD ="form_field.parameter_name";
	public BankChecker checkParameterNameOfFormField(String parameterName)
	{
		
	 	checkStringLengthRange(parameterName,2, 16,PARAMETER_NAME_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  TYPE_OF_FORM_FIELD ="form_field.type";
	public BankChecker checkTypeOfFormField(String type)
	{
		
	 	checkStringLengthRange(type,1, 36,TYPE_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  FORM_OF_FORM_FIELD ="form_field.form";
	public BankChecker checkFormIdOfFormField(String formId)
	{
		
	 	checkIdOfFormField(formId ); 		
		
		return this;
	}	

	public static final String  PLACEHOLDER_OF_FORM_FIELD ="form_field.placeholder";
	public BankChecker checkPlaceholderOfFormField(String placeholder)
	{
		
	 	checkStringLengthRange(placeholder,4, 48,PLACEHOLDER_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  DEFAULT_VALUE_OF_FORM_FIELD ="form_field.default_value";
	public BankChecker checkDefaultValueOfFormField(String defaultValue)
	{
		
	 	checkStringLengthRange(defaultValue,1, 12,DEFAULT_VALUE_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  DESCRIPTION_OF_FORM_FIELD ="form_field.description";
	public BankChecker checkDescriptionOfFormField(String description)
	{
		
	 	checkStringLengthRange(description,4, 48,DESCRIPTION_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  FIELD_GROUP_OF_FORM_FIELD ="form_field.field_group";
	public BankChecker checkFieldGroupOfFormField(String fieldGroup)
	{
		
	 	checkStringLengthRange(fieldGroup,2, 16,FIELD_GROUP_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  MINIMUM_VALUE_OF_FORM_FIELD ="form_field.minimum_value";
	public BankChecker checkMinimumValueOfFormField(String minimumValue)
	{
		
	 	checkStringLengthRange(minimumValue,4, 60,MINIMUM_VALUE_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  MAXIMUM_VALUE_OF_FORM_FIELD ="form_field.maximum_value";
	public BankChecker checkMaximumValueOfFormField(String maximumValue)
	{
		
	 	checkStringLengthRange(maximumValue,5, 72,MAXIMUM_VALUE_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  REQUIRED_OF_FORM_FIELD ="form_field.required";
	public BankChecker checkRequiredOfFormField(boolean required)
	{
		
	 	checkBooleanRange(required,0, true|false,REQUIRED_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  DISABLED_OF_FORM_FIELD ="form_field.disabled";
	public BankChecker checkDisabledOfFormField(boolean disabled)
	{
		
	 	checkBooleanRange(disabled,0, true|false,DISABLED_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  CUSTOM_RENDERING_OF_FORM_FIELD ="form_field.custom_rendering";
	public BankChecker checkCustomRenderingOfFormField(boolean customRendering)
	{
		
	 	checkBooleanRange(customRendering,0, false,CUSTOM_RENDERING_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  CANDIDATE_VALUES_OF_FORM_FIELD ="form_field.candidate_values";
	public BankChecker checkCandidateValuesOfFormField(String candidateValues)
	{
		
	 	checkStringLengthRange(candidateValues,0, 12,CANDIDATE_VALUES_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  SUGGEST_VALUES_OF_FORM_FIELD ="form_field.suggest_values";
	public BankChecker checkSuggestValuesOfFormField(String suggestValues)
	{
		
	 	checkStringLengthRange(suggestValues,0, 12,SUGGEST_VALUES_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_FORM_FIELD ="form_field.version";
	public BankChecker checkVersionOfFormField(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  ID_OF_FORM_ACTION ="form_action.id";
	public BankChecker checkIdOfFormAction(String id)
	{
		
	 	checkStringLengthRange(id,2, 64,ID_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  LABEL_OF_FORM_ACTION ="form_action.label";
	public BankChecker checkLabelOfFormAction(String label)
	{
		
	 	checkStringLengthRange(label,1, 8,LABEL_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  LOCALE_KEY_OF_FORM_ACTION ="form_action.locale_key";
	public BankChecker checkLocaleKeyOfFormAction(String localeKey)
	{
		
	 	checkStringLengthRange(localeKey,2, 16,LOCALE_KEY_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  ACTION_KEY_OF_FORM_ACTION ="form_action.action_key";
	public BankChecker checkActionKeyOfFormAction(String actionKey)
	{
		
	 	checkStringLengthRange(actionKey,2, 24,ACTION_KEY_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  LEVEL_OF_FORM_ACTION ="form_action.level";
	public BankChecker checkLevelOfFormAction(String level)
	{
		
	 	checkStringLengthRange(level,3, 28,LEVEL_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  URL_OF_FORM_ACTION ="form_action.url";
	public BankChecker checkUrlOfFormAction(String url)
	{
		
	 	checkStringLengthRange(url,11, 168,URL_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  FORM_OF_FORM_ACTION ="form_action.form";
	public BankChecker checkFormIdOfFormAction(String formId)
	{
		
	 	checkIdOfFormAction(formId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_FORM_ACTION ="form_action.version";
	public BankChecker checkVersionOfFormAction(int version)
	{
		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_FORM_ACTION ); 		
		
		return this;
	}	
}








