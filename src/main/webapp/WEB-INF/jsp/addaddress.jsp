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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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

<h1>Add Addresses for ${sessionScope.user.firstName}</h1>
<div class="form-group">
<form:form modelAttribute="address" action="addaddress.htm" method="post">
<label>Street Name</label>
<input type="text" id="StreetName" name="StreetName" class="form-control">
<br>
<label>ApartmentNumber</label>
<input type="text" id="ApartmentNumber" name="ApartmentNumber" class="form-control">
<br>
<label>City</label>
 <select id="City" name="City" class="form-control">
 <option>Select a City </option>
 </select>
<br>
<label>State</label>
<input type="text" id="State" name="State" class="form-control">

<br>
<label>ZipCode</label>
<input type="text" id="ZipCode" name="ZipCode"class="form-control">
<br>
<input type="submit" value="Add the Address" class="form-control"/>
</form:form>
</div>
</body>
<script>
$.ajax({
    type: "GET",
    url: "cities",
    success: function(data)
    {
    	for (var index = 0; index <data.length; index++) {
    		var newOption = $('<option value="'+data[index]+'">'+data[index]+'</option>');
    		 $('#City').append(newOption);
  	   }
    }
});
$.ajax({
    type: "GET",
    url: "states",
    success: function(data)
    {
    	for (var index = 0; index <data.length; index++) {
    		var newOption = $('<option value="'+data[index]+'">'+data[index]+'</option>');
    		 $('#State').append(newOption);
   		 console.log(data[index]);
  	   }
    }
});
</script>
</html>