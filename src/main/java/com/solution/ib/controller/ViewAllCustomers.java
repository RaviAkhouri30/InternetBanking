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
 * Servlet implementation class ViewAllCustomers
 */
@WebServlet("/ViewAllCustomers")
public class ViewAllCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllCustomers() {
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
		Logger LOGGER = Logger.getLogger(ViewAllCustomers.class.getName());
		try {
		PrintWriter out = response.getWriter();
		ArrayList<CustomerBean> dat = new ArrayList<CustomerBean>();
		BankingDao dao = new BankingDao();
		dat = dao.getCustDetails();
		Iterator<CustomerBean> idat = dat.iterator();
		String data = new String();
		data="<table>";
		data = data +"<tr>";
		data = data +"<th>Customer Name</th>";
		data = data +"<th>Branch<th>";
		data = data +"<th>email</th>";
		data = data +"<th>Mobile Number</th>";
		data = data +"<th>Status</th>";
		data = data +"<th>View Details</th>";
		data = data +"<th>Delete</th>";
		data = data +"</tr>";
		while (idat.hasNext()) {
			CustomerBean bean = (CustomerBean) idat.next();
			data = data + "<tr>";
			data = data +"<td>"+bean.getCustfName()+" "+bean.getCustlName()+"</td>";
			data = data +"<td>"+bean.getBranch()+"<td>";
			data = data + "<td>"+bean.getPemail()+"</td>";
			data = data + "<td>"+bean.getPriMob()+"</td>";
			String status= new String();
			if(bean.getStatus()==1) {
				status = "Registration Pending";
			}
			if(bean.getStatus()==2) {
				status = "KYC Pending";
			}
			if(bean.getStatus()==3) {
				status = "KYC Done";
			}
			if(bean.getStatus()==4) {
				status = "Account Closed";
			}
			data = data + "<td>"+status+"</td>";
			data = data + "<td><div class=\"view-button\" onclick=\"allDetails('"+bean.getCustID()+"')\">View</div></td>";
			data = data + "<td><div class=\"view-button\" onclick=\"tempdel('"+bean.getCustID()+"')\">Delete</div></td>";
			data = data + "</tr>";
		}
		data = data +"</table>";
		out.print(data);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
