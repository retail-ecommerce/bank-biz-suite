
package com.doublechain.bank.genericform;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class GenericFormManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public GenericFormManagerException(String string) {
		super(string);
	}
	public GenericFormManagerException(Message message) {
		super(message);
	}
	public GenericFormManagerException(List<Message> messageList) {
		super(messageList);
	}

}


