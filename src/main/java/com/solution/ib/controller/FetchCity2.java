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
 * Servlet implementation class FetchCity2
 */
@WebServlet("/FetchCity2")
public class FetchCity2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchCity2() {
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
		Logger LOGGER = Logger.getLogger(FetchCity2.class.getName());
		try {
		String city=request.getParameter("city2");
		String state2=request.getParameter("state2");
		BankingDao dao = new BankingDao();
		ArrayList<String> city_names = new ArrayList<String>();
		city_names = dao.getCity(city,state2);
		String data =  new String();
		PrintWriter out = response.getWriter();
		data="<datalist id ='city_names2'>";
		for (String string : city_names) {
			data = data + "<option value ='"+string+"'>";
		}
		data= data + "</datalist>";
		out.println(data);
		} catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}
}

