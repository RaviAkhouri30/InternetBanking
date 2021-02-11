package com.solution.ib.bean;

import java.io.Serializable;

public class CardBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4894563848291832447L;
	String cardNo;
	String exp;
	String cvv;
	String accNo;
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getAcc_no() {
		return accNo;
	}
	public void setAcc_no(String acc_no) {
		this.accNo = acc_no;
	}
}
