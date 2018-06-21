package com.dynastech.base.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.MDC;
import org.apache.log4j.jdbc.JDBCAppender;

/**
 * 
 * @author yuan
 *
 */
public class SystemJDBCAppender extends JDBCAppender {
	@Override
	protected void execute(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			String type = MDC.get("type") == null ? "" : String.valueOf(MDC.get("type"));
			String msg = MDC.get("msg") == null ? "" : String.valueOf(MDC.get("msg"));
			String userid = MDC.get("userid") == null ? "" : String.valueOf(MDC.get("userid"));
			String username = MDC.get("username") == null ? "" : String.valueOf(MDC.get("username"));
			stmt.setString(1, userid);
			stmt.setString(2, username);
			stmt.setString(3, type);
			stmt.setString(4, msg);
			stmt.executeUpdate();
		} catch (SQLException e) {
			if (stmt != null){
				stmt.close();
			}
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			closeConnection(con);
		}
	}
}
