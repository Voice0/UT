<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University timetable: adminpanel</title>
</head>
<body>	
	
	<table>
	<tr><td><b>Choose to view/change:</b></td></tr>	
	<tr><td><form action="group" method ="GET"><button type="submit" name="crud" value="getall" style="width:150Px">Group</button></form></td></tr>
	<tr><td><form action="student" method ="GET"><button type="submit" name="crud" value="getall" style="width:150Px">Student</button></form></td></tr>
	<tr><td><form action="teacher" method ="GET"><button type="submit" name="crud" value=getall style="width:150Px">Teacher</button></form></td></tr>
	<tr><td><form action="subject" method ="GET"><button type="submit" name="crud" value="getall" style="width:150Px">Subject</button></form></td></tr>
	<tr><td><form action="teacherandsubject" method ="GET"><button type="submit" name="crud" value="getall" style="width:330Px">TeacherAndSubject</button></form></td></tr>
	<tr><td><form action="room" method ="GET"><button type="submit" name="crud" value="getall" style="width:150Px">Room</button></form></td></tr>
	<tr><td><form action="lecture" method ="GET"><button type="submit" name="crud" value="getall" style="width:150Px">Lecture</button></form></td></tr>
	</table>		
	
</body>
</html>

