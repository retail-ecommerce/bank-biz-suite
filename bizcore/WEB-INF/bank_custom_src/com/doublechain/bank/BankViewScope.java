package com.doublechain.bank;


import com.terapico.caf.viewpage.SerializeScope;

public class BankViewScope extends BankBaseViewScope{

	static {
		// 定制化本项目的序列化scope的代码放在这里
		System.out.println("**************************************************************\n定制序列化\n");
	}
	
	protected static BankViewScope instance = null;
	public static BankViewScope getInstance() {
		if (instance != null) {
			return instance;
		}
		synchronized (BankViewScope.class) {
			instance = new BankViewScope();
		}
		return instance;
	}
}







