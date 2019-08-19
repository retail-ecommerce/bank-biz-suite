
package com.doublechain.bank.namechangeevent;
import com.doublechain.bank.EntityNotFoundException;

public class NameChangeEventVersionChangedException extends NameChangeEventManagerException {
	private static final long serialVersionUID = 1L;
	public NameChangeEventVersionChangedException(String string) {
		super(string);
	}


}


