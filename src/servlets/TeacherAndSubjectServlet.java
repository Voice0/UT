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
import objects.TeacherAndSubject;

@WebServlet("/teacherandsubject")
public class TeacherAndSubjectServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;

    public TeacherAndSubjectServlet() {
		super();
		Map<String, Object> params = new HashMap<String, Object>();
		setBaseObject(new TeacherAndSubject(params));
    }

    void setParamsForDoGet(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.TEACHER_AND_SUBJECT_ID, request.getParameter(Constants.TEACHER_AND_SUBJECT_ID));
		getParams().put(Constants.TEACHERNAME, request.getParameter(Constants.TEACHERNAME));
		getParams().put(Constants.SUBJECTNAME, request.getParameter(Constants.SUBJECTNAME));
	}

	public List<Printable> getAll(HttpServletRequest request) {
		List<Printable> teacherAndSubjectList = getBaseObject().getAll();
		request.setAttribute("TeacherAndSubjectList", teacherAndSubjectList);
		return null;
	}

	public List<Printable> getAllObjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Printable> teacherAndSubjectList = super.getAllObjects(request, response);
		// XXX get teacher list for combo box
		BaseObject<?> teacher = new Teacher(baseObject.getParams());
		List<Printable> teacherList = teacher.getAll();
		BaseObject<?> subject = new Subject(baseObject.getParams());
		List<Printable> subjectList = subject.getAll();	
		request.setAttribute("TeacherAndSubjectList", teacherAndSubjectList);
		request.setAttribute("TeacherList", teacherList);
		request.setAttribute("SubjectList", subjectList);
		request.getRequestDispatcher("/teacherandsubject.jsp").forward(request, response);
		return null;
	}

	public void getAllObjectsAfterChanging(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAll(request);
		request.getRequestDispatcher("/teacherandsubject.jsp").forward(request, response);
	}

	public Printable createEditForm(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.TEACHER_AND_SUBJECT_ID, request.getParameter(Constants.TEACHER_AND_SUBJECT_ID));
		Printable result = baseObject.createEditForm();
		return result;
	}

	public int editObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.TEACHERNAME, request.getParameter(Constants.TEACHERNAME));
		getParams().put(Constants.SUBJECTNAME, request.getParameter(Constants.SUBJECTNAME));
		getParams().put(Constants.TEACHER_AND_SUBJECT_ID, request.getParameter(Constants.TEACHER_AND_SUBJECT_ID));
		int result = baseObject.editObject();
		return result;
	}

	public int deleteObject(HttpServletRequest request, HttpServletResponse response) {
		getParams().clear();
		getParams().put(Constants.TEACHER_AND_SUBJECT_ID, request.getParameter(Constants.TEACHER_AND_SUBJECT_ID));
		int result = baseObject.deleteObject();
		return result;
	}

}
