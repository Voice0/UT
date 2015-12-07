package objects;

import java.util.Map;

import dao.SubjectDAO;

public class Subject extends BaseObject<SubjectDAO>implements Printable {

	private int subjectID;
	private String name;

	public Subject(Map<String, Object> params) {
		super();
		setDao(new SubjectDAO());
		this.name = (String) params.get(Constants.SUBJECTNAME);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
