package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import objects.ScheduleItem;

public class TimetableDAO {

	private static Logger log = LogManager.getLogger(TimetableDAO.class);
	private String QUERY_BY_TEACHERS_NAME = "SELECT lectures.id, lectures.datetime,  groups.name, "
			+ "rooms.number, subjects.name, teachers.name from lectures, groups, rooms, subjects, "
			+ "teachers where teachers.name = ? and lectures.groups = groups.id and lectures.rooms = rooms.id "
			+ "and lectures.subjects = subjects.id and lectures.teachers = teachers.id order by datetime;";
	private String QUERY_BY_GROUPS_NAME = "SELECT lectures.id, lectures.datetime,  groups.name, "
			+ "rooms.number, subjects.name, teachers.name from lectures, groups, rooms, subjects, "
			+ "teachers where groups.name = ? and lectures.groups = groups.id and lectures.rooms = rooms.id "
			+ "and lectures.subjects = subjects.id and lectures.teachers = teachers.id order by datetime;";

	public List<ScheduleItem> getTimetable(String name, String personType) throws DaoException {
		log.info("Entered to getTimetable");
		List<ScheduleItem> result = new ArrayList<ScheduleItem>();
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			if (personType.equals("Teacher")) {
				log.debug(String.format("Creating prepared statement for: %s", QUERY_BY_TEACHERS_NAME));
				preparedStatement = connection.prepareStatement(QUERY_BY_TEACHERS_NAME);
			} else if (personType.equals("Student")) {
				log.info("Creating prepared statement for: " + QUERY_BY_GROUPS_NAME);
				preparedStatement = connection.prepareStatement(QUERY_BY_GROUPS_NAME);
			} else {
				log.info("Peson type isn`t equals to Teacher or Student.");
				return null;
			}
			preparedStatement.setString(1, name);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				createScheduleItemByParams(result, resultset);
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
		return result;
	}

	private void createScheduleItemByParams(List<ScheduleItem> result, ResultSet resultset) throws SQLException {
		String datetime = resultset.getString(2);
		String groups = resultset.getString(3);
		String rooms = resultset.getString(4);
		String subjects = resultset.getString(5);
		String teachers = resultset.getString(6);
		ScheduleItem scheduleItem = new ScheduleItem(datetime, groups, rooms, subjects, teachers);
		result.add(scheduleItem);
	}
}
