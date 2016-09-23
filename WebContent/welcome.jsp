<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<body>
<h2 align="center">Welcome <%=request.getParameter("username") %></h2>
<p>welcome !</p>  
 <form action="login.do" method="POST">

<input type="hidden" name="username" value="<%=request.getParameter("username")%>">
<input type="hidden" name="id" value="logout">
<button type="submit">Logout</button>

</form>   

</body>
</html>

