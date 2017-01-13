<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page	import="commands.route.RouteCommandFactory"%>
<%@page import="commands.user.RegisterUserCommand"%>
<%@page import="commands.CommandFactory"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./style.css">
<c:set var="FIND_ALL_ROUTE_AND_STOPS_COMMAND"
	value="<%=RouteCommandFactory.FIND_ALL_ROUTE_AND_STOPS_COMMAND%>" />
<c:set var="REGISTER_USER_COMMAND"
	value="<%=CommandFactory.REGISTER_USER_COMMAND%>" />

<c:set var="LOGIN_ATTRIBUTE"
	value="<%=RegisterUserCommand.LOGIN_ATTRIBUTE%>" />
<c:set var="PASSWORD_ATTRIBUTE"
	value="<%=RegisterUserCommand.PASSWORD_ATTRIBUTE%>" />
<c:set var="CONFIRM_PASSWORD_ATTRIBUTE"
	value="<%=RegisterUserCommand.CONFIRM_PASSWORD_ATTRIBUTE%>" />
<c:set var="NAME_ATTRIBUTE"
	value="<%=RegisterUserCommand.NAME_ATTRIBUTE%>" />
<c:set var="SURNAME_ATTRIBUTE"
	value="<%=RegisterUserCommand.SURNAME_ATTRIBUTE%>" />
<c:set var="ROLE_ATTRIBUTE"
	value="<%=RegisterUserCommand.ROLE_ATTRIBUTE%>" />

</head>
<body>
	<fmt:bundle basename="ua.kpi.epam.transport.properties.labels">
		<div style="display: block; margin: 20px;">
			<a class="my_link" href="./index.jsp"><fmt:message
					key="RouteSeach" /></a> <a class="my_link"
				href="./TransportServlet?command=${FIND_ALL_ROUTE_AND_STOPS_COMMAND}"><fmt:message
					key="RouteSchemes" /></a> <a class="my_link"
				href="./autentification.jsp"><fmt:message key="EnterSystem" /></a>
		</div>

		<div class="inlineDiv">
			<form method="POST" action="./TransportServlet">
				<fieldset>
					<legend>
						<fmt:message key="RegisterInfo" />
					</legend>

					<fmt:message key="Login" />
					<sup>*</sup><br /> <input type="text" name="${LOGIN_ATTRIBUTE}"
						required="required" size="40"> <br />
					<fmt:message key="Password" />
					<sup>*</sup><br /> <input type="password"
						name="${PASSWORD_ATTRIBUTE}" size="40" required="required">
					<br />
					<fmt:message key="PasswordConfirm" />
					<br /> <input type="password" name="${CONFIRM_PASSWORD_ATTRIBUTE}"
						size="40" required="required"> <br />
					<fmt:message key="UserName" />
					<sup>*</sup><br /> <input type="text" size="40"
						name="${NAME_ATTRIBUTE}" required="required"> <br />
					<fmt:message key="Surname" />
					<sup>*</sup><br /> <input type="text" name="${SURNAME_ATTRIBUTE}"
						size="40" required="required"> <br />
					<fmt:message key="ChoosePriviledge" />
					<sup>*</sup><br /> <input type="radio" name="${ROLE_ATTRIBUTE}"
						value="admin" required="required">
					<fmt:message key="AdminRightConfirmation" />
					<br /> <input type="radio" name="${ROLE_ATTRIBUTE}" value="user"
						required="required">
					<fmt:message key="UserRightConfirmation" />
					<br /> <br />
					<button type="submit" name="command"
						value="${REGISTER_USER_COMMAND}">
						<fmt:message key="MakeRegistration" />
					</button>
				</fieldset>
			</form>
		</div>
		<div
			style="display: block; margin-top: 400px; text-align: left; color: red;">
			<c:out value="${result}" />
		</div>
	</fmt:bundle>
</body>
</html>