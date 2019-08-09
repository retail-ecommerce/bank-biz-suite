
package com.doublechain.bank.platform;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechain.bank.BaseEntity;
import com.doublechain.bank.SmartList;
import com.doublechain.bank.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.account.Account;

@JsonSerialize(using = PlatformSerializer.class)
public class Platform extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String FOUNDED_PROPERTY               = "founded"           ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String CHANGE_REQUEST_LIST                      = "changeRequestList" ;
	public static final String ACCOUNT_LIST                             = "accountList"       ;

	public static final String INTERNAL_TYPE="Platform";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mName               ;
	protected		DateTime            	mFounded            ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<ChangeRequest>	mChangeRequestList  ;
	protected		SmartList<Account>  	mAccountList        ;
	
		
	public 	Platform(){
		// lazy load for all the properties
	}
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){

		this.changed = true;
	}
	
	public 	Platform(String name, DateTime founded)
	{
		setName(name);
		setFounded(founded);

		this.mChangeRequestList = new SmartList<ChangeRequest>();
		this.mAccountList = new SmartList<Account>();	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(FOUNDED_PROPERTY.equals(property)){
			changeFoundedProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeNameProperty(String newValueExpr){
		String oldValue = getName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateName(newValue);
		this.onChangeProperty(NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeFoundedProperty(String newValueExpr){
		DateTime oldValue = getFounded();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateFounded(newValue);
		this.onChangeProperty(FOUNDED_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(FOUNDED_PROPERTY.equals(property)){
			return getFounded();
		}
		if(CHANGE_REQUEST_LIST.equals(property)){
			List<BaseEntity> list = getChangeRequestList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(ACCOUNT_LIST.equals(property)){
			List<BaseEntity> list = getAccountList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}

    		//other property not include here
		return super.propertyOf(property);
	}
    
    


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public Platform updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setName(String name){
		this.mName = trimString(name);;
	}
	public String getName(){
		return this.mName;
	}
	public Platform updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setFounded(DateTime founded){
		this.mFounded = founded;;
	}
	public DateTime getFounded(){
		return this.mFounded;
	}
	public Platform updateFounded(DateTime founded){
		this.mFounded = founded;;
		this.changed = true;
		return this;
	}
	public void mergeFounded(DateTime founded){
		setFounded(founded);
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Platform updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<ChangeRequest> getChangeRequestList(){
		if(this.mChangeRequestList == null){
			this.mChangeRequestList = new SmartList<ChangeRequest>();
			this.mChangeRequestList.setListInternalName (CHANGE_REQUEST_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mChangeRequestList;	
	}
	public  void setChangeRequestList(SmartList<ChangeRequest> changeRequestList){
		for( ChangeRequest changeRequest:changeRequestList){
			changeRequest.setPlatform(this);
		}

		this.mChangeRequestList = changeRequestList;
		this.mChangeRequestList.setListInternalName (CHANGE_REQUEST_LIST );
		
	}
	
	public  void addChangeRequest(ChangeRequest changeRequest){
		changeRequest.setPlatform(this);
		getChangeRequestList().add(changeRequest);
	}
	public  void addChangeRequestList(SmartList<ChangeRequest> changeRequestList){
		for( ChangeRequest changeRequest:changeRequestList){
			changeRequest.setPlatform(this);
		}
		getChangeRequestList().addAll(changeRequestList);
	}
	public  void mergeChangeRequestList(SmartList<ChangeRequest> changeRequestList){
		if(changeRequestList==null){
			return;
		}
		if(changeRequestList.isEmpty()){
			return;
		}
		addChangeRequestList( changeRequestList );
		
	}
	public  ChangeRequest removeChangeRequest(ChangeRequest changeRequestIndex){
		
		int index = getChangeRequestList().indexOf(changeRequestIndex);
        if(index < 0){
        	String message = "ChangeRequest("+changeRequestIndex.getId()+") with version='"+changeRequestIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ChangeRequest changeRequest = getChangeRequestList().get(index);        
        // changeRequest.clearPlatform(); //disconnect with Platform
        changeRequest.clearFromAll(); //disconnect with Platform
		
		boolean result = getChangeRequestList().planToRemove(changeRequest);
        if(!result){
        	String message = "ChangeRequest("+changeRequestIndex.getId()+") with version='"+changeRequestIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return changeRequest;
        
	
	}
	//断舍离
	public  void breakWithChangeRequest(ChangeRequest changeRequest){
		
		if(changeRequest == null){
			return;
		}
		changeRequest.setPlatform(null);
		//getChangeRequestList().remove();
	
	}
	
	public  boolean hasChangeRequest(ChangeRequest changeRequest){
	
		return getChangeRequestList().contains(changeRequest);
  
	}
	
	public void copyChangeRequestFrom(ChangeRequest changeRequest) {

		ChangeRequest changeRequestInList = findTheChangeRequest(changeRequest);
		ChangeRequest newChangeRequest = new ChangeRequest();
		changeRequestInList.copyTo(newChangeRequest);
		newChangeRequest.setVersion(0);//will trigger copy
		getChangeRequestList().add(newChangeRequest);
		addItemToFlexiableObject(COPIED_CHILD, newChangeRequest);
	}
	
	public  ChangeRequest findTheChangeRequest(ChangeRequest changeRequest){
		
		int index =  getChangeRequestList().indexOf(changeRequest);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ChangeRequest("+changeRequest.getId()+") with version='"+changeRequest.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getChangeRequestList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpChangeRequestList(){
		getChangeRequestList().clear();
	}
	
	
	


	public  SmartList<Account> getAccountList(){
		if(this.mAccountList == null){
			this.mAccountList = new SmartList<Account>();
			this.mAccountList.setListInternalName (ACCOUNT_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mAccountList;	
	}
	public  void setAccountList(SmartList<Account> accountList){
		for( Account account:accountList){
			account.setPlatform(this);
		}

		this.mAccountList = accountList;
		this.mAccountList.setListInternalName (ACCOUNT_LIST );
		
	}
	
	public  void addAccount(Account account){
		account.setPlatform(this);
		getAccountList().add(account);
	}
	public  void addAccountList(SmartList<Account> accountList){
		for( Account account:accountList){
			account.setPlatform(this);
		}
		getAccountList().addAll(accountList);
	}
	public  void mergeAccountList(SmartList<Account> accountList){
		if(accountList==null){
			return;
		}
		if(accountList.isEmpty()){
			return;
		}
		addAccountList( accountList );
		
	}
	public  Account removeAccount(Account accountIndex){
		
		int index = getAccountList().indexOf(accountIndex);
        if(index < 0){
        	String message = "Account("+accountIndex.getId()+") with version='"+accountIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Account account = getAccountList().get(index);        
        // account.clearPlatform(); //disconnect with Platform
        account.clearFromAll(); //disconnect with Platform
		
		boolean result = getAccountList().planToRemove(account);
        if(!result){
        	String message = "Account("+accountIndex.getId()+") with version='"+accountIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return account;
        
	
	}
	//断舍离
	public  void breakWithAccount(Account account){
		
		if(account == null){
			return;
		}
		account.setPlatform(null);
		//getAccountList().remove();
	
	}
	
	public  boolean hasAccount(Account account){
	
		return getAccountList().contains(account);
  
	}
	
	public void copyAccountFrom(Account account) {

		Account accountInList = findTheAccount(account);
		Account newAccount = new Account();
		accountInList.copyTo(newAccount);
		newAccount.setVersion(0);//will trigger copy
		getAccountList().add(newAccount);
		addItemToFlexiableObject(COPIED_CHILD, newAccount);
	}
	
	public  Account findTheAccount(Account account){
		
		int index =  getAccountList().indexOf(account);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Account("+account.getId()+") with version='"+account.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getAccountList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpAccountList(){
		getAccountList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){


		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getChangeRequestList(), internalType);
		collectFromList(this, entityList, getAccountList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getChangeRequestList());
		listOfList.add( getAccountList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, FOUNDED_PROPERTY, getFounded());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, CHANGE_REQUEST_LIST, getChangeRequestList());
		if(!getChangeRequestList().isEmpty()){
			appendKeyValuePair(result, "changeRequestCount", getChangeRequestList().getTotalCount());
			appendKeyValuePair(result, "changeRequestCurrentPageNumber", getChangeRequestList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, ACCOUNT_LIST, getAccountList());
		if(!getAccountList().isEmpty()){
			appendKeyValuePair(result, "accountCount", getAccountList().getTotalCount());
			appendKeyValuePair(result, "accountCurrentPageNumber", getAccountList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
		
			Platform dest =(Platform)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setFounded(getFounded());
			dest.setVersion(getVersion());
			dest.setChangeRequestList(getChangeRequestList());
			dest.setAccountList(getAccountList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
			
			Platform dest =(Platform)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeFounded(getFounded());
			dest.mergeVersion(getVersion());
			dest.mergeChangeRequestList(getChangeRequestList());
			dest.mergeAccountList(getAccountList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
			
			Platform dest =(Platform)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeFounded(getFounded());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Platform{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tfounded='"+getFounded()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

