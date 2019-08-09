
package com.doublechain.bank.changerequest;
import com.doublechain.bank.EntityNotFoundException;
public class ChangeRequestNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestNotFoundException(String string) {
		super(string);
	}

}

