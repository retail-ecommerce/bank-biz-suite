
package com.doublechain.bank.userwhitelist;
import com.doublechain.bank.EntityNotFoundException;
public class UserWhiteListNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserWhiteListNotFoundException(String string) {
		super(string);
	}

}

