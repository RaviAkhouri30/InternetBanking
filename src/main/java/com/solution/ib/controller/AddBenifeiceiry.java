package com.solution.ib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.solution.ib.bean.Benifieceiry;
import com.solution.ib.bean.CustomerBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class AddBenifeiceiry
 */
@WebServlet("/AddBenifeiceiry")
public class AddBenifeiceiry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBenifeiceiry() {
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
		Logger LOGGER = Logger.getLogger(AddBenifeiceiry.class.getName());
		try {
			HttpSession sess = request.getSession();
			PrintWriter out = response.getWriter();
			CustomerBean b = (CustomerBean)sess.getAttribute("data");
			Benifieceiry bean = new Benifieceiry();
			bean.setName(request.getParameter("name"));
			bean.setAccno(request.getParameter("accno"));
			bean.setAcctype(request.getParameter("acc_type"));
			bean.setBank(request.getParameter("bank"));
			bean.setId(b.getCustID());
			bean.setIfsc(request.getParameter("ifsc"));
			bean.setBname(request.getParameter("branch"));
			BankingDao dao = new BankingDao();
			if(bean.getBname()==null || bean.getName()==null) {
				out.print("<div id='error'>"+bean.getName()+"Failed to add New Benificiery! Please Try Again!!!</div>");
				return;
			}
			if(dao.validateAccountNo(bean.getAccno())) {
				dao.addbenifieceiry(bean);
				out.print("<div id='error'>New Benificiery is added Succesfully</div>");
			}else {
				out.print("<div id='error'>"+bean.getName()+"Failed to add New Benificiery! Please Try Again!!!</div>");
			}
		}catch (UnknownHostException e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}catch (Exception e) {
			LOGGER.log(java.util.logging.Level.WARNING,"error", e);
		}
	}

}
