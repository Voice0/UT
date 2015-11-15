<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%Integer result = (Integer) request.getAttribute("Result");
	String operation = (String) request.getAttribute("Operation");
		if(!"".equals(operation)){
			if(result == 1) {
				out.println(operation + " was successful" + "</br>");
			}else{
				out.println(operation + " was NOT successful. Try again." + "</br>");
			}
		}
	%>
	

</body>
</html>