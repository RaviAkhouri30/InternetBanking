package com.solution.ib.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.solution.ib.bean.EmpBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class EmpLogin
 */
@WebServlet("/EmpLogin")
public class EmpLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpLogin() {
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
		Logger LOGGER = Logger.getLogger(EmpLogin.class.getName());
		try {
		BankingDao dao = new BankingDao();
		EmpBean bean = new EmpBean();
		HttpSession session = request.getSession();
		bean.setEmpID(request.getParameter("uname"));
		bean.setPassword(request.getParameter("pass"));
		bean.setStatus(dao.statusCheck(bean.getEmpID()));
		EmpBean data = new EmpBean();
			try {
				if(bean.getStatus()==1 && dao.validateEmp(bean.getEmpID(), bean.getPassword(), bean.getStatus())==true) {
					data = dao.empDetails(bean.getEmpID());
					session.setAttribute("data", data);
					response.sendRedirect("FirstTimeLogin.jsp");
				}else if(bean.getStatus()==2 && dao.validateEmp(bean.getEmpID(), bean.getPassword(), bean.getStatus())==true){
					response.sendRedirect("Employee_login.jsp?msg=deactive");
				}else if(bean.getStatus()==3 && dao.validateEmp(bean.getEmpID(), bean.getPassword(), bean.getStatus())==true) {
				data = dao.empDetails(bean.getEmpID());
				data.setPassword(bean.getPassword());
				session.setAttribute("data", data);
				response.sendRedirect("Employee.jsp");
				}else if(bean.getStatus()==9 && dao.validateEmp(bean.getEmpID(), bean.getPassword(), bean.getStatus())==true) {
					session.setAttribute("data", bean);
					response.sendRedirect("EmployeeHome.jsp");
				}else {
					response.sendRedirect("Employee_login.jsp?msg=Failed");
				}
			} catch (NoSuchAlgorithmException | SQLException e) {
				LOGGER.log(Level.WARNING,"error",e );
			}
		}catch (Exception e){
			LOGGER.log(Level.WARNING,"error",e );
		}
	}

}
