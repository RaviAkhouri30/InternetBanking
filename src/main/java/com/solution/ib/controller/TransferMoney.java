package com.solution.ib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.solution.ib.bean.AccDetails;
import com.solution.ib.bean.TransactionBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class TransferMoney
 */
@WebServlet("/TransferMoney")
public class TransferMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferMoney() {
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
		Logger LOGGER = Logger.getLogger(TransferMoney.class.getName());
		try {
		String toacc = request.getParameter("toaccno");
		AccDetails det = new AccDetails();
		HttpSession session = request.getSession();
		det = (AccDetails)session.getAttribute("acc_det");
		String fromacc = det.getAcc_No();
		String amt = request.getParameter("money");
		TransactionBean bean = new TransactionBean();
		bean.setAmount(amt);
		Date date = new Date();
		bean.setFromAccount(fromacc);
		bean.setToAccount(toacc);
		BankingDao dao = new BankingDao();
		PrintWriter out = response.getWriter();
		String toAccBal = dao.balance(toacc);
		String fromAccBal = dao.balance(fromacc);
		int amount = Integer.parseInt(amt);
		int toBal = Integer.parseInt(toAccBal);
		int frBal = Integer.parseInt(fromAccBal);
		if(frBal>amount) {
			toBal = toBal+amount;
			frBal = frBal-amount;
			StringBuffer toBal1 = new StringBuffer();
			toBal1.append(toBal);
			StringBuffer frBal1 = new StringBuffer();
			frBal1.append(frBal);
			dao.updateBalance(toacc, toBal1.toString());
			dao.updateBalance(fromacc, frBal1.toString());
			dao.transfer(bean, date);
			out.print("<div id = 'error'>WooHoo!!!Transferred Successfully!!!</div>");
		}else {
			out.print("<div id = 'error'>Transfer Failed! Transfer Amount is higher than Balance Available</div>");
		}
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
