<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background: azure;">
<nav class="nav">
<li class="nav-item">
<a class="nav-link active" href="/shop/logout.htm">Logout</a>
</li>
<li class="nav-item">
<a class="nav-link active" href="/shop/userview.htm/1">Home</a>
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

<table class="table table-striped table-bordered table-sm">
<thead class="thead-dark">
<tr>
<th>Product Name</th>
<th>Product Description</th>
<th>Product Image</th>
<th>Price</th>
<th>Quantity</th>
</tr>
</thead>
<tbody >
<c:forEach var="val" items="${orderdetailist}">
<tr>
<td>${val.productname}
</td>
<td>${val.product.productDesc}
</td>
<td> <img src="/shop/images/${val.product.imgname}" width="100" height="100">
</td>
<td>${val.product.price}

<td>${val.quantity}</td>
</tr>
</c:forEach>
</tbody>
</table>
<p class="h6"> Order Price $: ${totalprice}</p>
<a class="btn btn-primary" href="/shop/orderhistory.htm" role="button">Click here to view orders</a>
</body>
</html>