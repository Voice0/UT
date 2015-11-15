package unittests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.BaseDAO;
import dao.DaoException;
import objects.Group;
import objects.Printable;
import objects.ScheduleItem;

public class DAOstub extends BaseDAO {

	public int createObject(Map<String, Object> params) {
		int response = 1;
		return response;
	}

	public List<Printable> getAllObjects() {
		List<Printable> result = new ArrayList<Printable>();
		Group group = new Group();
		for (int i = 0; i < 12; i++) {
			result.add(group);
		}
		return result;
	}

	public Printable getObject(Map<String, Object> params) {
		Group group = new Group();
		return group;
	}

	public int updateObject(Map<String, Object> params) {
		int response = 1;
		return response;
	}

	public int deleteObject(Map<String, Object> params) {
		int response = 1;
		return response;
	}

	public List<ScheduleItem> getTimetable(String name, String personType) throws DaoException {
		List<ScheduleItem> result = new ArrayList<ScheduleItem>();
		ScheduleItem scheduleItem = new ScheduleItem("1", "2", "3", "4", "5");
		if (personType.equals("teacher")) {
			for (int i = 0; i < 2; i++) {
				result.add(scheduleItem);
			}
		} else if (personType.equals("group")) {
			for (int i = 0; i < 4; i++) {
				result.add(scheduleItem);
			}
		} else
			result = null;
		return result;
	}

}
