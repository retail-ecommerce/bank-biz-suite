
package com.doublechain.bank.formfieldmessage;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class FormFieldMessageManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public FormFieldMessageManagerException(String string) {
		super(string);
	}
	public FormFieldMessageManagerException(Message message) {
		super(message);
	}
	public FormFieldMessageManagerException(List<Message> messageList) {
		super(messageList);
	}

}


