
package com.doublechain.bank.listaccess;
import com.doublechain.bank.EntityNotFoundException;
public class ListAccessNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public ListAccessNotFoundException(String string) {
		super(string);
	}

}

