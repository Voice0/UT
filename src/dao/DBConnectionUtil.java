package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DBConnectionUtil {

	private static Logger log = LogManager.getLogger(DBConnectionUtil.class);
	private static DataSource ds;

	static {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/universitytimetable");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static class DBStatementException extends SQLException {

		private static final long serialVersionUID = 1L;

		public DBStatementException(String string, Throwable e) {
			super(string, e);
		}
	}

	public static class DBResultsetException extends SQLException {
		private static final long serialVersionUID = 1L;

		public DBResultsetException(String string, Throwable e) {
			super(string, e);
		}
	}

	public static void closeConnectionToDB(Connection connection) throws DaoException {
		try {
			if (!connection.isClosed()) {
				connection.close();
				if (connection.isClosed()) {
					log.info("Connection closed");
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Connection was not closed.", e);
		}
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) throws DBStatementException {
		try {
			if (!preparedStatement.isClosed()) {
				preparedStatement.close();
				if (preparedStatement.isClosed()) {
					log.info("Prepared Statement closed");
				}
			}
		} catch (SQLException e) {
			throw new DBStatementException("Prepared Statement was not closed.", e);
		}
	}

	public static void closeStatement(Statement statement) throws DBStatementException {
		try {
			if (!statement.isClosed()) {
				statement.close();
				if (statement.isClosed()) {
					log.info("Statement closed");
				}
			}
		} catch (SQLException e) {
			throw new DBStatementException("Statement was not closed.", e);
		}
	}

	public static void closeResultset(ResultSet resultset) throws DBResultsetException {
		try {
			if (!resultset.isClosed()) {
				resultset.close();
				if (resultset.isClosed()) {
					log.info("Resultset closed");
				}
			}
		} catch (SQLException e) {
			throw new DBResultsetException("Resultset was not closed.", e);
		}
	}

}
