package com.solution.ib.bean;

import java.io.Serializable;
import java.util.Date;

public class CustomerBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4962056699234189658L;
	private String custfName;
	private String custmName;
	private String custlName;
	private String password;
	private String custID;
	private String priAddr;
	private String secAddr;
	private String father;
	private String mother;
	private String spouse;
	private String addhaar;
	private String panCard;
	private String passport;
	private Date DOJ;
	private String secMob;
	private String priMob;
	private String gender;
	private String bloodGroup;
	private String termsAndCondition;
	private int Status;
	private String OfficeAddr;
	private double basicPay;
	private String pemail;
	private String semail;
	private String employement;
	private Date DOB;
	private String branch;
	private String lastLogin;
	
	public String getBranch()  {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public CustomerBean() {
		super();
	}
	public String getCustfName() {
		return custfName;
	}
	public void setCustfName(String custfName) {
		this.custfName = custfName;
	}
	public String getCustmName() {
		return custmName;
	}
	public void setCustmName(String custmName) {
		this.custmName = custmName;
	}
	public String getCustlName() {
		return custlName;
	}
	public void setCustlName(String custlName) {
		this.custlName = custlName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getPriAddr() {
		return priAddr;
	}
	public void setPriAddr(String priAddr) {
		this.priAddr = priAddr;
	}
	public String getSecAddr() {
		return secAddr;
	}
	public void setSecAddr(String secAddr) {
		this.secAddr = secAddr;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getSpouse() {
		return spouse;
	}
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}
	public String getAddhaar() {
		return addhaar;
	}
	public void setAddhaar(String addhaar) {
		this.addhaar = addhaar;
	}
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public Date getDOJ() {
		return DOJ;
	}
	public void setDOJ(Date dOJ) {
		DOJ = dOJ;
	}
	public String getSecMob() {
		return secMob;
	}
	public void setSecMob(String secMob) {
		this.secMob = secMob;
	}
	public String getPriMob() {
		return priMob;
	}
	public void setPriMob(String priMob) {
		this.priMob = priMob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getTermsAndCondition() {
		return termsAndCondition;
	}
	public void setTermsAndCondition(String termsAndCondition) {
		this.termsAndCondition = termsAndCondition;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getOfficeAddr() {
		return OfficeAddr;
	}
	public void setOfficeAddr(String officeAddr) {
		OfficeAddr = officeAddr;
	}
	public double getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}
	public String getPemail() {
		return pemail;
	}
	public void setPemail(String pemail) {
		this.pemail = pemail;
	}
	public String getSemail() {
		return semail;
	}
	public void setSemail(String semail) {
		this.semail = semail;
	}
	public String getEmployment() {
		return employement;
	}
	public void setEmployment(String employemnet) {
		this.employement = employemnet;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}
