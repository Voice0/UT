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
import objects.Group;
import objects.Printable;
import objects.Student;

public class StudentDAO extends BaseDAO {

	private static Logger log = LogManager.getLogger(StudentDAO.class);
	private String CREATE_STUDENT = "INSERT INTO students (name, group_) SELECT ?, groups.id FROM groups WHERE groups.name = ?;";
	private String GET_ALL_STUDENTS = "SELECT students.id, students.name, groups.name, groups.year from students,"
			+ "groups where students.group_ = groups.id;";
	private String GET_STUDENT = "SELECT students.id, students.name, groups.name, groups.year from students, groups "
			+ "where students.name = ? and students.group_ = groups.id;";
	private String GET_STUDENTS_GROUP = "SELECT groups.id groups.name, groups.year from students, groups where "
			+ "students.name = ? and students.group_ = groups.id;";
	private String UPDATE_STUDENT = "UPDATE students SET name = ?, group_ = (SELECT groups.id "
			+ "FROM groups WHERE groups.name = ?) WHERE students.name = ?;";
	private String DELETE_STUDENT_BY_NAME = "DELETE FROM students where name = ?;";

	public int createObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to createStudent");
		int response = -1;
		String studentName = (String) params.get(Constants.STUDENTNAME);
		String groupName = (String) params.get(Constants.GROUPNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + CREATE_STUDENT);
			preparedStatement = connection.prepareStatement(CREATE_STUDENT);
			preparedStatement.setString(1, studentName);
			preparedStatement.setString(2, groupName);
			response = preparedStatement.executeUpdate();
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
		log.info("Entered to getAllStudents");
		List<Printable> result = new ArrayList<Printable>();
		Connection connection = DBConnectionUtil.getConnection();
		Statement statement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating statement for: " + GET_ALL_STUDENTS);
			statement = connection.createStatement();
			resultset = statement.executeQuery(GET_ALL_STUDENTS);
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				Student student = createNewStudentByParams(resultset);
				result.add(student);
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

	private Student createNewStudentByParams(ResultSet resultset) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.STUDENTNAME, resultset.getString(2));
		params.put(Constants.GROUPNAME, resultset.getString(3));
		Student student = new Student(params);
		return student;
	}

	public Printable getObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to getStudentByName");
		Student student = null;
		String studentName = (String) params.get(Constants.STUDENTNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating prepared statement for: " + GET_STUDENT);
			preparedStatement = connection.prepareStatement(GET_STUDENT);
			preparedStatement.setString(1, studentName);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				student = createNewStudentByParams(resultset);
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
		return student;
	}

	public Group getStudentsGroup(String studentName) throws DaoException {
		log.info("Entered to getStudentsGroup");
		Group group = null;
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		GroupDAO groupdao = new GroupDAO();
		try {
			log.info("Creating statement for: " + GET_STUDENTS_GROUP);
			preparedStatement = connection.prepareStatement(GET_STUDENTS_GROUP);
			preparedStatement.setString(1, studentName);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				group = groupdao.createNewGroupByParams(resultset);
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
		return group;
	}

	public int updateObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to updateStudentNameByName");
		int response = -1;
		String newName = (String) params.get(Constants.NEW_STUDENTNAME);
		String groupName = (String) params.get(Constants.GROUPNAME);
		String oldName = (String) params.get(Constants.STUDENTNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + UPDATE_STUDENT);
			preparedStatement = connection.prepareStatement(UPDATE_STUDENT);
			preparedStatement.setString(1, newName);
			preparedStatement.setString(2, groupName);
			preparedStatement.setString(3, oldName);
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
		log.info("Entered to deleteStudentByName");
		int response = -1;
		String name = (String) params.get(Constants.STUDENTNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + DELETE_STUDENT_BY_NAME);
			preparedStatement = connection.prepareStatement(DELETE_STUDENT_BY_NAME);
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
