
package com.doublechain.bank.changerequest;
import com.doublechain.bank.CommonTokens;
import java.util.Map;
public class ChangeRequestTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="changeRequest";
	
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
	protected ChangeRequestTokens(){
		//ensure not initialized outside the class
	}
	public  static  ChangeRequestTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ChangeRequestTokens tokens = new ChangeRequestTokens(options);
		return tokens;
		
	}
	protected ChangeRequestTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ChangeRequestTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ChangeRequestTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ChangeRequestTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static ChangeRequestTokens start(){
		return new ChangeRequestTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static ChangeRequestTokens allTokens(){
		
		return start()
			.withPlatform()
			.withTransactionList()
			.withAccountChangeList();
	
	}
	public static ChangeRequestTokens withoutListsTokens(){
		
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
	
	public ChangeRequestTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public ChangeRequestTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String TRANSACTION_LIST = "transactionList";
	public String getTransactionList(){
		return TRANSACTION_LIST;
	}
	public ChangeRequestTokens withTransactionList(){		
		addSimpleOptions(TRANSACTION_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeTransactionList(){		
		addSimpleOptions(TRANSACTION_LIST+".anaylze");
		return this;
	}
	public boolean analyzeTransactionListEnabled(){		
		
		if(checkOptions(this.options(), TRANSACTION_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public ChangeRequestTokens extractMoreFromTransactionList(String idsSeperatedWithComma){		
		addSimpleOptions(TRANSACTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int transactionListSortCounter = 0;
	public ChangeRequestTokens sortTransactionListWith(String field, String descOrAsc){		
		addSortMoreOptions(TRANSACTION_LIST,transactionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int transactionListSearchCounter = 0;
	public ChangeRequestTokens searchTransactionListWith(String field, String verb, String value){		
		addSearchMoreOptions(TRANSACTION_LIST,transactionListSearchCounter++, field, verb, value);
		return this;
	}
	
	public ChangeRequestTokens searchAllTextOfTransactionList(String verb, String value){	
		String field = "id|name|type";
		addSearchMoreOptions(TRANSACTION_LIST,transactionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfTransactionList(int rowsPerPage){		
		addSimpleOptions(TRANSACTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfTransactionList(int currentPageNumber){		
		addSimpleOptions(TRANSACTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfTransactionList(String[] columns){		
		addSimpleOptions(TRANSACTION_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfTransactionList(String[] columns){		
		addSimpleOptions(TRANSACTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String ACCOUNT_CHANGE_LIST = "accountChangeList";
	public String getAccountChangeList(){
		return ACCOUNT_CHANGE_LIST;
	}
	public ChangeRequestTokens withAccountChangeList(){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeAccountChangeList(){		
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
	public ChangeRequestTokens extractMoreFromAccountChangeList(String idsSeperatedWithComma){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int accountChangeListSortCounter = 0;
	public ChangeRequestTokens sortAccountChangeListWith(String field, String descOrAsc){		
		addSortMoreOptions(ACCOUNT_CHANGE_LIST,accountChangeListSortCounter++, field, descOrAsc);
		return this;
	}
	private int accountChangeListSearchCounter = 0;
	public ChangeRequestTokens searchAccountChangeListWith(String field, String verb, String value){		
		addSearchMoreOptions(ACCOUNT_CHANGE_LIST,accountChangeListSearchCounter++, field, verb, value);
		return this;
	}
	
	public ChangeRequestTokens searchAllTextOfAccountChangeList(String verb, String value){	
		String field = "id|name|type";
		addSearchMoreOptions(ACCOUNT_CHANGE_LIST,accountChangeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfAccountChangeList(int rowsPerPage){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfAccountChangeList(int currentPageNumber){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfAccountChangeList(String[] columns){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfAccountChangeList(String[] columns){		
		addSimpleOptions(ACCOUNT_CHANGE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ChangeRequestTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfTransactionList(verb, value);	
		searchAllTextOfAccountChangeList(verb, value);	
		return this;
	}
}

