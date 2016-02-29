package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Constants;
import objects.Printable;
import objects.Subject;

@WebServlet("/subject")
public class SubjectServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public SubjectServlet() {
		super();
		Map<String, Object> params = new HashMap<String, Object>();
		setBaseObject(new Subject(params));
	}

	void setParamsForDoGet(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_SUBJECTNAME, request.getParameter(Constants.NEW_SUBJECTNAME));
		getParams().put(Constants.SUBJECTNAME, request.getParameter(Constants.SUBJECTNAME));
	}

	public List<Printable> getAll(HttpServletRequest request) {
		List<Printable> subject = getBaseObject().getAll();
		request.setAttribute("Subjectlist", subject);
		return null;
	}

	public List<Printable> getAllObjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Printable> subject = super.getAllObjects(request, response);
		request.setAttribute("Subjectlist", subject);
		request.getRequestDispatcher("/subject.jsp").forward(request, response);
		return null;
	}

	public void getAllObjectsAfterChanging(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAll(request);
		request.getRequestDispatcher("/subject.jsp").forward(request, response);
	}

	public Printable createEditForm(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.SUBJECTNAME, request.getParameter(Constants.SUBJECTNAME));
		Printable result = baseObject.createEditForm();
		return result;
	}

	public int editObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_SUBJECTNAME, request.getParameter(Constants.NEW_SUBJECTNAME));
		getParams().put(Constants.SUBJECTNAME, request.getParameter(Constants.SUBJECTNAME));
		int result = baseObject.editObject();
		return result;
	}

	public int deleteObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.SUBJECTNAME, request.getParameter(Constants.SUBJECTNAME));
		int result = baseObject.deleteObject();
		return result;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teacherName = request.getParameter("showRegionForInsert");
		teacherName = teacherName + 1;
		
	}
//showRegionForInsert
}
