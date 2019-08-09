
package com.doublechain.bank.formaction;
import com.doublechain.bank.EntityNotFoundException;

public class FormActionVersionChangedException extends FormActionManagerException {
	private static final long serialVersionUID = 1L;
	public FormActionVersionChangedException(String string) {
		super(string);
	}


}


