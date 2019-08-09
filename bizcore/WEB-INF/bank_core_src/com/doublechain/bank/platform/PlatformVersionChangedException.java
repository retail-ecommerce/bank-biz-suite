
package com.doublechain.bank.platform;
import com.doublechain.bank.EntityNotFoundException;

public class PlatformVersionChangedException extends PlatformManagerException {
	private static final long serialVersionUID = 1L;
	public PlatformVersionChangedException(String string) {
		super(string);
	}


}


