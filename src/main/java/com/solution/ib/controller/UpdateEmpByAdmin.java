package com.solution.ib.controller;

import java.io.IOException;
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

import com.solution.ib.bean.EmpBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class UpdateEmpByAdmin
 */
@WebServlet("/UpdateEmpByAdmin")
public class UpdateEmpByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmpByAdmin() {
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
		Logger LOGGER = Logger.getLogger(UpdateEmpByAdmin.class.getName());
		try {
		EmpBean bean = new EmpBean();
		bean.setEmpID(request.getParameter("empid"));
		bean.setEmpfName(request.getParameter("fname"));
		bean.setEmplName(request.getParameter("lname"));
		String dob = request.getParameter("dob");
		String doj = request.getParameter("doj");
		Date dateoj = null;
		Date dateob = null;
		bean.setPemail(request.getParameter("email"));
		bean.setBranch(request.getParameter("branch"));
		try {
			dateoj = new SimpleDateFormat("yyyy-MM-dd").parse(doj);
			dateob = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
		} catch (ParseException e) {
			LOGGER.log(Level.WARNING,"error",e );
			return;
		}
		bean.setDOB(dateob);
		bean.setDOJ(dateoj);
		BankingDao dao = new BankingDao();
		if(dao.updateEmpDetByAdmin(bean)) {
			
		}else {
			return;
		}
		}catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e );
		}
	}

}
