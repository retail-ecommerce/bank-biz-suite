
package com.doublechain.bank.platform;
import com.doublechain.bank.AccessKey;


public class PlatformTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="platform_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_FOUNDER = "founder";
	static final String COLUMN_FOUNDED = "founded";
	static final String COLUMN_DESCRIPTION = "description";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID, 
		COLUMN_NAME, COLUMN_FOUNDER, COLUMN_FOUNDED, COLUMN_DESCRIPTION, 
		COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {
		COLUMN_NAME, COLUMN_FOUNDER, COLUMN_FOUNDED, COLUMN_DESCRIPTION
		};
	
	
}


