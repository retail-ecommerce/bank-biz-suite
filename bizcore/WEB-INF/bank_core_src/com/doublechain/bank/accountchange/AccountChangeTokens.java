
package com.doublechain.bank.accountchange;
import com.doublechain.bank.CommonTokens;
import java.util.Map;
public class AccountChangeTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="accountChange";
	
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
	protected AccountChangeTokens(){
		//ensure not initialized outside the class
	}
	public  static  AccountChangeTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		AccountChangeTokens tokens = new AccountChangeTokens(options);
		return tokens;
		
	}
	protected AccountChangeTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public AccountChangeTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static AccountChangeTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected AccountChangeTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static AccountChangeTokens start(){
		return new AccountChangeTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static AccountChangeTokens allTokens(){
		
		return start()
			.withAccount()
			.withChangeRequest();
	
	}
	public static AccountChangeTokens withoutListsTokens(){
		
		return start()
			.withAccount()
			.withChangeRequest();
	
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
	
	public AccountChangeTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String ACCOUNT = "account";
	public String getAccount(){
		return ACCOUNT;
	}
	public AccountChangeTokens withAccount(){		
		addSimpleOptions(ACCOUNT);
		return this;
	}
	
	
	protected static final String CHANGEREQUEST = "changeRequest";
	public String getChangeRequest(){
		return CHANGEREQUEST;
	}
	public AccountChangeTokens withChangeRequest(){		
		addSimpleOptions(CHANGEREQUEST);
		return this;
	}
	
	
	
	public  AccountChangeTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

