
package com.doublechain.bank.transaction;

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

@JsonSerialize(using = TransactionSerializer.class)
public class Transaction extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String FROM_ACCOUNT_PROPERTY          = "fromAccount"       ;
	public static final String TO_ACCOUNT_PROPERTY            = "toAccount"         ;
	public static final String AMOUNT_PROPERTY                = "amount"            ;
	public static final String TYPE_PROPERTY                  = "type"              ;
	public static final String CHANGE_REQUEST_PROPERTY        = "changeRequest"     ;
	public static final String CURRENT_STATUS_PROPERTY        = "currentStatus"     ;
	public static final String VERSION_PROPERTY               = "version"           ;


	public static final String INTERNAL_TYPE="Transaction";
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
	protected		Account             	mFromAccount        ;
	protected		Account             	mToAccount          ;
	protected		BigDecimal          	mAmount             ;
	protected		String              	mType               ;
	protected		ChangeRequest       	mChangeRequest      ;
	protected		String              	mCurrentStatus      ;
	protected		int                 	mVersion            ;
	
	
	
		
	public 	Transaction(){
		// lazy load for all the properties
	}
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setFromAccount( null );
		setToAccount( null );
		setChangeRequest( null );

		this.changed = true;
	}
	
	public 	Transaction(String name, Account fromAccount, Account toAccount, BigDecimal amount, String type, String currentStatus)
	{
		setName(name);
		setFromAccount(fromAccount);
		setToAccount(toAccount);
		setAmount(amount);
		setType(type);
		setCurrentStatus(currentStatus);
	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(AMOUNT_PROPERTY.equals(property)){
			changeAmountProperty(newValueExpr);
		}
		if(TYPE_PROPERTY.equals(property)){
			changeTypeProperty(newValueExpr);
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
			
			
			
	protected void changeAmountProperty(String newValueExpr){
		BigDecimal oldValue = getAmount();
		BigDecimal newValue = parseBigDecimal(newValueExpr);
		if(equalsBigDecimal(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateAmount(newValue);
		this.onChangeProperty(AMOUNT_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeTypeProperty(String newValueExpr){
		String oldValue = getType();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateType(newValue);
		this.onChangeProperty(TYPE_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(FROM_ACCOUNT_PROPERTY.equals(property)){
			return getFromAccount();
		}
		if(TO_ACCOUNT_PROPERTY.equals(property)){
			return getToAccount();
		}
		if(AMOUNT_PROPERTY.equals(property)){
			return getAmount();
		}
		if(TYPE_PROPERTY.equals(property)){
			return getType();
		}
		if(CHANGE_REQUEST_PROPERTY.equals(property)){
			return getChangeRequest();
		}
		if(CURRENT_STATUS_PROPERTY.equals(property)){
			return getCurrentStatus();
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
	public Transaction updateId(String id){
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
	public Transaction updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setFromAccount(Account fromAccount){
		this.mFromAccount = fromAccount;;
	}
	public Account getFromAccount(){
		return this.mFromAccount;
	}
	public Transaction updateFromAccount(Account fromAccount){
		this.mFromAccount = fromAccount;;
		this.changed = true;
		return this;
	}
	public void mergeFromAccount(Account fromAccount){
		if(fromAccount != null) { setFromAccount(fromAccount);}
	}
	
	
	public void clearFromAccount(){
		setFromAccount ( null );
		this.changed = true;
	}
	
	public void setToAccount(Account toAccount){
		this.mToAccount = toAccount;;
	}
	public Account getToAccount(){
		return this.mToAccount;
	}
	public Transaction updateToAccount(Account toAccount){
		this.mToAccount = toAccount;;
		this.changed = true;
		return this;
	}
	public void mergeToAccount(Account toAccount){
		if(toAccount != null) { setToAccount(toAccount);}
	}
	
	
	public void clearToAccount(){
		setToAccount ( null );
		this.changed = true;
	}
	
	public void setAmount(BigDecimal amount){
		this.mAmount = amount;;
	}
	public BigDecimal getAmount(){
		return this.mAmount;
	}
	public Transaction updateAmount(BigDecimal amount){
		this.mAmount = amount;;
		this.changed = true;
		return this;
	}
	public void mergeAmount(BigDecimal amount){
		setAmount(amount);
	}
	
	
	public void setType(String type){
		this.mType = trimString(type);;
	}
	public String getType(){
		return this.mType;
	}
	public Transaction updateType(String type){
		this.mType = trimString(type);;
		this.changed = true;
		return this;
	}
	public void mergeType(String type){
		if(type != null) { setType(type);}
	}
	
	
	public void setChangeRequest(ChangeRequest changeRequest){
		this.mChangeRequest = changeRequest;;
	}
	public ChangeRequest getChangeRequest(){
		return this.mChangeRequest;
	}
	public Transaction updateChangeRequest(ChangeRequest changeRequest){
		this.mChangeRequest = changeRequest;;
		this.changed = true;
		return this;
	}
	public void mergeChangeRequest(ChangeRequest changeRequest){
		if(changeRequest != null) { setChangeRequest(changeRequest);}
	}
	
	
	public void clearChangeRequest(){
		setChangeRequest ( null );
		this.changed = true;
	}
	
	public void setCurrentStatus(String currentStatus){
		this.mCurrentStatus = trimString(currentStatus);;
	}
	public String getCurrentStatus(){
		return this.mCurrentStatus;
	}
	public Transaction updateCurrentStatus(String currentStatus){
		this.mCurrentStatus = trimString(currentStatus);;
		this.changed = true;
		return this;
	}
	public void mergeCurrentStatus(String currentStatus){
		if(currentStatus != null) { setCurrentStatus(currentStatus);}
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Transaction updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getFromAccount(), internalType);
		addToEntityList(this, entityList, getToAccount(), internalType);
		addToEntityList(this, entityList, getChangeRequest(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, FROM_ACCOUNT_PROPERTY, getFromAccount());
		appendKeyValuePair(result, TO_ACCOUNT_PROPERTY, getToAccount());
		appendKeyValuePair(result, AMOUNT_PROPERTY, getAmount());
		appendKeyValuePair(result, TYPE_PROPERTY, getType());
		appendKeyValuePair(result, CHANGE_REQUEST_PROPERTY, getChangeRequest());
		appendKeyValuePair(result, CURRENT_STATUS_PROPERTY, getCurrentStatus());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Transaction){
		
		
			Transaction dest =(Transaction)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setFromAccount(getFromAccount());
			dest.setToAccount(getToAccount());
			dest.setAmount(getAmount());
			dest.setType(getType());
			dest.setChangeRequest(getChangeRequest());
			dest.setCurrentStatus(getCurrentStatus());
			dest.setVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Transaction){
		
			
			Transaction dest =(Transaction)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeFromAccount(getFromAccount());
			dest.mergeToAccount(getToAccount());
			dest.mergeAmount(getAmount());
			dest.mergeType(getType());
			dest.mergeChangeRequest(getChangeRequest());
			dest.mergeCurrentStatus(getCurrentStatus());
			dest.mergeVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Transaction){
		
			
			Transaction dest =(Transaction)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAmount(getAmount());
			dest.mergeType(getType());
			dest.mergeCurrentStatus(getCurrentStatus());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Transaction{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		if(getFromAccount() != null ){
 			stringBuilder.append("\tfromAccount='Account("+getFromAccount().getId()+")';");
 		}
		if(getToAccount() != null ){
 			stringBuilder.append("\ttoAccount='Account("+getToAccount().getId()+")';");
 		}
		stringBuilder.append("\tamount='"+getAmount()+"';");
		stringBuilder.append("\ttype='"+getType()+"';");
		if(getChangeRequest() != null ){
 			stringBuilder.append("\tchangeRequest='ChangeRequest("+getChangeRequest().getId()+")';");
 		}
		stringBuilder.append("\tcurrentStatus='"+getCurrentStatus()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

