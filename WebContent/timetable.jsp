<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University timetable</title>
</head>
<body>

	<%@page import="java.util.List"%>
	<%@page import="objects.ScheduleItem"%>
	<%@page import="objects.Teacher"%>
	<%@page import="objects.Group"%>

	<form action="timetable" method = "GET">
		Who are you? Click corresponding button: 
		<table>
		<tr>
		<td> <input type="submit" name="persontype" value="Teacher"/> </td>  
		<td> <input type="submit" name="persontype" value="Student"/> </td>  
		</tr>
		</table>
	</form>
	
	<%String persontype = (String) request.getAttribute("PersonType");
		if ("Teacher".equals(persontype)){
			List<Teacher> teacherlist = (List<Teacher>) request.getAttribute("Teacherlist");		
			out.println("<form action=\"timetable\" method=\"POST\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Select your name:</td>");			
			out.println("<td><select name=\"name\">");
			for(Teacher teacher : teacherlist) {
				out.println("<option value=" + teacher.getName() + ">" + teacher.getName() + "</option>");
			}
			out.println("</select></td>");
			out.println("<td><input type=\"submit\" name=\"submitteachername\" value=\"Submit\"/></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");			
		} else if ("Student".equals(persontype)){			
			List<Group> grouplist = (List<Group>) request.getAttribute("Grouplist");		
			out.println("<form action=\"timetable\" method=\"POST\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Select your group:</td>");			
			out.println("<td><select name=\"name\">");
			for(Group group : grouplist) {
				out.println("<option value=" + group.getName() + ">" + group.getName() + "</option>");
			}
			out.println("</select></td>");
			out.println("<td><input type=\"submit\" name=\"submitgroupname\" value=\"Submit\"/></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");			
		}
		%>		
	
		<%List<ScheduleItem> list = (List<ScheduleItem>) request.getAttribute("list");
		String nolist = (String) request.getAttribute("nolist");
			if(list != null){
				out.println("<table border=1>");
				out.println("<caption>Timetable</caption>");
				out.println("<tr>");
				out.println("<th>Time</th>");
				out.println("<th>Group</th>");
				out.println("<th>Room</th>");
				out.println("<th>Subject</th>");
				out.println("<th>Teacher</th>");
				out.println("</tr>");	 	
				for(ScheduleItem scheduleItem : list) {
					out.println("<tr>");	
			 		out.println("<td>" + scheduleItem.getDatetime() + "</td>");
			 		out.println("<td>" + scheduleItem.getGroups() + "</td>");
			 		out.println("<td>" + scheduleItem.getRooms() + "</td>");
			 		out.println("<td>" + scheduleItem.getSubjects() + "</td>");
			 		out.println("<td>" + scheduleItem.getTeachers() + "</td>");	
			    	out.println("</tr>");
				} 							
			}else if(nolist != null){
				out.println(nolist);
			}
		%>
	
</body>
</html>