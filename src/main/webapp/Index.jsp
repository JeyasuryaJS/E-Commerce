<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <% String message =(String) request.getAttribute("message"); %>
    <% if(message!= null) { %>
    <h1> <%= message %></h1>
    <%} %>
    
    <a href = "Login.jsp">LOGIN</a>
    <a href = "Signup.jsp">SIGNUP</a>


</body>
</html>