package objects;

import java.util.Map;

import dao.DaoException;
import dao.GroupDAO;
import dao.LectureDAO;
import dao.RoomDAO;
import dao.SubjectDAO;
import dao.TeacherDAO;

public class Lecture extends BaseObject<LectureDAO>implements Printable {

	private int id;
	private String datetime;
	private Teacher teacher;
	private Group group;
	private Room room;
	private Subject subject;

	public Lecture(Map<String, Object> params) {
		super();
		setDao(new LectureDAO());
		if (params.get(Constants.YEAR) != null) {
			this.id = (int) params.get(Constants.LECTUREID);
		}
		this.datetime = (String) params.get(Constants.DATETIME);
		TeacherDAO teacherdao = new TeacherDAO();
		GroupDAO groupdao = new GroupDAO();
		RoomDAO roomdao = new RoomDAO();
		SubjectDAO subjectdao = new SubjectDAO();
		try {
			this.teacher = (Teacher) teacherdao.getObject(params);
			this.group = (Group) groupdao.getObject(params);
			this.room = (Room) roomdao.getObject(params);
			this.subject = (Subject) subjectdao.getObject(params);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
