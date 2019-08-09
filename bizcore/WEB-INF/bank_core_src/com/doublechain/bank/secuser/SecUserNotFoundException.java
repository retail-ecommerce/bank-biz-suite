
package com.doublechain.bank.secuser;
import com.doublechain.bank.EntityNotFoundException;
public class SecUserNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public SecUserNotFoundException(String string) {
		super(string);
	}

}

