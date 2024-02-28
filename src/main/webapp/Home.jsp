<%@page import="java.util.Base64"%>
<%@page import="dto.ProductDTO"%>
<%@page import="java.util.List"%>
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
    
<h1>welcome</h1>

 <a href = "AddProduct.jsp">Add Product</a>
 <table border="2px">
 
  <tr> 
  <th>id </th>
  <th>name </th>
  <th>brand </th>
  <th>price </th>
  <th>discount </th>
  <th>image </th>
  <th>Edit</th>
  <th>Delete</th>
  </tr>
  
   <% List<ProductDTO> products  = (List)request.getAttribute("products"); %>
   <% for(ProductDTO p : products) {%>
   <tr> 
   <td> <%= p.getId() %> </td>
   <td> <%= p.getName()%> </td>
   <td> <%= p.getBrand()%> </td>
   <td> <%= p.getPrice() %> </td>
   <td> <%= p.getDiscount()%> </td>
   <% String base64image = new String(Base64.getEncoder().encode(p.getImage())) ; %>
   <td> <img src="data:image/jpeg/;base64, <%= base64image %>" height="100px" width="100px" src="">  </td>
  
   <td><a href = "edit?id=<%= p.getId() %>" >edit</a></td>
   
   <td><a href = "delete?id=<%= p.getId() %>" >delete</a></td>
   </tr>
  
   <% } %>
   
 </table>
 
 <a href="Logout">LogOut</a>


</body>
</html>