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

import com.solution.ib.bean.EmpBean;
import com.solution.ib.dao.BankingDao;

//import jdk.nashorn.internal.ir.SetSplitState;

/**
 * Servlet implementation class EmployeeDetails
 */
@WebServlet("/EmployeeDetails")
public class EmployeeDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @return 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger LOGGER = Logger.getLogger(EmployeeDetails.class.getName());
		try {
		EmpBean bean = new EmpBean();
		HttpSession session = request.getSession();
		EmpBean b = (EmpBean) session.getAttribute("data");
		bean.setEmpID(b.getEmpID());
		bean.setEmpfName(request.getParameter("fname"));
		bean.setEmpmName(request.getParameter("mname"));
		bean.setEmplName(request.getParameter("lname"));
		bean.setPassword(request.getParameter("pass"));
		bean.setFather(request.getParameter("father"));
		bean.setMother(request.getParameter("mother"));
		bean.setSpouse(request.getParameter("spouse"));
		bean.setAadhar(request.getParameter("aadhar"));
		bean.setPanCard(request.getParameter("pancard"));
		bean.setPassport(request.getParameter("passport"));
		bean.setPriMob(request.getParameter("mob"));
		bean.setGender(request.getParameter("gender"));
		bean.setPemail(request.getParameter("mail"));
		bean.setSemail(request.getParameter("smail"));
		bean.setTermsAndCondition(request.getParameter("agree"));
		String priaddr= request.getParameter("al1")+" "+request.getParameter("al3")+" "+request.getParameter("country1")+" "+request.getParameter("state1")+" "+request.getParameter("city1")+" "+request.getParameter("pin1");
		String secaddr= request.getParameter("al2")+" "+request.getParameter("al4")+" "+request.getParameter("country2")+" "+request.getParameter("state2")+" "+request.getParameter("city2")+" "+request.getParameter("pin2");
		bean.setPriAddr(priaddr);
		bean.setSecAddr(secaddr);
		BankingDao bdao=new BankingDao();
		if(bdao.empcompletedetails(bean)) {
			if(BankingDao.empCompleteDetailsTBL(bean.getPassword(), bean.getEmpID(), 2)) {
				request.setAttribute("Loginstatus", "success");
				session.removeAttribute("data");
				RequestDispatcher rd = request.getRequestDispatcher("Employee_login.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("Loginstatus", "failed");
				session.removeAttribute("data");
				RequestDispatcher rd = request.getRequestDispatcher("Employee_login.jsp");
				rd.forward(request, response);
			}
		}else {
			request.setAttribute("Loginstatus", "failed");
			session.removeAttribute("data");
			RequestDispatcher rd = request.getRequestDispatcher("Employee_login.jsp");
			rd.forward(request, response);
		}
		}catch(Exception e) {
			LOGGER.log(Level.WARNING,"error",e );
		}
	}

}
