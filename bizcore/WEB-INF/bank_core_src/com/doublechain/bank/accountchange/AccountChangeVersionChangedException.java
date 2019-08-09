
package com.doublechain.bank.accountchange;
import com.doublechain.bank.EntityNotFoundException;

public class AccountChangeVersionChangedException extends AccountChangeManagerException {
	private static final long serialVersionUID = 1L;
	public AccountChangeVersionChangedException(String string) {
		super(string);
	}


}


