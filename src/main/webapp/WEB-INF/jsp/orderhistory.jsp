<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
.card
{margin-left: 100px;

}
.list-group-item
{border:white;
}
</style>
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
<a class="nav-link active" href="/shop/userview.htm/1">Home</a>
</li>
<li class="nav-item">
<a class="nav-link active" href="/shop/addaddress.htm">Add Address</a>
</li>
</nav>

<p class="h2">Your Orders with us:</p>
<c:forEach var="item" items="${requestScope.orderhistory}">
<div class="card" style="width: 18rem;">
  <ul class="list-group list-group-flush">
  <p><b>Order Id#: ${item.orderID}</b><p> 
   <li class="list-group-item">Order Placed Date:  ${item.createdDate} </li>
    <li class="list-group-item">Order Price:${item.price}</li>
    <li class="list-group-item"><b> Name: </b>  ${item.user.firstName } ${item.user.lastName} </li>
    <li class="list-group-item"><b>Address: </b> ${item.address.apartmentNumber},${item.address.streetName},
    <br>
    ${item.address.city},
    ${item.address.zipCode}
    </li>
  </ul>
  <a href="/shop/orderdetails.htm/${item.orderID}">Order Details</a>
  <a href="/shop/generatepdf.htm/${item.orderID}">Click here for Bill</a>
</div>
	<br>
</c:forEach>
</body>
</html>