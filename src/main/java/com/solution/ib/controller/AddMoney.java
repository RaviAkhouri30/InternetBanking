package com.solution.ib.controller;

import java.io.IOException;
import java.util.logging.Level;
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
 * Servlet implementation class AddMoney
 */
@WebServlet("/AddMoney")
public class AddMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMoney() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger LOGGER = Logger.getLogger(AddMoney.class.getName());
		String amt = request.getParameter("amt");
		String accno = request.getParameter("accno");
		CustomerBean bean = new CustomerBean();
		HttpSession ses = request.getSession();
		bean = (CustomerBean) ses.getAttribute("data");
		AccDetails det = new AccDetails();
		BankingDao dao = new BankingDao();
		det = dao.accdetails(bean.getCustID());
		int amount =0;
		int bal =0;
		try {
			bal = Integer.parseInt(det.getBalance());
			amount = Integer.parseInt(amt);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
		bal=amount+bal;
		StringBuffer baltemp = new StringBuffer();
		baltemp.append(bal);
		String balance = baltemp.toString();
		try {
			dao.updateBalance(accno, balance);
		} catch (NumberFormatException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
	}

}
