
package com.doublechain.bank.secuserblocking;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class SecUserBlockingManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public SecUserBlockingManagerException(String string) {
		super(string);
	}
	public SecUserBlockingManagerException(Message message) {
		super(message);
	}
	public SecUserBlockingManagerException(List<Message> messageList) {
		super(messageList);
	}

}


