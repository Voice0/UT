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
import objects.Teacher;

public class TeacherDAO extends BaseDAO {

	private static Logger log = LogManager.getLogger(TeacherDAO.class);
	private String CREATE_TEACHER = "INSERT INTO teachers (name) VALUES (?);";
	private String GET_ALL_TEACHERS = "SELECT * FROM teachers ORDER BY name;";
	private String GET_TEACHER = "SELECT * FROM teachers WHERE name = ?";
//	private String GET_TEACHERS_SUBJECT = "SELECT subjects.id, subjects.name from teachers, subjects where "
//			+ "teachers.name = ? and teachers.subjects = subjects.id;";
	private String UPDATE_TEACHER = "UPDATE teachers SET name = ? WHERE name = ?;";
	private String DELETE_TEACHER = "DELETE FROM teachers where name = ?";

	public int createObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to createTeacher");
		int response = -1;
		String name = (String) params.get(Constants.TEACHERNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + CREATE_TEACHER);
			preparedStatement = connection.prepareStatement(CREATE_TEACHER);
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
		log.info("Entered to getAllTeachers");
		List<Printable> result = new ArrayList<Printable>();
		Connection connection = DBConnectionUtil.getConnection();
		Statement statement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating statement for: " + GET_ALL_TEACHERS);
			statement = connection.createStatement();
			resultset = statement.executeQuery(GET_ALL_TEACHERS);
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				Teacher teacher = createNewTeacherByParams(resultset);
				result.add(teacher);
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

	private Teacher createNewTeacherByParams(ResultSet resultset) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.TEACHERNAME, resultset.getString(2));
		Teacher teacher = new Teacher(params);
		return teacher;
	}

	public Printable getObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to getTeacher");
		String name = (String) params.get(Constants.TEACHERNAME);
		Teacher teacher = null;
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating prepared statement for: " + GET_TEACHER);
			preparedStatement = connection.prepareStatement(GET_TEACHER);
			preparedStatement.setString(1, name);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				teacher = createNewTeacherByParams(resultset);
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
		return teacher;
	}

//	public Subject getTeachersSubject(String teacherName) throws DaoException {
//		log.info("Entered to getTeachersSubject");
//		Subject subject = null;
//		Connection connection = DBConnectionUtil.getConnection();
//		PreparedStatement preparedStatement = null;
//		ResultSet resultset = null;
//		SubjectDAO subjectdao = new SubjectDAO();
//		try {
//			log.info("Creating statement for: " + GET_TEACHERS_SUBJECT);
//			preparedStatement = connection.prepareStatement(GET_TEACHERS_SUBJECT);
//			preparedStatement.setString(1, teacherName);
//			resultset = preparedStatement.executeQuery();
//			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
//			while (resultset.next()) {
//				subject = subjectdao.createNewSubjectByParams(resultset);
//			}
//		} catch (SQLException e) {
//			throw new DaoException("Unable to execute statement.", e);
//		} finally {
//			try {
//				DBConnectionUtil.closeResultset(resultset);
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//			}
//			try {
//				DBConnectionUtil.closePreparedStatement(preparedStatement);
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//			}
//			try {
//				DBConnectionUtil.closeConnectionToDB(connection);
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//			}
//		}
//		return subject;
//	}

	public int updateObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to updateTeacher");
		int response = -1;
		String newValue = (String) params.get(Constants.NEW_TEACHERNAME);
		String oldValue = (String) params.get(Constants.TEACHERNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + UPDATE_TEACHER);
			preparedStatement = connection.prepareStatement(UPDATE_TEACHER);
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
		log.info("Entered to deleteTeacherByName");
		int response = -1;
		String teacherName = (String) params.get(Constants.TEACHERNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating statement for: " + DELETE_TEACHER);
			preparedStatement = connection.prepareStatement(DELETE_TEACHER);
			preparedStatement.setString(1, teacherName);
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
