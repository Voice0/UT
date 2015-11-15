package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Group;
import objects.Printable;
import objects.ScheduleItem;
import objects.Teacher;
import objects.Timetable;

@WebServlet("/timetable")
public class TimeTableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Timetable timetable = new Timetable();
	Teacher teacher = new Teacher();
	Group group = new Group();
	String personType = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		personType = request.getParameter("persontype");
		setValuesForComboBox(request);
		request.getRequestDispatcher("/timetable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String entityName = request.getParameter("name");
		setValuesForComboBox(request);
		List<ScheduleItem> timetableList = timetable.getTimetable(entityName, personType);
		if (timetableList.size() > 0) {
			request.setAttribute("list", timetableList);
		} else {
			String message = "There is NO timetable for you.";
			request.setAttribute("nolist", message);
		}
		request.getRequestDispatcher("/timetable.jsp").forward(request, response);
	}

	private void setValuesForComboBox(HttpServletRequest request) {
		List<Printable> teacherList = teacher.getAll();
		List<Printable> groupList = group.getAll();
		request.setAttribute("PersonType", personType);
		request.setAttribute("Teacherlist", teacherList);
		request.setAttribute("Grouplist", groupList);
	}

}