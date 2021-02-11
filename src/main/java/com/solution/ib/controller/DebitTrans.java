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
import javax.servlet.http.HttpSession;

import com.solution.ib.bean.AccDetails;
import com.solution.ib.bean.TransactionBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class DebitTrans
 */
@WebServlet("/DebitTrans")
public class DebitTrans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DebitTrans() {
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
		Logger LOGGER = Logger.getLogger(DebitTrans.class.getName());
		try {
		ArrayList<TransactionBean> dat = new ArrayList<TransactionBean>();
		BankingDao dao = new BankingDao();
		AccDetails det = new AccDetails();
		HttpSession session = request.getSession();
		det = (AccDetails) session.getAttribute("acc_det");
		dat = dao.getDebitTransaction(det.getAcc_No());
		Iterator<TransactionBean> i = dat.iterator();
		String data = new String();
		data = "<table>";
		data = data + "<tr>";
		data = data + "<th>Transaction Id</th>";
		data = data + "<th>To Account</th>";
		data = data + "<th>Amount</th>";
		data = data + "<th>Date of Transaction</th>";
		data = data + "</tr>";
		while (i.hasNext()) {
			TransactionBean transactionBean = (TransactionBean) i.next();
			data = data + "<tr>";
			data = data + "<td>"+ transactionBean.getId()+"</td>";
			data = data + "<td>"+ transactionBean.getToAccount()+"</td>";
			data = data + "<td>"+ transactionBean.getAmount()+"</td>";
			data = data + "<td>" + transactionBean.getDateoftrans()+"</td>";
			data = data + "</tr>";
		}
		data = data + "</table>";
		PrintWriter out = response.getWriter();
		out.print(data);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
