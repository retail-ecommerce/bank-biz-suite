package com.doublechain.bank;


import com.terapico.caf.viewpage.SerializeScope;

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


public class BankBaseViewScope {

	protected static SerializeScope PlatformBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.FOUNDER_PROPERTY)
		.field(Platform.FOUNDED_PROPERTY)
		.field(Platform.DESCRIPTION_PROPERTY)
		;
	/** 用于Platform的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPlatformSummaryScope() {
		return PlatformBaseSummaryScope;
	}

	protected static SerializeScope ChangeRequestBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		;
	/** 用于ChangeRequest的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSummaryScope() {
		return ChangeRequestBaseSummaryScope;
	}

	protected static SerializeScope TransactionBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Transaction.ID_PROPERTY)
		.field(Transaction.NAME_PROPERTY)
		.field(Transaction.AMOUNT_PROPERTY)
		.field(Transaction.TYPE_PROPERTY)
		.field(Transaction.CURRENT_STATUS_PROPERTY)
		;
	/** 用于Transaction的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getTransactionSummaryScope() {
		return TransactionBaseSummaryScope;
	}

	protected static SerializeScope NameChangeEventBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(NameChangeEvent.ID_PROPERTY)
		.field(NameChangeEvent.NAME_PROPERTY)
		.field(NameChangeEvent.CURRENT_STATUS_PROPERTY)
		;
	/** 用于NameChangeEvent的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getNameChangeEventSummaryScope() {
		return NameChangeEventBaseSummaryScope;
	}

	protected static SerializeScope AccountBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Account.ID_PROPERTY)
		.field(Account.NAME_PROPERTY)
		.field(Account.BALANCE_PROPERTY)
		.field(Account.CREATE_TIME_PROPERTY)
		.field(Account.UPDATE_TIME_PROPERTY)
		;
	/** 用于Account的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getAccountSummaryScope() {
		return AccountBaseSummaryScope;
	}

	protected static SerializeScope AccountChangeBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(AccountChange.ID_PROPERTY)
		.field(AccountChange.NAME_PROPERTY)
		.field(AccountChange.PREVIOUS_BALANCE_PROPERTY)
		.field(AccountChange.TYPE_PROPERTY)
		.field(AccountChange.AMOUNT_PROPERTY)
		.field(AccountChange.CURRENT_BALANCE_PROPERTY)
		.field(AccountChange.CURRENT_STATUS_PROPERTY)
		;
	/** 用于AccountChange的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getAccountChangeSummaryScope() {
		return AccountChangeBaseSummaryScope;
	}

	protected static SerializeScope UserDomainBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		;
	/** 用于UserDomain的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserDomainSummaryScope() {
		return UserDomainBaseSummaryScope;
	}

	protected static SerializeScope UserWhiteListBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		;
	/** 用于UserWhiteList的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListSummaryScope() {
		return UserWhiteListBaseSummaryScope;
	}

	protected static SerializeScope SecUserBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.CURRENT_STATUS_PROPERTY)
		;
	/** 用于SecUser的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserSummaryScope() {
		return SecUserBaseSummaryScope;
	}

	protected static SerializeScope SecUserBlockingBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(SecUserBlocking.ID_PROPERTY)
		.field(SecUserBlocking.WHO_PROPERTY)
		.field(SecUserBlocking.BLOCK_TIME_PROPERTY)
		.field(SecUserBlocking.COMMENTS_PROPERTY)
		;
	/** 用于SecUserBlocking的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserBlockingSummaryScope() {
		return SecUserBlockingBaseSummaryScope;
	}

	protected static SerializeScope UserAppBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		;
	/** 用于UserApp的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserAppSummaryScope() {
		return UserAppBaseSummaryScope;
	}

	protected static SerializeScope ListAccessBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		;
	/** 用于ListAccess的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getListAccessSummaryScope() {
		return ListAccessBaseSummaryScope;
	}

	protected static SerializeScope ObjectAccessBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		;
	/** 用于ObjectAccess的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessSummaryScope() {
		return ObjectAccessBaseSummaryScope;
	}

	protected static SerializeScope LoginHistoryBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		;
	/** 用于LoginHistory的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLoginHistorySummaryScope() {
		return LoginHistoryBaseSummaryScope;
	}

	protected static SerializeScope GenericFormBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		;
	/** 用于GenericForm的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getGenericFormSummaryScope() {
		return GenericFormBaseSummaryScope;
	}

	protected static SerializeScope FormMessageBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormMessageSummaryScope() {
		return FormMessageBaseSummaryScope;
	}

	protected static SerializeScope FormFieldMessageBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageSummaryScope() {
		return FormFieldMessageBaseSummaryScope;
	}

	protected static SerializeScope FormFieldBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldSummaryScope() {
		return FormFieldBaseSummaryScope;
	}

	protected static SerializeScope FormActionBaseSummaryScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		;
	/** 用于FormAction的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormActionSummaryScope() {
		return FormActionBaseSummaryScope;
	}

	protected static SerializeScope PlatformBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.FOUNDER_PROPERTY)
		.field(Platform.FOUNDED_PROPERTY)
		.field(Platform.DESCRIPTION_PROPERTY)
		;
	/** 用于Platform的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPlatformSecondaryListItemScope() {
		return PlatformBaseSecondaryListItemScope;
	}

	protected static SerializeScope ChangeRequestBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		;
	/** 用于ChangeRequest的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSecondaryListItemScope() {
		return ChangeRequestBaseSecondaryListItemScope;
	}

	protected static SerializeScope TransactionBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Transaction.ID_PROPERTY)
		.field(Transaction.NAME_PROPERTY)
		.field(Transaction.AMOUNT_PROPERTY)
		.field(Transaction.TYPE_PROPERTY)
		.field(Transaction.CURRENT_STATUS_PROPERTY)
		;
	/** 用于Transaction的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getTransactionSecondaryListItemScope() {
		return TransactionBaseSecondaryListItemScope;
	}

	protected static SerializeScope NameChangeEventBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(NameChangeEvent.ID_PROPERTY)
		.field(NameChangeEvent.NAME_PROPERTY)
		.field(NameChangeEvent.CURRENT_STATUS_PROPERTY)
		;
	/** 用于NameChangeEvent的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getNameChangeEventSecondaryListItemScope() {
		return NameChangeEventBaseSecondaryListItemScope;
	}

	protected static SerializeScope AccountBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Account.ID_PROPERTY)
		.field(Account.NAME_PROPERTY)
		.field(Account.BALANCE_PROPERTY)
		.field(Account.CREATE_TIME_PROPERTY)
		.field(Account.UPDATE_TIME_PROPERTY)
		;
	/** 用于Account的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getAccountSecondaryListItemScope() {
		return AccountBaseSecondaryListItemScope;
	}

	protected static SerializeScope AccountChangeBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(AccountChange.ID_PROPERTY)
		.field(AccountChange.NAME_PROPERTY)
		.field(AccountChange.PREVIOUS_BALANCE_PROPERTY)
		.field(AccountChange.TYPE_PROPERTY)
		.field(AccountChange.AMOUNT_PROPERTY)
		.field(AccountChange.CURRENT_BALANCE_PROPERTY)
		.field(AccountChange.CURRENT_STATUS_PROPERTY)
		;
	/** 用于AccountChange的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getAccountChangeSecondaryListItemScope() {
		return AccountChangeBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserDomainBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		;
	/** 用于UserDomain的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserDomainSecondaryListItemScope() {
		return UserDomainBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserWhiteListBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		;
	/** 用于UserWhiteList的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListSecondaryListItemScope() {
		return UserWhiteListBaseSecondaryListItemScope;
	}

	protected static SerializeScope SecUserBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.CURRENT_STATUS_PROPERTY)
		;
	/** 用于SecUser的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserSecondaryListItemScope() {
		return SecUserBaseSecondaryListItemScope;
	}

	protected static SerializeScope SecUserBlockingBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(SecUserBlocking.ID_PROPERTY)
		.field(SecUserBlocking.WHO_PROPERTY)
		.field(SecUserBlocking.BLOCK_TIME_PROPERTY)
		.field(SecUserBlocking.COMMENTS_PROPERTY)
		;
	/** 用于SecUserBlocking的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserBlockingSecondaryListItemScope() {
		return SecUserBlockingBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserAppBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		;
	/** 用于UserApp的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserAppSecondaryListItemScope() {
		return UserAppBaseSecondaryListItemScope;
	}

	protected static SerializeScope ListAccessBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		;
	/** 用于ListAccess的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getListAccessSecondaryListItemScope() {
		return ListAccessBaseSecondaryListItemScope;
	}

	protected static SerializeScope ObjectAccessBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		;
	/** 用于ObjectAccess的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessSecondaryListItemScope() {
		return ObjectAccessBaseSecondaryListItemScope;
	}

	protected static SerializeScope LoginHistoryBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		;
	/** 用于LoginHistory的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLoginHistorySecondaryListItemScope() {
		return LoginHistoryBaseSecondaryListItemScope;
	}

	protected static SerializeScope GenericFormBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		;
	/** 用于GenericForm的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getGenericFormSecondaryListItemScope() {
		return GenericFormBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormMessageBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormMessageSecondaryListItemScope() {
		return FormMessageBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormFieldMessageBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageSecondaryListItemScope() {
		return FormFieldMessageBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormFieldBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldSecondaryListItemScope() {
		return FormFieldBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormActionBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		;
	/** 用于FormAction的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormActionSecondaryListItemScope() {
		return FormActionBaseSecondaryListItemScope;
	}

	protected static SerializeScope PlatformBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.FOUNDER_PROPERTY)
		.field(Platform.FOUNDED_PROPERTY)
		.field(Platform.DESCRIPTION_PROPERTY)
		.field(Platform.CHANGE_REQUEST_LIST, getChangeRequestSecondaryListItemScope())
		.field(Platform.ACCOUNT_LIST, getAccountSecondaryListItemScope())
		;
	/** 用于Platform对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPlatformListItemScope() {
		return PlatformBaseListItemScope;
	}

	protected static SerializeScope ChangeRequestBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequest.TRANSACTION_LIST, getTransactionSecondaryListItemScope())
		.field(ChangeRequest.NAME_CHANGE_EVENT_LIST, getNameChangeEventSecondaryListItemScope())
		.field(ChangeRequest.ACCOUNT_CHANGE_LIST, getAccountChangeSecondaryListItemScope())
		;
	/** 用于ChangeRequest对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestListItemScope() {
		return ChangeRequestBaseListItemScope;
	}

	protected static SerializeScope TransactionBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Transaction.ID_PROPERTY)
		.field(Transaction.NAME_PROPERTY)
		.field(Transaction.FROM_ACCOUNT_PROPERTY, getAccountSummaryScope())
		.field(Transaction.TO_ACCOUNT_PROPERTY, getAccountSummaryScope())
		.field(Transaction.AMOUNT_PROPERTY)
		.field(Transaction.TYPE_PROPERTY)
		.field(Transaction.CHANGE_REQUEST_PROPERTY, getChangeRequestSummaryScope())
		.field(Transaction.CURRENT_STATUS_PROPERTY)
		;
	/** 用于Transaction对象的列表时需要序列化的属性列表 */
	public static SerializeScope getTransactionListItemScope() {
		return TransactionBaseListItemScope;
	}

	protected static SerializeScope NameChangeEventBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(NameChangeEvent.ID_PROPERTY)
		.field(NameChangeEvent.NAME_PROPERTY)
		.field(NameChangeEvent.ACCOUNT_PROPERTY, getAccountSummaryScope())
		.field(NameChangeEvent.CHANGE_REQUEST_PROPERTY, getChangeRequestSummaryScope())
		.field(NameChangeEvent.CURRENT_STATUS_PROPERTY)
		;
	/** 用于NameChangeEvent对象的列表时需要序列化的属性列表 */
	public static SerializeScope getNameChangeEventListItemScope() {
		return NameChangeEventBaseListItemScope;
	}

	protected static SerializeScope AccountBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Account.ID_PROPERTY)
		.field(Account.NAME_PROPERTY)
		.field(Account.BALANCE_PROPERTY)
		.field(Account.CREATE_TIME_PROPERTY)
		.field(Account.UPDATE_TIME_PROPERTY)
		.field(Account.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Account.TRANSACTION_LIST_AS_FROM_ACCOUNT, getTransactionSecondaryListItemScope())
		.field(Account.TRANSACTION_LIST_AS_TO_ACCOUNT, getTransactionSecondaryListItemScope())
		.field(Account.NAME_CHANGE_EVENT_LIST, getNameChangeEventSecondaryListItemScope())
		.field(Account.ACCOUNT_CHANGE_LIST, getAccountChangeSecondaryListItemScope())
		;
	/** 用于Account对象的列表时需要序列化的属性列表 */
	public static SerializeScope getAccountListItemScope() {
		return AccountBaseListItemScope;
	}

	protected static SerializeScope AccountChangeBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(AccountChange.ID_PROPERTY)
		.field(AccountChange.NAME_PROPERTY)
		.field(AccountChange.PREVIOUS_BALANCE_PROPERTY)
		.field(AccountChange.TYPE_PROPERTY)
		.field(AccountChange.AMOUNT_PROPERTY)
		.field(AccountChange.CURRENT_BALANCE_PROPERTY)
		.field(AccountChange.ACCOUNT_PROPERTY, getAccountSummaryScope())
		.field(AccountChange.CHANGE_REQUEST_PROPERTY, getChangeRequestSummaryScope())
		.field(AccountChange.CURRENT_STATUS_PROPERTY)
		;
	/** 用于AccountChange对象的列表时需要序列化的属性列表 */
	public static SerializeScope getAccountChangeListItemScope() {
		return AccountChangeBaseListItemScope;
	}

	protected static SerializeScope UserDomainBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		.field(UserDomain.USER_WHITE_LIST_LIST, getUserWhiteListSecondaryListItemScope())
		.field(UserDomain.SEC_USER_LIST, getSecUserSecondaryListItemScope())
		;
	/** 用于UserDomain对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserDomainListItemScope() {
		return UserDomainBaseListItemScope;
	}

	protected static SerializeScope UserWhiteListBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		.field(UserWhiteList.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		;
	/** 用于UserWhiteList对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListListItemScope() {
		return UserWhiteListBaseListItemScope;
	}

	protected static SerializeScope SecUserBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(SecUser.BLOCKING_PROPERTY, getSecUserBlockingSummaryScope())
		.field(SecUser.CURRENT_STATUS_PROPERTY)
		.field(SecUser.USER_APP_LIST, getUserAppSecondaryListItemScope())
		.field(SecUser.LOGIN_HISTORY_LIST, getLoginHistorySecondaryListItemScope())
		;
	/** 用于SecUser对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserListItemScope() {
		return SecUserBaseListItemScope;
	}

	protected static SerializeScope SecUserBlockingBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(SecUserBlocking.ID_PROPERTY)
		.field(SecUserBlocking.WHO_PROPERTY)
		.field(SecUserBlocking.BLOCK_TIME_PROPERTY)
		.field(SecUserBlocking.COMMENTS_PROPERTY)
		.field(SecUserBlocking.SEC_USER_LIST, getSecUserSecondaryListItemScope())
		;
	/** 用于SecUserBlocking对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserBlockingListItemScope() {
		return SecUserBlockingBaseListItemScope;
	}

	protected static SerializeScope UserAppBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		.field(UserApp.LIST_ACCESS_LIST, getListAccessSecondaryListItemScope())
		.field(UserApp.OBJECT_ACCESS_LIST, getObjectAccessSecondaryListItemScope())
		;
	/** 用于UserApp对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserAppListItemScope() {
		return UserAppBaseListItemScope;
	}

	protected static SerializeScope ListAccessBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		.field(ListAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ListAccess对象的列表时需要序列化的属性列表 */
	public static SerializeScope getListAccessListItemScope() {
		return ListAccessBaseListItemScope;
	}

	protected static SerializeScope ObjectAccessBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		.field(ObjectAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ObjectAccess对象的列表时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessListItemScope() {
		return ObjectAccessBaseListItemScope;
	}

	protected static SerializeScope LoginHistoryBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		.field(LoginHistory.SEC_USER_PROPERTY, getSecUserSummaryScope())
		;
	/** 用于LoginHistory对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLoginHistoryListItemScope() {
		return LoginHistoryBaseListItemScope;
	}

	protected static SerializeScope GenericFormBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		.field(GenericForm.FORM_MESSAGE_LIST, getFormMessageSecondaryListItemScope())
		.field(GenericForm.FORM_FIELD_MESSAGE_LIST, getFormFieldMessageSecondaryListItemScope())
		.field(GenericForm.FORM_FIELD_LIST, getFormFieldSecondaryListItemScope())
		.field(GenericForm.FORM_ACTION_LIST, getFormActionSecondaryListItemScope())
		;
	/** 用于GenericForm对象的列表时需要序列化的属性列表 */
	public static SerializeScope getGenericFormListItemScope() {
		return GenericFormBaseListItemScope;
	}

	protected static SerializeScope FormMessageBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormMessageListItemScope() {
		return FormMessageBaseListItemScope;
	}

	protected static SerializeScope FormFieldMessageBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageListItemScope() {
		return FormFieldMessageBaseListItemScope;
	}

	protected static SerializeScope FormFieldBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldListItemScope() {
		return FormFieldBaseListItemScope;
	}

	protected static SerializeScope FormActionBaseListItemScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		.field(FormAction.FORM_PROPERTY, getGenericFormSummaryScope())
		;
	/** 用于FormAction对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormActionListItemScope() {
		return FormActionBaseListItemScope;
	}

	protected static SerializeScope PlatformBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.FOUNDER_PROPERTY)
		.field(Platform.FOUNDED_PROPERTY)
		.field(Platform.DESCRIPTION_PROPERTY)
		.field(Platform.CHANGE_REQUEST_LIST, getChangeRequestListItemScope())
		.field(Platform.ACCOUNT_LIST, getAccountListItemScope())
		;
	/** 用于Platform对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPlatformDetailScope() {
		return PlatformBaseDetailScope;
	}

	protected static SerializeScope ChangeRequestBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequest.TRANSACTION_LIST, getTransactionListItemScope())
		.field(ChangeRequest.NAME_CHANGE_EVENT_LIST, getNameChangeEventListItemScope())
		.field(ChangeRequest.ACCOUNT_CHANGE_LIST, getAccountChangeListItemScope())
		;
	/** 用于ChangeRequest对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestDetailScope() {
		return ChangeRequestBaseDetailScope;
	}

	protected static SerializeScope TransactionBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Transaction.ID_PROPERTY)
		.field(Transaction.NAME_PROPERTY)
		.field(Transaction.FROM_ACCOUNT_PROPERTY, getAccountSummaryScope())
		.field(Transaction.TO_ACCOUNT_PROPERTY, getAccountSummaryScope())
		.field(Transaction.AMOUNT_PROPERTY)
		.field(Transaction.TYPE_PROPERTY)
		.field(Transaction.CHANGE_REQUEST_PROPERTY, getChangeRequestSummaryScope())
		.field(Transaction.CURRENT_STATUS_PROPERTY)
		;
	/** 用于Transaction对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getTransactionDetailScope() {
		return TransactionBaseDetailScope;
	}

	protected static SerializeScope NameChangeEventBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(NameChangeEvent.ID_PROPERTY)
		.field(NameChangeEvent.NAME_PROPERTY)
		.field(NameChangeEvent.ACCOUNT_PROPERTY, getAccountSummaryScope())
		.field(NameChangeEvent.CHANGE_REQUEST_PROPERTY, getChangeRequestSummaryScope())
		.field(NameChangeEvent.CURRENT_STATUS_PROPERTY)
		;
	/** 用于NameChangeEvent对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getNameChangeEventDetailScope() {
		return NameChangeEventBaseDetailScope;
	}

	protected static SerializeScope AccountBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(Account.ID_PROPERTY)
		.field(Account.NAME_PROPERTY)
		.field(Account.BALANCE_PROPERTY)
		.field(Account.CREATE_TIME_PROPERTY)
		.field(Account.UPDATE_TIME_PROPERTY)
		.field(Account.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Account.TRANSACTION_LIST_AS_FROM_ACCOUNT, getTransactionListItemScope())
		.field(Account.TRANSACTION_LIST_AS_TO_ACCOUNT, getTransactionListItemScope())
		.field(Account.NAME_CHANGE_EVENT_LIST, getNameChangeEventListItemScope())
		.field(Account.ACCOUNT_CHANGE_LIST, getAccountChangeListItemScope())
		;
	/** 用于Account对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getAccountDetailScope() {
		return AccountBaseDetailScope;
	}

	protected static SerializeScope AccountChangeBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(AccountChange.ID_PROPERTY)
		.field(AccountChange.NAME_PROPERTY)
		.field(AccountChange.PREVIOUS_BALANCE_PROPERTY)
		.field(AccountChange.TYPE_PROPERTY)
		.field(AccountChange.AMOUNT_PROPERTY)
		.field(AccountChange.CURRENT_BALANCE_PROPERTY)
		.field(AccountChange.ACCOUNT_PROPERTY, getAccountSummaryScope())
		.field(AccountChange.CHANGE_REQUEST_PROPERTY, getChangeRequestSummaryScope())
		.field(AccountChange.CURRENT_STATUS_PROPERTY)
		;
	/** 用于AccountChange对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getAccountChangeDetailScope() {
		return AccountChangeBaseDetailScope;
	}

	protected static SerializeScope UserDomainBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		.field(UserDomain.USER_WHITE_LIST_LIST, getUserWhiteListListItemScope())
		.field(UserDomain.SEC_USER_LIST, getSecUserListItemScope())
		;
	/** 用于UserDomain对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserDomainDetailScope() {
		return UserDomainBaseDetailScope;
	}

	protected static SerializeScope UserWhiteListBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		.field(UserWhiteList.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		;
	/** 用于UserWhiteList对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListDetailScope() {
		return UserWhiteListBaseDetailScope;
	}

	protected static SerializeScope SecUserBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(SecUser.BLOCKING_PROPERTY, getSecUserBlockingSummaryScope())
		.field(SecUser.CURRENT_STATUS_PROPERTY)
		.field(SecUser.USER_APP_LIST, getUserAppListItemScope())
		.field(SecUser.LOGIN_HISTORY_LIST, getLoginHistoryListItemScope())
		;
	/** 用于SecUser对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserDetailScope() {
		return SecUserBaseDetailScope;
	}

	protected static SerializeScope SecUserBlockingBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(SecUserBlocking.ID_PROPERTY)
		.field(SecUserBlocking.WHO_PROPERTY)
		.field(SecUserBlocking.BLOCK_TIME_PROPERTY)
		.field(SecUserBlocking.COMMENTS_PROPERTY)
		.field(SecUserBlocking.SEC_USER_LIST, getSecUserListItemScope())
		;
	/** 用于SecUserBlocking对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserBlockingDetailScope() {
		return SecUserBlockingBaseDetailScope;
	}

	protected static SerializeScope UserAppBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		.field(UserApp.LIST_ACCESS_LIST, getListAccessListItemScope())
		.field(UserApp.OBJECT_ACCESS_LIST, getObjectAccessListItemScope())
		;
	/** 用于UserApp对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserAppDetailScope() {
		return UserAppBaseDetailScope;
	}

	protected static SerializeScope ListAccessBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		.field(ListAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ListAccess对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getListAccessDetailScope() {
		return ListAccessBaseDetailScope;
	}

	protected static SerializeScope ObjectAccessBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		.field(ObjectAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ObjectAccess对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessDetailScope() {
		return ObjectAccessBaseDetailScope;
	}

	protected static SerializeScope LoginHistoryBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		.field(LoginHistory.SEC_USER_PROPERTY, getSecUserSummaryScope())
		;
	/** 用于LoginHistory对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLoginHistoryDetailScope() {
		return LoginHistoryBaseDetailScope;
	}

	protected static SerializeScope GenericFormBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		.field(GenericForm.FORM_MESSAGE_LIST, getFormMessageListItemScope())
		.field(GenericForm.FORM_FIELD_MESSAGE_LIST, getFormFieldMessageListItemScope())
		.field(GenericForm.FORM_FIELD_LIST, getFormFieldListItemScope())
		.field(GenericForm.FORM_ACTION_LIST, getFormActionListItemScope())
		;
	/** 用于GenericForm对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getGenericFormDetailScope() {
		return GenericFormBaseDetailScope;
	}

	protected static SerializeScope FormMessageBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormMessageDetailScope() {
		return FormMessageBaseDetailScope;
	}

	protected static SerializeScope FormFieldMessageBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageDetailScope() {
		return FormFieldMessageBaseDetailScope;
	}

	protected static SerializeScope FormFieldBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldDetailScope() {
		return FormFieldBaseDetailScope;
	}

	protected static SerializeScope FormActionBaseDetailScope = SerializeScope.INCLUDE()
		.field(BankBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		.field(FormAction.FORM_PROPERTY, getGenericFormSummaryScope())
		;
	/** 用于FormAction对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormActionDetailScope() {
		return FormActionBaseDetailScope;
	}

	

}






