package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.BaseObject;
import objects.Constants;
import objects.Group;
import objects.Lecture;
import objects.Printable;
import objects.Room;
import objects.Subject;
import objects.Teacher;
import objects.TeacherAndSubject;

@WebServlet("/lecture")
public class LectureServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public LectureServlet() {
		super();
		Map<String, Object> params = new HashMap<String, Object>();
		setBaseObject(new Lecture(params));
	}

	void setParamsForDoGet(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.LECTUREID, request.getParameter(Constants.LECTUREID));
		getParams().put(Constants.DATETIME, request.getParameter(Constants.DATETIME));
		getParams().put(Constants.TEACHERNAME, request.getParameter(Constants.TEACHERNAME));
		getParams().put(Constants.GROUPNAME, request.getParameter(Constants.GROUPNAME));
		getParams().put(Constants.ROOMNUMBER, request.getParameter(Constants.ROOMNUMBER));
		getParams().put(Constants.SUBJECTNAME, request.getParameter(Constants.SUBJECTNAME));
	}

	public List<Printable> getAll(HttpServletRequest request) {
		List<Printable> lecture = getBaseObject().getAll();
		request.setAttribute("Lecturelist", lecture);
		return null;
	}

	public List<Printable> getAllObjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Printable> lecture = super.getAllObjects(request, response);
		BaseObject<?> group = new Group(baseObject.getParams());
		BaseObject<?> room = new Room(baseObject.getParams());
		BaseObject<?> teacher = new Teacher(baseObject.getParams());
		BaseObject<?> subject = new Subject(baseObject.getParams());
		TeacherAndSubject teachersubject = new TeacherAndSubject(baseObject.getParams());
		List<Printable> groupList = group.getAll();
		List<Printable> roomList = room.getAll();
		List<Printable> teacherList = teacher.getAll();
		List<Printable> subjectList = subject.getAll();
		List<Printable> teachersubjectList = teachersubject.getAll();
		Map <String, List<Subject>> tsmap = teachersubject.getMap();
		request.setAttribute("Lecturelist", lecture);
		request.setAttribute("Grouplist", groupList);
		request.setAttribute("Roomlist", roomList);
		request.setAttribute("Teacherlist", teacherList);
		request.setAttribute("Subjectlist", subjectList);
		request.setAttribute("TeacherAndSubjectlist", teachersubjectList);
		request.setAttribute("TeacherAndSubjectMap", tsmap);
		request.getRequestDispatcher("/lecture.jsp").forward(request, response);
		return null;
	}

	public void getAllObjectsAfterChanging(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAll(request);
		request.getRequestDispatcher("/lecture.jsp").forward(request, response);
	}

	public Printable createEditForm(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.LECTUREID, request.getParameter(Constants.LECTUREID));
		Printable result = baseObject.createEditForm();
		return result;
	}

	public int editObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.LECTUREID, request.getParameter(Constants.LECTUREID));
		getParams().put(Constants.DATETIME, request.getParameter(Constants.DATETIME));
		getParams().put(Constants.GROUPNAME, request.getParameter(Constants.GROUPNAME));
		getParams().put(Constants.ROOMNUMBER, request.getParameter(Constants.ROOMNUMBER));
		getParams().put(Constants.TEACHERNAME, request.getParameter(Constants.TEACHERNAME));
		getParams().put(Constants.SUBJECTNAME, request.getParameter(Constants.SUBJECTNAME));
		int result = baseObject.editObject();
		return result;
	}

	public int deleteObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.LECTUREID, request.getParameter(Constants.LECTUREID));
		int result = baseObject.deleteObject();
		return result;
	}

}