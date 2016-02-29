<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group</title>
</head>
<body>

	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="objects.Group"%>
	<%@page import="objects.Constants"%>
	
	
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
		<td align="center" colspan="2"><b>List of groups</b></td>
		<td align="center" rowspan="2" colspan="2">
			<form action="group" method = "GET" name="crud">
			<button type="submit" name="crud" value="addform">Add new</button>
			</form>	
		</td>
		</tr>
		<tr>
		<td align="center">Name</td>
		<td align="center">Year of learning</td>					
	 	</tr>	 	
		<%List<Group> list = (ArrayList<Group>) request.getAttribute("Grouplist");
			for(Group group : list) {
				out.println("<tr>");	
		 		out.println("<td align=\"center\">" + group.getName() + "</td>");
		 		out.println("<td align=\"center\">" + group.getYearOfLearning() + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/group?crud=editform&" + Constants.GROUPNAME + "=" + group.getName() + ">edit</a>" + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/group?crud=delete&" + Constants.GROUPNAME + "=" + group.getName() + ">delete</a>" + "</td>");
		    	out.println("</tr>");
			}
		%>
		</table>			
	</td>
	
	<%String type = (String) request.getAttribute("Crud");
		if ("addform".equals(type)){
			out.println("<td valign=\"top\">");
			out.println("<form action=\"group\" method =\"GET\">");
			out.println("<table>");
			out.println("<caption>For creating new group</caption>");
			out.println("<tr>");
			out.println("<td>Enter name of new group:</td>");
			out.println("<td><input type=\"text\" name=" + Constants.GROUPNAME + " placeholder=\"Name\" required/></td>");
			out.println("</tr><tr>");
			out.println("<td>Select year of learning:</td>");
			out.println("<td><select name=" + Constants.YEAR + "><option value=\"1\">1</option><option value=\"2\">2</option>" +
						"<option value=\"3\">3</option><option value=\"4\">4</option><option value=\"5\">5</option></select></td>");
			out.println("</tr><tr><td></td>");			
			out.println("<td><button type=\"submit\" name=\"crud\" value=\"addobject\" style=\"width:200Px\">Add group</button></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</td>");
			type = null;
		} else if("editform".equals(type)){
			Group group = (Group) request.getAttribute("Object");
			String groupname = group.getName();
			int year = group.getYearOfLearning();
			out.println("<td valign=\"top\">");
			out.println("<form action=\"group\" method =\"GET\">");
			out.println("<table>");
			out.println("<caption>Enter new values for update a group</caption>");
			out.println("<tr>");
			out.println("<td>Name of group:</td>");
			out.println("<td><input type=\"text\" name=" + Constants.NEW_GROUPNAME + " Value=" + groupname + "></td>");
			out.println("</tr><tr>");
			out.println("<td>Year of learning:</td>");
			out.println("<td><select name=" + Constants.YEAR + "><option selected value=" + year + ">" + year + "</option><option value=\"1\">1</option><option value=\"2\">2</option>" +
						"<option value=\"3\">3</option><option value=\"4\">4</option><option value=\"5\">5</option></select></td>");
			out.println("</tr><tr>");
			out.println("<td><input type=\"hidden\" name=" + Constants.GROUPNAME + " Value=" + groupname + ">");
			out.println("<input type=\"hidden\" name=\"crud\" Value=\"editobject\"></td> ");
			out.println("<td><button type=\"submit\" name=\"objecttype\" value=\"group\">Save</button></td> ");
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