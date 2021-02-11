package com.solution.ib.test;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.solution.ib.bean.AccDetails;
import com.solution.ib.bean.CardBean;
import com.solution.ib.dao.BankingDao;

public class test {

	@Test
	public void tc1() throws NoSuchAlgorithmException {
		Logger LOGGER = Logger.getLogger(test.class.getName());
		BankingDao dao = new BankingDao();
		try {
			assertEquals(true, dao.validateEmp("admin", "admin123", 9));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
	}
	@Test
	public void tc2() {
		BankingDao dao = new BankingDao();
		assertEquals(10, dao.generateEmpID("admin").length());
				
	}
	@Test
	public void tc3() {
		BankingDao dao = new BankingDao();
		assertEquals(19	, dao.generateCardNo().length());
	}
	@Test
	public void tc4() {
		BankingDao dao = new BankingDao();
		assertEquals(true, dao.login_log("Harry1"));
	}
	@Test
	public void tc5() {
		BankingDao dao = new BankingDao();
		assertEquals(28, dao.lastLogin("Harry1").length());
	}
	@Test
	public void tc6() {
		BankingDao dao = new BankingDao();
		CardBean bean = new CardBean();
		bean.setAcc_no("132543");
		bean.setCardNo(dao.generateCardNo());
		StringBuffer cvv = new StringBuffer();
		cvv.append((int)(Math.random()*10)%10);
		cvv.append((int)(Math.random()*10)%10);
		cvv.append((int)(Math.random()*10)%10);
		bean.setCvv(cvv.toString());
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int mon = cal.get(Calendar.MONTH)+1;
		int y = cal.get(Calendar.YEAR)+5;
		StringBuffer expDate = new StringBuffer();
		expDate.append(mon);
		expDate.append("/");
		expDate.append(y%100);
		bean.setExp(expDate.toString());
		assertEquals(true, dao.generateDebitCard(bean));
	}
	@Test
	public void tc7() {
		BankingDao dao = new BankingDao();
		AccDetails det = new AccDetails();
		det.setAcc_No(dao.generateAccNo());
		det.setBalance("1000000000");
		det.setCardNo("134");
		det.setCustid("1346");
		det.setIFSC("fagaf");
		Date da = new Date();
		String date = da.toString();
		det.setOpeningDate(date);
		assertEquals(true, dao.createNewAccount(det));
	}
}
