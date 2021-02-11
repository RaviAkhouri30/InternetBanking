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

import com.solution.ib.bean.CustomerBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class CustomerCompleteDetails
 */
@WebServlet("/CustomerCompleteDetails")
public class CustomerCompleteDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerCompleteDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger LOGGER = Logger.getLogger(CustomerCompleteDetails.class.getName());
		try {
		PrintWriter out = response.getWriter();
		ArrayList<CustomerBean> dat = new ArrayList<CustomerBean>();
		BankingDao dao = new BankingDao();
		String id = request.getParameter("custid");
		dat = dao.getCustDetails(id,0);
		Iterator<CustomerBean> idat = dat.iterator();
		String data = new String();
		data ="<input type=\"image\" src=\"images/BackButton.jpg\" style=\"height: 50px; outline: none; width: 50px; margin-top: 20px; margin-right: 100px;\" onclick=\"allcustomers()\">";
		data = data + "<table>";
		while (idat.hasNext()) {
			CustomerBean bean = (CustomerBean) idat.next();
			data = data + "<tr>";
			data = data + "<td>Name of Customer:</td><td>"+bean.getCustfName()+" "+bean.getCustlName()+"</td>";
			data = data + "</tr>";
			data = data + "<tr>";
			data = data + "<tr>";
			data = data + "<td>Father 's Name:</td><td>"+bean.getFather()+"</td>";
			data = data + "</tr>";
			data = data + "<tr>";
			data = data + "<td>Mother 's Name:</td><td>"+bean.getMother()+"</td>";
			data = data + "</tr>";
			data = data + "<td>Mobile Number:</td><td>"+bean.getPriMob()+"</td>";
			data = data + "</tr>";
			data = data + "<tr>";
			data = data + "<td>Email:</td><td>"+bean.getPemail()+"</td>";
			data = data + "</tr>";
			data = data + "<tr>";
			data = data + "<td>Addhaar:</td><td>"+bean.getAddhaar()+"</td>";
			data = data + "</tr>";
			data = data + "<tr>";
			data = data + "<td>Pan Card:</td><td>"+bean.getPanCard()+"</td>";
			data = data + "</tr>";
			data = data + "<tr>";
			data = data + "<td>Passport Number:</td><td>"+bean.getPassport()+"</td>";
			data = data + "</tr>";
			data = data + "<tr>";
			data = data + "<td>Mailing Address:</td><td>"+bean.getOfficeAddr()+"</td>";
			data = data + "</tr>";
			data = data + "<tr>";
			data = data + "<td>Permanent Address:</td><td>"+bean.getSecAddr()+"</td>";
			data = data + "</tr>";
		}
		data = data + "</table>";
		data = data + "<input type=\"image\" src=\"images/BackButton.jpg\" style=\"height: 50px; outline: none; width: 50px; margin-top: 20px; margin-right: 100px;\" onclick=\"allcustomers()\">";
		out.print(data);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
