
package com.doublechain.bank.changerequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechain.bank.BaseRowMapper;
import com.doublechain.bank.platform.Platform;

public class ChangeRequestMapper extends BaseRowMapper<ChangeRequest>{
	
	protected ChangeRequest internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		ChangeRequest changeRequest = getChangeRequest();		
		 		
 		setId(changeRequest, rs, rowNumber); 		
 		setName(changeRequest, rs, rowNumber); 		
 		setCreateTime(changeRequest, rs, rowNumber); 		
 		setPlatform(changeRequest, rs, rowNumber); 		
 		setVersion(changeRequest, rs, rowNumber);

		return changeRequest;
	}
	
	protected ChangeRequest getChangeRequest(){
		return new ChangeRequest();
	}		
		
	protected void setId(ChangeRequest changeRequest, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(ChangeRequestTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		changeRequest.setId(id);
	}
		
	protected void setName(ChangeRequest changeRequest, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(ChangeRequestTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		changeRequest.setName(name);
	}
		
	protected void setCreateTime(ChangeRequest changeRequest, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(ChangeRequestTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		changeRequest.setCreateTime(convertToDateTime(createTime));
	}
		 		
 	protected void setPlatform(ChangeRequest changeRequest, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(ChangeRequestTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform lplatform = changeRequest.getPlatform();
 		if( lplatform != null ){
 			//if the root object 'changeRequest' already have the property, just set the id for it;
 			lplatform.setId(platformId);
 			
 			return;
 		}
 		changeRequest.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(ChangeRequest changeRequest, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(ChangeRequestTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		changeRequest.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


