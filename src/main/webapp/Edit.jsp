<%@page import="java.util.Base64"%>
<%@page import="dto.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   
   <% ProductDTO p = (ProductDTO) request.getAttribute("products"); %>
   
   <form action="update" method="post" enctype="multipart/form-data">
  
      id : <input type="text" name="pid" value="<%= p.getId() %>" readonly="true"> <br>
      name:<input type="text" name="name" value="<%= p.getName() %>"> <br>
      brand:<input type="text" name="brand" value="<%= p.getBrand() %>"> <br>
      price:<input type="text" name="price" value="<%= p.getPrice() %>"> <br>
	  discount:<input type="text" name="discount" value="<%= p.getDiscount() %>"> <br>
	  
	  <% String base64image = new String(Base64.getEncoder().encode(p.getImage())) ; %>
      <img src="data:image/jpeg/;base64, <%= base64image %>" height="100px" width="100px" > 
      image:<input type="file" name="image"> <br>
     
      <input type="submit">
        
   </form>

</body>
</html>