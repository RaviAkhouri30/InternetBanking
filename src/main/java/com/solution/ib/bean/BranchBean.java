package com.solution.ib.bean;

import java.io.Serializable;

public class BranchBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3552054723184409579L;
	private String ifsc;
	private String bname;
	private String city;
	private String state;
	private String country;
	public String getIFSC() {
	return ifsc;
	}
	public void setIFSC(String iFSC) {
	ifsc = iFSC;
	}
	public String getBname() {
	return bname;
	}
	public void setBname(String bname) {
	this.bname = bname;
	}
	public String getCity() {
	return city;
	}
	public void setCity(String city) {
	this.city = city;
	}
	public String getState() {
	return state;
	}
	public void setState(String state) {
	this.state = state;
	}
	public String getCountry() {
	return country;
	}
	public void setCountry(String country) {
	this.country = country;
	}
}
