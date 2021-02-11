package com.solution.ib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class FetchBranchOnly
 */
@WebServlet("/FetchBranchOnly")
public class FetchBranchOnly extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBranchOnly() {
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
		Logger LOGGER = Logger.getLogger(FetchBranchOnly.class.getName());
		try {
		String bname = request.getParameter("bname");
		BankingDao dao = new BankingDao();
		ArrayList<String> branch = new ArrayList<String>();
		branch = dao.fetchBranch(bname);
		String data = new String();
		PrintWriter out = response.getWriter();
		data = "<datalist id ='branch_names'>";
		for (String string : branch) {
			data = data + "<option value='"+string+"'>";
		}
		data = data + "</datalist>";
		out.print(data);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
