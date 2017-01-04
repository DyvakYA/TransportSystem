<%@ page import="model.commands.CommandFactory" %>
<%@ page import="model.commands.user.AutentificateUserCommand" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
  <title>Login Page</title>
  <link href="css/style.css" rel="stylesheet">

  <c:set var="AUTENTIFICATION_COMMAND"
         value="<%=CommandFactory.AUTENTIFICATION_COMMAND%>" />
  <c:set var="LOGIN_ATTRIBUTE"
         value="<%=AutentificateUserCommand.LOGIN_ATTRIBUTE%>" />
  <c:set var="PASSWORD_ATTRIBUTE"
         value="<%=AutentificateUserCommand.PASSWORD_ATTRIBUTE%>" />
  <c:set var="CHANGE_LOCALE_COMMAND"
         value="<%=CommandFactory.CHANGE_LOCALE_COMMAND%>" />
</head>
<body>

<c:if test="${sessionScope.userLocale != null}">
  <fmt:setLocale value="${sessionScope.userLocale}" scope="session" />
</c:if>
<fmt:bundle basename="labels">
  <div style="display: block; margin: 20px;">
   <a class="my_link"
  href="./TransportServlet?command=${FIND_ALL_ROUTE_AND_STOPS_COMMAND}"><fmt:message
        key="RouteSchemes" /></a> <a class="my_link"
  href="./autentification.jsp"><fmt:message key="EnterSystem" /></a> <a
  class="my_link" href="./admin/transport.jsp"><fmt:message
        key="AdminPage" /></a> <a class="my_link"
  href="./TransportServlet?command=${CHANGE_LOCALE_COMMAND}"><fmt:message
        key="ChangeLocale" /></a>

<form accept-charset="UTF-8" action="./TransportServlet">
  <div ><input name="utf8" type="hidden" value="✓">
  <div class="auth-form-header">
    <h3>Sign in to Transport System</h3>
  </div>
  <div id="js-flash-container">
  </div>
    <div class="auth-form-body">
      <label for="login_field">
        Username or email address
      </label>
      <input class="form-control input-block" id="login_field" name="${LOGIN_ATTRIBUTE}" tabindex="1" type="text" required="required">
      <label for="password">
        Password <a href="/password_reset" class="label-link">Forgot password?</a>
      </label>
      <input class="form-control input-block" id="password" name="${PASSWORD_ATTRIBUTE}" tabindex="2" type="password" required="required">

      <input class="btn btn-primary btn-block" name="command" tabindex="3" type="submit" value="${AUTENTIFICATION_COMMAND}">
    </div>
    </div>
</form>
</fmt:bundle>
</body>
</html>

