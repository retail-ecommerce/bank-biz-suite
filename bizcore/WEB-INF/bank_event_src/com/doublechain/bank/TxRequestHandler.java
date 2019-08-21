package com.doublechain.bank;

import com.doublechain.bank.account.Account;
import com.doublechain.bank.accountchange.AccountChange;
import com.doublechain.bank.changerequest.ChangeRequest;
import com.doublechain.bank.transaction.Transaction;

public class TxRequestHandler extends RequestHandler{
	public void apply(BankUserContext userContext, ChangeRequest newReq, Transaction tx) throws Exception {
		
		Account a1 = accountManagerOf(userContext).loadAccount(userContext, 
				tx.getFromAccount().getId(), new String[] {});
		Account a2 = accountManagerOf(userContext).loadAccount(userContext, 
				tx.getToAccount().getId(), new String[] {});
		
		
		AccountChange ac1 = new AccountChange().updateAmount(tx.getAmount())
				.updateName(tx.getName()).updatePreviousBalance(a1.getBalance())
				.updateType("转出").updateCurrentBalance(a1.getBalance().subtract(tx.getAmount()))
				.updateChangeRequest(newReq);
		
		AccountChange ac2=	new AccountChange().updateAmount(tx.getAmount())
				.updateName(tx.getName()).updatePreviousBalance(a2.getBalance())
				.updateType("转入").updateCurrentBalance(a2.getBalance().add(tx.getAmount()))
				.updateChangeRequest(newReq);
		a1.addAccountChange(ac1);
		a2.addAccountChange(ac2);
		a1.updateBalance(a1.getBalance().subtract(tx.getAmount()));
		a2.updateBalance(a2.getBalance().add(tx.getAmount()));
		
		
		accountManagerOf(userContext).internalSaveAccount(userContext, a1);
		accountManagerOf(userContext).internalSaveAccount(userContext, a2);
		
	}
}
