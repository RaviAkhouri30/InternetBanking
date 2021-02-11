package com.solution.ib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HttpServletBean;
import javax.servlet.annotation.WebServlet;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class Acc_Status
 */
@WebServlet("/Acc_Status")
public class Acc_Status extends HttpServletBean {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Acc_Status() {
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
		Logger LOGGER = Logger.getLogger(Acc_Status.class.getName());
		try {
		String custid = request.getParameter("status");
		BankingDao dao = new BankingDao();
		String output = new String();
		String msg = new String();
		int st = dao.AccStatus(custid);
		
		if(st==0) {
			msg ="!!!Customer ID Not Found!!!";
		}else if(st==1) {
			msg ="!!!Please Complete Your Registration to be part of Our Bank!!!";
		}else if(st==2) {
			msg = "Your Application for new Account is Under Verification. We will inform you Once your Varification is Done";
		}else if(st==3) {
			msg ="Wohoo, Your Account is Activated and Ready to Use!!!";
		}else if(st==4) {
			msg = "Sorry! Your Account is Not Active!!!";
		}else {
			msg ="!!!Customer ID Not Found!!!";
		}
		output = "<table>";
		output = output+"<tr>";
		output = output+"<th>Customer ID</th>";
		output = output+"<th>Status</th>";
		output = output+"</tr>";
		output = output +"<tr>";
		output = output+"<td>"+custid+"</td>";
		output = output+"<td>"+msg+"</td>";
		output = output +"</tr>";
		output = output +"</table>";
			PrintWriter out = response.getWriter();
			out.print(output);
			out.close();
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}
}
