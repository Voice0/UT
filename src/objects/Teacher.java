package objects;

import java.util.Map;

import dao.DaoException;
import dao.SubjectDAO;
import dao.TeacherDAO;

public class Teacher extends BaseObject<TeacherDAO>implements Printable {

	private int teacherID;
	private String name;
	private Subject subject;

	public Teacher(Map<String, Object> params) {
		super();
		setDao(new TeacherDAO());
		this.name = (String) params.get(Constants.TEACHERNAME);
		SubjectDAO subjectdao = new SubjectDAO();
		try {
			this.subject = (Subject) subjectdao.getObject(params);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	public Teacher() {
		setDao(new TeacherDAO());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
