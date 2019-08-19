
package com.doublechain.bank.namechangeevent;
import com.doublechain.bank.CommonTokens;
import java.util.Map;
public class NameChangeEventTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="nameChangeEvent";
	
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
	protected NameChangeEventTokens(){
		//ensure not initialized outside the class
	}
	public  static  NameChangeEventTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		NameChangeEventTokens tokens = new NameChangeEventTokens(options);
		return tokens;
		
	}
	protected NameChangeEventTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public NameChangeEventTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static NameChangeEventTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected NameChangeEventTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static NameChangeEventTokens start(){
		return new NameChangeEventTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static NameChangeEventTokens allTokens(){
		
		return start()
			.withAccount()
			.withChangeRequest();
	
	}
	public static NameChangeEventTokens withoutListsTokens(){
		
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
	
	public NameChangeEventTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String ACCOUNT = "account";
	public String getAccount(){
		return ACCOUNT;
	}
	public NameChangeEventTokens withAccount(){		
		addSimpleOptions(ACCOUNT);
		return this;
	}
	
	
	protected static final String CHANGEREQUEST = "changeRequest";
	public String getChangeRequest(){
		return CHANGEREQUEST;
	}
	public NameChangeEventTokens withChangeRequest(){		
		addSimpleOptions(CHANGEREQUEST);
		return this;
	}
	
	
	
	public  NameChangeEventTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

