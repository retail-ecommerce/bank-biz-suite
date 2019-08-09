
package com.doublechain.bank.transaction;
import com.doublechain.bank.EntityNotFoundException;
public class TransactionNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public TransactionNotFoundException(String string) {
		super(string);
	}

}

