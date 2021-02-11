package com.solution.ib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.solution.ib.bean.AccDetails;
import com.solution.ib.bean.CustomerBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class CheckBalance
 */
@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBalance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger LOGGER = Logger.getLogger(CheckBalance.class.getName());
		try {
		CustomerBean bean = new CustomerBean();
		HttpSession ses = request.getSession();
		bean = (CustomerBean) ses.getAttribute("data");
		AccDetails det = new AccDetails();
		BankingDao dao = new BankingDao();
		det = dao.accdetails(bean.getCustID());
		String bal = det.getBalance();
		PrintWriter out = response.getWriter();
		out.print("<span class=\"acc_det\" id=\"accbal\">Account Balance: "+bal+"</span>");
	}catch (UnknownHostException e) {
		LOGGER.log(java.util.logging.Level.WARNING,"error", e);
	}catch (Exception e) {
		LOGGER.log(java.util.logging.Level.WARNING,"error", e);
	}
	}
}
