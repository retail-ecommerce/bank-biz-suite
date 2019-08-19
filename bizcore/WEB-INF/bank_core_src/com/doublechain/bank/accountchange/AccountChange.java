
package com.doublechain.bank.accountchange;

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

@JsonSerialize(using = AccountChangeSerializer.class)
public class AccountChange extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String ACCOUNT_PROPERTY               = "account"           ;
	public static final String PREVIOUS_BALANCE_PROPERTY      = "previousBalance"   ;
	public static final String TYPE_PROPERTY                  = "type"              ;
	public static final String AMOUNT_PROPERTY                = "amount"            ;
	public static final String CURRENT_BALANCE_PROPERTY       = "currentBalance"    ;
	public static final String CHANGE_REQUEST_PROPERTY        = "changeRequest"     ;
	public static final String VERSION_PROPERTY               = "version"           ;


	public static final String INTERNAL_TYPE="AccountChange";
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
	protected		Account             	mAccount            ;
	protected		BigDecimal          	mPreviousBalance    ;
	protected		String              	mType               ;
	protected		BigDecimal          	mAmount             ;
	protected		BigDecimal          	mCurrentBalance     ;
	protected		ChangeRequest       	mChangeRequest      ;
	protected		int                 	mVersion            ;
	
	
	
		
	public 	AccountChange(){
		// lazy load for all the properties
	}
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setAccount( null );
		setChangeRequest( null );

		this.changed = true;
	}
	
	public 	AccountChange(String name, Account account, BigDecimal previousBalance, String type, BigDecimal amount, BigDecimal currentBalance, ChangeRequest changeRequest)
	{
		setName(name);
		setAccount(account);
		setPreviousBalance(previousBalance);
		setType(type);
		setAmount(amount);
		setCurrentBalance(currentBalance);
		setChangeRequest(changeRequest);
	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(PREVIOUS_BALANCE_PROPERTY.equals(property)){
			changePreviousBalanceProperty(newValueExpr);
		}
		if(TYPE_PROPERTY.equals(property)){
			changeTypeProperty(newValueExpr);
		}
		if(AMOUNT_PROPERTY.equals(property)){
			changeAmountProperty(newValueExpr);
		}
		if(CURRENT_BALANCE_PROPERTY.equals(property)){
			changeCurrentBalanceProperty(newValueExpr);
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
			
			
			
	protected void changePreviousBalanceProperty(String newValueExpr){
		BigDecimal oldValue = getPreviousBalance();
		BigDecimal newValue = parseBigDecimal(newValueExpr);
		if(equalsBigDecimal(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updatePreviousBalance(newValue);
		this.onChangeProperty(PREVIOUS_BALANCE_PROPERTY, oldValue, newValue);
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
			
			
			
	protected void changeCurrentBalanceProperty(String newValueExpr){
		BigDecimal oldValue = getCurrentBalance();
		BigDecimal newValue = parseBigDecimal(newValueExpr);
		if(equalsBigDecimal(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateCurrentBalance(newValue);
		this.onChangeProperty(CURRENT_BALANCE_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(ACCOUNT_PROPERTY.equals(property)){
			return getAccount();
		}
		if(PREVIOUS_BALANCE_PROPERTY.equals(property)){
			return getPreviousBalance();
		}
		if(TYPE_PROPERTY.equals(property)){
			return getType();
		}
		if(AMOUNT_PROPERTY.equals(property)){
			return getAmount();
		}
		if(CURRENT_BALANCE_PROPERTY.equals(property)){
			return getCurrentBalance();
		}
		if(CHANGE_REQUEST_PROPERTY.equals(property)){
			return getChangeRequest();
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
	public AccountChange updateId(String id){
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
	public AccountChange updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setAccount(Account account){
		this.mAccount = account;;
	}
	public Account getAccount(){
		return this.mAccount;
	}
	public AccountChange updateAccount(Account account){
		this.mAccount = account;;
		this.changed = true;
		return this;
	}
	public void mergeAccount(Account account){
		if(account != null) { setAccount(account);}
	}
	
	
	public void clearAccount(){
		setAccount ( null );
		this.changed = true;
	}
	
	public void setPreviousBalance(BigDecimal previousBalance){
		this.mPreviousBalance = previousBalance;;
	}
	public BigDecimal getPreviousBalance(){
		return this.mPreviousBalance;
	}
	public AccountChange updatePreviousBalance(BigDecimal previousBalance){
		this.mPreviousBalance = previousBalance;;
		this.changed = true;
		return this;
	}
	public void mergePreviousBalance(BigDecimal previousBalance){
		setPreviousBalance(previousBalance);
	}
	
	
	public void setType(String type){
		this.mType = trimString(type);;
	}
	public String getType(){
		return this.mType;
	}
	public AccountChange updateType(String type){
		this.mType = trimString(type);;
		this.changed = true;
		return this;
	}
	public void mergeType(String type){
		if(type != null) { setType(type);}
	}
	
	
	public void setAmount(BigDecimal amount){
		this.mAmount = amount;;
	}
	public BigDecimal getAmount(){
		return this.mAmount;
	}
	public AccountChange updateAmount(BigDecimal amount){
		this.mAmount = amount;;
		this.changed = true;
		return this;
	}
	public void mergeAmount(BigDecimal amount){
		setAmount(amount);
	}
	
	
	public void setCurrentBalance(BigDecimal currentBalance){
		this.mCurrentBalance = currentBalance;;
	}
	public BigDecimal getCurrentBalance(){
		return this.mCurrentBalance;
	}
	public AccountChange updateCurrentBalance(BigDecimal currentBalance){
		this.mCurrentBalance = currentBalance;;
		this.changed = true;
		return this;
	}
	public void mergeCurrentBalance(BigDecimal currentBalance){
		setCurrentBalance(currentBalance);
	}
	
	
	public void setChangeRequest(ChangeRequest changeRequest){
		this.mChangeRequest = changeRequest;;
	}
	public ChangeRequest getChangeRequest(){
		return this.mChangeRequest;
	}
	public AccountChange updateChangeRequest(ChangeRequest changeRequest){
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
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public AccountChange updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getAccount(), internalType);
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
		appendKeyValuePair(result, ACCOUNT_PROPERTY, getAccount());
		appendKeyValuePair(result, PREVIOUS_BALANCE_PROPERTY, getPreviousBalance());
		appendKeyValuePair(result, TYPE_PROPERTY, getType());
		appendKeyValuePair(result, AMOUNT_PROPERTY, getAmount());
		appendKeyValuePair(result, CURRENT_BALANCE_PROPERTY, getCurrentBalance());
		appendKeyValuePair(result, CHANGE_REQUEST_PROPERTY, getChangeRequest());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof AccountChange){
		
		
			AccountChange dest =(AccountChange)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setAccount(getAccount());
			dest.setPreviousBalance(getPreviousBalance());
			dest.setType(getType());
			dest.setAmount(getAmount());
			dest.setCurrentBalance(getCurrentBalance());
			dest.setChangeRequest(getChangeRequest());
			dest.setVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof AccountChange){
		
			
			AccountChange dest =(AccountChange)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAccount(getAccount());
			dest.mergePreviousBalance(getPreviousBalance());
			dest.mergeType(getType());
			dest.mergeAmount(getAmount());
			dest.mergeCurrentBalance(getCurrentBalance());
			dest.mergeChangeRequest(getChangeRequest());
			dest.mergeVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof AccountChange){
		
			
			AccountChange dest =(AccountChange)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergePreviousBalance(getPreviousBalance());
			dest.mergeType(getType());
			dest.mergeAmount(getAmount());
			dest.mergeCurrentBalance(getCurrentBalance());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("AccountChange{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		if(getAccount() != null ){
 			stringBuilder.append("\taccount='Account("+getAccount().getId()+")';");
 		}
		stringBuilder.append("\tpreviousBalance='"+getPreviousBalance()+"';");
		stringBuilder.append("\ttype='"+getType()+"';");
		stringBuilder.append("\tamount='"+getAmount()+"';");
		stringBuilder.append("\tcurrentBalance='"+getCurrentBalance()+"';");
		if(getChangeRequest() != null ){
 			stringBuilder.append("\tchangeRequest='ChangeRequest("+getChangeRequest().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

