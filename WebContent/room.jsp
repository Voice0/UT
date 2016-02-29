<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Room</title>
</head>
<body>

	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
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
		<td align="center"><b>List of rooms</b></td>
		<td align="center" rowspan="2" colspan="2">
			<form action="room" method ="GET" name="crud">
			<button type="submit" name="crud" value="addform">Add new</button>
			</form>	
		</td>
		</tr>
		<tr>
		<td align="center">Number</td>					
	 	</tr>	 	
		<%List<Room> list = (ArrayList<Room>) request.getAttribute("Roomlist");
			for(Room room : list) {
				out.println("<tr>");	
		 		out.println("<td align=\"center\">" + room.getNumber() + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/room?crud=editform&roomnumber=" + room.getNumber() + ">edit</a>" + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/room?crud=delete&roomnumber=" + room.getNumber() + ">delete</a>" + "</td>");
		    	out.println("</tr>");
			}
		%>
		</table>			
	</td>
	
	<%String type = (String) request.getAttribute("Crud");
		if ("addform".equals(type)){
			out.println("<td valign=\"top\">");
			out.println("<form action=\"room\" method =\"GET\">");
			out.println("<table>");
			out.println("<caption>For creating new room</caption>");
			out.println("<tr>");
			out.println("<td>Enter number of new room:</td>");
			out.println("<td><input type=\"text\" name=\"roomnumber\" placeholder=\"Number\" required/></td>");
			out.println("</tr><tr><td></td>");			
			out.println("<td><button type=\"submit\" name=\"crud\" value=\"addobject\" style=\"width:200Px\">Add room</button></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</td>");
			type = null;
		} else if("editform".equals(type)){
			Room room = (Room) request.getAttribute("Object");
			String roomnumber = room.getNumber();
			out.println("<td valign=\"top\">");
			out.println("<form action=\"room\" method =\"GET\">");
			out.println("<table>");
			out.println("<caption>Enter new value for update a room</caption>");
			out.println("<tr>");
			out.println("<td>Number of room:</td>");
			out.println("<td><input type=\"text\" name=\"newroomnumber\" Value=" + roomnumber + "></td>");
			out.println("</tr><tr>");
			out.println("<td><input type=\"hidden\" name=\"roomnumber\" Value=" + roomnumber + ">");
			out.println("<input type=\"hidden\" name=\"crud\" Value=\"editobject\"></td> ");
			out.println("<td><button type=\"submit\" name=\"objecttype\" value=\"room\">Save</button></td> ");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</td>");
			}		
		%>		
	
	</tr>
</table>	

</body>
</html>