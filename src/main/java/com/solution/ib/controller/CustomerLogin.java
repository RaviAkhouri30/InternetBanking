package com.solution.ib.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.solution.ib.bean.AccDetails;
import com.solution.ib.bean.CardBean;
import com.solution.ib.bean.CustomerBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class CustomerLogin
 */
@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger LOGGER = Logger.getLogger(CustomerLogin.class.getName());
    	try {
			BankingDao dao = new BankingDao();
			CustomerBean bean = new CustomerBean();
			HttpSession session = request.getSession();
			bean.setCustID(request.getParameter("uname1"));
			bean.setPassword(request.getParameter("pass"));
			bean.setStatus(dao.custStatus(bean));
			CustomerBean data = new CustomerBean();
			 if(bean.getStatus()==1 && dao.validateCustomer(bean)==true) {
				 data = dao.getCustDetails(bean.getCustID());
				 session.setAttribute("data", data);
				 response.sendRedirect("CustomerRegistrationForm.jsp");
			 }else if(bean.getStatus()==2 && dao.validateCustomer(bean)==true) {
				 response.sendRedirect("index.jsp?msg=review");
			 }else if(bean.getStatus()==3 && dao.validateCustomer(bean)==true) {
				 data = dao.getCustDetails(bean.getCustID());
				 data.setLastLogin(dao.lastLogin(bean.getCustID()));
				 data.setPassword(bean.getPassword());
				  if(data.getLastLogin()==null) {
					  dao.login_log(bean.getCustID());
					  session.setAttribute("data", data);
					  RequestDispatcher rd = request.getRequestDispatcher("GenerateAccountNumber");
					  rd.forward(request, response);
				  }else {
					  dao.login_log(bean.getCustID());
					  AccDetails det = new AccDetails();
					  det = dao.accdetails(bean.getCustID());
					  CardBean card = new CardBean();
					  card = dao.cardDetails(det.getAcc_No());
					  session.setAttribute("data", data);
					  session.setAttribute("acc_det", det);
					  session.setAttribute("card", card);
					  response.sendRedirect("CustomerHome.jsp");
				  }
			 }else {
				 response.sendRedirect("index.jsp?msg=invalid");
			 }
		} catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
    	 
	}
	}


