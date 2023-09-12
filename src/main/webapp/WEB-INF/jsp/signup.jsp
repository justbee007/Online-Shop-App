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
<h1 class="display-3 text-center" style="color:blue;text-align:center">Signup to Shopping Spree</h1>
<h1>${message}</h1>
<div class="form-group">
<form:form modelAttribute="user" method="post">
<label>First Name:</label>
<form:input type="text" id="firstname" name="firstName" path="firstName" class="form-control"/>
<form:errors path="firstName"/>
<br>
<br>
<label>Last Name:</label>
<form:input type="text" id="lastname" name="lastName" path="lastName" class="form-control"/>
<form:errors path="lastName"/>
<br>
<br>
<label>User Name:</label>
<form:input type="text" id="username" name="userName" path="userName" class="form-control"/>
<br>
<br>
<label>Password:</label>
<input type="password" id="password" name="password" class="form-control">
<br>
<br>
<input class="btn btn-primary" type="submit" value="Submit">
</form:form>
<hr>
<a class="btn btn-primary" href="/shop/signin.htm" role="button">Click here to Sign In</a>
</div>
<br>
<br>
<a href="signin.htm">Click here to Sign In</a>
</body>
</html>