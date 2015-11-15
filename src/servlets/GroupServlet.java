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
import objects.Group;
import objects.Printable;

@WebServlet("/group")
public class GroupServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	public static final String GROUPNAME = "groupname";
	public static final String NEW_GROUPNAME = "newgroupname";
	public static final String YEAR = "year";

	public GroupServlet() {
		super();
		Map<String, Object> params = new HashMap<String, Object>();
		setBaseObject(new Group(params));
	}

	void setParamsForDoGet(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_GROUPNAME, request.getParameter(Constants.NEW_GROUPNAME));
		getParams().put(Constants.GROUPNAME, request.getParameter(Constants.GROUPNAME));
		String yearStr = request.getParameter(Constants.YEAR);
		if (yearStr != null && yearStr.length() > 0) {
			getParams().put(Constants.YEAR, Integer.parseInt(yearStr));
		}
	}

	public List<Printable> getAll(HttpServletRequest request) {
		List<Printable> group = getBaseObject().getAll();
		request.setAttribute("Grouplist", group);
		return null;
	}

	public List<Printable> getAllObjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Printable> group = super.getAllObjects(request, response);
		request.setAttribute("Grouplist", group);
		request.getRequestDispatcher("/group.jsp").forward(request, response);
		return null;
	}

	public void getAllObjectsAfterChanging(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAll(request);
		request.getRequestDispatcher("/group.jsp").forward(request, response);
	}

	public Printable createEditForm(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.GROUPNAME, request.getParameter(GROUPNAME));
		Printable result = baseObject.createEditForm();
		return result;
	}

	public int editObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_GROUPNAME, request.getParameter(NEW_GROUPNAME));
		getParams().put(Constants.GROUPNAME, request.getParameter(GROUPNAME));
		getParams().put(Constants.YEAR, Integer.parseInt(request.getParameter(YEAR)));
		int result = baseObject.editObject();
		return result;
	}

	public int deleteObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.GROUPNAME, request.getParameter(GROUPNAME));
		int result = baseObject.deleteObject();
		return result;
	}

}