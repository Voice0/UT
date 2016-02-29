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
import objects.TeacherAndSubject;

public class TeacherAndSubjectDAO extends BaseDAO {
	
	private static Logger log = LogManager.getLogger(StudentDAO.class);
	private String CREATE_TEACHER_AND_SUBJECT = "INSERT INTO teacher_and_subject (teacher, subject) "
			+ "SELECT teachers.id, subjects.id FROM teachers, subjects "
			+ "WHERE teachers.name = ? and subjects.name = ?";
	private String GET_ALL_TEACHER_AND_SUBJECT = "SELECT teacher_and_subject.id, teachers.name, subjects.name "
			+ "FROM teacher_and_subject, teachers, subjects "
			+ "WHERE teacher_and_subject.teacher = teachers.id "
			+ "and teacher_and_subject.subject = subjects.id ORDER BY teachers.name, subjects.name;";
	private String GET_TEACHER_AND_SUBJECT = "SELECT teacher_and_subject.id, teachers.name, subjects.name "
			+ "from teacher_and_subject, teachers, subjects where teacher_and_subject.id = ? "
			+ "and teacher_and_subject.teacher = teachers.id and teacher_and_subject.subject = subjects.id;";
	private String UPDATE_TEACHER_AND_SUBJECT = "UPDATE teacher_and_subject SET teacher = "
			+ "(SELECT teachers.id FROM teachers WHERE teachers.name = ?), "
			+ "subject = (SELECT subjects.id FROM subjects WHERE subjects.name = ?) "
			+ "WHERE teacher_and_subject.id = ?;";
	private String DELETE_TEACHER_AND_SUBJECT = "DELETE FROM teacher_and_subject where id = ?;";
	private String GET_TEACHER_SUBJECTS = "SELECT teacher_and_subject.id, subjects.name " 
			+ "FROM teacher_and_subject, teachers, subjects "
			+ "WHERE teacher_and_subject.teacher = teachers.id and teacher_"
			+ "and_subject.subject = subjects.id and teachers.name = ?";
	
	public int createObject(Map<String, Object> params) throws DaoException{
		log.info("Entered to createTeacherAndSubject");
		int response = -1;
		String teacherName = (String) params.get(Constants.TEACHERNAME);
		String subjectName = (String) params.get(Constants.SUBJECTNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + CREATE_TEACHER_AND_SUBJECT);
			preparedStatement = connection.prepareStatement(CREATE_TEACHER_AND_SUBJECT);
			preparedStatement.setString(1, teacherName);
			preparedStatement.setString(2, subjectName);
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
	
	public List<Subject> getTeacherSubjects(String teacherName) throws DaoException{
		log.info("Entered to getTeacherSubjects");
		List<Subject> result = new ArrayList<Subject>();
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating statement for: " + GET_TEACHER_SUBJECTS);
			preparedStatement = connection.prepareStatement(GET_TEACHER_SUBJECTS);
			preparedStatement.setString(1, teacherName);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));	
			SubjectDAO subjectDao = new SubjectDAO();
			while (resultset.next()) {
				Subject subject = subjectDao.createNewSubjectByParams(resultset);
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
				DBConnectionUtil.closeStatement(preparedStatement);
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

	public List<Printable> getAllObjects() throws DaoException{
		log.info("Entered to getAllTeacherAndSubject");
		List<Printable> result = new ArrayList<Printable>();
		Connection connection = DBConnectionUtil.getConnection();
		Statement statement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating statement for: " + GET_ALL_TEACHER_AND_SUBJECT);
			statement = connection.createStatement();
			resultset = statement.executeQuery(GET_ALL_TEACHER_AND_SUBJECT);
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				TeacherAndSubject teacherAndSubject = createNewTeacherAndSubjectByParams(resultset);
				result.add(teacherAndSubject);
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
	
	private TeacherAndSubject createNewTeacherAndSubjectByParams(ResultSet resultset) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.TEACHER_AND_SUBJECT_ID, resultset.getInt(1));
		params.put(Constants.TEACHERNAME, resultset.getString(2));
		params.put(Constants.SUBJECTNAME, resultset.getString(3));
		TeacherAndSubject teacherAndSubject = new TeacherAndSubject(params);
		return teacherAndSubject;
	}


	public Printable getObject(Map<String, Object> params) throws DaoException{
		log.info("Entered to getTeacherAndSubject");
		int id = Integer.parseInt((String) params.get(Constants.TEACHER_AND_SUBJECT_ID));
		TeacherAndSubject teacherAndSubject = null;
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating prepared statement for: " + GET_TEACHER_AND_SUBJECT);
			preparedStatement = connection.prepareStatement(GET_TEACHER_AND_SUBJECT);
			preparedStatement.setInt(1, id);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				teacherAndSubject = createNewTeacherAndSubjectByParams(resultset);
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
		return teacherAndSubject;
	}

	public int updateObject(Map<String, Object> params) throws DaoException{
		log.info("Entered to updateTeacherAndSubject");
		int response = -1;
		String newTeacherName = (String) params.get(Constants.TEACHERNAME);
		String newSubjectName = (String) params.get(Constants.SUBJECTNAME);
		int id = Integer.parseInt((String) params.get(Constants.TEACHER_AND_SUBJECT_ID));
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + UPDATE_TEACHER_AND_SUBJECT);
			preparedStatement = connection.prepareStatement(UPDATE_TEACHER_AND_SUBJECT);
			preparedStatement.setString(1, newTeacherName);
			preparedStatement.setString(2, newSubjectName);
			preparedStatement.setInt(3, id);
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

	public int deleteObject(Map<String, Object> params) throws DaoException{
		log.info("Entered to deleteTeacherAndSubject");
		int response = -1;
		int id = Integer.parseInt((String) params.get(Constants.TEACHER_AND_SUBJECT_ID));
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + DELETE_TEACHER_AND_SUBJECT);
			preparedStatement = connection.prepareStatement(DELETE_TEACHER_AND_SUBJECT);
			preparedStatement.setInt(1, id);
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
