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
import objects.Printable;
import objects.Student;

@WebServlet("/student")
public class StudentServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public StudentServlet() {
		super();
		Map<String, Object> params = new HashMap<String, Object>();
		setBaseObject(new Student(params));
	}

	void setParamsForDoGet(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_STUDENTNAME, request.getParameter(Constants.NEW_STUDENTNAME));
		getParams().put(Constants.STUDENTNAME, request.getParameter(Constants.STUDENTNAME));
		getParams().put(Constants.GROUPNAME, request.getParameter(Constants.GROUPNAME));
	}

	public List<Printable> getAll(HttpServletRequest request) {
		List<Printable> student = getBaseObject().getAll();
		request.setAttribute("Studentlist", student);
		return null;
	}

	public List<Printable> getAllObjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Printable> studentList = super.getAllObjects(request, response);
		// XXX get group list for combo box
		BaseObject<?> group = new Group(baseObject.getParams());
		List<Printable> groupList = group.getAll();
		request.setAttribute("Studentlist", studentList);
		request.setAttribute("Grouplist", groupList);
		request.getRequestDispatcher("/student.jsp").forward(request, response);
		return null;
	}

	public void getAllObjectsAfterChanging(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAll(request);
		request.getRequestDispatcher("/student.jsp").forward(request, response);
	}

	public Printable createEditForm(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.STUDENTNAME, request.getParameter(Constants.STUDENTNAME));
		Printable result = baseObject.createEditForm();
		return result;
	}

	public int editObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_STUDENTNAME, request.getParameter(Constants.NEW_STUDENTNAME));
		getParams().put(Constants.STUDENTNAME, request.getParameter(Constants.STUDENTNAME));
		getParams().put(Constants.GROUPNAME, request.getParameter(Constants.GROUPNAME));
		int result = baseObject.editObject();
		return result;
	}

	public int deleteObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.STUDENTNAME, request.getParameter(Constants.STUDENTNAME));
		int result = baseObject.deleteObject();
		return result;
	}

}