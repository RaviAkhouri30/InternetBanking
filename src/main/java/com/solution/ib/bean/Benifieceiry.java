package com.solution.ib.bean;

import java.io.Serializable;

public class Benifieceiry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2619103399034976236L;
	String id = new String();
	String accno = new String();
	String acctype = new String();
	String bank = new String();
	String bname = new String();
	String ifsc = new String();
	String name = new String();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getAcctype() {
		return acctype;
	}
	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
