<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>
<a href="./rest/route">Routes</a>

<a class="my_link" href="authentication.jsp">Sign In</a>
<a class="my_link" href="registration.jsp">Sign Up</a>
<a href="./rest/changeLocale"><fmt:message key="ChangeLocale" /></a>
<br>
<a href="./rest/user"><fmt:message key="Users" /></a>
<br>
<a href="./rest/driver"><fmt:message key="Drivers" /></a>
<br>
<a href="./rest/route"><fmt:message key="Routes" /></a>
<br>
<a href="./rest/stop"><fmt:message key="Stops" /></a>
<br>
<a href="./rest/plan"><fmt:message key="Plans" /></a>
<br>
<a href="./rest/transport"><fmt:message key="Transports" /></a>


</body>
</html>