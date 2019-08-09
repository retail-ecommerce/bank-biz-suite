
package com.doublechain.bank.transaction;
import com.doublechain.bank.EntityNotFoundException;

public class TransactionVersionChangedException extends TransactionManagerException {
	private static final long serialVersionUID = 1L;
	public TransactionVersionChangedException(String string) {
		super(string);
	}


}


