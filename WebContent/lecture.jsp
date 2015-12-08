<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lecture</title>
</head>
<body>

	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="objects.Lecture"%>
	<%@page import="objects.Group"%>
	<%@page import="objects.Teacher"%>
	<%@page import="objects.Subject"%>
	<%@page import="objects.Room"%>


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
		<td align="center" colspan="6"><b>List of lectures</b></td>
		<td align="center" rowspan="2" colspan="2">
			<form action="lecture" method ="GET" name="crud">
			<button type="submit" name="crud" value="addform">Add new</button>
			</form>	
		</td>
		</tr>
		<tr>
		<td align="center">ID</td>
		<td align="center">Date and time</td>
		<td align="center">Group</td>	
		<td align="center">Room</td>	
		<td align="center">Subject</td>
		<td align="center">Teacher</td>					
	 	</tr>	 	
		<%List<Lecture> lecturelist = (ArrayList<Lecture>) request.getAttribute("Lecturelist");
			for(Lecture lecture : lecturelist) {
				out.println("<tr>");	
		 		out.println("<td align=\"center\">" + lecture.getId() + "</td>");
		 		out.println("<td align=\"center\">" + lecture.getDatetime() + "</td>");
		 		out.println("<td align=\"center\">" + lecture.getGroup().getName() + "</td>");
		 		out.println("<td align=\"center\">" + lecture.getRoom().getNumber() + "</td>");
		 		out.println("<td align=\"center\">" + lecture.getSubject().getName() + "</td>");
		 		out.println("<td align=\"center\">" + lecture.getTeacher().getName() + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/lecture?crud=editform&lectureid=" + lecture.getId() + ">edit</a>" + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/lecture?crud=delete&lectureid=" + lecture.getId() + ">delete</a>" + "</td>");
		    	out.println("</tr>");
			}
		%>
		</table>			
	</td>
	
	<%String type = (String) request.getAttribute("Crud");
	List<Group> grouplist = (ArrayList<Group>) request.getAttribute("Grouplist");
	List<Room> roomlist = (ArrayList<Room>) request.getAttribute("Roomlist");
	List<Teacher> teacherlist = (ArrayList<Teacher>) request.getAttribute("Teacherlist");
		if ("addform".equals(type)){			
			out.println("<td valign=\"top\">");
			out.println("<form action=\"lecture\" method =\"GET\">");
			out.println("<table>");
			out.println("<caption>For creating new lecture</caption>");
			
			out.println("<tr>");
			out.println("<td>Enter date and time:</td>");
			out.println("<td><input type=\"text\" name=\"datetime\" placeholder=\"Date & time\" required/></td>");
			out.println("</tr>");			
			
			out.println("<td>Select group:</td>");
			out.println("<td><select name=\"groupname\">");
			for(Group groupForAdding : grouplist) {
				out.println("<option value=" + groupForAdding.getName() + ">" + groupForAdding.getName() + "</option>");
			}
			out.println("</select></td>");
			
			out.println("<tr>");
			out.println("<td>Select room:</td>");
			out.println("<td><select name=\"roomnumber\">");
			for(Room roomForAdding : roomlist) {
				out.println("<option value=" + roomForAdding.getNumber() + ">" + roomForAdding.getNumber() + "</option>");
			}
			out.println("</select></td>");
			
			out.println("<tr>");
			out.println("<td>Select teacher:</td>");
			out.println("<td><select name=\"teachername\">");
			for(Teacher teacherForAdding : teacherlist) {
				out.println("<option value=" + teacherForAdding.getName() + ">" + teacherForAdding.getName() + "</option>");
			}
			out.println("</select></td>");
			out.println("</tr>");
			
			out.println("<td></td>");
			out.println("</tr><tr><td></td>");			
			out.println("<td><button type=\"submit\" name=\"crud\" value=\"addobject\" style=\"width:100Px\">Add lecture</button></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</td>");
			type = null;
		} else {
			if("editform".equals(type)){
				Lecture lecture = (Lecture) request.getAttribute("Object");
				String lectureid = String.valueOf(lecture.getId());
				String datetime = lecture.getDatetime();
				String groupname = lecture.getGroup().getName();
				String roomnumber = lecture.getRoom().getNumber();
				String teachername = lecture.getTeacher().getName();
				out.println("<td valign=\"top\">");
				out.println("<form action=\"lecture\" method =\"GET\">");
				out.println("<table>");
				out.println("<caption>Enter new values for update a lecture</caption>");
				out.println("<tr>");
				out.println("<td>Date and time:</td>");
				out.println("<td><input type=\"text\" name=\"datetime\" Value=" + datetime + "></td>");
				out.println("</tr>");
				
				out.println("<tr>");				
				out.println("<td>Group:</td>");				
				out.println("<td><select name=\"groupname\">");
				for(Group groupForUpdating : grouplist) {
					out.println("<option value=" + groupForUpdating.getName() + ">" + groupForUpdating.getName() + "</option>");
				}
				out.println("</select></td></tr>");
				
				out.println("<tr>");				
				out.println("<td>Room:</td>");				
				out.println("<td><select name=\"roomnumber\">");
				for(Room roomForUpdating : roomlist) {
					out.println("<option value=" + roomForUpdating.getNumber() + ">" + roomForUpdating.getNumber() + "</option>");
				}
				out.println("</select></td></tr>");
				
				out.println("<tr>");				
				out.println("<td>Teacher:</td>");				
				out.println("<td><select name=\"teachername\">");
				for(Teacher teacherForUpdating : teacherlist) {
					out.println("<option value=" + teacherForUpdating.getName() + ">" + teacherForUpdating.getName() + "</option>");
				}
				out.println("</select></td></tr>");
				
				out.println("<tr>");
				out.println("<td><input type=\"hidden\" name=\"lectureid\" Value=" + lectureid + ">");
				out.println("<input type=\"hidden\" name=\"crud\" Value=\"editobject\"></td> ");
				out.println("<td><button type=\"submit\" name=\"objecttype\" value=\"lecture\">Save</button></td> ");
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