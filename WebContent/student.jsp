<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student</title>
</head>
<body>

	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="objects.Student"%>
	<%@page import="objects.Group"%>

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
		<td align="center" colspan="2"><b>List of students</b></td>
		<td align="center" rowspan="2" colspan="2">
			<form action="student" method = "GET" name="crud">
			<button type="submit" name="crud" value="addform">Add new</button>
			</form>	
		</td>
		</tr>
		<tr>
		<td align="center">Name</td>
		<td align="center">Group</td>					
	 	</tr>	 	
		<%List<Student> studentlist = (ArrayList<Student>) request.getAttribute("Studentlist");
			for(Student student : studentlist) {
				out.println("<tr>");	
		 		out.println("<td align=\"center\">" + student.getName() + "</td>");
		 		out.println("<td align=\"center\">" + student.getGroup().getName() + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/student?crud=editform&studentname=" + student.getName() + ">edit</a>" + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/student?crud=delete&studentname=" + student.getName() + ">delete</a>" + "</td>");
		    	out.println("</tr>");
			}
		%>
		</table>			
	</td>
	
	<%String type = (String) request.getAttribute("Crud");
	List<Group> grouplist = (ArrayList<Group>) request.getAttribute("Grouplist");
		if ("addform".equals(type)){			
			out.println("<td valign=\"top\">");
			out.println("<form action=\"student\" method =\"GET\">");
			out.println("<table>");
			out.println("<caption>For creating new student</caption>");
			out.println("<tr>");
			out.println("<td> Enter name of new student:</td>");
			out.println("<td><input type=\"text\" name=\"studentname\" placeholder=\"Name\" required/></td>");
			out.println("</tr><tr>");
			out.println("<td>Select name of group:</td>");
			out.println("<td><select name=\"groupname\">");
			for(Group groupForAdding : grouplist) {
				out.println("<option value=" + groupForAdding.getName() + ">" + groupForAdding.getName() + "</option>");
			}
			out.println("</select></td>");
			out.println("<td></td>");
			out.println("</tr><tr><td></td>");			
			out.println("<td><button type=\"submit\" name=\"crud\" value=\"addobject\" style=\"width:100Px\">Add student</button></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</td>");
			type = null;
		} else {
			if("editform".equals(type)){
				Student student = (Student) request.getAttribute("Object");
				String studentname = student.getName();
				String studentsgroupname = student.getGroup().getName();
				out.println("<td valign=\"top\">");
				out.println("<form action=\"student\" method =\"GET\">");
				out.println("<table>");
				out.println("<caption>Enter new values for update a student</caption>");
				out.println("<tr>");
				out.println("<td>Name of student:</td>");
				out.println("<td><input type=\"text\" name=\"newstudentname\" Value=" + studentname + "></td>");
				out.println("</tr><tr>");
				out.println("<td>Group:</td>");				
				out.println("<td><select name=\"groupname\">");
				for(Group groupForUpdating : grouplist) {
					out.println("<option value=" + groupForUpdating.getName() + ">" + groupForUpdating.getName() + "</option>");
				}
				out.println("</select></td>");
				out.println("<td></td>");
				out.println("</tr><tr>");
				out.println("<td><input type=\"hidden\" name=\"studentname\" Value=" + studentname + "></td> ");
				out.println("<td><input type=\"hidden\" name=\"crud\" Value=\"editobject\"></td> ");
				out.println("<td><button type=\"submit\" name=\"objecttype\" value=\"student\">Save</button></td> ");
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