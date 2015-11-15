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
import objects.Printable;
import objects.Subject;
import objects.Teacher;

@WebServlet("/teacher")
public class TeacherServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	public static final String TEACHERNAME = "teachername";
	public static final String NEW_TEACHERNAME = "newteachername";
	public static final String SUBJECTNAME = "subjectname";

	public TeacherServlet() {
		super();
		Map<String, Object> params = new HashMap<String, Object>();
		setBaseObject(new Teacher(params));
	}

	void setParamsForDoGet(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_TEACHERNAME, request.getParameter(Constants.NEW_TEACHERNAME));
		getParams().put(Constants.TEACHERNAME, request.getParameter(Constants.TEACHERNAME));
		getParams().put(Constants.SUBJECTNAME, request.getParameter(Constants.SUBJECTNAME));
	}

	public List<Printable> getAll(HttpServletRequest request) {
		List<Printable> teacher = getBaseObject().getAll();
		request.setAttribute("Teacherlist", teacher);
		return null;
	}

	public List<Printable> getAllObjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Printable> teacherList = super.getAllObjects(request, response);
		BaseObject<?> subject = new Subject(baseObject.getParams());
		List<Printable> subjectList = subject.getAll();
		request.setAttribute("Teacherlist", teacherList);
		request.setAttribute("Subjectlist", subjectList);
		request.getRequestDispatcher("/teacher.jsp").forward(request, response);
		return null;
	}

	public void getAllObjectsAfterChanging(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAll(request);
		request.getRequestDispatcher("/teacher.jsp").forward(request, response);
	}

	public Printable createEditForm(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.TEACHERNAME, request.getParameter(TEACHERNAME));
		Printable result = baseObject.createEditForm();
		return result;
	}

	public int editObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_TEACHERNAME, request.getParameter(NEW_TEACHERNAME));
		getParams().put(Constants.TEACHERNAME, request.getParameter(TEACHERNAME));
		getParams().put(Constants.SUBJECTNAME, request.getParameter(SUBJECTNAME));
		int result = baseObject.editObject();
		return result;
	}

	public int deleteObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.TEACHERNAME, request.getParameter(TEACHERNAME));
		int result = baseObject.deleteObject();
		return result;
	}

}