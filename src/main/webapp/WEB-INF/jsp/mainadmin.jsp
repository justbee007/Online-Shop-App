<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
.form-group
{    width: 500px;
    margin: 200px;
}
</style>
</head>
<body style="background: azure;">
<br>
<nav class="nav">
<li class="nav-item">
<a class="nav-link active" href="/shop/logout.htm">Logout</a>
</li>
<li class="nav-item">
<a class="nav-link active" href="/shop/signin.htm">Home</a>
</li>
<li class="nav-item">
<a class="nav-link active" href="/shop/viewproducts.htm/1">View Products</a>
</li>
</nav>
<br>
<h1 class="display-3">Welcome Admin ${sessionScope.user.firstName}</h1> 

<div class="form-group">
<form:form modelAttribute="product" action="upload.htm" method="post" enctype="multipart/form-data">
<label>Product Name</label>
<input type="text" id="productName" name="productName" path="productName" class="form-control">
<br>
<br>
<label>Product Description:</label>
<br>
<br>
<textarea name="productDesc" id = "productDesc" class="form-control" rows="2"> </textarea>
<br>
<label>Product Price</label>
<input type="number" id="price" name="price" min="1.00" max="100.00" step="0.5">
<br>
<br>
Select File: <input type="file" name="imgfile" id="imgfile" class="form-control"/> 
<br>
<input type="submit" value="Upload File" class="form-control"/>  
</form:form>
</div>
</body>
</html>