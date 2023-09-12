<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="java.util.Iterator"%>
<%@page import="javax.servlet.http.HttpSession" %> 
<%@page import="com.me.Shop.pojo.Cart"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
.table
{ width:700px;
 left-padding:200px;
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
<br>

<h1>Your Order Details</h1>

<table class="table table-striped table-bordered">
<thead class="thead-dark">
<tr>
<th scope="col">Product Name</th>
<th scope="col">Product Description</th>
<th scope="col">Product Image</th>
<th scope="col">Price</th>
<th scope="col">Quantity</th>
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${cart}">
<tr>
<td>${item.product.productName}
<input type="hidden" id="productName" name="productName" value="${product.productName}">
</td>
<td>${item.product.productDesc}
<input type="hidden" id="productDesc" name="productDesc" value="${product.productDesc}">
</td>
<td>
<img src="/shop/images/${item.product.imgname}" width="100" height="100">
<input type="hidden" id="imgname" name="imgname" value="${product.imgname}"></td>
<td>${item.product.price}
<input type="hidden" id="price" name="price" value="${product.price}"></td>
<td>
<input type="number" id="quantity" name="quantity" min="1" max="5" value=${item.quantity}></td>
</tr>
</c:forEach>
</tbody>
</table>
<h4>Total Price is ${total}</h4>
<form:form modelAttribute="address" action="/shop/placeorder.htm" method="post">
<fieldset>
<div class="input-group-text">
	<c:forEach var="item" items="${addresslist}">
	<br>
	

<label for="address">
	<ul class="list-group">
	${item.streetName},
	${item.apartmentNumber},
	${item.city},
	${item.state},
	${item.zipCode},
	</label>
   	<input type="hidden" id="addressid" name="addressid" value="${item.id}">
	 <input type="radio" name="address" id="address" value="${item.id}" aria-label="Radio button for following text input">
    </ul>
    </c:forEach>
    </div>
</fieldset>
<input type="submit" value="Place Order"/>

</form:form>
</body>
</html>