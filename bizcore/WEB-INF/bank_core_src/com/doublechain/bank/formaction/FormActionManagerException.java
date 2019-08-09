
package com.doublechain.bank.formaction;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class FormActionManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public FormActionManagerException(String string) {
		super(string);
	}
	public FormActionManagerException(Message message) {
		super(message);
	}
	public FormActionManagerException(List<Message> messageList) {
		super(messageList);
	}

}









