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
 * Servlet implementation class EmpRecycleBin
 */
@WebServlet("/EmpRecycleBin")
public class EmpRecycleBin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpRecycleBin() {
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
		Logger LOGGER = Logger.getLogger(EmpRecycleBin.class.getName());
		try {
		PrintWriter out = response.getWriter();
		ArrayList<EmpBean> det = new ArrayList<EmpBean>();
		BankingDao dao = new BankingDao();
		det = dao.empRecycleBin();
		String data=new String();
		Iterator<EmpBean> i = det.iterator();
		data = "<h4>Warning</h4>";
		data = data + "<Data Once delected cannot be Restored Again!!!>";
		data=data+"<table>";
		data = data+"<tr>";
		data = data+"<th>Emp ID</th>";
		data = data + "<th>Employee Name</th>";
		data = data+"<th>Date of Joining</th>";
		data = data+ "<th>Branch</th>";
		data = data + "<th>Email</th>";
		data = data + "<th>View Details</th>";
		data = data + "<th>Restore</th>";
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
			data = data + "<td><div class=\"view-button\" onclick=\"view('"+bean.getEmpID()+"')\">View</div></td>";
			data = data + "<td><div class=\"view-button\" onclick=\"empRestore('"+bean.getEmpID()+"')\">Restore</div></td>";
			data = data + "<td><div class=\"view-button\" onclick=\"Permanentdelete('"+bean.getEmpID()+"')\">delete</div></td>";
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


