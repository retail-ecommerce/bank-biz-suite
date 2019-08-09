
package com.doublechain.bank.account;
import com.doublechain.bank.AccessKey;


public class AccountTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	
	public static AccessKey withName(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_NAME);
		accessKey.setValue(value);
		return accessKey;
	}
	 

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="account_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_BALANCE = "balance";
	static final String COLUMN_CREATE_TIME = "create_time";
	static final String COLUMN_UPDATE_TIME = "update_time";
	static final String COLUMN_PLATFORM = "platform";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID, 
		COLUMN_NAME, COLUMN_BALANCE, COLUMN_CREATE_TIME, COLUMN_UPDATE_TIME, COLUMN_PLATFORM, 
		COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {
		COLUMN_NAME, COLUMN_BALANCE, COLUMN_CREATE_TIME, COLUMN_UPDATE_TIME, COLUMN_PLATFORM
		};
	
	
}


