
package com.doublechain.bank.namechangeevent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechain.bank.BaseRowMapper;
import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.account.Account;

public class NameChangeEventMapper extends BaseRowMapper<NameChangeEvent>{
	
	protected NameChangeEvent internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		NameChangeEvent nameChangeEvent = getNameChangeEvent();		
		 		
 		setId(nameChangeEvent, rs, rowNumber); 		
 		setName(nameChangeEvent, rs, rowNumber); 		
 		setAccount(nameChangeEvent, rs, rowNumber); 		
 		setChangeRequest(nameChangeEvent, rs, rowNumber); 		
 		setVersion(nameChangeEvent, rs, rowNumber);

		return nameChangeEvent;
	}
	
	protected NameChangeEvent getNameChangeEvent(){
		return new NameChangeEvent();
	}		
		
	protected void setId(NameChangeEvent nameChangeEvent, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(NameChangeEventTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		nameChangeEvent.setId(id);
	}
		
	protected void setName(NameChangeEvent nameChangeEvent, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(NameChangeEventTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		nameChangeEvent.setName(name);
	}
		 		
 	protected void setAccount(NameChangeEvent nameChangeEvent, ResultSet rs, int rowNumber) throws SQLException{
 		String accountId = rs.getString(NameChangeEventTable.COLUMN_ACCOUNT);
 		if( accountId == null){
 			return;
 		}
 		if( accountId.isEmpty()){
 			return;
 		}
 		Account laccount = nameChangeEvent.getAccount();
 		if( laccount != null ){
 			//if the root object 'nameChangeEvent' already have the property, just set the id for it;
 			laccount.setId(accountId);
 			
 			return;
 		}
 		nameChangeEvent.setAccount(createEmptyAccount(accountId));
 	}
 	 		
 	protected void setChangeRequest(NameChangeEvent nameChangeEvent, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(NameChangeEventTable.COLUMN_CHANGE_REQUEST);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest lchangeRequest = nameChangeEvent.getChangeRequest();
 		if( lchangeRequest != null ){
 			//if the root object 'nameChangeEvent' already have the property, just set the id for it;
 			lchangeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		nameChangeEvent.setChangeRequest(createEmptyChangeRequest(changeRequestId));
 	}
 	
	protected void setVersion(NameChangeEvent nameChangeEvent, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(NameChangeEventTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		nameChangeEvent.setVersion(version);
	}
		
		

 	protected Account  createEmptyAccount(String accountId){
 		Account account = new Account();
 		account.setId(accountId);
 		account.setVersion(Integer.MAX_VALUE);
 		return account;
 	}
 	
 	protected ChangeRequest  createEmptyChangeRequest(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


