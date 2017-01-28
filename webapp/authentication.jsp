<%--
  Created by IntelliJ IDEA.
  User: Dyvak
  Date: 21.01.2017
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fmt="http://www.w3.org/1999/XSL/Transform"><head>
  <!-- Design by Free CSS Templates http://www.freecsstemplates.org Released for free under a Creative Commons Attribution 2.5 License -->
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><title>Endless by Free Css Templates</title>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<c:if test="${sessionScope.userLocale != null}">
  <fmt:setLocale value="${sessionScope.userLocale}" scope="session" />
</c:if>
<fmt:bundle basename="labels">
  <div id="header">
  </div>
  <div id="menu">
    <ul>
      <li><a href="./rest/route"><fmt:message key="Routes" /></a></li>
      <li><a href="./rest/transport"><fmt:message key="Transports" /></a></li>
      <li><a class="my_link" href="authentication.jsp"><fmt:message key="SignIn" /></a></li>
      <li><a class="my_link" href="registration.jsp"><fmt:message key="SignUp" /></a></li>
      <li><a href="./rest/changeLocale"><fmt:message key="ChangeLocale" /></a></li>
    </ul>
  </div>
  <div id="content">
    <div id="colOne">
    </div>
    <div style="clear: both;">&nbsp;</div>
    <form method="get" action="./rest/login" >
      <div class="main">

      <div class="field">
        <fmt:message key="Email" />
        <input type="text" name="email"/>
      </div>

      <div class="field">
        <fmt:message key="Password" />
        <input type="password" name="password"/><br/>
        <input type="submit">
      </div>

      </div>
      <br/><br/><br/>
    </form>
  </div>
  <div id="footer">
  </div>
</fmt:bundle>
</body>
</html>
