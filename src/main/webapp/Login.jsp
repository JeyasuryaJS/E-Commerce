<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

   <% String message =(String) request.getAttribute("message1"); %>
    <% if(message!= null) { %>
    <h1> <%= message %></h1>
    <%} %>
    
    <% String message2 =(String) request.getAttribute("message2"); %>
    <% if(message2!= null) { %>
    <h1> <%= message2 %></h1>
    <%} %>
    
<form action="log" method="post">
email: <input type="text" name="email"> <br>
password: <input type="text" name="password"> <br>
        <input type="submit">
</form>
</body>
</html>