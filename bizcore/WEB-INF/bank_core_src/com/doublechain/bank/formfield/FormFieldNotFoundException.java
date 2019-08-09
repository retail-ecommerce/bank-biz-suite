
package com.doublechain.bank.formfield;
import com.doublechain.bank.EntityNotFoundException;
public class FormFieldNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public FormFieldNotFoundException(String string) {
		super(string);
	}

}

