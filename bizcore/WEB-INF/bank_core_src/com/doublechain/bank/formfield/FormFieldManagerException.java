
package com.doublechain.bank.formfield;
//import com.doublechain.bank.EntityNotFoundException;
import com.doublechain.bank.BankException;
import com.doublechain.bank.Message;
import java.util.List;

public class FormFieldManagerException extends BankException {
	private static final long serialVersionUID = 1L;
	public FormFieldManagerException(String string) {
		super(string);
	}
	public FormFieldManagerException(Message message) {
		super(message);
	}
	public FormFieldManagerException(List<Message> messageList) {
		super(messageList);
	}

}


