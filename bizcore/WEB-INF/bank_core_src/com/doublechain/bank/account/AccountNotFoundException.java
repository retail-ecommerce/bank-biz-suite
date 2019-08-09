
package com.doublechain.bank.account;
import com.doublechain.bank.EntityNotFoundException;
public class AccountNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public AccountNotFoundException(String string) {
		super(string);
	}

}

