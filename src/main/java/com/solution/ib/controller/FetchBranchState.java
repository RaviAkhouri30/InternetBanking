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
 * Servlet implementation class FetchBranchState
 */
@WebServlet("/FetchBranchState")
public class FetchBranchState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBranchState() {
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
		Logger LOGGER = Logger.getLogger(FetchBranchState.class.getName());
		try {
		String state = request.getParameter("bstate");
		BankingDao dao = new BankingDao();
		ArrayList<String> st = new ArrayList<>();
		st=dao.fetchBranchState(state);
		String msg = new String();
		PrintWriter out = response.getWriter();
		msg="<datalist id='branch_states'>"; 
		for (String str :st) { 
			msg =msg+"<option value='"+str+"'> "; 
			} 
		msg = msg +"</datalist>";
		out.print(msg);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
