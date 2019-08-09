
package com.doublechain.bank.platform;
import com.doublechain.bank.CommonTokens;
import java.util.Map;
public class PlatformTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="platform";
	
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
	protected PlatformTokens(){
		//ensure not initialized outside the class
	}
	public  static  PlatformTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PlatformTokens tokens = new PlatformTokens(options);
		return tokens;
		
	}
	protected PlatformTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PlatformTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PlatformTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PlatformTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static PlatformTokens start(){
		return new PlatformTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static PlatformTokens allTokens(){
		
		return start()
			.withChangeRequestList()
			.withAccountList();
	
	}
	public static PlatformTokens withoutListsTokens(){
		
		return start();
	
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
	
	public PlatformTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String CHANGE_REQUEST_LIST = "changeRequestList";
	public String getChangeRequestList(){
		return CHANGE_REQUEST_LIST;
	}
	public PlatformTokens withChangeRequestList(){		
		addSimpleOptions(CHANGE_REQUEST_LIST);
		return this;
	}
	public PlatformTokens analyzeChangeRequestList(){		
		addSimpleOptions(CHANGE_REQUEST_LIST+".anaylze");
		return this;
	}
	public boolean analyzeChangeRequestListEnabled(){		
		
		if(checkOptions(this.options(), CHANGE_REQUEST_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromChangeRequestList(String idsSeperatedWithComma){		
		addSimpleOptions(CHANGE_REQUEST_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int changeRequestListSortCounter = 0;
	public PlatformTokens sortChangeRequestListWith(String field, String descOrAsc){		
		addSortMoreOptions(CHANGE_REQUEST_LIST,changeRequestListSortCounter++, field, descOrAsc);
		return this;
	}
	private int changeRequestListSearchCounter = 0;
	public PlatformTokens searchChangeRequestListWith(String field, String verb, String value){		
		addSearchMoreOptions(CHANGE_REQUEST_LIST,changeRequestListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfChangeRequestList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(CHANGE_REQUEST_LIST,changeRequestListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfChangeRequestList(int rowsPerPage){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfChangeRequestList(int currentPageNumber){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfChangeRequestList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfChangeRequestList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String ACCOUNT_LIST = "accountList";
	public String getAccountList(){
		return ACCOUNT_LIST;
	}
	public PlatformTokens withAccountList(){		
		addSimpleOptions(ACCOUNT_LIST);
		return this;
	}
	public PlatformTokens analyzeAccountList(){		
		addSimpleOptions(ACCOUNT_LIST+".anaylze");
		return this;
	}
	public boolean analyzeAccountListEnabled(){		
		
		if(checkOptions(this.options(), ACCOUNT_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromAccountList(String idsSeperatedWithComma){		
		addSimpleOptions(ACCOUNT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int accountListSortCounter = 0;
	public PlatformTokens sortAccountListWith(String field, String descOrAsc){		
		addSortMoreOptions(ACCOUNT_LIST,accountListSortCounter++, field, descOrAsc);
		return this;
	}
	private int accountListSearchCounter = 0;
	public PlatformTokens searchAccountListWith(String field, String verb, String value){		
		addSearchMoreOptions(ACCOUNT_LIST,accountListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfAccountList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(ACCOUNT_LIST,accountListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfAccountList(int rowsPerPage){		
		addSimpleOptions(ACCOUNT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfAccountList(int currentPageNumber){		
		addSimpleOptions(ACCOUNT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfAccountList(String[] columns){		
		addSimpleOptions(ACCOUNT_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfAccountList(String[] columns){		
		addSimpleOptions(ACCOUNT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  PlatformTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfChangeRequestList(verb, value);	
		searchAllTextOfAccountList(verb, value);	
		return this;
	}
}

