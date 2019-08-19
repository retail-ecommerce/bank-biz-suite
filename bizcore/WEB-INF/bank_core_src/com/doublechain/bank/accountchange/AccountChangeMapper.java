
package com.doublechain.bank.accountchange;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechain.bank.BaseRowMapper;
import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.account.Account;

public class AccountChangeMapper extends BaseRowMapper<AccountChange>{
	
	protected AccountChange internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		AccountChange accountChange = getAccountChange();		
		 		
 		setId(accountChange, rs, rowNumber); 		
 		setName(accountChange, rs, rowNumber); 		
 		setPreviousBalance(accountChange, rs, rowNumber); 		
 		setType(accountChange, rs, rowNumber); 		
 		setAmount(accountChange, rs, rowNumber); 		
 		setCurrentBalance(accountChange, rs, rowNumber); 		
 		setAccount(accountChange, rs, rowNumber); 		
 		setChangeRequest(accountChange, rs, rowNumber); 		
 		setCurrentStatus(accountChange, rs, rowNumber); 		
 		setVersion(accountChange, rs, rowNumber);

		return accountChange;
	}
	
	protected AccountChange getAccountChange(){
		return new AccountChange();
	}		
		
	protected void setId(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(AccountChangeTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountChange.setId(id);
	}
		
	protected void setName(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(AccountChangeTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountChange.setName(name);
	}
		
	protected void setPreviousBalance(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		BigDecimal previousBalance = rs.getBigDecimal(AccountChangeTable.COLUMN_PREVIOUS_BALANCE);
		if(previousBalance == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountChange.setPreviousBalance(previousBalance);
	}
		
	protected void setType(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String type = rs.getString(AccountChangeTable.COLUMN_TYPE);
		if(type == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountChange.setType(type);
	}
		
	protected void setAmount(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		BigDecimal amount = rs.getBigDecimal(AccountChangeTable.COLUMN_AMOUNT);
		if(amount == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountChange.setAmount(amount);
	}
		
	protected void setCurrentBalance(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		BigDecimal currentBalance = rs.getBigDecimal(AccountChangeTable.COLUMN_CURRENT_BALANCE);
		if(currentBalance == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountChange.setCurrentBalance(currentBalance);
	}
		 		
 	protected void setAccount(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
 		String accountId = rs.getString(AccountChangeTable.COLUMN_ACCOUNT);
 		if( accountId == null){
 			return;
 		}
 		if( accountId.isEmpty()){
 			return;
 		}
 		Account laccount = accountChange.getAccount();
 		if( laccount != null ){
 			//if the root object 'accountChange' already have the property, just set the id for it;
 			laccount.setId(accountId);
 			
 			return;
 		}
 		accountChange.setAccount(createEmptyAccount(accountId));
 	}
 	 		
 	protected void setChangeRequest(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(AccountChangeTable.COLUMN_CHANGE_REQUEST);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest lchangeRequest = accountChange.getChangeRequest();
 		if( lchangeRequest != null ){
 			//if the root object 'accountChange' already have the property, just set the id for it;
 			lchangeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		accountChange.setChangeRequest(createEmptyChangeRequest(changeRequestId));
 	}
 	
	protected void setCurrentStatus(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String currentStatus = rs.getString(AccountChangeTable.COLUMN_CURRENT_STATUS);
		if(currentStatus == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountChange.setCurrentStatus(currentStatus);
	}
		
	protected void setVersion(AccountChange accountChange, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(AccountChangeTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		accountChange.setVersion(version);
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


