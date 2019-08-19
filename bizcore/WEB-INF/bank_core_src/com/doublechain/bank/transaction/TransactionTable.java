
package com.doublechain.bank.transaction;
import com.doublechain.bank.AccessKey;


public class TransactionTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="transaction_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_FROM_ACCOUNT = "from_account";
	static final String COLUMN_TO_ACCOUNT = "to_account";
	static final String COLUMN_AMOUNT = "amount";
	static final String COLUMN_TYPE = "type";
	static final String COLUMN_CHANGE_REQUEST = "change_request";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID, 
		COLUMN_NAME, COLUMN_FROM_ACCOUNT, COLUMN_TO_ACCOUNT, COLUMN_AMOUNT, COLUMN_TYPE, COLUMN_CHANGE_REQUEST, 
		COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {
		COLUMN_NAME, COLUMN_FROM_ACCOUNT, COLUMN_TO_ACCOUNT, COLUMN_AMOUNT, COLUMN_TYPE, COLUMN_CHANGE_REQUEST
		};
	
	
}


