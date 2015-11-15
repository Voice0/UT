package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import objects.Constants;
import objects.Printable;
import objects.Subject;

public class SubjectDAO extends BaseDAO {

	private static Logger log = LogManager.getLogger(SubjectDAO.class);
	private String CREATE_SUBJECT = "INSERT INTO subjects (name) VALUES (?);";
	private String GET_ALL_SUBJECTS = "SELECT * FROM subjects ORDER BY name;";
	private String GET_SUBJECT = "SELECT * FROM subjects WHERE name = ?";
	private String UPDATE_SUBJECT = "UPDATE subjects SET name = ? WHERE name = ?;";
	private String DELETE_SUBJECT = "DELETE FROM subjects WHERE name = ?;";

	public int createObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to createSubject");
		int response = -1;
		String name = (String) params.get(Constants.SUBJECTNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + CREATE_SUBJECT);
			preparedStatement = connection.prepareStatement(CREATE_SUBJECT);
			preparedStatement.setString(1, name);
			response = preparedStatement.executeUpdate();
			log.debug(String.format("Got response: %s", response));
		} catch (SQLException e) {
			throw new DaoException("Unable to execute prepared statement.", e);
		} finally {
			try {
				DBConnectionUtil.closePreparedStatement(preparedStatement);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			try {
				DBConnectionUtil.closeConnectionToDB(connection);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return response;
	}

	public List<Printable> getAllObjects() throws DaoException {
		log.info("Entered to getAllSubjects");
		List<Printable> result = new ArrayList<Printable>();
		Connection connection = DBConnectionUtil.getConnection();
		Statement statement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating statement for: " + GET_ALL_SUBJECTS);
			statement = connection.createStatement();
			resultset = statement.executeQuery(GET_ALL_SUBJECTS);
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				Subject subject = createNewSubjectByParams(resultset);
				result.add(subject);
			}
		} catch (SQLException e) {
			throw new DaoException("Unable to execute statement.", e);
		} finally {
			try {
				DBConnectionUtil.closeResultset(resultset);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			try {
				DBConnectionUtil.closeStatement(statement);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			try {
				DBConnectionUtil.closeConnectionToDB(connection);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return result;
	}

	public Subject createNewSubjectByParams(ResultSet resultset) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.SUBJECTNAME, resultset.getString(2));
		Subject subject = new Subject(params);
		return subject;
	}

	public Printable getObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to getSubjectByName");
		String name = (String) params.get(Constants.SUBJECTNAME);
		Subject subject = null;
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating prepared statement for: " + GET_SUBJECT);
			preparedStatement = connection.prepareStatement(GET_SUBJECT);
			preparedStatement.setString(1, name);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				subject = createNewSubjectByParams(resultset);
			}
		} catch (SQLException e) {
			throw new DaoException("Unable to execute statement.", e);
		} finally {
			try {
				DBConnectionUtil.closeResultset(resultset);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			try {
				DBConnectionUtil.closePreparedStatement(preparedStatement);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			try {
				DBConnectionUtil.closeConnectionToDB(connection);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return subject;
	}

	public int updateObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to updateSubject");
		int response = -1;
		String newValue = (String) params.get(Constants.NEW_SUBJECTNAME);
		String oldValue = (String) params.get(Constants.SUBJECTNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + UPDATE_SUBJECT);
			preparedStatement = connection.prepareStatement(UPDATE_SUBJECT);
			preparedStatement.setString(1, newValue);
			preparedStatement.setString(2, oldValue);
			response = preparedStatement.executeUpdate();
			log.debug(String.format("Got response: %s", response));
		} catch (SQLException e) {
			throw new DaoException("Unable to execute prepared statement.", e);
		} finally {
			try {
				DBConnectionUtil.closePreparedStatement(preparedStatement);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			try {
				DBConnectionUtil.closeConnectionToDB(connection);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return response;
	}

	public int deleteObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to deleteSujbject");
		int response = -1;
		String name = (String) params.get(Constants.SUBJECTNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + DELETE_SUBJECT);
			preparedStatement = connection.prepareStatement(DELETE_SUBJECT);
			preparedStatement.setString(1, name);
			response = preparedStatement.executeUpdate();
			log.debug(String.format("Got response: %s", response));
		} catch (SQLException e) {
			throw new DaoException("Unable to execute prepared statement.", e);
		} finally {
			try {
				DBConnectionUtil.closePreparedStatement(preparedStatement);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			try {
				DBConnectionUtil.closeConnectionToDB(connection);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return response;
	}
}
