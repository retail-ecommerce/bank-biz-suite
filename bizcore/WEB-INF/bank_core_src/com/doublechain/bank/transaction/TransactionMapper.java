
package com.doublechain.bank.transaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechain.bank.BaseRowMapper;
import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.account.Account;

public class TransactionMapper extends BaseRowMapper<Transaction>{
	
	protected Transaction internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Transaction transaction = getTransaction();		
		 		
 		setId(transaction, rs, rowNumber); 		
 		setName(transaction, rs, rowNumber); 		
 		setFromAccount(transaction, rs, rowNumber); 		
 		setToAccount(transaction, rs, rowNumber); 		
 		setAmount(transaction, rs, rowNumber); 		
 		setType(transaction, rs, rowNumber); 		
 		setChangeRequest(transaction, rs, rowNumber); 		
 		setVersion(transaction, rs, rowNumber);

		return transaction;
	}
	
	protected Transaction getTransaction(){
		return new Transaction();
	}		
		
	protected void setId(Transaction transaction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(TransactionTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		transaction.setId(id);
	}
		
	protected void setName(Transaction transaction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(TransactionTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		transaction.setName(name);
	}
		 		
 	protected void setFromAccount(Transaction transaction, ResultSet rs, int rowNumber) throws SQLException{
 		String accountId = rs.getString(TransactionTable.COLUMN_FROM_ACCOUNT);
 		if( accountId == null){
 			return;
 		}
 		if( accountId.isEmpty()){
 			return;
 		}
 		Account laccount = transaction.getFromAccount();
 		if( laccount != null ){
 			//if the root object 'transaction' already have the property, just set the id for it;
 			laccount.setId(accountId);
 			
 			return;
 		}
 		transaction.setFromAccount(createEmptyFromAccount(accountId));
 	}
 	 		
 	protected void setToAccount(Transaction transaction, ResultSet rs, int rowNumber) throws SQLException{
 		String accountId = rs.getString(TransactionTable.COLUMN_TO_ACCOUNT);
 		if( accountId == null){
 			return;
 		}
 		if( accountId.isEmpty()){
 			return;
 		}
 		Account laccount = transaction.getToAccount();
 		if( laccount != null ){
 			//if the root object 'transaction' already have the property, just set the id for it;
 			laccount.setId(accountId);
 			
 			return;
 		}
 		transaction.setToAccount(createEmptyToAccount(accountId));
 	}
 	
	protected void setAmount(Transaction transaction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		BigDecimal amount = rs.getBigDecimal(TransactionTable.COLUMN_AMOUNT);
		if(amount == null){
			//do nothing when nothing found in database
			return;
		}
		
		transaction.setAmount(amount);
	}
		
	protected void setType(Transaction transaction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String type = rs.getString(TransactionTable.COLUMN_TYPE);
		if(type == null){
			//do nothing when nothing found in database
			return;
		}
		
		transaction.setType(type);
	}
		 		
 	protected void setChangeRequest(Transaction transaction, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(TransactionTable.COLUMN_CHANGE_REQUEST);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest lchangeRequest = transaction.getChangeRequest();
 		if( lchangeRequest != null ){
 			//if the root object 'transaction' already have the property, just set the id for it;
 			lchangeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		transaction.setChangeRequest(createEmptyChangeRequest(changeRequestId));
 	}
 	
	protected void setVersion(Transaction transaction, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(TransactionTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		transaction.setVersion(version);
	}
		
		

 	protected Account  createEmptyFromAccount(String accountId){
 		Account account = new Account();
 		account.setId(accountId);
 		account.setVersion(Integer.MAX_VALUE);
 		return account;
 	}
 	
 	protected Account  createEmptyToAccount(String accountId){
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


