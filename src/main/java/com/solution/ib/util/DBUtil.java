package com.solution.ib.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {
	static Logger LOGGER = Logger.getLogger(DBUtil.class.getName());
	public static Connection getDBConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return null;
		}
		StringBuffer pass = new StringBuffer();
		pass.append("ravi123");
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","ravi",pass.toString());
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"error",e);
			return null;
		}
	}
}
