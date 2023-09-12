<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
  <style>
 
      table,td,th
      {
        padding: 10px;
        border: 2px solid black;
        text-align: center;
      }
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background: azure;">
<nav class="nav">
<li class="nav-item">
<a class="nav-link active" href="/shop/logout.htm">Logout</a>
</li>
<li class="nav-item">
<a class="nav-link active" href="/shop/viewcart.htm">View Cart</a>
</li>
<li class="nav-item">
<a class="nav-link active" href="/shop/orderhistory.htm">View Order History</a>
</li>
<li class="nav-item">
<a class="nav-link active" href="/shop/addaddress.htm">Add Address</a>
</li>
</nav>
<br>
<br>
<h1>Welcome Customer ${sessionScope.user.firstName} to Shopping Spree</h1> 
<hr>
<h1>${message}</h1>
<hr>

<form action="/shop/addcart.htm" method="post" enctype="multipart/form-data">
<div class="card-deck">
<c:forEach var="product" items="${sessionScope.productlist}">
<div class="card">
<img class="card-img-top" src="/shop/images/${product.imgname}" alt="Card image cap">
<input type="hidden" id="imgname" name="imgname" value="${product.imgname}">
<div class="card-body">
<h5 class="card-title">${product.productName}</h5>
<input type="hidden" id="productName" name="productName" value="${product.productName}">
<p class="card-text">${product.productDesc}</p>
<input type="hidden" id="productDesc" name="productDesc" value="${product.productDesc}">
<p class="card-text">$ ${product.price}</p>
<input type="hidden" id="price" name="price" value="${product.price}">

<p class="card-text"><label for="quantity">Quantity (between 1 and 5):</label>
<input type="number" id="quantity" name="quantity" min="1" max="5"></p>

<input type="hidden" id="id" name="id" value="${product.id}">

</div>
</div>
</c:forEach>
</div>
<input type="submit" value="Add to Cart"/>
</form>

<footer>
<nav aria-label="Page navigation example">
  <ul class="pagination">
<c:forEach var = "i" begin = "1" end = "${sessionScope.pagecount}">
<li class="page-item"><a class="page-link" href="${i}">${i}</a> </li>
</c:forEach> 
</ul>
</nav>
</footer>
</body>
</html>