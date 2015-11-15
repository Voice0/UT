package objects;

public class ScheduleItem {
	private String datetime;
	private String groups;
	private String rooms;
	private String subjects;
	private String teachers;

	public ScheduleItem(String datetime, String groups, String rooms, String subjects, String teachers) {
		this.datetime = datetime;
		this.groups = groups;
		this.rooms = rooms;
		this.subjects = subjects;
		this.teachers = teachers;
	}

	public String getDatetime() {
		return datetime;
	}

	public String getGroups() {
		return groups;
	}

	public String getRooms() {
		return rooms;
	}

	public String getSubjects() {
		return subjects;
	}

	public String getTeachers() {
		return teachers;
	}
}