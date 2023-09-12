<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background: azure;">
<h1>

</h1>
<form:form modelAttribute="prod" action="/shop/updateproduct.htm" method="post" enctype="multipart/form-data">
<img src="/shop/images/${requestScope.prod.imgname}" width="300" height="300">
<br>
<label>Product Name</label>
<br>
<input type="text" id="productName" name="productName"value="${requestScope.prod.productName}">
<br>
<label>Product Description:</label>
<br>
<br>
<textarea name="productDesc" id = "productDesc"> ${requestScope.prod.productDesc}</textarea>
<br>
<label>Product Price</label>
<input type="text" id="price" name="price" value="${requestScope.prod.price}"> 
<br>
<input type="hidden" id="id" name="id" value="${requestScope.prod.id}">
<br>

<p>Upload new image to replace previous one</p>
Select File: <input type="file" name="imgfile" id="imgfile"/>  
<input type="submit" value="Update the product"/>  
</form:form>
<a href="logout.htm">Logout</a>
</body>
</html>