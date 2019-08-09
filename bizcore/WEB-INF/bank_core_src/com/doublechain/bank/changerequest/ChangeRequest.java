
package com.doublechain.bank.changerequest;

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
import com.doublechain.bank.platform.Platform;
import com.doublechain.bank.transaction.Transaction;
import com.doublechain.bank.accountchange.AccountChange;

@JsonSerialize(using = ChangeRequestSerializer.class)
public class ChangeRequest extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String TRANSACTION_LIST                         = "transactionList"   ;
	public static final String ACCOUNT_CHANGE_LIST                      = "accountChangeList" ;

	public static final String INTERNAL_TYPE="ChangeRequest";
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
	protected		DateTime            	mCreateTime         ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Transaction>	mTransactionList    ;
	protected		SmartList<AccountChange>	mAccountChangeList  ;
	
		
	public 	ChangeRequest(){
		// lazy load for all the properties
	}
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	public 	ChangeRequest(String name, DateTime createTime, Platform platform)
	{
		setName(name);
		setCreateTime(createTime);
		setPlatform(platform);

		this.mTransactionList = new SmartList<Transaction>();
		this.mAccountChangeList = new SmartList<AccountChange>();	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			changeCreateTimeProperty(newValueExpr);
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
			
			
			
	protected void changeCreateTimeProperty(String newValueExpr){
		DateTime oldValue = getCreateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateCreateTime(newValue);
		this.onChangeProperty(CREATE_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(TRANSACTION_LIST.equals(property)){
			List<BaseEntity> list = getTransactionList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(ACCOUNT_CHANGE_LIST.equals(property)){
			List<BaseEntity> list = getAccountChangeList().stream().map(item->item).collect(Collectors.toList());
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
	public ChangeRequest updateId(String id){
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
	public ChangeRequest updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public ChangeRequest updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public ChangeRequest updatePlatform(Platform platform){
		this.mPlatform = platform;;
		this.changed = true;
		return this;
	}
	public void mergePlatform(Platform platform){
		if(platform != null) { setPlatform(platform);}
	}
	
	
	public void clearPlatform(){
		setPlatform ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public ChangeRequest updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<Transaction> getTransactionList(){
		if(this.mTransactionList == null){
			this.mTransactionList = new SmartList<Transaction>();
			this.mTransactionList.setListInternalName (TRANSACTION_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mTransactionList;	
	}
	public  void setTransactionList(SmartList<Transaction> transactionList){
		for( Transaction transaction:transactionList){
			transaction.setChangeRequest(this);
		}

		this.mTransactionList = transactionList;
		this.mTransactionList.setListInternalName (TRANSACTION_LIST );
		
	}
	
	public  void addTransaction(Transaction transaction){
		transaction.setChangeRequest(this);
		getTransactionList().add(transaction);
	}
	public  void addTransactionList(SmartList<Transaction> transactionList){
		for( Transaction transaction:transactionList){
			transaction.setChangeRequest(this);
		}
		getTransactionList().addAll(transactionList);
	}
	public  void mergeTransactionList(SmartList<Transaction> transactionList){
		if(transactionList==null){
			return;
		}
		if(transactionList.isEmpty()){
			return;
		}
		addTransactionList( transactionList );
		
	}
	public  Transaction removeTransaction(Transaction transactionIndex){
		
		int index = getTransactionList().indexOf(transactionIndex);
        if(index < 0){
        	String message = "Transaction("+transactionIndex.getId()+") with version='"+transactionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Transaction transaction = getTransactionList().get(index);        
        // transaction.clearChangeRequest(); //disconnect with ChangeRequest
        transaction.clearFromAll(); //disconnect with ChangeRequest
		
		boolean result = getTransactionList().planToRemove(transaction);
        if(!result){
        	String message = "Transaction("+transactionIndex.getId()+") with version='"+transactionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return transaction;
        
	
	}
	//断舍离
	public  void breakWithTransaction(Transaction transaction){
		
		if(transaction == null){
			return;
		}
		transaction.setChangeRequest(null);
		//getTransactionList().remove();
	
	}
	
	public  boolean hasTransaction(Transaction transaction){
	
		return getTransactionList().contains(transaction);
  
	}
	
	public void copyTransactionFrom(Transaction transaction) {

		Transaction transactionInList = findTheTransaction(transaction);
		Transaction newTransaction = new Transaction();
		transactionInList.copyTo(newTransaction);
		newTransaction.setVersion(0);//will trigger copy
		getTransactionList().add(newTransaction);
		addItemToFlexiableObject(COPIED_CHILD, newTransaction);
	}
	
	public  Transaction findTheTransaction(Transaction transaction){
		
		int index =  getTransactionList().indexOf(transaction);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Transaction("+transaction.getId()+") with version='"+transaction.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getTransactionList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpTransactionList(){
		getTransactionList().clear();
	}
	
	
	


	public  SmartList<AccountChange> getAccountChangeList(){
		if(this.mAccountChangeList == null){
			this.mAccountChangeList = new SmartList<AccountChange>();
			this.mAccountChangeList.setListInternalName (ACCOUNT_CHANGE_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mAccountChangeList;	
	}
	public  void setAccountChangeList(SmartList<AccountChange> accountChangeList){
		for( AccountChange accountChange:accountChangeList){
			accountChange.setChangeRequest(this);
		}

		this.mAccountChangeList = accountChangeList;
		this.mAccountChangeList.setListInternalName (ACCOUNT_CHANGE_LIST );
		
	}
	
	public  void addAccountChange(AccountChange accountChange){
		accountChange.setChangeRequest(this);
		getAccountChangeList().add(accountChange);
	}
	public  void addAccountChangeList(SmartList<AccountChange> accountChangeList){
		for( AccountChange accountChange:accountChangeList){
			accountChange.setChangeRequest(this);
		}
		getAccountChangeList().addAll(accountChangeList);
	}
	public  void mergeAccountChangeList(SmartList<AccountChange> accountChangeList){
		if(accountChangeList==null){
			return;
		}
		if(accountChangeList.isEmpty()){
			return;
		}
		addAccountChangeList( accountChangeList );
		
	}
	public  AccountChange removeAccountChange(AccountChange accountChangeIndex){
		
		int index = getAccountChangeList().indexOf(accountChangeIndex);
        if(index < 0){
        	String message = "AccountChange("+accountChangeIndex.getId()+") with version='"+accountChangeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        AccountChange accountChange = getAccountChangeList().get(index);        
        // accountChange.clearChangeRequest(); //disconnect with ChangeRequest
        accountChange.clearFromAll(); //disconnect with ChangeRequest
		
		boolean result = getAccountChangeList().planToRemove(accountChange);
        if(!result){
        	String message = "AccountChange("+accountChangeIndex.getId()+") with version='"+accountChangeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return accountChange;
        
	
	}
	//断舍离
	public  void breakWithAccountChange(AccountChange accountChange){
		
		if(accountChange == null){
			return;
		}
		accountChange.setChangeRequest(null);
		//getAccountChangeList().remove();
	
	}
	
	public  boolean hasAccountChange(AccountChange accountChange){
	
		return getAccountChangeList().contains(accountChange);
  
	}
	
	public void copyAccountChangeFrom(AccountChange accountChange) {

		AccountChange accountChangeInList = findTheAccountChange(accountChange);
		AccountChange newAccountChange = new AccountChange();
		accountChangeInList.copyTo(newAccountChange);
		newAccountChange.setVersion(0);//will trigger copy
		getAccountChangeList().add(newAccountChange);
		addItemToFlexiableObject(COPIED_CHILD, newAccountChange);
	}
	
	public  AccountChange findTheAccountChange(AccountChange accountChange){
		
		int index =  getAccountChangeList().indexOf(accountChange);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "AccountChange("+accountChange.getId()+") with version='"+accountChange.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getAccountChangeList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpAccountChangeList(){
		getAccountChangeList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getTransactionList(), internalType);
		collectFromList(this, entityList, getAccountChangeList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getTransactionList());
		listOfList.add( getAccountChangeList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, TRANSACTION_LIST, getTransactionList());
		if(!getTransactionList().isEmpty()){
			appendKeyValuePair(result, "transactionCount", getTransactionList().getTotalCount());
			appendKeyValuePair(result, "transactionCurrentPageNumber", getTransactionList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, ACCOUNT_CHANGE_LIST, getAccountChangeList());
		if(!getAccountChangeList().isEmpty()){
			appendKeyValuePair(result, "accountChangeCount", getAccountChangeList().getTotalCount());
			appendKeyValuePair(result, "accountChangeCurrentPageNumber", getAccountChangeList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ChangeRequest){
		
		
			ChangeRequest dest =(ChangeRequest)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setCreateTime(getCreateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setTransactionList(getTransactionList());
			dest.setAccountChangeList(getAccountChangeList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ChangeRequest){
		
			
			ChangeRequest dest =(ChangeRequest)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeTransactionList(getTransactionList());
			dest.mergeAccountChangeList(getAccountChangeList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ChangeRequest){
		
			
			ChangeRequest dest =(ChangeRequest)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("ChangeRequest{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

