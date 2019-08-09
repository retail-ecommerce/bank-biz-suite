
package com.doublechain.bank.accountchange;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class AccountChangeManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public AccountChangeManagerException(String string) {
		super(string);
	}
	public AccountChangeManagerException(Message message) {
		super(message);
	}
	public AccountChangeManagerException(List<Message> messageList) {
		super(messageList);
	}

}


