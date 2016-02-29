<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subject</title>
</head>
<body>

	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
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
		<td align="center"><b>List of subjects</b></td>
		<td align="center" rowspan="2" colspan="2">
			<form action="subject" method ="GET" name="crud">
			<button type="submit" name="crud" value="addform">Add new</button>
			</form>	
		</td>
		</tr>
		<tr>
		<td align="center">Name</td>					
	 	</tr>	 	
		<%List<Subject> list = (ArrayList<Subject>) request.getAttribute("Subjectlist");
			for(Subject subject : list) {
				out.println("<tr>");	
		 		out.println("<td align=\"center\">" + subject.getName() + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/subject?crud=editform&subjectname=" + subject.getName() + ">edit</a>" + "</td>");
		 		out.println("<td align=\"center\">" + "<a href=" + "/UT/subject?crud=delete&subjectname=" + subject.getName() + ">delete</a>" + "</td>");
		    	out.println("</tr>");
			}
		%>
		</table>			
	</td>
	
	<%String type = (String) request.getAttribute("Crud");
		if ("addform".equals(type)){
			out.println("<td valign=\"top\">");
			out.println("<form action=\"subject\" method =\"GET\">");
			out.println("<table>");
			out.println("<caption>For creating new subject</caption>");
			out.println("<tr>");
			out.println("<td>Enter name of new subject:</td>");
			out.println("<td><input type=\"text\" name=\"subjectname\" placeholder=\"Name\" required/></td>");
			out.println("</tr><tr><td></td>");			
			out.println("<td><button type=\"submit\" name=\"crud\" value=\"addobject\" style=\"width:200Px\">Add subject</button></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</td>");
			type = null;
		} else if("editform".equals(type)){
			Subject subject = (Subject) request.getAttribute("Object");
			String subjectname = subject.getName();
			out.println("<td valign=\"top\">");
			out.println("<form action=\"subject\" method =\"GET\">");
			out.println("<table>");
			out.println("<caption>Enter new value for update a subject</caption>");
			out.println("<tr>");
			out.println("<td>Name of subject:</td>");
			out.println("<td><input type=\"text\" name=\"newsubjectname\" Value=" + subjectname + "></td>");
			out.println("</tr><tr>");
			out.println("<td><input type=\"hidden\" name=\"subjectname\" Value=" + subjectname + ">");
			out.println("<input type=\"hidden\" name=\"crud\" Value=\"editobject\"></td> ");
			out.println("<td><button type=\"submit\" name=\"objecttype\" value=\"subject\">Save</button></td> ");
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