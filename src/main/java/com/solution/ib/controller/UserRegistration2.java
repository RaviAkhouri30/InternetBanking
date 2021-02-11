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

import com.solution.ib.bean.CustomerBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class UserRegistration2
 */
@WebServlet("/UserRegistration2")
public class UserRegistration2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration2() {
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
		Logger LOGGER = Logger.getLogger(UserRegistration2.class.getName());
		try {
		CustomerBean bean = new CustomerBean();
		CustomerBean data = new CustomerBean();
		HttpSession session = request.getSession();
		data = (CustomerBean)session.getAttribute("data");
		bean.setCustID(data.getCustID());
		bean.setCustfName(request.getParameter("fname"));
		bean.setCustlName(request.getParameter("lname"));
		bean.setCustmName(request.getParameter("mname"));
		bean.setFather(request.getParameter("father"));
		bean.setMother(request.getParameter("mother"));
		bean.setSpouse(request.getParameter("spouse"));
		bean.setAddhaar(request.getParameter("aadhar"));
		bean.setPanCard(request.getParameter("pancard"));
		bean.setPassport(request.getParameter("passport"));
		bean.setGender(request.getParameter("gender"));
		bean.setBranch(request.getParameter("bname"));
		bean.setSemail(request.getParameter("mail"));
		bean.setPriMob(request.getParameter("mob"));
		bean.setSecMob(request.getParameter("mob2"));
		bean.setSecAddr(request.getParameter("al1")+" "+request.getParameter("al3")+" "+request.getParameter("state1")+" "+request.getParameter("city1")+" "+request.getParameter("country1")+" "+request.getParameter("pin1"));
		bean.setPriAddr(request.getParameter("al2")+" "+request.getParameter("al4")+" "+request.getParameter("state2")+" "+request.getParameter("city2")+" "+request.getParameter("country2")+" "+request.getParameter("pin2"));
		bean.setTermsAndCondition(request.getParameter("agree"));
		BankingDao dao = new BankingDao();
		if(dao.setCustDetails(bean)==true && dao.setStatusInTBL(bean.getCustID())) {
			session.removeAttribute("data");
			response.sendRedirect("index.jsp?msg=registered");
		}else {
			session.removeAttribute("data");
			response.sendRedirect("index.jsp?msg=Failed");
		}
		}catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e );
		}
	}

}
