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
 * Servlet implementation class EmpDetailAdmin
 */
@WebServlet("/EmpDetailAdmin")
public class EmpDetailAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpDetailAdmin() {
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
		Logger LOGGER = Logger.getLogger(EmpDetailAdmin.class.getName());
		try {
			String fname=request.getParameter("fname");
			String lname=request.getParameter("lname");
			String dj=request.getParameter("doj1");
			String bname=request.getParameter("bname");
			String email=request.getParameter("mail");
			String d = request.getParameter("dob");
			Date doj=null;
			Date dob=null;
			try {
				doj = new SimpleDateFormat("yyyy-MM-dd").parse(dj);
				
				dob =  new SimpleDateFormat("yyyy-MM-dd").parse(d);
			} catch (ParseException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
			EmpBean obj=new EmpBean();
			obj.setEmpfName(fname);
			obj.setDOJ(doj);
			obj.setEmplName(lname);
			obj.setBranch(bname);
			obj.setPemail(email);
			obj.setDOB(dob);
			BankingDao dao = new BankingDao();
			dao.setEmpDetails(obj);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
		
	}
}
