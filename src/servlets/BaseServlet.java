package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.BaseDAO;
import objects.BaseObject;
import objects.Printable;

@WebServlet("/baseservlet")
public abstract class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = LogManager.getLogger(BaseServlet.class);
	BaseObject<? extends BaseDAO> baseObject;

	abstract void setParamsForDoGet(HttpServletRequest request, HttpServletResponse response);

	abstract Printable createEditForm(HttpServletRequest request, HttpServletResponse response);

	abstract int editObject(HttpServletRequest request, HttpServletResponse response);

	abstract int deleteObject(HttpServletRequest request, HttpServletResponse response);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String crud = request.getParameter("crud");
		switch (crud) {
		case "getall":
			getAllObjects(request, response);
			break;
		case "addform":
			createAddForm(request, response);
			getAllObjects(request, response);
			break;
		case "addobject":
			int result = -1;
			setParamsForDoGet(request, response);
			try {
				result = baseObject.addObject();
			} finally {
				request.setAttribute("Result", result);
				request.setAttribute("Operation", "Adding");
			}
			getAllObjectsAfterChanging(request, response);
			break;
		case "editform":
			Printable objectToEdit = createEditForm(request, response);
			request.setAttribute("Crud", request.getParameter("crud"));
			request.setAttribute("Object", objectToEdit);
			getAllObjects(request, response);
			break;
		case "editobject":
			int editobject = editObject(request, response);
			request.setAttribute("Result", editobject);
			request.setAttribute("Operation", "Changing");
			getAllObjectsAfterChanging(request, response);
			break;
		case "delete":
			int deleteObject = deleteObject(request, response);
			request.setAttribute("Result", deleteObject);
			request.setAttribute("Operation", "Deleting");
			getAllObjectsAfterChanging(request, response);
			break;
		}
	}

	public List<Printable> getAllObjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("Result", -1);
		request.setAttribute("Operation", "");
		return getBaseObject().getAll();
	}

	public void getAllObjectsAfterChanging(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public BaseObject<? extends BaseDAO> getBaseObject() {
		return baseObject;
	}

	public void setBaseObject(BaseObject<? extends BaseDAO> baseObject) {
		this.baseObject = baseObject;
	}

	public Map<String, Object> getParams() {
		return getBaseObject().getParams();
	}

	public void createAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("Crud", request.getParameter("crud"));
	}

}
