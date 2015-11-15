package objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.BaseDAO;
import dao.DaoException;

public class BaseObject<T extends BaseDAO> {
	
	protected static Logger log = LogManager.getLogger(BaseObject.class);
	public T dao;
	private Map<String, Object> params = new HashMap<>();

	public T getDao() {
		return this.dao;
	}

	public void setDao(T dao) {
		this.dao = dao;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public List<Printable> getAll() {
		List<Printable> result = null;
		try {
			result = getDao().getAllObjects();
		} catch (DaoException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public int addObject() {
		int result = -1;
		try {
			result = getDao().createObject(this.params);
		} catch (DaoException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public Printable createEditForm() {
		Printable objectToEdit = null;
		try {
			objectToEdit = getDao().getObject(params);
		} catch (DaoException e) {
			log.error(e.getMessage());
		}
		return objectToEdit;
	}

	public int editObject() {
		int result = -1;
		try {
			result = getDao().updateObject(this.params);
		} catch (DaoException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public int deleteObject() {
		int result = -1;
		try {
			result = getDao().deleteObject(this.params);
		} catch (DaoException e) {
			log.error(e.getMessage());
		}
		return result;
	}

}
