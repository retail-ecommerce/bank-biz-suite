
package com.doublechain.bank.account;
import com.doublechain.bank.CommonTokens;
import java.util.Map;
public class AccountTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="account";
	
	public static boolean checkOptions(Map<String,Object> options, String optionToCheck){
		
		if(options==null){
 			return false; //completely no option here
 		}
 		if(options.containsKey(ALL)){
 			//danger, debug only, might load the entire database!, really terrible
 			return true;
 		}
		String ownerKey = getOwnerObjectKey();
		Object ownerObject =(String) options.get(ownerKey);
		if(ownerObject ==  null){
			return false;
		}
		if(!ownerObject.equals(OWNER_OBJECT_NAME)){ //is the owner? 
			return false; 
		}
		
 		if(options.containsKey(optionToCheck)){
 			//options.remove(optionToCheck);
 			//consume the key, can not use any more to extract the data with the same token.			
 			return true;
 		}
 		
 		return false;
	
	}
	protected AccountTokens(){
		//ensure not initialized outside the class
	}
	public  static  AccountTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		AccountTokens tokens = new AccountTokens(options);
		return tokens;
		
	}
	protected AccountTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public AccountTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static AccountTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected AccountTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static AccountTokens start(){
		return new AccountTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static AccountTokens allTokens(){
		
		return start()
			.withPlatform()
			.withTransactionListAsFromAccount()
			.withTransactionListAsToAccount()
			.withAccountChangeList();
	
	}
	public static AccountTokens withoutListsTokens(){
		
		return start()
			.withPlatform();
	
	}
	
	public static Map <String,Object> all(){
		return allTokens().done();
	}
	public static Map <String,Object> withoutLists(){
		return withoutListsTokens().done();
	}
	public static Map <String,Object> empty(){
		return start().done();
	}
	
	public AccountTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public AccountTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String TRANSACTION_LIST_AS_FROM_ACCOUNT = "transactionListAsFromAccount";
	public String getTransactionListAsFromAccount(){
		return TRANSACTION_LIST_AS_FROM_ACCOUNT;
	}
	public AccountTokens withTransactionListAsFromAccount(){		
		addSimpleOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT);
		return this;
	}
	public AccountTokens analyzeTransactionListAsFromAccount(){		
		addSimpleOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT+".anaylze");
		return this;
	}
	public boolean analyzeTransactionListAsFromAccountEnabled(){		
		
		if(checkOptions(this.options(), TRANSACTION_LIST_AS_FROM_ACCOUNT+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public AccountTokens extractMoreFromTransactionListAsFromAccount(String idsSeperatedWithComma){		
		addSimpleOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int transactionListAsFromAccountSortCounter = 0;
	public AccountTokens sortTransactionListAsFromAccountWith(String field, String descOrAsc){		
		addSortMoreOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT,transactionListAsFromAccountSortCounter++, field, descOrAsc);
		return this;
	}
	private int transactionListAsFromAccountSearchCounter = 0;
	public AccountTokens searchTransactionListAsFromAccountWith(String field, String verb, String value){		
		addSearchMoreOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT,transactionListAsFromAccountSearchCounter++, field, verb, value);
		return this;
	}
	
	public AccountTokens searchAllTextOfTransactionListAsFromAccount(String verb, String value){	
		String field = "id|name|type";
		addSearchMoreOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT,transactionListAsFromAccountSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public AccountTokens rowsPerPageOfTransactionListAsFromAccount(int rowsPerPage){		
		addSimpleOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT+"RowsPerPage",rowsPerPage);
		return this;
	}
	public AccountTokens currentPageNumberOfTransactionListAsFromAccount(int currentPageNumber){		
		addSimpleOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT+"CurrentPage",currentPageNumber);
		return this;
	}
	public AccountTokens retainColumnsOfTransactionListAsFromAccount(String[] columns){		
		addSimpleOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT+"RetainColumns",columns);
		return this;
	}
	public AccountTokens excludeColumnsOfTransactionListAsFromAccount(String[] columns){		
		addSimpleOptions(TRANSACTION_LIST_AS_FROM_ACCOUNT+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String TRANSACTION_LIST_AS_TO_ACCOUNT = "transactionListAsToAccount";
	public String getTransactionListAsToAccount(){
		return TRANSACTION_LIST_AS_TO_ACCOUNT;
	}
	public AccountTokens withTransactionListAsToAccount(){		
		addSimpleOptions(TRANSACTION_LIST_AS_TO_ACCOUNT);
		return this;
	}
	public AccountTokens analyzeTransactionListAsToAccount(){		
		addSimpleOptions(TRANSACTION_LIST_AS_TO_ACCOUNT+".anaylze");
		return this;
	}
	public boolean analyzeTransactionListAsToAccountEnabled(){		
		
		if(checkOptions(this.options(), TRANSACTION_LIST_AS_TO_ACCOUNT+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public AccountTokens extractMoreFromTransactionListAsToAccount(String idsSeperatedWithComma){		
		addSimpleOptions(TRANSACTION_LIST_AS_TO_ACCOUNT+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int transactionListAsToAccountSortCounter = 0;
	public AccountTokens sortTransactionListAsToAccountWith(String field, String descOrAsc){		
		addSortMoreOptions(TRANSACTION_LIST_AS_TO_ACCOUNT,transactionListAsToAccountSortCounter++, field, descOrAsc);
		return this;
	}
	private int transactionListAsToAccountSearchCounter = 0;
	public AccountTokens searchTransactionListAsToAccountWith(String field, String verb, String value){		
		addSearchMoreOptions(TRANSACTION_LIST_AS_TO_ACCOUNT,transactionListAsToAccountSearchCounter++, field, verb, value);
		return this;
	}
	
	public AccountTokens searchAllTextOfTransactionListAsToAccount(String verb, String value){	
		String field = "id|name|type";
		addSearchMoreOptions(TRANSACTION_LIST_AS_TO_ACCOUNT,transactionListAsToAccountSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public AccountTokens rowsPerPageOfTransactionListAsToAccount(int rowsPerPage){		
		addSimpleOptions(TRANSACTION_LIST_AS_TO_ACCOUNT+"RowsPerPage",rowsPerPage);
		return this;
	}
	public AccountTokens currentPageNumberOfTransactionListAsToAccount(int currentPageNumber){		
		addSimpleOptions(TRANSACTION_LIST_AS_TO_ACCOUNT+"CurrentPage",currentPageNumber);
		return this;
	}
	public AccountTokens retainColumnsOfTransactionListAsToAccount(String[] columns){		
		addSimpleOptions(TRANSACTION_LIST_AS_TO_ACCOUNT+"RetainColumns",columns);
		return this;
	}
	public AccountTokens excludeColumnsOfTransactionListAsToAccount(String[] columns){		
		addSimpleOptions(TRANSACTION_LIST_AS_TO_ACCOUNT+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String ACCOUNT_CHANGE_LIST = "accountChangeList";
	public String getAccountChangeList(){
		return ACCOUNT_CHANGE_LIST;
	}
	public AccountTokens withAccountChangeList(){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST);
		return this;
	}
	public AccountTokens analyzeAccountChangeList(){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeAccountChangeListEnabled(){		
		
		if(checkOptions(this.options(), ACCOUNT_CHANGE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public AccountTokens extractMoreFromAccountChangeList(String idsSeperatedWithComma){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int accountChangeListSortCounter = 0;
	public AccountTokens sortAccountChangeListWith(String field, String descOrAsc){		
		addSortMoreOptions(ACCOUNT_CHANGE_LIST,accountChangeListSortCounter++, field, descOrAsc);
		return this;
	}
	private int accountChangeListSearchCounter = 0;
	public AccountTokens searchAccountChangeListWith(String field, String verb, String value){		
		addSearchMoreOptions(ACCOUNT_CHANGE_LIST,accountChangeListSearchCounter++, field, verb, value);
		return this;
	}
	
	public AccountTokens searchAllTextOfAccountChangeList(String verb, String value){	
		String field = "id|name|type";
		addSearchMoreOptions(ACCOUNT_CHANGE_LIST,accountChangeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public AccountTokens rowsPerPageOfAccountChangeList(int rowsPerPage){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public AccountTokens currentPageNumberOfAccountChangeList(int currentPageNumber){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public AccountTokens retainColumnsOfAccountChangeList(String[] columns){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+"RetainColumns",columns);
		return this;
	}
	public AccountTokens excludeColumnsOfAccountChangeList(String[] columns){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  AccountTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfTransactionListAsFromAccount(verb, value);	
		searchAllTextOfTransactionListAsToAccount(verb, value);	
		searchAllTextOfAccountChangeList(verb, value);	
		return this;
	}
}

