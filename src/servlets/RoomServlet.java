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
import objects.Room;

@WebServlet("/room")
public class RoomServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public RoomServlet() {
		super();
		Map<String, Object> params = new HashMap<String, Object>();
		setBaseObject(new Room(params));
	}

	void setParamsForDoGet(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_ROOMNUMBER, request.getParameter(Constants.NEW_ROOMNUMBER));
		getParams().put(Constants.ROOMNUMBER, request.getParameter(Constants.ROOMNUMBER));
	}

	public List<Printable> getAll(HttpServletRequest request) {
		List<Printable> room = getBaseObject().getAll();
		request.setAttribute("Roomlist", room);
		return null;
	}

	public List<Printable> getAllObjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Printable> room = super.getAllObjects(request, response);
		request.setAttribute("Roomlist", room);
		request.getRequestDispatcher("/room.jsp").forward(request, response);
		return null;
	}

	public void getAllObjectsAfterChanging(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAll(request);
		request.getRequestDispatcher("/room.jsp").forward(request, response);
	}

	public Printable createEditForm(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.ROOMNUMBER, request.getParameter(Constants.ROOMNUMBER));
		Printable result = baseObject.createEditForm();
		return result;
	}

	public int editObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.NEW_ROOMNUMBER, request.getParameter(Constants.NEW_ROOMNUMBER));
		getParams().put(Constants.ROOMNUMBER, request.getParameter(Constants.ROOMNUMBER));
		int result = baseObject.editObject();
		return result;
	}

	public int deleteObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.ROOMNUMBER, request.getParameter(Constants.ROOMNUMBER));
		int result = baseObject.deleteObject();
		return result;
	}

}