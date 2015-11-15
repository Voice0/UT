package dao;

import java.sql.SQLException;

public class DaoException extends SQLException {
	
	private static final long serialVersionUID = 1L;

	public DaoException(String string) {
		super(string);
	}

	public DaoException(String string, Throwable e) {
		super(string, e);
	}
}
