package objects;

import java.util.Map;

import dao.DaoException;
import dao.GroupDAO;
import dao.LectureDAO;
import dao.RoomDAO;
import dao.SubjectDAO;
import dao.TeacherAndSubjectDAO;
import dao.TeacherDAO;

public class TeacherAndSubject extends BaseObject<TeacherAndSubjectDAO>implements Printable {
	
	private int teacherAndSubjectID;
	private Teacher teacher;
	private Subject subject;
	
	public TeacherAndSubject(Map<String, Object> params) {
		super();
		setDao(new TeacherAndSubjectDAO());
		if (params.get(Constants.TEACHER_AND_SUBJECT_ID) != null) {
			this.teacherAndSubjectID = (int) params.get(Constants.TEACHER_AND_SUBJECT_ID);
		}
		TeacherDAO teacherdao = new TeacherDAO();
		SubjectDAO subjectdao = new SubjectDAO();
		try {
			this.teacher = (Teacher) teacherdao.getObject(params);
			this.subject = (Subject) subjectdao.getObject(params);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	public int getTeacherAndSubjectID() {
		return teacherAndSubjectID;
	}
	public void setTeacherAndSubjectID(int teacherAndSubjectID) {
		this.teacherAndSubjectID = teacherAndSubjectID;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
