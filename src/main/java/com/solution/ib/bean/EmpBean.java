package com.solution.ib.bean;

import java.io.Serializable;
import java.util.Date;

public class EmpBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5494378722627978671L;
	private String empfName;
	private String empmName;
	private String emplName;
	private String password;
	private String empID;
	private String priAddr;
	private String secAddr;
	private String father;
	private String mother;
	private String spouse;
	private String aadhar;
	private String panCard;
	private String passport;
	private Date dOJ;
	private Date dOL;
	private String secMob;
	private String priMob;
	private String gender;
	private String bloodGroup;
	private String termsAndCondition;
	private String branch;
	private String OfficeAddr;
	private String desig;
	private double basicPay;
	private double DA;
	private double TA;
	private double HRA;
	private double rent;
	private double bonus;
	private double targetpay;
	private String lastCompany;
	private String pemail;
	private String semail;
	private int Status;
	private Date dOB;
	
	public Date getDOB() {
		return dOB;
	}
	public void setDOB(Date dOB) {
		this.dOB = dOB;
	}
	public Date getDOL() {
		return dOL;
	}
	public void setDOL(Date dOL) {
		this.dOL = dOL;
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
	public EmpBean() {
		super();
	}
	public String getEmpfName() {
		return empfName;
	}
	public void setEmpfName(String empfName) {
		this.empfName = empfName;
	}
	public String getEmpmName() {
		return empmName;
	}
	public void setEmpmName(String empmName) {
		this.empmName = empmName;
	}
	public String getEmplName() {
		return emplName;
	}
	public void setEmplName(String emplName) {
		this.emplName = emplName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
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
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String addhaar) {
		this.aadhar = addhaar;
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
		return dOJ;
	}
	public void setDOJ(Date dOJ) {
		this.dOJ = dOJ;
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
	public void setTermsAndCondition(String string) {
		this.termsAndCondition = string;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String workLocation) {
		this.branch = workLocation;
	}
	public String getOfficeAddr() {
		return OfficeAddr;
	}
	public void setOfficeAddr(String officeAddr) {
		OfficeAddr = officeAddr;
	}
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public double getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}
	public double getDA() {
		return DA;
	}
	public void setDA(double dA) {
		DA = dA;
	}
	public double getTA() {
		return TA;
	}
	public void setTA(double tA) {
		TA = tA;
	}
	public double getHRA() {
		return HRA;
	}
	public void setHRA(double hRA) {
		HRA = hRA;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public double getTargetpay() {
		return targetpay;
	}
	public void setTargetpay(double targetpay) {
		this.targetpay = targetpay;
	}
	public String getLastCompany() {
		return lastCompany;
	}
	public void setLastCompany(String lastCompany) {
		this.lastCompany = lastCompany;
	}
	
}
