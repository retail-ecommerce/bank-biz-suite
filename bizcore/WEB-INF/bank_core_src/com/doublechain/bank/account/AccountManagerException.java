
package com.doublechain.bank.account;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class AccountManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public AccountManagerException(String string) {
		super(string);
	}
	public AccountManagerException(Message message) {
		super(message);
	}
	public AccountManagerException(List<Message> messageList) {
		super(messageList);
	}

}


