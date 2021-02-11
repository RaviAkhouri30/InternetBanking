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

import com.solution.ib.bean.BranchBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class ViewBranch
 */
@WebServlet("/ViewBranch")
public class ViewBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBranch() {
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
		Logger LOGGER = Logger.getLogger(ViewBranch.class.getName());
		try {
		PrintWriter out = response.getWriter();
		ArrayList<BranchBean> dat = new ArrayList<BranchBean>();
		BankingDao dao = new BankingDao();
		dat = dao.branchView();
		Iterator<BranchBean> idat = dat.iterator();
		String data = new String();
		data = "<table>";
		data = data +"<tr>";
		data = data + "<th>IFSC</th>";
		data = data + "<th>Branch Name</th>";
		data = data + "<th>City</th>";
		data = data + "<th>State</th>";
		data = data + "<th>Country</th>";
		data = data + "<th>Delete</th>";
		data = data + "</tr>";
		while (idat.hasNext()) {
			BranchBean branchBean = (BranchBean) idat.next();
			data = data + "<tr>";
			data = data + "<td>"+branchBean.getIFSC()+"</td>";
			data = data + "<td>"+branchBean.getBname()+"</td>";
			data = data + "<td>"+branchBean.getCity()+"</td>";
			data = data + "<td>"+branchBean.getState()+"</td>";
			data = data + "<td>"+branchBean.getCountry()+"</td>";
			data = data + "<td><div class=\"view-button\" onclick=\"branchdel('"+branchBean.getIFSC()+"')\">delete</div></td>";
			data = data + "</tr>";
		}
		data = data + "</table>";
		out.print(data);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
