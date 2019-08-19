
package com.doublechain.bank.namechangeevent;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class NameChangeEventManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public NameChangeEventManagerException(String string) {
		super(string);
	}
	public NameChangeEventManagerException(Message message) {
		super(message);
	}
	public NameChangeEventManagerException(List<Message> messageList) {
		super(messageList);
	}

}


