
package com.doublechain.bank.accountchange;
import com.doublechain.bank.EntityNotFoundException;
public class AccountChangeNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public AccountChangeNotFoundException(String string) {
		super(string);
	}

}

