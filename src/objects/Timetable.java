package objects;

import java.util.List;

import dao.BaseDAO;
import dao.DaoException;
import dao.TimetableDAO;

public class Timetable extends BaseObject<BaseDAO> {

	TimetableDAO timetableDAO = new TimetableDAO();

	public List<ScheduleItem> getTimetable(String name, String personType) {
		List<ScheduleItem> timetableList = null;
		try {
			timetableList = timetableDAO.getTimetable(name, personType);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return timetableList;
	}

}