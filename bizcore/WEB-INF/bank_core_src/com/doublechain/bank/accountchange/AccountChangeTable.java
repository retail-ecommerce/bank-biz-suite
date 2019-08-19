
package com.doublechain.bank.accountchange;
import com.doublechain.bank.AccessKey;


public class AccountChangeTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="account_change_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_ACCOUNT = "account";
	static final String COLUMN_PREVIOUS_BALANCE = "previous_balance";
	static final String COLUMN_TYPE = "type";
	static final String COLUMN_AMOUNT = "amount";
	static final String COLUMN_CURRENT_BALANCE = "current_balance";
	static final String COLUMN_CHANGE_REQUEST = "change_request";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID, 
		COLUMN_NAME, COLUMN_ACCOUNT, COLUMN_PREVIOUS_BALANCE, COLUMN_TYPE, COLUMN_AMOUNT, COLUMN_CURRENT_BALANCE, COLUMN_CHANGE_REQUEST, 
		COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {
		COLUMN_NAME, COLUMN_ACCOUNT, COLUMN_PREVIOUS_BALANCE, COLUMN_TYPE, COLUMN_AMOUNT, COLUMN_CURRENT_BALANCE, COLUMN_CHANGE_REQUEST
		};
	
	
}


