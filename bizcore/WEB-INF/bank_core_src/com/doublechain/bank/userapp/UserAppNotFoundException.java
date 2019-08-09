
package com.doublechain.bank.userapp;
import com.doublechain.bank.EntityNotFoundException;
public class UserAppNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserAppNotFoundException(String string) {
		super(string);
	}

}

