
package com.doublechain.bank.changerequest;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class ChangeRequestManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestManagerException(String string) {
		super(string);
	}
	public ChangeRequestManagerException(Message message) {
		super(message);
	}
	public ChangeRequestManagerException(List<Message> messageList) {
		super(messageList);
	}

}


