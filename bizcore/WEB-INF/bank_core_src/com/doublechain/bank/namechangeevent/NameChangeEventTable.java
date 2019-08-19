
package com.doublechain.bank.namechangeevent;
import com.doublechain.bank.AccessKey;


public class NameChangeEventTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="name_change_event_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_ACCOUNT = "account";
	static final String COLUMN_CHANGE_REQUEST = "change_request";
	static final String COLUMN_CURRENT_STATUS = "current_status";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID, 
		COLUMN_NAME, COLUMN_ACCOUNT, COLUMN_CHANGE_REQUEST, COLUMN_CURRENT_STATUS, 
		COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {
		COLUMN_NAME, COLUMN_ACCOUNT, COLUMN_CHANGE_REQUEST, COLUMN_CURRENT_STATUS
		};
	
	
}


