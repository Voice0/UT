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

public class GroupDAO extends BaseDAO {

	private static Logger log = LogManager.getLogger(GroupDAO.class);
	private String CREATE_GROUP = "INSERT INTO groups (name, year) VALUES (?, ?);";
	private String GET_ALL_GROUPS = "SELECT * from GROUPS ORDER BY name;";
	private String GET_GROUP = "SELECT * from GROUPS where name = ?;";
	private String UPDATE_GROUP = "UPDATE GROUPS SET name = ?, year = ? where name = ?;";
	private String DELETE_GROUP_BY_NAME = "DELETE FROM GROUPS WHERE name = ?;";

	public int createObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to createGroup");
		int response = -1;
		String name = (String) params.get(Constants.GROUPNAME);
		int year = (int) params.get(Constants.YEAR);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + CREATE_GROUP);
			preparedStatement = connection.prepareStatement(CREATE_GROUP);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, year);
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
		log.info("Entered to getAllGROUPS");
		List<Printable> result = new ArrayList<Printable>();
		Connection connection = DBConnectionUtil.getConnection();
		Statement statement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating statement for: " + GET_ALL_GROUPS);
			statement = connection.createStatement();
			resultset = statement.executeQuery(GET_ALL_GROUPS);
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				Group group = createNewGroupByParams(resultset);
				result.add(group);
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

	public Group createNewGroupByParams(ResultSet resultset) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.GROUPNAME, resultset.getString(2));
		params.put(Constants.YEAR, resultset.getInt(3));
		Group group = new Group(params);
		return group;
	}

	public Printable getObject(Map<String, Object> params) throws DaoException {
		log.info("Entered to getGroupByName");
		String name = (String) params.get(Constants.GROUPNAME);
		Group group = null;
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			log.info("Creating prepared statement for: " + GET_GROUP);
			preparedStatement = connection.prepareStatement(GET_GROUP);
			preparedStatement.setString(1, name);
			resultset = preparedStatement.executeQuery();
			log.debug("resultset is: " + (resultset.isBeforeFirst() ? "not empty" : "empty"));
			while (resultset.next()) {
				group = createNewGroupByParams(resultset);
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
		log.info("Entered to updateGroup");
		int response = -1;
		String newName = (String) params.get(Constants.NEW_GROUPNAME);
		int year = (int) params.get(Constants.YEAR);
		String oldName = (String) params.get(Constants.GROUPNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + UPDATE_GROUP);
			preparedStatement = connection.prepareStatement(UPDATE_GROUP);
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, year);
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
		log.info("Entered to deleteGroupByName");
		int response = -1;
		String name = (String) params.get(Constants.GROUPNAME);
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			log.info("Creating prepared statement for: " + DELETE_GROUP_BY_NAME);
			preparedStatement = connection.prepareStatement(DELETE_GROUP_BY_NAME);
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
