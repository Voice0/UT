package objects;

import java.util.Map;

import dao.DaoException;
import dao.GroupDAO;
import dao.StudentDAO;

public class Student extends BaseObject<StudentDAO>implements Printable {

	private int studentID;	
	private String name;
	private Group group;

	public Student(Map<String, Object> params) {
		super();
		setDao(new StudentDAO());
		this.name = (String) params.get(Constants.STUDENTNAME);
		GroupDAO groupdao = new GroupDAO();
		try {
			this.group = (Group) groupdao.getObject(params);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
