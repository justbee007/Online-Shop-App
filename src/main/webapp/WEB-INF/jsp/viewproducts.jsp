<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
<hr>
<div class="card-deck">
<c:forEach var="product" items="${sessionScope.productlist}">
<div class="card">
<img class="card-img-top" src="/shop/images/${product.imgname}" alt="Card image cap">
<div class="card-body">
<h5 class="card-title">${product.productName}</h5>
<p class="card-text">${product.productDesc }<p>
<p class="card-text"> ${product.price}</p>
 <p class="card-text"> <a href="/shop/updateproduct.htm/${product.id}">Click here to Edit product</a> | <a href="/shop/deleteproduct.htm/${product.id}">Click here to delete product</a>
</p>
<br>
</div>
</div>
</c:forEach>
</div>
<nav aria-label="Page navigation example">
  <ul class="pagination">
<c:forEach var = "i" begin = "1" end = "${sessionScope.pagecount}">
<li class="page-item"><a class="page-link" href="${i}">${i}</a> </li>
</c:forEach> 
 
</ul>
 </nav>
</body>
</html>