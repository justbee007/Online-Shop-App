<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign in to Shopping Spree</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
.form-group
{ 
width: 500px;
padding-left:50px;
}
.btn-primary
{margin-left: 50px;
}

</style>
</head>
<body style="background: azure;">
<h1 class="display-3"  blockquote text-center style="color:blue;text-align:center">Login to Shopping Spree</h1>
<form:form modelAttribute="user" method="post">
<div class="form-group">
<label>User Name:</label>
<input type="text" id="username" name="userName" class="form-control">
</div>
<br>
<br>
<div class="form-group">
<label>Password:</label>
<input type="password" id="password" class="form-control" name="password">
</div>
<br>
<br>
<input type="submit" value="Submit" class="btn btn-primary">
</form:form>
<hr>
<a class="btn btn-primary" href="/shop/" role="button">Click here to Sign Up</a>
<br>
<br>
${message}
</body>
</html>