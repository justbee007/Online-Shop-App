<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<hr>
<p><b> Shopping Spree Pvt Ltd</b></p>
<hr>
<p><b> 150 Commonwealth Avenue</b></p>
<hr>
<p><b> Boston</b><p>
<hr>
<p><b>02115</b></p>
<hr>
<hr>
<ul>
<p><b>Name: </b>${requestScope.personname}</p>
<p> <b>OrderID#: </b> ${requestScope.OrderId} 
</p>
<p> <b>Address:</b></p>
<p>${requestScope.adddress1}</p>
<p>${requestScope.address2}</p>
</ul>
<table>
<tr>
<th>Product Name</th>
<th>Price</th>
<th>Quantity</th>
</tr>

<c:forEach var="val" items="${orderdetailist}">
<tr>
<td>${val.productname}
</td>
<td>${val.product.price}

<td>${val.quantity}</td>
</tr>
</c:forEach>
</table>
<p> Order Price $: ${totalprice}</p>

<p>
</body>
</html>