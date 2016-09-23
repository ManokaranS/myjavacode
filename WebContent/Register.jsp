<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<h2 align="center">Registration Form</h2>

<form action="Register.do" method="POST">
    <label><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

	<input type="hidden" name="id" value="registerform">
       
    <button type="submit">Register</button>
    
</form>

</body>
</html>

