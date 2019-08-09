package com.doublechain.bank;

import java.util.ArrayList;
import java.util.List;

public class CommonChangeRequest {
	
	public List<ChangeRequestItem> getItemList() {
		if(itemList==null) {
			itemList = new ArrayList<ChangeRequestItem>();
		}
		
		return itemList;
	}
	
	public void setItemList(List<ChangeRequestItem> itemList) {
		this.itemList = itemList;
	}
	
	private List<ChangeRequestItem> itemList;
	
	
	
	
}
