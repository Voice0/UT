<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher</title>
</head>
<body>

	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="objects.Teacher"%>
	<%@page import="objects.Subject"%>

<table cellpadding="1" cellspacing="1">
	<tr>
	<td align="center" colspan="2">
		<%@ include file="result.jsp" %>	
	</td>
	</tr>
	
	<tr>
	<td valign="top"> 	
		<%@ include file="admin.jsp" %>	
	</td>
	<td>	
	
		<table border=1 cellpadding="1" cellspacing="1"> 	
		<tr>
		<td align="center" colspan="2"><b>List of teachers</b></td>
		<td align="center" rowspan="2" colspan="2">
			<form action="teacher" method = "GET" name="crud">
			<button type="submit" name="crud" value="addform">Add new</button>
			</form>	
		</td>
		</tr>
		<tr>
		<td align="center">Name</td>
		<td align="center">Subject</td>					
	 	</tr>	 	
		<%List<Teacher> teacherlist = (ArrayList<Teacher>) request.getAttribute("Teacherlist");
			for(Teacher teacher : teacherlist) {
				out.println("<tr>");	
		 		out.println("<td align=\"center\">" + teacher.getName() + "</td>");
		 		out.println("<td align=\"center\">" + teacher.getSubject().getName() + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/teacher?crud=editform&teachername=" + teacher.getName() + ">edit</a>" + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/teacher?crud=delete&teachername=" + teacher.getName() + ">delete</a>" + "</td>");
		    	out.println("</tr>");
			}
		%>
		</table>			
	</td>
	
	<%String type = (String) request.getAttribute("Crud");
	List<Subject> subjectlist = (ArrayList<Subject>) request.getAttribute("Subjectlist");
		if ("addform".equals(type)){			
			out.println("<td valign=\"top\">");
			out.println("<form action=\"teacher\" method =\"GET\">");
			out.println("<table>");
			out.println("<caption>For creating new teacher</caption>");
			out.println("<tr>");
			out.println("<td> Enter name of new teacher:</td>");
			out.println("<td><input type=\"text\" name=\"teachername\" placeholder=\"Name\" required/></td>");
			out.println("</tr><tr>");
			out.println("<td>Select name of subject:</td>");
			out.println("<td><select name=\"subjectname\">");
			for(Subject subjectForAdding : subjectlist) {
				out.println("<option value=" + subjectForAdding.getName() + ">" + subjectForAdding.getName() + "</option>");
			}
			out.println("</select></td>");
			out.println("<td></td>");
			out.println("</tr><tr><td></td>");			
			out.println("<td><button type=\"submit\" name=\"crud\" value=\"addobject\" style=\"width:100Px\">Add teacher</button></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</td>");
			type = null;
		} else {
			if("editform".equals(type)){
				Teacher teacher = (Teacher) request.getAttribute("Object");
				String teachername = teacher.getName();
				String teacherssubjectname = teacher.getSubject().getName();
				out.println("<td valign=\"top\">");
				out.println("<form action=\"teacher\" method =\"GET\">");
				out.println("<table>");
				out.println("<caption>Enter new values for update a teacher</caption>");
				out.println("<tr>");
				out.println("<td>Name of teacher:</td>");
				out.println("<td><input type=\"text\" name=\"newteachername\" Value=" + teachername + "></td>");
				out.println("</tr><tr>");
				out.println("<td>Subject:</td>");				
				out.println("<td><select name=\"subjectname\">");
				for(Subject subjectForUpdating : subjectlist) {
					out.println("<option value=" + subjectForUpdating.getName() + ">" + subjectForUpdating.getName() + "</option>");
				}
				out.println("</select></td>");
				out.println("<td></td>");
				out.println("</tr><tr>");
				out.println("<td><input type=\"hidden\" name=\"teachername\" Value=" + teachername + "></td> ");
				out.println("<td><input type=\"hidden\" name=\"crud\" Value=\"editobject\"></td> ");
				out.println("<td><button type=\"submit\" name=\"objecttype\" value=\"teacher\">Save</button></td> ");
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</td>");
			}
		}
		%>		
	
	</tr>
</table>	

</body>
</html>