
package com.doublechain.bank.platform;
import com.doublechain.bank.EntityNotFoundException;
public class PlatformNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public PlatformNotFoundException(String string) {
		super(string);
	}

}

