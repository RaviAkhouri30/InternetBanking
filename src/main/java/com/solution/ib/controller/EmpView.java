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
 * Servlet implementation class empView
 */
@WebServlet("/EmpView")
public class EmpView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpView() {
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
		Logger LOGGER = Logger.getLogger(EmpView.class.getName());
		try {
		PrintWriter out = response.getWriter();
		ArrayList<EmpBean> det = new ArrayList<EmpBean>();
		BankingDao dao = new BankingDao();
		det = dao.empView();
		String data=new String();
		Iterator<EmpBean> i = det.iterator();
		data="<table>";
		data = data+"<tr>";
		data = data+"<th>Emp ID</th>";
		data = data + "<th>Employee Name</th>";
		data = data+"<th>Date of Joining</th>";
		data = data+ "<th>Branch</th>";
		data = data + "<th>Email</th>";
		data = data + "<th>Status</th>";
		data = data + "<th>View Details</th>";
		data = data + "<th>Edit</th>";
		data = data + "<th>Delete</th>";
		data = data +"</tr>";
		while (i.hasNext()) {
			EmpBean bean = new EmpBean();
			bean=i.next();
			data = data + "<tr>";
			data = data + "<td>"+bean.getEmpID()+"</td>";
			data = data + "<td>"+bean.getEmpfName()+" "+bean.getEmplName()+"</td>";
			data = data + "<td>"+bean.getDOJ()+"</td>";
			data = data + "<td>"+bean.getBranch()+"</td>";
			data = data + "<td>"+bean.getPemail()+"</td>";
			String status = new String();
			if(bean.getStatus()==1) {
				status ="Registration Pending";
			}
			if(bean.getStatus()==2) {
				status ="Review Pending";
			}
			if(bean.getStatus()==3) {
				status ="Verification Done";
			}
			if(bean.getStatus()==4) {
				status ="Resigned job";
			}
			data = data + "<td>"+status+"</td>";
			data = data + "<td><div class=\"view-button\" onclick=\"viewcompletedetails('"+bean.getEmpID()+"')\">View</div></td>";
			data = data + "<td><div class=\"view-button\" onclick=\"edit('"+bean.getEmpID()+"','"+bean.getEmpfName()+"','"+bean.getEmplName()+"','"+bean.getDOJ()+"','"+bean.getDOB()+"','"+bean.getBranch()+"','"+bean.getPemail()+"')\">edit</div></td>";
			data = data + "<td><div class=\"view-button\" onclick=\"tempdelete('"+bean.getEmpID()+"')\">delete</div></td>";
			data = data + "</tr>";
		}
		data= data + "</table>";
		out.print(data);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}
}
