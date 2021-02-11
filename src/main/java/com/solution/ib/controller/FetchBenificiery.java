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

import com.solution.ib.bean.Benifieceiry;
import com.solution.ib.bean.CustomerBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class FetchBenificiery
 */
@WebServlet("/FetchBenificiery")
public class FetchBenificiery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBenificiery() {
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
		Logger LOGGER = Logger.getLogger(FetchBenificiery.class.getName());
		try {
		CustomerBean bean = new CustomerBean();
		HttpSession session = request.getSession();
		bean = (CustomerBean) session.getAttribute("data");
		BankingDao dao = new BankingDao();
		ArrayList<Benifieceiry> ben = new ArrayList<Benifieceiry>();
		ben = dao.fetchBenifieceiry(bean.getCustID());
		String data = new String();
		data = "<table>";
		data = data + "<tr>";
		data = data + "<th>Name of Benificiery</th>";
		data = data + "<th>Account Number</th>";
		data = data + "<th>IFSC</th>";
		data = data + "<th> Transfer Money</th>";
		data = data + "</tr>";
		Iterator<Benifieceiry> i = ben.iterator();
		while (i.hasNext()) {
			Benifieceiry b1 = i.next();
			data = data + "<tr>";
			data = data + "<td>"+b1.getName()+"</td>";
			data = data + "<td>"+b1.getAccno()+"</td>";
			data = data + "<td>"+b1.getIfsc()+"</td>";
			data = data + "<td>"+"<div class=\"view-button\" onclick=\"transfer('"+b1.getAccno()+"')\">Transfer</div>"+"</td>";
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
