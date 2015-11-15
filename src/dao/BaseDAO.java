package dao;

import java.util.List;
import java.util.Map;

import objects.Printable;

public abstract class BaseDAO {

	public abstract int createObject(Map<String, Object> params) throws DaoException;

	public abstract List<Printable> getAllObjects() throws DaoException;

	public abstract Printable getObject(Map<String, Object> params) throws DaoException;

	public abstract int updateObject(Map<String, Object> params) throws DaoException;

	public abstract int deleteObject(Map<String, Object> params) throws DaoException;

}
