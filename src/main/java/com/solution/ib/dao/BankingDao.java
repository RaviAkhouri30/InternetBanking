package com.solution.ib.dao;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.solution.ib.bean.AccDetails;
import com.solution.ib.bean.Benifieceiry;
import com.solution.ib.bean.BranchBean;
import com.solution.ib.bean.CardBean;
import com.solution.ib.bean.CustomerBean;
import com.solution.ib.bean.EmpBean;
import com.solution.ib.bean.TransactionBean;
import com.solution.ib.util.DBUtil;


public class BankingDao {
	private Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
	
	public boolean validateEmp(String empName, String password,int status) throws NoSuchAlgorithmException, SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from emp_tbl where empid='");
		sql.append(empName);
		sql.append("' AND password='");
		if(status==1 || status==9 || status==2 || status==3) {
			String securePassword = toHexString(getPasswordSHA(password));
			boolean ret = false;
			sql.append(securePassword);
			sql.append("' AND status='");
			sql.append(status);
			sql.append("'");
			try (Statement st = DBUtil.getDBConnection().createStatement()) {
				try (ResultSet rs = st.executeQuery(sql.toString())){
					ret = rs.next();
				} finally {
					st.getResultSet().close();
				}
			} finally {
				DBUtil.getDBConnection().close();
			}
			return ret;
		}else {
			return false;
		}
	}
	
	public int statusCheck(String empid) {
		int status = 0;
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select status from emp_tbl where empid=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next())
					status = rs.getInt(1);
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return status;
	}
	
	public EmpBean empDetails(String empId) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select * from emp_details where empid=?";
		EmpBean bean = new EmpBean();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empId);
			try(ResultSet rs = ps.executeQuery()) {
				rs.next();
				bean.setEmpID(empId);
				bean.setEmpfName(rs.getString(1));
				bean.setEmpmName(rs.getString(2));
				bean.setEmplName(rs.getString(3));
				bean.setPriAddr(rs.getString(5));
				bean.setSecAddr(rs.getString(6));
				bean.setFather(rs.getString(7));
				bean.setMother(rs.getString(8));
				bean.setSpouse(rs.getString(9));
				bean.setAadhar(rs.getString(10));
				bean.setPanCard(rs.getString(11));
				bean.setPassport(rs.getString(12));
				bean.setDOJ(rs.getDate(13));
				bean.setSecMob(rs.getString(14));
				bean.setPriMob(rs.getString(15));
				bean.setGender(rs.getString(16));
				bean.setBloodGroup(rs.getString(17));
				bean.setStatus(rs.getInt(19));
				bean.setBranch(rs.getString(20));
				bean.setDesig(rs.getString(21));
				bean.setBasicPay(rs.getDouble(22));
				bean.setDA(rs.getDouble(23));
				bean.setTA(rs.getDouble(24));
				bean.setHRA(rs.getDouble(25));
				bean.setRent(rs.getDouble(26));
				bean.setBonus(rs.getDouble(27));
				bean.setTargetpay(rs.getDouble(28));
				bean.setPemail(rs.getString(29));
				bean.setSemail(rs.getString(30));
				bean.setDOL(rs.getDate(31));
				bean.setDOB(rs.getDate(32));
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return bean;
	}
	public String createPassword(String fname, String lname, String dob) {
		dob=dob.replaceAll("-", "");
		StringBuffer pass = new StringBuffer();
		pass.append(fname.substring(0, 2));
		pass.append(lname.substring(0, 1));
		pass.append("@gib");
		return pass.toString().toUpperCase();
	}
	public String generateEmpID(String empName) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		StringBuffer empID = new StringBuffer();
		empID.append(empName.substring(0, 4));
		String sql = "select emp_seq.nextval from dual";
		try(PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)) {
			try(ResultSet rs = ps.executeQuery()) {
				rs.next();
				empID.append(rs.getInt(1));
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return empID.toString().toUpperCase();
	}
	public boolean validatePassword(String empid, String password){
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select * from emp_tbl where empid=? and password=?";
		String securePassword=new String();
		boolean ret =false;
		try {
			securePassword = toHexString(getPasswordSHA(password));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			ps.setString(2, securePassword);
			try(ResultSet rs = ps.executeQuery()) {
				ret = rs.next();
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean changeEmpPassword(String empid, String pass) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update emp_tbl set password = ? where empid = ?";
		int n=0;
		boolean ret =false;
			String securePassword= new String();
			try {
				securePassword = toHexString(getPasswordSHA(pass));
			} catch (NoSuchAlgorithmException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
			try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
				ps.setString(1, securePassword);
				ps.setString(2, empid);
				n = ps.executeUpdate();
				if(n>0) {
					ret = true;
				}
				else {
					ret = false;
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}finally {
				try {
					DBUtil.getDBConnection().close();
				} catch (SQLException | NullPointerException e) {
					LOGGER.log(Level.WARNING,"error",e);
					ret = false;
				}
			}
		return ret;	
	}
	public ArrayList<EmpBean> empView() {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select * from emp_details where empid is not null and status<5 order by empfname asc";
		ArrayList<EmpBean> det = new ArrayList<EmpBean>();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					EmpBean bean = new EmpBean();
					bean.setEmpID(rs.getString(4));
					bean.setEmpfName(rs.getString(1));
					bean.setEmpmName(rs.getString(2));
					bean.setEmplName(rs.getString(3));
					bean.setPriAddr(rs.getString(5));
					bean.setSecAddr(rs.getString(6));
					bean.setFather(rs.getString(7));
					bean.setMother(rs.getString(8));
					bean.setSpouse(rs.getString(9));
					bean.setAadhar(rs.getString(10));
					bean.setPanCard(rs.getString(11));
					bean.setPassport(rs.getString(12));
					bean.setDOJ(rs.getDate(13));
					bean.setSecMob(rs.getString(14));
					bean.setPriMob(rs.getString(15));
					bean.setGender(rs.getString(16));
					bean.setBloodGroup(rs.getString(17));
					bean.setStatus(rs.getInt(19));
					bean.setBranch(rs.getString(20));
					bean.setDesig(rs.getString(21));
					bean.setBasicPay(rs.getDouble(22));
					bean.setDA(rs.getDouble(23));
					bean.setTA(rs.getDouble(24));
					bean.setHRA(rs.getDouble(25));
					bean.setRent(rs.getDouble(26));
					bean.setBonus(rs.getDouble(27));
					bean.setTargetpay(rs.getDouble(28));
					bean.setPemail(rs.getString(29));
					bean.setSemail(rs.getString(30));
					bean.setDOL(rs.getDate(31));
					bean.setDOB(rs.getDate(32));
					det.add(bean);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return null;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return det;
	}
	public ArrayList<EmpBean> empView(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select * from emp_details where empid=? and status<5";
		ArrayList<EmpBean> det = new ArrayList<EmpBean>();
		try(PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)) {
			ps.setString(1, empid);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					EmpBean bean = new EmpBean();
					bean.setEmpID(rs.getString(4));
					bean.setEmpfName(rs.getString(1));
					bean.setEmpmName(rs.getString(2));
					bean.setEmplName(rs.getString(3));
					bean.setPriAddr(rs.getString(5));
					bean.setSecAddr(rs.getString(6));
					bean.setFather(rs.getString(7));
					bean.setMother(rs.getString(8));
					bean.setSpouse(rs.getString(9));
					bean.setAadhar(rs.getString(10));
					bean.setPanCard(rs.getString(11));
					bean.setPassport(rs.getString(12));
					bean.setDOJ(rs.getDate(13));
					bean.setSecMob(rs.getString(14));
					bean.setPriMob(rs.getString(15));
					bean.setGender(rs.getString(16));
					bean.setBloodGroup(rs.getString(17));
					bean.setStatus(rs.getInt(19));
					bean.setBranch(rs.getString(20));
					bean.setDesig(rs.getString(21));
					bean.setBasicPay(rs.getDouble(22));
					bean.setDA(rs.getDouble(23));
					bean.setTA(rs.getDouble(24));
					bean.setHRA(rs.getDouble(25));
					bean.setRent(rs.getDouble(26));
					bean.setBonus(rs.getDouble(27));
					bean.setTargetpay(rs.getDouble(28));
					bean.setPemail(rs.getString(29));
					bean.setSemail(rs.getString(30));
					bean.setDOL(rs.getDate(31));
					bean.setDOB(rs.getDate(32));
					det.add(bean);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return null;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return det;
	}
	public ArrayList<EmpBean> empPending() {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select * from emp_details where status=2";
		ArrayList<EmpBean> det = new ArrayList<EmpBean>();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					EmpBean bean = new EmpBean();
					bean.setEmpID(rs.getString(4));
					bean.setEmpfName(rs.getString(1));
					bean.setEmpmName(rs.getString(2));
					bean.setEmplName(rs.getString(3));
					bean.setPriAddr(rs.getString(5));
					bean.setSecAddr(rs.getString(6));
					bean.setFather(rs.getString(7));
					bean.setMother(rs.getString(8));
					bean.setSpouse(rs.getString(9));
					bean.setAadhar(rs.getString(10));
					bean.setPanCard(rs.getString(11));
					bean.setPassport(rs.getString(12));
					bean.setDOJ(rs.getDate(13));
					bean.setSecMob(rs.getString(14));
					bean.setPriMob(rs.getString(15));
					bean.setGender(rs.getString(16));
					bean.setBloodGroup(rs.getString(17));
					bean.setStatus(rs.getInt(19));
					bean.setBranch(rs.getString(20));
					bean.setDesig(rs.getString(21));
					bean.setBasicPay(rs.getDouble(22));
					bean.setDA(rs.getDouble(23));
					bean.setTA(rs.getDouble(24));
					bean.setHRA(rs.getDouble(25));
					bean.setRent(rs.getDouble(26));
					bean.setBonus(rs.getDouble(27));
					bean.setTargetpay(rs.getDouble(28));
					bean.setPemail(rs.getString(29));
					bean.setSemail(rs.getString(30));
					bean.setDOL(rs.getDate(31));
					bean.setDOB(rs.getDate(32));
					det.add(bean);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return null;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return det;
	}
	public ArrayList<EmpBean> empRecycleBin() {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select * from emp_details where status=5";
		ArrayList<EmpBean> det = new ArrayList<EmpBean>();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					EmpBean bean = new EmpBean();
					bean.setEmpID(rs.getString(4));
					bean.setEmpfName(rs.getString(1));
					bean.setEmpmName(rs.getString(2));
					bean.setEmplName(rs.getString(3));
					bean.setPriAddr(rs.getString(5));
					bean.setSecAddr(rs.getString(6));
					bean.setFather(rs.getString(7));
					bean.setMother(rs.getString(8));
					bean.setSpouse(rs.getString(9));
					bean.setAadhar(rs.getString(10));
					bean.setPanCard(rs.getString(11));
					bean.setPassport(rs.getString(12));
					bean.setDOJ(rs.getDate(13));
					bean.setSecMob(rs.getString(14));
					bean.setPriMob(rs.getString(15));
					bean.setGender(rs.getString(16));
					bean.setBloodGroup(rs.getString(17));
					bean.setStatus(rs.getInt(19));
					bean.setBranch(rs.getString(20));
					bean.setDesig(rs.getString(21));
					bean.setBasicPay(rs.getDouble(22));
					bean.setDA(rs.getDouble(23));
					bean.setTA(rs.getDouble(24));
					bean.setHRA(rs.getDouble(25));
					bean.setRent(rs.getDouble(26));
					bean.setBonus(rs.getDouble(27));
					bean.setTargetpay(rs.getDouble(28));
					bean.setPemail(rs.getString(29));
					bean.setSemail(rs.getString(30));
					bean.setDOL(rs.getDate(31));
					bean.setDOB(rs.getDate(32));
					det.add(bean);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return null;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return det;
	}
	public boolean updateEmpDetByAdmin(EmpBean bean) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update emp_details set EMPFNAME=?,EMPLNAME=?,DOJ=?,DOB=?,BRANCH=?,PEMAIL=? where empid=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, bean.getEmpfName());
			ps.setString(2, bean.getEmplName());
			java.sql.Date doj = new java.sql.Date(bean.getDOJ().getTime());
			java.sql.Date dob = new java.sql.Date(bean.getDOB().getTime());
			ps.setDate(3, doj);
			ps.setDate(4, dob);
			ps.setString(5, bean.getBranch());
			ps.setString(6, bean.getPemail());
			ps.setString(7, bean.getEmpID());
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
	}
	public boolean createEmp( String empID,String pass, String fname, String lname) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		int n=0;
		String sql = "insert into emp_tbl (empid, empfname, emplname, password, status) values (?,?,?,?,?)";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empID);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, pass);
			ps.setInt(5, 1);
			n = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		if(n==1)
			return true;
		else
			return false;
	}
	
	public boolean setEmpDetails(EmpBean bean) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "insert into emp_details (empid, empfname, doj, emplname, branch, pemail, dob,status) values(?,?,?,?,?,?,?,?)";
		BankingDao dao = new BankingDao();
		
			String id = dao.generateEmpID(bean.getEmpfName());
			if(id==null) {
				return false;
			}else {
				bean.setEmpID(id);
			}
			String securePassword= new String();
			try {
				securePassword = toHexString(getPasswordSHA(dao.createPassword(bean.getEmpfName(), bean.getEmplName(), bean.getDOB().toString())));
			} catch (NoSuchAlgorithmException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
			bean.setPassword(securePassword);
			try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
				ps.setString(1, bean.getEmpID());
				ps.setString(2, bean.getEmpfName());
				java.sql.Date doj  = new java.sql.Date(bean.getDOJ().getTime());
				ps.setDate(3, doj);
				ps.setString(4, bean.getEmplName());
				ps.setString(5, bean.getBranch());
				ps.setString(6, bean.getPemail());
				java.sql.Date date  = new java.sql.Date(bean.getDOB().getTime());
				ps.setDate(7,date);
				ps.setInt(8, 1);
				int rs = ps.executeUpdate();
				if(rs>0 && dao.createEmp(bean.getEmpID(), bean.getPassword(), bean.getEmpfName(), bean.getEmplName())==true) {
						return true;
					}else {
						return false;
					}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}finally {
				try {
					DBUtil.getDBConnection().close();
				} catch (SQLException | NullPointerException e) {
					LOGGER.log(Level.WARNING,"error",e);
				}
			}
			return false;
	} 
	
	public boolean empcompletedetails(EmpBean bean) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update emp_details set priaddr = ?, secaddr = ?, father = ?, mother = ?, spouse = ?, addhaar = ?, pancard = ?, passport = ?, primob = ?, secmob = ?, gender = ?, branch = ?,  semail = ? , status=?  where empid=?";
		try (PreparedStatement ps= DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, bean.getPriAddr());
			ps.setString(2, bean.getSecAddr());
			ps.setString(3, bean.getFather());
			ps.setString(4, bean.getMother());
			ps.setString(5, bean.getSpouse());
			ps.setString(6, bean.getAadhar());
			ps.setString(7, bean.getPanCard());
			ps.setString(8, bean.getPassport());
			ps.setString(9, bean.getPriMob());
			ps.setString(10, bean.getSecMob());
			ps.setString(11, bean.getGender());
			ps.setString(12, bean.getBranch());
			ps.setString(13, bean.getSemail());
			ps.setInt(14, 2);
			ps.setString(15, bean.getEmpID());
			int n = ps.executeUpdate();
			if(n>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public static boolean empCompleteDetailsTBL(String pass,String id, int status) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String secure= new String();
		try {
			secure = toHexString(getPasswordSHA(pass));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
		String sql="update emp_tbl set status='"+status+"' , password='"+secure+"' where empid='"+id+"'";
		try (Statement st = DBUtil.getDBConnection().createStatement()){
			int rs = st.executeUpdate(sql);
			if(rs>0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
	}
	public boolean empDataTemporaryDelete(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql="update emp_tbl set status=5 where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean empDataPermanentDelete(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql= "delete from emp_tbl where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	
	public boolean empDataTemporaryDeleteTBL(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql="update emp_details set status=5 where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean empDataPermanentDeleteTBL(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql= "delete from emp_details where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean EmpApprove(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update emp_tbl set status=3 where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean EmpApproveTBL(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update emp_details set status=3 where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean EmpReject(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update emp_tbl set status=1 where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean EmpRejectTBL(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update emp_details set status=1 where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean empRestore(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update emp_details set status=2 where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean empRestoreTBL(String empid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update emp_tbl set status=2 where empid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, empid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
public boolean AddBranch(String ifsc, String bname, String city, String state, String country) {
	Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		
		String qry="insert into branch values(?,?,?,?,?)";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(qry)){
			ps.setString(1, ifsc);
			ps.setString(2, bname);
			ps.setString(3, city);
			ps.setString(4, state);
			ps.setString(5, country);
			int res= ps.executeUpdate();
			if(res==1) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
public String createIFSC(String branch, String state) {
	Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
	StringBuilder sb=new StringBuilder();
	sb.append("gib");
	sb.append(branch.substring(0, 2));
	sb.append(state.substring(0, 2));
	String sql="select ifsc_seq.nextval from dual";
	String ifsc = new String();
	try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
	try (ResultSet rs=ps.executeQuery()){
		rs.next();
		sb.append(rs.getInt(1));
		ifsc= sb.toString().toUpperCase();
	} finally {
		ps.getResultSet().close();
	}
	} catch (SQLException e) {
	LOGGER.log(Level.WARNING,"error",e);
	}finally {
		try {
			DBUtil.getDBConnection().close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
	}
	return ifsc;
}
public ArrayList<BranchBean> branchView(){
	Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
String sql = "select * from branch";
ArrayList<BranchBean> det = new ArrayList<BranchBean>();
try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
	try (ResultSet rs = ps.executeQuery()){
		while(rs.next()) {
			BranchBean bean=new BranchBean();
			bean.setIFSC(rs.getString(1));
			bean.setBname(rs.getString(2));
			bean.setCity(rs.getString(3));
			bean.setState(rs.getString(4));
			bean.setCountry(rs.getString(5));
			det.add(bean);
		}
	} finally {
		ps.getResultSet().close();
	}
} catch (SQLException e) {
		LOGGER.log(Level.WARNING,"error",e);
		return null;
	}finally {
		try {
			DBUtil.getDBConnection().close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
	}
return det;
}
 public boolean delBranch(String IFSC) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
	String sql = "delete from branch where IFSC=?";
	boolean status = false;
	try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
		ps.setString(1, IFSC);
		int rs = ps.executeUpdate();
		 if(rs>0) {
			 status = true;
		 }else {
			 status = false;
		 }
	} catch (SQLException e) {
		LOGGER.log(Level.WARNING,"error",e);
		status = false;
	}finally {
		try {
			DBUtil.getDBConnection().close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
	}
	return status;
}
 public ArrayList<String> fetchBranch(String bname,String state) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
	String sql = "select bname from branch where state='"+state+"' and bname like'"+bname+"%'";
	ArrayList<String> branch = new ArrayList<String>();
	try (Statement st = DBUtil.getDBConnection().createStatement()){
		try (ResultSet rs = st.executeQuery(sql)){
			while(rs.next()) {
				branch.add(rs.getString(1));
			}
		} finally {
			st.getResultSet().close();
		}
	} catch (SQLException e) {
		LOGGER.log(Level.WARNING,"error",e);
	}finally {
		try {
			DBUtil.getDBConnection().close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
	}
	return branch;
}
 public String fetchBranchFromIfsc(String ifsc) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select bname from branch where ifsc=?";
		String branch = null;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, ifsc);
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				branch = rs.getString(1);
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return branch;
	}
 public ArrayList<String> fetchBranch(String bname) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
	String sql = "select bname from branch where bname like'"+bname+"%'";
	ArrayList<String> branch = new ArrayList<String>();
	try (Statement st = DBUtil.getDBConnection().createStatement()){
		try (ResultSet rs = st.executeQuery(sql)){
			while(rs.next()) {
				branch.add(rs.getString(1));
			}
		} finally {
			st.getResultSet().close();
		}
	} catch (SQLException e) {
		LOGGER.log(Level.WARNING,"error",e);
	}finally {
		try {
			DBUtil.getDBConnection().close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
	}
	return branch;
}
 public String fetchIFSC(String branch) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
	String sql = "select ifsc from branch where bname=?";
	String ifsc = null;
	try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
		ps.setString(1, branch);
		try (ResultSet rs = ps.executeQuery()){
			rs.next();
			ifsc = rs.getString(1);
		} finally {
			ps.getResultSet().close();
		}
	} catch (SQLException e) {
		LOGGER.log(Level.WARNING,"error",e);
	}finally {
		try {
			DBUtil.getDBConnection().close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}
	}
	return ifsc;
}
 public ArrayList<String> getCity(String city,String state) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		ArrayList<String> city_list = new ArrayList<String>();
		try (java.sql.Statement st = DBUtil.getDBConnection().createStatement()){
			String sql ="select city from branch where state='"+state+"' and city like '"+city+"%'" ;
			try (ResultSet rs = st.executeQuery(sql)){
				while(rs.next()) {
					city_list.add(rs.getString(1));
				}
			} finally {
				st.getResultSet().close();
			}
		} catch (SQLException e) {
			return null;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return city_list;
	}
 public ArrayList<String> fetchBranchState(String bname) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select state from branch where state like'"+bname+"%'";
		ArrayList<String> branch = new ArrayList<String>();
		try (Statement st = DBUtil.getDBConnection().createStatement()){
			try (ResultSet rs = st.executeQuery(sql)){
				while(rs.next()) {
					branch.add(rs.getString(1));
				}
			} finally {
				st.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return branch;
	}
 public boolean checkUserIdAvailability(String uname) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
	String sql = "select * from cust_tbl where uname=?";
	boolean ret = false;
	try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
		ps.setString(1, uname);
		try (ResultSet rs = ps.executeQuery()){
			ret=rs.next();
		} finally {
			ps.getResultSet().close();
		}
	} catch (SQLException e) {
		LOGGER.log(Level.WARNING,"error",e);
		ret = false;
	}finally {
		try {
			DBUtil.getDBConnection().close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}
	}
	return ret;
}
 public ArrayList<String> fetchCountry(String country) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select country from branch where country like'"+country+"%'";
		ArrayList<String> branch = new ArrayList<String>();
		try (Statement st = DBUtil.getDBConnection().createStatement()){
			try (ResultSet rs = st.executeQuery(sql)){
				while(rs.next()) {
					branch.add(rs.getString(1));
				}
			} finally {
				st.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return branch;
	}
 public ArrayList<String> fetchBranchState(String state,String country) {
	 Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select state from branch where country='"+country+"' and state like'"+state+"%'";
		ArrayList<String> branch = new ArrayList<String>();
		try (Statement st = DBUtil.getDBConnection().createStatement()){
			try (ResultSet rs = st.executeQuery(sql)){
				while(rs.next()) {
					branch.add(rs.getString(1));
				}
			} finally {
				st.getResultSet().close();
			}	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return null;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return branch;
	}
	public boolean changeCustPassword(String custid, String pass) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update cust_tbl set password = ? where custid = ?";
		boolean ret = false;
		int n=0;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			String securePassword = toHexString(getPasswordSHA(pass));
			ps.setString(1, securePassword);
			ps.setString(2, custid);
			n = ps.executeUpdate();
			if(n==1)
				ret = true;
			else
				ret = false;
		} catch (SQLException | NoSuchAlgorithmException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		} finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return ret;
	}
	
	public boolean createCustomer(CustomerBean bean) throws NoSuchAlgorithmException {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		int n=0;
		String sql = "insert into cust_tbl values (?,?,?)";
		boolean ret = false;
		String securePassword = toHexString(getPasswordSHA(bean.getPassword()));
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, bean.getCustID());
			ps.setString(2, securePassword);
			ps.setInt(3, 1);
			n = ps.executeUpdate();
			if(n>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean createCustomerDetails(CustomerBean bean) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		int n=0;
		String sql = "insert into cust_details (CUSTFNAME,CUSTLNAME,PEMAIL,CUSTID,DOB,status) values (?,?,?,?,?,?)";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, bean.getCustfName());
			ps.setString(2, bean.getCustlName());
			ps.setString(3, bean.getPemail());
			ps.setString(4, bean.getCustID());
			java.sql.Date dob = null;
			if(bean.getDOB()!=null) {
				dob  = new java.sql.Date(bean.getDOB().getTime());
			}
			ps.setDate(5, dob);
			ps.setInt(6, 1);
			n = ps.executeUpdate();
			if (n>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public int custStatus(CustomerBean bean) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "Select status from cust_tbl where uname=?";
		int status = 0;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, bean.getCustID());
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				status = rs.getInt(1);
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			status = 0;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				status = 0;
			}
		}
		return status;
	}
	public boolean validateCustomer(CustomerBean bean) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select * from cust_tbl where uname=? and password=?";
		String secure= new String();
		boolean ret = false;
		try {
			secure = toHexString(getPasswordSHA(bean.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, bean.getCustID());
			ps.setString(2, secure);
			try (ResultSet rs = ps.executeQuery()){
				ret = rs.next();
				System.out.println(ret);
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}
		return ret;
	}
	public boolean setStatusInTBL(String uname) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update cust_tbl set status=2 where uname=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, uname);
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	public boolean setCustDetails(CustomerBean bean) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update cust_details set CUSTFNAME=? , CUSTMNAME=?, CUSTLNAME=?, FATHER=?,MOTHER=?, SPOUSE=?,ADDHAAR=?,PANCARD=?,PASSPORT=?,SECMOB=?,PRIMOB=?,GENDER=?,BLOODGROUP=?,TERMSANDCONDITION=?,STATUS=?,SEMAIL=?,MAILINGADDR=?,PREADDR=?,branch=? where custid=?";
		boolean ret = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, bean.getCustfName());
			ps.setString(2, bean.getCustmName());
			ps.setString(3, bean.getCustlName());
			ps.setString(4, bean.getFather());
			ps.setString(5, bean.getMother());
			ps.setString(6, bean.getSpouse());
			ps.setString(7, bean.getAddhaar());
			ps.setString(8, bean.getPanCard());
			ps.setString(9, bean.getPassport());
			ps.setString(10, bean.getSecMob());
			ps.setString(11, bean.getPriMob());
			ps.setString(12, bean.getGender());
			ps.setString(13, bean.getBloodGroup());
			ps.setString(14, bean.getTermsAndCondition());
			ps.setInt(15, 2);
			ps.setString(16, bean.getSemail());
			ps.setString(17, bean.getPriAddr());
			ps.setString(18, bean.getSecAddr());
			ps.setString(19, bean.getBranch());
			ps.setString(20, bean.getCustID());
			int rs = ps.executeUpdate();
			if(rs>0) {
				ret = true;
			}else {
				ret = false;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			ret = false;
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
				ret = false;
			}
		}
		return ret;
	}
	
	public CustomerBean getCustDetails(String custId) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select * from cust_details where CUSTID=?";
		CustomerBean bean = new CustomerBean();
			try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
				ps.setString(1, custId);
				try (ResultSet rs = ps.executeQuery()){
					if(rs.next()) {
						bean.setCustID(custId);
						bean.setCustfName(rs.getString(1));
						bean.setCustmName(rs.getString(2));
						bean.setCustlName(rs.getString(3));
						bean.setPriAddr(rs.getString(5));
						bean.setSecAddr(rs.getString(6));
						bean.setFather(rs.getString(7));
						bean.setMother(rs.getString(8));
						bean.setSpouse(rs.getString(9));
						bean.setAddhaar(rs.getString(10));
						bean.setPanCard(rs.getString(11));
						bean.setPassport(rs.getString(12));
						bean.setDOJ(rs.getDate(13));
						bean.setPriMob(rs.getString(15));
						bean.setSecMob(rs.getString(14));
						bean.setGender(rs.getString(16));
						bean.setBloodGroup(rs.getString(17));
						bean.setTermsAndCondition(rs.getString(18));
						bean.setStatus(rs.getInt(19));
						bean.setPemail(rs.getString("pemail"));
						bean.setSemail(rs.getString("semail"));
						bean.setEmployment(rs.getString(22));
						bean.setDOB(rs.getDate("dob"));
						bean.setBranch(rs.getString("branch"));
					}
				} finally {
					ps.getResultSet().close();
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}finally {
				try {
					DBUtil.getDBConnection().close();
				} catch (SQLException e) {
					LOGGER.log(Level.WARNING,"error",e);
				}
			}
			return bean;
	}
	public ArrayList<CustomerBean> getCustDetails(int status) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql ="select * from cust_details where status=?";
		ArrayList<CustomerBean> data = new ArrayList<CustomerBean>();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setInt(1, status);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					CustomerBean bean = new CustomerBean();
					bean.setCustID(rs.getString(4));
					bean.setCustfName(rs.getString(1));
					bean.setCustmName(rs.getString(2));
					bean.setCustlName(rs.getString(3));
					bean.setPriAddr(rs.getString(5));
					bean.setSecAddr(rs.getString(6));
					bean.setFather(rs.getString(7));
					bean.setMother(rs.getString(8));
					bean.setSpouse(rs.getString(9));
					bean.setAddhaar(rs.getString(10));
					bean.setPanCard(rs.getString(11));
					bean.setPassport(rs.getString(12));
					bean.setDOJ(rs.getDate(13));
					bean.setPriMob(rs.getString(15));
					bean.setSecMob(rs.getString(14));
					bean.setGender(rs.getString(16));
					bean.setBloodGroup(rs.getString(17));
					bean.setTermsAndCondition(rs.getString(18));
					bean.setStatus(rs.getInt(19));
					bean.setPemail(rs.getString("pemail"));
					bean.setSemail(rs.getString("semail"));
					bean.setEmployment(rs.getString(22));
					bean.setDOB(rs.getDate("dob"));
					bean.setBranch(rs.getString("branch"));
					data.add(bean);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return data;
	}
	
	public ArrayList<CustomerBean> getCustDetails(String id,int status) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql ="select * from cust_details where custid=?";
		ArrayList<CustomerBean> data = new ArrayList<CustomerBean>();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					CustomerBean bean = new CustomerBean();
					bean.setCustID(rs.getString(4));
					bean.setCustfName(rs.getString(1));
					bean.setCustmName(rs.getString(2));
					bean.setCustlName(rs.getString(3));
					bean.setPriAddr(rs.getString(5));
					bean.setSecAddr(rs.getString(6));
					bean.setFather(rs.getString(7));
					bean.setMother(rs.getString(8));
					bean.setSpouse(rs.getString(9));
					bean.setAddhaar(rs.getString(10));
					bean.setPanCard(rs.getString(11));
					bean.setPassport(rs.getString(12));
					bean.setDOJ(rs.getDate(13));
					bean.setPriMob(rs.getString(15));
					bean.setSecMob(rs.getString(14));
					bean.setGender(rs.getString(16));
					bean.setBloodGroup(rs.getString(17));
					bean.setTermsAndCondition(rs.getString(18));
					bean.setStatus(rs.getInt(19));
					bean.setPemail(rs.getString("pemail"));
					bean.setSemail(rs.getString("semail"));
					bean.setEmployment(rs.getString(22));
					bean.setDOB(rs.getDate("dob"));
					bean.setBranch(rs.getString("branch"));
					data.add(bean);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return data;
	}
	public ArrayList<CustomerBean> getCustDetails() {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql ="select * from cust_details where status<5";
		ArrayList<CustomerBean> data = new ArrayList<CustomerBean>();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					CustomerBean bean = new CustomerBean();
					bean.setCustID(rs.getString(4));
					bean.setCustfName(rs.getString(1));
					bean.setCustmName(rs.getString(2));
					bean.setCustlName(rs.getString(3));
					bean.setPriAddr(rs.getString(5));
					bean.setSecAddr(rs.getString(6));
					bean.setFather(rs.getString(7));
					bean.setMother(rs.getString(8));
					bean.setSpouse(rs.getString(9));
					bean.setAddhaar(rs.getString(10));
					bean.setPanCard(rs.getString(11));
					bean.setPassport(rs.getString(12));
					bean.setDOJ(rs.getDate(13));
					bean.setPriMob(rs.getString(15));
					bean.setSecMob(rs.getString(14));
					bean.setGender(rs.getString(16));
					bean.setBloodGroup(rs.getString(17));
					bean.setTermsAndCondition(rs.getString(18));
					bean.setStatus(rs.getInt(19));
					bean.setPemail(rs.getString("pemail"));
					bean.setSemail(rs.getString("semail"));
					bean.setEmployment(rs.getString(22));
					bean.setDOB(rs.getDate("dob"));
					bean.setBranch(rs.getString("branch"));
					data.add(bean);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return data;
	}
	public int AccStatus(String custid) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select status from cust_details where custid=?";
		int ret =0;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, custid);
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				ret = rs.getInt(1);
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return ret;
	}
	public boolean permDelCust(String id) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "delete from cust_details where custid=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public boolean permDelCustTbl(String id) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "delete from cust_tbl where uname=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public boolean TempDelCust(String id) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update cust_details set status=5 where custid=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public boolean TempDelCustTbl(String id) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update cust_tbl set status=5 where uname=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public boolean restoreCust(String id) {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "update cust_details set status=2 where custid=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public boolean restoreCustTbl(String id) {
		String sql = "update cust_tbl set status=2 where uname=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public boolean approveCust(String id) {
		String sql = "update cust_details set status=3 where custid=?";
			try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public boolean approveCustTbl(String id) {
		String sql = "update cust_tbl set status=3 where uname=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (Exception e2) {
				LOGGER.log(Level.WARNING,"error",e2);
			}
		}
		return false;
	}
	public boolean rejectCust(String id) {
		String sql = "update cust_details set status=1 where custid=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public boolean rejectCustTbl(String id) {
		String sql = "update cust_tbl set status=1 where uname=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	
	public boolean generateDebitCard(CardBean data) {
		String sql = "insert into debitcard values(?,?,?,?)";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, data.getCardNo());
			ps.setString(2, data.getExp());
			ps.setString(3, data.getCvv());
			ps.setString(4, data.getAcc_no());
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public CardBean cardDetails(String AccNo) {
		String sql = "Select * from debitcard where Acc_No=?";
		CardBean card = new CardBean();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, AccNo);
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				card.setAcc_no(AccNo);
				card.setCardNo(rs.getString(1));
				card.setExp(rs.getString(2));
				card.setCvv(rs.getString(3));
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return card;
	}
	public boolean createNewAccount(AccDetails data) {
		String sql = "insert into Acc_Details values (?,?,?,?,?,?)";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, data.getAcc_No());
			ps.setString(2, data.getBalance());
			ps.setString(3, data.getOpeningDate());
			ps.setString(4, data.getCustid());
			ps.setString(5, data.getCardNo());
			ps.setString(6, data.getIFSC());
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public boolean updateBalance(String acc_no,String bal) {
		String sql = "update acc_details set balance=? where acc_no=?";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, bal);
			ps.setString(2, acc_no);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public AccDetails accdetails(String custid) {
		String sql = "select * from acc_details where custid=?";
		AccDetails det = new AccDetails();
			try(PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)) {
			ps.setString(1, custid);
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				det.setAcc_No(rs.getString(1));
				det.setBalance(rs.getString(2));
				det.setOpeningDate(rs.getString(3));
				det.setCustid(rs.getString(4));
				det.setCardNo(rs.getString(5));
				det.setIFSC(rs.getString(6));
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
			return det;
	}
	public String balance(String accno) {
		String sql = "select balance from acc_details where acc_no=?";
		String bal = "0";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, accno);
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				bal = rs.getString(1);
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return bal;
	}
	public String generateAccNo() {
		String sql = "select ACC_NO.nextval from dual";
		StringBuffer AccNo = new StringBuffer();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				AccNo.append(rs.getInt(1));
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return AccNo.toString();
	}
	public String generateCardNo() {
		String sql = "select debitCardID.nextval from dual";
		StringBuffer cardNo = new StringBuffer();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				cardNo.append(rs.getBigDecimal(1));
				cardNo.insert(4, " ");
				cardNo.insert(9, " ");
				cardNo.insert(14, " ");
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return cardNo.toString();
	}
	public String lastLogin(String custid) {
		String lastDate = null;
		String sql = "select LOGIN_TIMEDATE from login_log where custid=? order by logid asc";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, custid);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					lastDate = rs.getString(1);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return lastDate;
	}
	public boolean login_log(String custid) {
		String sql = "insert into login_log values(?,?,?)";
		StringBuffer id = new StringBuffer();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			id.append(generateLogID());
			ps.setString(1, id.toString());
			Date date = new Date();
			ps.setString(2, date.toString());
			ps.setString(3, custid);
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public static int generateLogID() {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select log_seq.nextval from dual";
		int id=0;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				id = rs.getInt(1);
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return id;
	}
	public boolean addbenifieceiry(Benifieceiry bean) {
		String sql = "insert into benifieceiry values (?,?,?,?,?,?,?)";
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getAccno());
			ps.setString(3, bean.getBank());
			ps.setString(4, bean.getIfsc());
			ps.setString(5, bean.getBname());
			ps.setString(6, bean.getAcctype());
			ps.setString(7, bean.getId());
			int rs = ps.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return false;
	}
	public ArrayList<Benifieceiry> fetchBenifieceiry(String id) {
		String sql = "select * from benifieceiry where custid=?";
		ArrayList<Benifieceiry> data = new ArrayList<Benifieceiry>();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					Benifieceiry dat = new Benifieceiry();
					dat.setName(rs.getString(1));
					dat.setAccno(rs.getString(2));
					dat.setBank(rs.getString(3));
					dat.setIfsc(rs.getString(4));
					dat.setBname(rs.getString(5));
					dat.setAcctype(rs.getString(6));
					dat.setId(rs.getString(7));
					data.add(dat);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return data;
	}
	public boolean validateAccountNo(String accno) {
		String sql = "select * from acc_details where acc_no=?";
		boolean val = false;
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, accno);
			try (ResultSet rs = ps.executeQuery()){
				val = rs.next();
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return val;
	}
	public static String generateTransactionID() {
		Logger LOGGER = Logger.getLogger(BankingDao.class.getName());
		String sql = "select transid.nextval from dual";
		StringBuffer id = new StringBuffer();
		try (PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				id.append(rs.getInt(1));
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return id.toString();
	}
	public boolean transfer(TransactionBean bean,Date dat) {
		String sql ="insert into transaction values(?,?,?,?,?)";
		boolean val = false;
		try(PreparedStatement ps = DBUtil.getDBConnection().prepareStatement(sql)) {
			ps.setString(1, generateTransactionID());
			ps.setString(2, bean.getToAccount());
			ps.setString(3, bean.getFromAccount());
			java.sql.Date  date = new java.sql.Date(dat.getTime());
			ps.setDate(4, date);
			ps.setString(5, bean.getAmount());
			int rs = ps.executeUpdate();
			if(rs>0) {
				val = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return val;
	}
	public ArrayList<TransactionBean> getDebitTransaction(String accno) {
		String sql = "select * from transaction where FROMACCOUNT=? order by DATEOFTRANSACTION desc";
		ArrayList<TransactionBean> trans = new ArrayList<TransactionBean>();
		try (PreparedStatement ps =DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, accno);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					TransactionBean bean = new TransactionBean();
					bean.setAmount(rs.getString(5));
					bean.setId(rs.getString(1));
					bean.setToAccount(rs.getString(2));
					bean.setFromAccount(rs.getString(3));
					bean.setDateoftrans(rs.getDate(4).toString());
					trans.add(bean);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return trans;
	}
	public ArrayList<TransactionBean> getCreditTransaction(String accno) {
		String sql = "select * from transaction where TOACCOUNT=? order by DATEOFTRANSACTION desc";
		ArrayList<TransactionBean> trans = new ArrayList<TransactionBean>();
		try (PreparedStatement ps =DBUtil.getDBConnection().prepareStatement(sql)){
			ps.setString(1, accno);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					TransactionBean bean = new TransactionBean();
					bean.setAmount(rs.getString(5));
					bean.setId(rs.getString(1));
					bean.setToAccount(rs.getString(2));
					bean.setFromAccount(rs.getString(3));
					bean.setDateoftrans(rs.getDate(4).toString());
					trans.add(bean);
				}
			} finally {
				ps.getResultSet().close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
		}finally {
			try {
				DBUtil.getDBConnection().close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"error",e);
			}
		}
		return trans;
	}
	public static byte[] getPasswordSHA(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(password.getBytes(StandardCharsets.UTF_8));
	}
	public static String toHexString(byte[] passHash) {
		BigInteger pass = new BigInteger(1,passHash);
		StringBuilder hexString = new StringBuilder(pass.toString(16));
		while (hexString.length()<32) {
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}
}
