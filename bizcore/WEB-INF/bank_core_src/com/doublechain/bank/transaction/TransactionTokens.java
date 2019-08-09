
package com.doublechain.bank.transaction;
import com.doublechain.bank.CommonTokens;
import java.util.Map;
public class TransactionTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="transaction";
	
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
	protected TransactionTokens(){
		//ensure not initialized outside the class
	}
	public  static  TransactionTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		TransactionTokens tokens = new TransactionTokens(options);
		return tokens;
		
	}
	protected TransactionTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public TransactionTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static TransactionTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected TransactionTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static TransactionTokens start(){
		return new TransactionTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static TransactionTokens allTokens(){
		
		return start()
			.withFromAccount()
			.withToAccount()
			.withChangeRequest();
	
	}
	public static TransactionTokens withoutListsTokens(){
		
		return start()
			.withFromAccount()
			.withToAccount()
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
	
	public TransactionTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String FROMACCOUNT = "fromAccount";
	public String getFromAccount(){
		return FROMACCOUNT;
	}
	public TransactionTokens withFromAccount(){		
		addSimpleOptions(FROMACCOUNT);
		return this;
	}
	
	
	protected static final String TOACCOUNT = "toAccount";
	public String getToAccount(){
		return TOACCOUNT;
	}
	public TransactionTokens withToAccount(){		
		addSimpleOptions(TOACCOUNT);
		return this;
	}
	
	
	protected static final String CHANGEREQUEST = "changeRequest";
	public String getChangeRequest(){
		return CHANGEREQUEST;
	}
	public TransactionTokens withChangeRequest(){		
		addSimpleOptions(CHANGEREQUEST);
		return this;
	}
	
	
	
	public  TransactionTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

