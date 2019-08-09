
package com.doublechain.bank.account;
import com.doublechain.bank.EntityNotFoundException;

public class AccountVersionChangedException extends AccountManagerException {
	private static final long serialVersionUID = 1L;
	public AccountVersionChangedException(String string) {
		super(string);
	}


}


