
package com.doublechain.bank.changerequest;
import com.doublechain.bank.EntityNotFoundException;

public class ChangeRequestVersionChangedException extends ChangeRequestManagerException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestVersionChangedException(String string) {
		super(string);
	}


}


