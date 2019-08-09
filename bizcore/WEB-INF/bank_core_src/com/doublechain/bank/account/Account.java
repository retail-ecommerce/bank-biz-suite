
package com.doublechain.bank.account;

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

@JsonSerialize(using = AccountSerializer.class)
public class Account extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String BALANCE_PROPERTY               = "balance"           ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String UPDATE_TIME_PROPERTY           = "updateTime"        ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String TRANSACTION_LIST_AS_FROM_ACCOUNT         = "transactionListAsFromAccount";
	public static final String TRANSACTION_LIST_AS_TO_ACCOUNT           = "transactionListAsToAccount";
	public static final String ACCOUNT_CHANGE_LIST                      = "accountChangeList" ;

	public static final String INTERNAL_TYPE="Account";
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
	protected		BigDecimal          	mBalance            ;
	protected		DateTime            	mCreateTime         ;
	protected		DateTime            	mUpdateTime         ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Transaction>	mTransactionListAsFromAccount;
	protected		SmartList<Transaction>	mTransactionListAsToAccount;
	protected		SmartList<AccountChange>	mAccountChangeList  ;
	
		
	public 	Account(){
		// lazy load for all the properties
	}
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	public 	Account(String name, BigDecimal balance, DateTime createTime, DateTime updateTime, Platform platform)
	{
		setName(name);
		setBalance(balance);
		setCreateTime(createTime);
		setUpdateTime(updateTime);
		setPlatform(platform);

		this.mTransactionListAsFromAccount = new SmartList<Transaction>();
		this.mTransactionListAsToAccount = new SmartList<Transaction>();
		this.mAccountChangeList = new SmartList<AccountChange>();	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(BALANCE_PROPERTY.equals(property)){
			changeBalanceProperty(newValueExpr);
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			changeCreateTimeProperty(newValueExpr);
		}
		if(UPDATE_TIME_PROPERTY.equals(property)){
			changeUpdateTimeProperty(newValueExpr);
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
			
			
			
	protected void changeBalanceProperty(String newValueExpr){
		BigDecimal oldValue = getBalance();
		BigDecimal newValue = parseBigDecimal(newValueExpr);
		if(equalsBigDecimal(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateBalance(newValue);
		this.onChangeProperty(BALANCE_PROPERTY, oldValue, newValue);
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
			
			
			
	protected void changeUpdateTimeProperty(String newValueExpr){
		DateTime oldValue = getUpdateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateUpdateTime(newValue);
		this.onChangeProperty(UPDATE_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(BALANCE_PROPERTY.equals(property)){
			return getBalance();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(UPDATE_TIME_PROPERTY.equals(property)){
			return getUpdateTime();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(TRANSACTION_LIST_AS_FROM_ACCOUNT.equals(property)){
			List<BaseEntity> list = getTransactionListAsFromAccount().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(TRANSACTION_LIST_AS_TO_ACCOUNT.equals(property)){
			List<BaseEntity> list = getTransactionListAsToAccount().stream().map(item->item).collect(Collectors.toList());
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
	public Account updateId(String id){
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
	public Account updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setBalance(BigDecimal balance){
		this.mBalance = balance;;
	}
	public BigDecimal getBalance(){
		return this.mBalance;
	}
	public Account updateBalance(BigDecimal balance){
		this.mBalance = balance;;
		this.changed = true;
		return this;
	}
	public void mergeBalance(BigDecimal balance){
		setBalance(balance);
	}
	
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public Account updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setUpdateTime(DateTime updateTime){
		this.mUpdateTime = updateTime;;
	}
	public DateTime getUpdateTime(){
		return this.mUpdateTime;
	}
	public Account updateUpdateTime(DateTime updateTime){
		this.mUpdateTime = updateTime;;
		this.changed = true;
		return this;
	}
	public void mergeUpdateTime(DateTime updateTime){
		setUpdateTime(updateTime);
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public Account updatePlatform(Platform platform){
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
	public Account updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<Transaction> getTransactionListAsFromAccount(){
		if(this.mTransactionListAsFromAccount == null){
			this.mTransactionListAsFromAccount = new SmartList<Transaction>();
			this.mTransactionListAsFromAccount.setListInternalName (TRANSACTION_LIST_AS_FROM_ACCOUNT );
			//有名字，便于做权限控制
		}
		
		return this.mTransactionListAsFromAccount;	
	}
	public  void setTransactionListAsFromAccount(SmartList<Transaction> transactionListAsFromAccount){
		for( Transaction transaction:transactionListAsFromAccount){
			transaction.setFromAccount(this);
		}

		this.mTransactionListAsFromAccount = transactionListAsFromAccount;
		this.mTransactionListAsFromAccount.setListInternalName (TRANSACTION_LIST_AS_FROM_ACCOUNT );
		
	}
	
	public  void addTransactionAsFromAccount(Transaction transaction){
		transaction.setFromAccount(this);
		getTransactionListAsFromAccount().add(transaction);
	}
	public  void addTransactionListAsFromAccount(SmartList<Transaction> transactionListAsFromAccount){
		for( Transaction transaction:transactionListAsFromAccount){
			transaction.setFromAccount(this);
		}
		getTransactionListAsFromAccount().addAll(transactionListAsFromAccount);
	}
	public  void mergeTransactionListAsFromAccount(SmartList<Transaction> transactionListAsFromAccount){
		if(transactionListAsFromAccount==null){
			return;
		}
		if(transactionListAsFromAccount.isEmpty()){
			return;
		}
		addTransactionListAsFromAccount( transactionListAsFromAccount );
		
	}
	public  Transaction removeTransactionAsFromAccount(Transaction transactionIndex){
		
		int index = getTransactionListAsFromAccount().indexOf(transactionIndex);
        if(index < 0){
        	String message = "Transaction("+transactionIndex.getId()+") with version='"+transactionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Transaction transaction = getTransactionListAsFromAccount().get(index);        
        // transaction.clearFromAccount(); //disconnect with FromAccount
        transaction.clearFromAll(); //disconnect with FromAccount
		
		boolean result = getTransactionListAsFromAccount().planToRemove(transaction);
        if(!result){
        	String message = "Transaction("+transactionIndex.getId()+") with version='"+transactionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return transaction;
        
	
	}
	//断舍离
	public  void breakWithTransactionAsFromAccount(Transaction transaction){
		
		if(transaction == null){
			return;
		}
		transaction.setFromAccount(null);
		//getTransactionListAsFromAccount().remove();
	
	}
	
	public  boolean hasTransactionAsFromAccount(Transaction transaction){
	
		return getTransactionListAsFromAccount().contains(transaction);
  
	}
	
	public void copyTransactionAsFromAccountFrom(Transaction transaction) {

		Transaction transactionInList = findTheTransactionAsFromAccount(transaction);
		Transaction newTransaction = new Transaction();
		transactionInList.copyTo(newTransaction);
		newTransaction.setVersion(0);//will trigger copy
		getTransactionListAsFromAccount().add(newTransaction);
		addItemToFlexiableObject(COPIED_CHILD, newTransaction);
	}
	
	public  Transaction findTheTransactionAsFromAccount(Transaction transaction){
		
		int index =  getTransactionListAsFromAccount().indexOf(transaction);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Transaction("+transaction.getId()+") with version='"+transaction.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getTransactionListAsFromAccount().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpTransactionListAsFromAccount(){
		getTransactionListAsFromAccount().clear();
	}
	
	
	


	public  SmartList<Transaction> getTransactionListAsToAccount(){
		if(this.mTransactionListAsToAccount == null){
			this.mTransactionListAsToAccount = new SmartList<Transaction>();
			this.mTransactionListAsToAccount.setListInternalName (TRANSACTION_LIST_AS_TO_ACCOUNT );
			//有名字，便于做权限控制
		}
		
		return this.mTransactionListAsToAccount;	
	}
	public  void setTransactionListAsToAccount(SmartList<Transaction> transactionListAsToAccount){
		for( Transaction transaction:transactionListAsToAccount){
			transaction.setToAccount(this);
		}

		this.mTransactionListAsToAccount = transactionListAsToAccount;
		this.mTransactionListAsToAccount.setListInternalName (TRANSACTION_LIST_AS_TO_ACCOUNT );
		
	}
	
	public  void addTransactionAsToAccount(Transaction transaction){
		transaction.setToAccount(this);
		getTransactionListAsToAccount().add(transaction);
	}
	public  void addTransactionListAsToAccount(SmartList<Transaction> transactionListAsToAccount){
		for( Transaction transaction:transactionListAsToAccount){
			transaction.setToAccount(this);
		}
		getTransactionListAsToAccount().addAll(transactionListAsToAccount);
	}
	public  void mergeTransactionListAsToAccount(SmartList<Transaction> transactionListAsToAccount){
		if(transactionListAsToAccount==null){
			return;
		}
		if(transactionListAsToAccount.isEmpty()){
			return;
		}
		addTransactionListAsToAccount( transactionListAsToAccount );
		
	}
	public  Transaction removeTransactionAsToAccount(Transaction transactionIndex){
		
		int index = getTransactionListAsToAccount().indexOf(transactionIndex);
        if(index < 0){
        	String message = "Transaction("+transactionIndex.getId()+") with version='"+transactionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Transaction transaction = getTransactionListAsToAccount().get(index);        
        // transaction.clearToAccount(); //disconnect with ToAccount
        transaction.clearFromAll(); //disconnect with ToAccount
		
		boolean result = getTransactionListAsToAccount().planToRemove(transaction);
        if(!result){
        	String message = "Transaction("+transactionIndex.getId()+") with version='"+transactionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return transaction;
        
	
	}
	//断舍离
	public  void breakWithTransactionAsToAccount(Transaction transaction){
		
		if(transaction == null){
			return;
		}
		transaction.setFromAccount(null);
		//getTransactionListAsToAccount().remove();
	
	}
	
	public  boolean hasTransactionAsToAccount(Transaction transaction){
	
		return getTransactionListAsToAccount().contains(transaction);
  
	}
	
	public void copyTransactionAsToAccountFrom(Transaction transaction) {

		Transaction transactionInList = findTheTransactionAsToAccount(transaction);
		Transaction newTransaction = new Transaction();
		transactionInList.copyTo(newTransaction);
		newTransaction.setVersion(0);//will trigger copy
		getTransactionListAsToAccount().add(newTransaction);
		addItemToFlexiableObject(COPIED_CHILD, newTransaction);
	}
	
	public  Transaction findTheTransactionAsToAccount(Transaction transaction){
		
		int index =  getTransactionListAsToAccount().indexOf(transaction);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Transaction("+transaction.getId()+") with version='"+transaction.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getTransactionListAsToAccount().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpTransactionListAsToAccount(){
		getTransactionListAsToAccount().clear();
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
			accountChange.setAccount(this);
		}

		this.mAccountChangeList = accountChangeList;
		this.mAccountChangeList.setListInternalName (ACCOUNT_CHANGE_LIST );
		
	}
	
	public  void addAccountChange(AccountChange accountChange){
		accountChange.setAccount(this);
		getAccountChangeList().add(accountChange);
	}
	public  void addAccountChangeList(SmartList<AccountChange> accountChangeList){
		for( AccountChange accountChange:accountChangeList){
			accountChange.setAccount(this);
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
        // accountChange.clearAccount(); //disconnect with Account
        accountChange.clearFromAll(); //disconnect with Account
		
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
		accountChange.setAccount(null);
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
		collectFromList(this, entityList, getTransactionListAsFromAccount(), internalType);
		collectFromList(this, entityList, getTransactionListAsToAccount(), internalType);
		collectFromList(this, entityList, getAccountChangeList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getTransactionListAsFromAccount());
		listOfList.add( getTransactionListAsToAccount());
		listOfList.add( getAccountChangeList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, BALANCE_PROPERTY, getBalance());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, UPDATE_TIME_PROPERTY, getUpdateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, TRANSACTION_LIST_AS_FROM_ACCOUNT, getTransactionListAsFromAccount());
		if(!getTransactionListAsFromAccount().isEmpty()){
			appendKeyValuePair(result, "transactionAsFromAccountCount", getTransactionListAsFromAccount().getTotalCount());
			appendKeyValuePair(result, "transactionAsFromAccountCurrentPageNumber", getTransactionListAsFromAccount().getCurrentPageNumber());
		}
		appendKeyValuePair(result, TRANSACTION_LIST_AS_TO_ACCOUNT, getTransactionListAsToAccount());
		if(!getTransactionListAsToAccount().isEmpty()){
			appendKeyValuePair(result, "transactionAsToAccountCount", getTransactionListAsToAccount().getTotalCount());
			appendKeyValuePair(result, "transactionAsToAccountCurrentPageNumber", getTransactionListAsToAccount().getCurrentPageNumber());
		}
		appendKeyValuePair(result, ACCOUNT_CHANGE_LIST, getAccountChangeList());
		if(!getAccountChangeList().isEmpty()){
			appendKeyValuePair(result, "accountChangeCount", getAccountChangeList().getTotalCount());
			appendKeyValuePair(result, "accountChangeCurrentPageNumber", getAccountChangeList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Account){
		
		
			Account dest =(Account)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setBalance(getBalance());
			dest.setCreateTime(getCreateTime());
			dest.setUpdateTime(getUpdateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setTransactionListAsFromAccount(getTransactionListAsFromAccount());
			dest.setTransactionListAsToAccount(getTransactionListAsToAccount());
			dest.setAccountChangeList(getAccountChangeList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Account){
		
			
			Account dest =(Account)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeBalance(getBalance());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeUpdateTime(getUpdateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeTransactionListAsFromAccount(getTransactionListAsFromAccount());
			dest.mergeTransactionListAsToAccount(getTransactionListAsToAccount());
			dest.mergeAccountChangeList(getAccountChangeList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Account){
		
			
			Account dest =(Account)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeBalance(getBalance());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeUpdateTime(getUpdateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Account{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tbalance='"+getBalance()+"';");
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		stringBuilder.append("\tupdateTime='"+getUpdateTime()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

