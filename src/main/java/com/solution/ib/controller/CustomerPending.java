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
 * Servlet implementation class CustomerPending
 */
@WebServlet("/CustomerPending")
public class CustomerPending extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerPending() {
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
		Logger LOGGER = Logger.getLogger(CustomerPending.class.getName());
		try {
		ArrayList<CustomerBean> dat = new ArrayList<CustomerBean>();
		PrintWriter out = response.getWriter();
		BankingDao dao = new BankingDao();
		dat = dao.getCustDetails(2);
		String data = new String();
		data="<table>";
		data = data +"<tr>";
		data = data +"<th>Customer Name</th>";
		data = data +"<th>Branch<th>";
		data = data +"<th>email</th>";
		data = data +"<th>Mobile Number</th>";
		data = data +"<th>View Details</th>";
		data = data +"<th>Approve</th>";
		data = data +"<th>Reject</th>";
		data = data +"</tr>";
		Iterator<CustomerBean> idat = dat.iterator();
		while (idat.hasNext()) {
			CustomerBean bean = (CustomerBean) idat.next();
			data = data + "<tr>";
			data = data +"<td>"+bean.getCustfName()+" "+bean.getCustlName()+"</td>";
			data = data +"<td>"+bean.getBranch()+"<td>";
			data = data + "<td>"+bean.getPemail()+"</td>";
			data = data + "<td>"+bean.getPriMob()+"</td>";
			data = data + "<td><div class=\"view-button\" onclick=\"allDetails('"+bean.getCustID()+"')\">View</div></td>";
			data = data + "<td><div class=\"view-button\" onclick=\"approve('"+bean.getCustID()+"')\">Approve</div></td>";
			data = data + "<td><div class=\"view-button\" onclick=\"reject('"+bean.getCustID()+"')\">Reject</div></td>";
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
