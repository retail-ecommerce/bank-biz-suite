
package com.doublechain.bank.account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechain.bank.BaseRowMapper;
import com.doublechain.bank.platform.Platform;

public class AccountMapper extends BaseRowMapper<Account>{
	
	protected Account internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Account account = getAccount();		
		 		
 		setId(account, rs, rowNumber); 		
 		setName(account, rs, rowNumber); 		
 		setBalance(account, rs, rowNumber); 		
 		setCreateTime(account, rs, rowNumber); 		
 		setUpdateTime(account, rs, rowNumber); 		
 		setPlatform(account, rs, rowNumber); 		
 		setVersion(account, rs, rowNumber);

		return account;
	}
	
	protected Account getAccount(){
		return new Account();
	}		
		
	protected void setId(Account account, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(AccountTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		account.setId(id);
	}
		
	protected void setName(Account account, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(AccountTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		account.setName(name);
	}
		
	protected void setBalance(Account account, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		BigDecimal balance = rs.getBigDecimal(AccountTable.COLUMN_BALANCE);
		if(balance == null){
			//do nothing when nothing found in database
			return;
		}
		
		account.setBalance(balance);
	}
		
	protected void setCreateTime(Account account, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(AccountTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		account.setCreateTime(convertToDateTime(createTime));
	}
		
	protected void setUpdateTime(Account account, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date updateTime = rs.getTimestamp(AccountTable.COLUMN_UPDATE_TIME);
		if(updateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		account.setUpdateTime(convertToDateTime(updateTime));
	}
		 		
 	protected void setPlatform(Account account, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(AccountTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform lplatform = account.getPlatform();
 		if( lplatform != null ){
 			//if the root object 'account' already have the property, just set the id for it;
 			lplatform.setId(platformId);
 			
 			return;
 		}
 		account.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(Account account, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(AccountTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		account.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


