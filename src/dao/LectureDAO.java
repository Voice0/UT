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
import objects.Lecture;
import objects.Printable;

public class LectureDAO extends BaseDAO {

	private static Logger log = LogManager.getLogger(LectureDAO.class);
	TeacherDAO teacherDAO = new TeacherDAO();
	private String CREATE_LECTURE = "INSERT INTO lectures (datetime, groups, rooms, subjects, teachers) "
			+ "SELECT ?, groups.id, rooms.id, teachers.subjects, teachers.id FROM groups, rooms, subjects, teachers "
			+ "WHERE groups.name = ? and rooms.number = ? and subjects.id = teachers.subjects and teachers.name = ?;";
	private String GET_ALL_LECTURES = "SELECT lectures.id, lectures.datetime, groups.name, groups.year, "
			+ "rooms.number, subjects.name, teachers.name from lectures, groups, rooms, subjects, teachers "
			+ "where lectures.groups = groups.id and lectures.rooms = rooms.id and "
			+ "lectures.subjects = subjects.id and lectures.teachers = teachers.id;";
	private String GET_LECTURE_BY_ID = "SELECT lectures.id, lectures.datetime, groups.name, groups.year, rooms.number, subjects.name, teachers.name "
			+ "from lectures, groups, rooms, subjects, teachers where lectures.id = ? and lectures.groups = groups.id "
			+ "and lectures.rooms = rooms.id and lectures.subjects = subjects.id and lectures.teachers = teachers.id;";
	private String UPDATE_LECTURE = "UPDATE lectures SET datetime = ?,groups = (SELECT groups.id FROM groups WHERE groups.name = ?), "
			+ "rooms = (SELECT rooms.id FROM rooms WHERE rooms.number = ?), subjects  = (SELECT subjects.id FROM subjects WHERE subjects.name = ?), "
			+ "teachers = (SELECT teachers.id FROM teachers WHERE teachers.name = ?) WHERE lectures.id = ?;";
	private String DELETE_LECTURE = "DELETE FROM lectures where id = ?;";

	public int createObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to createLecture");
		int response = -1;
		String datetime = (String) params.get(Constants.DATETIME);
		String groupName = (String) params.get(Constants.GROUPNAME);
		String roomNumber = (String) params.get(Constants.ROOMNUMBER);
		String teacherName = (String) params.get(Constants.TEACHERNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + CREATE_LECTURE);
			preparedStatement = connection.prepareStatement(CREATE_LECTURE);
			preparedStatement.setString(1, datetime);
			preparedStatement.setString(2, groupName);
			preparedStatement.setString(3, roomNumber);
			preparedStatement.setString(4, teacherName);
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
		log.info("Entered to getAllLectures");
		List<Printable> result = new ArrayList<Printable>();
		Connection connection = DBConnectionUtil.getConnection();
		Statement statement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating statement for: " + GET_ALL_LECTURES);
			statement = connection.createStatement();
			resultset = statement.executeQuery(GET_ALL_LECTURES);
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				Lecture lecture = createNewLectureByParams(resultset);
				result.add(lecture);
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

	private Lecture createNewLectureByParams(ResultSet resultset) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.LECTUREID, resultset.getInt(1));
		params.put(Constants.DATETIME, resultset.getString(2));
		params.put(Constants.GROUPNAME, resultset.getString(3));
		params.put(Constants.YEAR, resultset.getInt(4));
		params.put(Constants.ROOMNUMBER, resultset.getString(5));
		params.put(Constants.SUBJECTNAME, resultset.getString(6));
		params.put(Constants.TEACHERNAME, resultset.getString(7));
		Lecture lecture = new Lecture(params);
		return lecture;
	}

	public Printable getObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to getLecture");
		int id = Integer.parseInt((String) params.get(Constants.LECTUREID));
		Lecture lecture = null;
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating prepared statement for: " + GET_LECTURE_BY_ID);
			preparedStatement = connection.prepareStatement(GET_LECTURE_BY_ID);
			preparedStatement.setInt(1, id);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				lecture = createNewLectureByParams(resultset);
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
		return lecture;
	}

	public int updateObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to updateLecture");
		int response = -1;
		String newDatetime = (String) params.get(Constants.DATETIME);
		String newGroupName = (String) params.get(Constants.GROUPNAME);
		String newRoomNumber = (String) params.get(Constants.ROOMNUMBER);
		String newTeacherName = (String) params.get(Constants.TEACHERNAME);
		String newSubjectName = (String) params.get(Constants.SUBJECTNAME);;
		int id = Integer.parseInt((String) params.get(Constants.LECTUREID));
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + UPDATE_LECTURE);
			preparedStatement = connection.prepareStatement(UPDATE_LECTURE);
			preparedStatement.setString(1, newDatetime);
			preparedStatement.setString(2, newGroupName);
			preparedStatement.setString(3, newRoomNumber);
			preparedStatement.setString(4, newSubjectName);
			preparedStatement.setString(5, newTeacherName);
			preparedStatement.setInt(6, id);
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
		log.info("Entered to deleteLecture");
		int response = -1;
		int id = Integer.parseInt((String) params.get(Constants.LECTUREID));
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + DELETE_LECTURE);
			preparedStatement = connection.prepareStatement(DELETE_LECTURE);
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
