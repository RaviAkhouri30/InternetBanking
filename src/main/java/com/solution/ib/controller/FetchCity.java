package com.solution.ib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class fetchCity
 */
@WebServlet("/FetchCity")
public class FetchCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchCity() {
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
		Logger LOGGER = Logger.getLogger(FetchCity.class.getName());
		try {
		String city=request.getParameter("city1");
		String state = request.getParameter("state1");
		BankingDao dao = new BankingDao();
		ArrayList<String> city_names = new ArrayList<String>();
		city_names = dao.getCity(city,state);
		String msg = new String();
		PrintWriter out = response.getWriter();
		msg="<datalist id='city_names'>"; 
		for (String str :city_names) { 
			msg =msg+"<option value='"+str+"'> "; 
			} 
		msg = msg +"</datalist>";
		out.print(msg);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
