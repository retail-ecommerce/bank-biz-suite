
package com.doublechain.bank.formmessage;
import com.doublechain.bank.EntityNotFoundException;

public class FormMessageVersionChangedException extends FormMessageManagerException {
	private static final long serialVersionUID = 1L;
	public FormMessageVersionChangedException(String string) {
		super(string);
	}


}


