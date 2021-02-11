package com.solution.ib.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solution.ib.bean.CustomerBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class UserRegistration1
 */
@WebServlet("/UserRegistration1")
public class UserRegistration1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration1() {
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
		Logger LOGGER = Logger.getLogger(UserRegistration1.class.getName());
		try {
		CustomerBean bean = new CustomerBean();
		bean.setCustfName(request.getParameter("fname"));
		bean.setCustlName(request.getParameter("lname"));
		bean.setPemail(request.getParameter("mail"));
		bean.setCustID(request.getParameter("uname"));
		String d = request.getParameter("dob");
		if(bean.getCustID()==null || bean.getCustfName()==null || bean.getCustlName()==null || bean.getPemail()==null || d==null) {
			response.sendRedirect("index.jsp?msg=regFailed");
			return;
		}else {
			Date date = null;
			try {
				date= new SimpleDateFormat("yyyy-MM-dd").parse(d);
			} catch (ParseException e) {
				try {
				response.sendRedirect("index.jsp?msg=regFailed");
				}catch (Exception e1) {
					LOGGER.log(Level.WARNING,"error",e1 );
				}
				return;
			}
	bean.setDOB(date);
	
	if(request.getParameter("passi").equals(request.getParameter("passc"))) {
		bean.setPassword(request.getParameter("passi"));
	}else {
		try {
		response.sendRedirect("index.jsp?msg=regFailed");
		}catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e );
			return;
		}
	}
	BankingDao dao = new BankingDao();
	boolean reg;
	try {
		reg = dao.createCustomer(bean);
	} catch (NoSuchAlgorithmException e) {
		LOGGER.log(Level.WARNING,"error",e);
		return;
	}
		if(dao.createCustomerDetails(bean)==true && reg==true) {
			try {
			response.sendRedirect("index.jsp?msg=reg");
			}catch (Exception e) {
				LOGGER.log(Level.WARNING,"error",e );
			}
		}else {
			try {
			response.sendRedirect("index.jsp?msg=regFailed");
			}catch (Exception e) {
				LOGGER.log(Level.WARNING,"error",e );
			}
		}
		}	
		}catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e );
		}
	}
}
