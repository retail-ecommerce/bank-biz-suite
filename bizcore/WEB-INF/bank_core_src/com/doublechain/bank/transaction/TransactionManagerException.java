
package com.doublechain.bank.transaction;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class TransactionManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public TransactionManagerException(String string) {
		super(string);
	}
	public TransactionManagerException(Message message) {
		super(message);
	}
	public TransactionManagerException(List<Message> messageList) {
		super(messageList);
	}

}


