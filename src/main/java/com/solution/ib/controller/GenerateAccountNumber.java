package com.solution.ib.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.solution.ib.bean.AccDetails;
import com.solution.ib.bean.CardBean;
import com.solution.ib.bean.CustomerBean;
import com.solution.ib.dao.BankingDao;

/**
 * Servlet implementation class GenerateAccountNumber
 */
@WebServlet("/GenerateAccountNumber")
public class GenerateAccountNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateAccountNumber() {
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
		Logger LOGGER = Logger.getLogger(GenerateAccountNumber.class.getName());
		try {
			SecureRandom random = new SecureRandom();
		HttpSession session = request.getSession();
		CustomerBean data = new CustomerBean();
		data = (CustomerBean) session.getAttribute("data");
		BankingDao dao =new BankingDao();
		AccDetails dat = new AccDetails();
		CardBean card = new CardBean();
		card.setCardNo(dao.generateCardNo());
		dat.setAcc_No(dao.generateAccNo());
		dat.setBalance("0");
		card.setAcc_no(dat.getAcc_No());
		StringBuffer cvv = new StringBuffer();
			cvv.append(random.nextInt(1000));
			/*
			 * cvv.append((int)(Math.random()*10)%10);
			 * cvv.append((int)(Math.random()*10)%10);
			 * cvv.append((int)(Math.random()*10)%10);
			 */
		card.setCvv(cvv.toString());
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int mon = cal.get(Calendar.MONTH)+1;
		int y = cal.get(Calendar.YEAR)+5;
		StringBuffer expDate = new StringBuffer();
		expDate.append(mon);
		expDate.append("/");
		expDate.append(y%100);
		card.setExp(expDate.toString());
		dat.setCardNo(card.getCardNo());
		dat.setCustid(data.getCustID());
		Date openingDate = new Date();
		dat.setOpeningDate(openingDate.toString());
		dat.setIFSC(dao.fetchIFSC(data.getBranch()));
		dao.generateDebitCard(card);
		dao.createNewAccount(dat);
		session.setAttribute("acc_det", dat);
		session.setAttribute("card", card);
		response.sendRedirect("CustomerHome.jsp?msg=acc_active");
		}catch (Exception e) {
			LOGGER.log(Level.WARNING,"error",e );
		}
	}

}
