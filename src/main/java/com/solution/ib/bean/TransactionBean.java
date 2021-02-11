package com.solution.ib.bean;

import java.io.Serializable;

public class TransactionBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5460406284550048375L;
	String toAccount;
	String fromAccount;
	String dateoftrans;
	String amount;
	String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getDateoftrans() {
		return dateoftrans;
	}
	public void setDateoftrans(String dateoftrans) {
		this.dateoftrans = dateoftrans;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
