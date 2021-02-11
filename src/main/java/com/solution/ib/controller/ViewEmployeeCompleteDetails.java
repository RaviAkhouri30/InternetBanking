package com.solution.ib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solution.ib.bean.EmpBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class ViewEmployeeCompleteDetails
 */
@WebServlet("/ViewEmployeeCompleteDetails")
public class ViewEmployeeCompleteDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployeeCompleteDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger LOGGER = Logger.getLogger(ViewEmployeeCompleteDetails.class.getName());
		try {
		PrintWriter print = response.getWriter();
		ArrayList<EmpBean> empData = new ArrayList<EmpBean>();
		String id = request.getParameter("empid");
		BankingDao data = new BankingDao();
		empData = data.empView(id);
		Iterator<EmpBean> iData = empData.iterator();
		String send ="<input type=\"image\" src=\"images/BackButton.jpg\" style=\"height: 50px; outline: none; width: 50px; margin-top: 20px; margin-right: 100px;\" onclick=\"view()\">";
		send = send + "<table>";
		while (iData.hasNext()) {
			EmpBean empBean = (EmpBean) iData.next();
			send = send +"<tr><td> Employee ID: </td><td>"+empBean.getEmpID()+"</td></tr>";
			send = send + "<tr><td>Name of Employee: </td><td>"+empBean.getEmpfName()+" "+empBean.getEmplName()+"</td></tr>";
			send = send + "<tr><td>Designation: </td><td>"+empBean.getDesig()+"</td></tr>";
			send = send + "<tr><td>Father's Name: </td><td>"+empBean.getFather()+"</td></tr>";
			send = send + "<tr><td>Mother's Name: </td><td>"+empBean.getMother()+"</td></tr>";
			send = send + "<tr><td>Aadhaar: </td><td>"+empBean.getAadhar()+"</td></tr>";
			send = send + "<tr><td>Pan Card Number: </td><td>"+empBean.getPanCard()+"</td></tr>";
			send = send + "<tr><td>Passport Number: </td><td>"+empBean.getPassport()+"</td></tr>";
			send = send + "<tr><td>Blood Group: </td><td>"+empBean.getBloodGroup()+"</td></tr>";
			String status = new String();
			if(empBean.getStatus()==1) {
				status ="Registration Pending";
			}
			if(empBean.getStatus()==2) {
				status ="Review Pending";
			}
			if(empBean.getStatus()==3) {
				status ="Verification Done";
			}
			if(empBean.getStatus()==4) {
				status ="Resigned job";
			}
			send = send + "<tr><td>Account Status: </td><td>"+status+"</td></tr>";
			send = send + "<tr><td>Last Company: </td><td>"+empBean.getLastCompany()+"</td></tr>";
			send = send + "<tr><td>Date of Birth: </td><td>"+empBean.getDOB()+"</td></tr>";
			send = send + "<tr><td>Date of Leaving: </td><td>"+empBean.getDOL()+"</td></tr>";
			send = send + "<tr><td>Branch: </td><td>"+empBean.getBranch()+"</td></tr>";
			send = send + "<tr><td>Primary Mobile: </td><td>"+empBean.getPriMob()+"</td></tr>";
			send = send + "<tr><td>Secondary Mobile: </td><td>"+empBean.getSecMob()+"</td></tr>";
			send = send + "<tr><td>Present Address: </td><td>"+empBean.getPriAddr()+"</td></tr>";
			send = send + "<tr><td>Permanent Address: </td><td>"+empBean.getSecAddr()+"</td></tr>";
		}
		send = send +"</table>";
		send =send +"<input type=\"image\" src=\"images/BackButton.jpg\" style=\"height: 50px; outline: none; width: 50px; margin-top: 20px; margin-right: 100px;\" onclick=\"view()\">";
		print.print(send);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
