package com.solution.ib.bean;

import java.io.Serializable;

public class AccDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1660634874857285952L;
	String accNo;
	String balance;
	String openingDate;
	String custid;
	String cardNo;
	String ifsc;
	public String getAcc_No() {
		return accNo;
	}
	public void setAcc_No(String acc_No) {
		accNo = acc_No;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getIFSC() {
		return ifsc;
	}
	public void setIFSC(String iFSC) {
		ifsc = iFSC;
	}
	
}
