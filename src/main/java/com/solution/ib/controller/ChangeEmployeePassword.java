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
import com.solution.ib.util.WrongDataException;

/**
 * Servlet implementation class ChangeEmployeePassword
 */
@WebServlet("/ChangeEmployeePassword")
public class ChangeEmployeePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeEmployeePassword() {
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
		Logger LOGGER = Logger.getLogger(ChangeEmployeePassword.class.getName());
		String oldpass=request.getParameter("oldpass");
		String pass=request.getParameter("pass");
		String passc=request.getParameter("passc");
		String empid = request.getParameter("empid");
		BankingDao dao = new BankingDao();
		if(pass.equals(passc)) {
			try {
				if(dao.validatePassword(empid, oldpass)==true) {
					if(dao.changeEmpPassword(empid, pass)==true) {
						
					}else {
						throw new WrongDataException();
					}
				}else {
						throw new WrongDataException();
				}
			} catch (WrongDataException e) {
				LOGGER.log(Level.WARNING,"error",e );
			}
		}
} }
