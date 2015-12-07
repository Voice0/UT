package objects;

import java.util.Map;

import dao.GroupDAO;

public class Group extends BaseObject<GroupDAO>implements Printable {

	private int groupID;
	private String name;
	private int year;

	public Group(Map<String, Object> params) {
		super();
		setDao(new GroupDAO());
		this.name = (String) params.get(Constants.GROUPNAME);
		if (params.get(Constants.YEAR) != null) {
			this.year = (int) params.get(Constants.YEAR);
		}
	}

	public Group() {
		setDao(new GroupDAO());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfLearning() {
		return year;
	}

	public void setYearOfLearning(int year) {
		this.year = year;
	}

}
