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
import objects.Room;

public class RoomDAO extends BaseDAO {

	private static Logger log = LogManager.getLogger(RoomDAO.class);
	private String CREATE_ROOM = "INSERT INTO rooms (number) VALUES (?);";
	private String GET_ALL_ROOMS = "SELECT * FROM rooms ORDER BY number;";
	private String GET_ROOM = "SELECT * FROM rooms WHERE number = ?";
	private String UPDATE_ROOM = "UPDATE rooms SET number = ? WHERE number = ?;";
	private String DELETE_ROOM = "DELETE FROM rooms where number = ?;";

	public int createObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to createRoom");
		int response = -1;
		String number = (String) params.get(Constants.ROOMNUMBER);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + CREATE_ROOM);
			preparedStatement = connection.prepareStatement(CREATE_ROOM);
			preparedStatement.setString(1, number);
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
		log.info("Entered to getAllRooms");
		List<Printable> result = new ArrayList<Printable>();
		Connection connection = DBConnectionUtil.getConnection();
		Statement statement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating statement for: " + GET_ALL_ROOMS);
			statement = connection.createStatement();
			resultset = statement.executeQuery(GET_ALL_ROOMS);
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				Room room = createNewRoomByParams(resultset);
				result.add(room);
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

	private Room createNewRoomByParams(ResultSet resultset) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.ROOMNUMBER, resultset.getString(2));
		Room room = new Room(params);
		return room;
	}

	public Printable getObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to getRoom");
		String number = (String) params.get(Constants.ROOMNUMBER);
		Room room = null;
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating prepared statement for: " + GET_ROOM);
			preparedStatement = connection.prepareStatement(GET_ROOM);
			preparedStatement.setString(1, number);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				room = createNewRoomByParams(resultset);
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
		return room;
	}

	public int updateObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to updateRoom");
		int response = -1;
		String newValue = (String) params.get(Constants.NEW_ROOMNUMBER);
		String oldValue = (String) params.get(Constants.ROOMNUMBER);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + UPDATE_ROOM);
			preparedStatement = connection.prepareStatement(UPDATE_ROOM);
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
		log.info("Entered to deleteRoom");
		int response = -1;
		String number = (String) params.get(Constants.ROOMNUMBER);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + DELETE_ROOM);
			preparedStatement = connection.prepareStatement(DELETE_ROOM);
			preparedStatement.setString(1, number);
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
