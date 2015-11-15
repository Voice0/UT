package unittests;

import java.util.List;

import dao.DaoException;
import objects.ScheduleItem;
import objects.Timetable;

public class TimetableStub extends Timetable {

	DAOstub timetableDAO = new DAOstub();

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
