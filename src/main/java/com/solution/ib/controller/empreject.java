package com.solution.ib.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class empreject
 */
@WebServlet("/empreject")
public class empreject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public empreject() {
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
		Logger LOGGER = Logger.getLogger(empreject.class.getName());
		try {
		String empid = request.getParameter("empid");
		BankingDao dao = new BankingDao();
		dao.EmpReject(empid);
		dao.EmpRejectTBL(empid);
		}catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e );
		}
	}

}
