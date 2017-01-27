<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fmt="http://www.w3.org/1999/XSL/Transform"><head>
    <!-- Design by Free CSS Templates http://www.freecsstemplates.org Released for free under a Creative Commons Attribution 2.5 License -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><title>Endless by Free Css Templates</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
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
        <br><a href="./rest/user"><fmt:message key="Users" /></a><br>
        <a href="./rest/driver"><fmt:message key="Drivers" /></a><br>
        <a href="./rest/route"><fmt:message key="Routes" /></a><br>
        <a href="./rest/stop"><fmt:message key="Stops" /></a><br>
        <a href="./rest/plan"><fmt:message key="Plans" /></a><br>
        <a href="./rest/transport"><fmt:message key="Transports" /></a>
        <div id="colOne">
        </div>
        <div style="clear: both;">&nbsp;</div>
        <table width="100%" cellspacing="1" border="1" cellpadding="1">

            <tr>
                <th><fmt:message key="Id" /></th>
                <th><fmt:message key="Name" /></th>
            </tr>

            <c:forEach var="routes" items="${routesList}">
                <tr>
                    <td>${routes.id}</td>
                    <td>${routes.name}</td>
                </tr>
            </c:forEach>
        </table>

        <form method="post" action="./TransportServlet">
            <table>
                <tr>
                    <td><fmt:message key="Id" /></td>
                    <td><fmt:message key="Name" /></td>
                </tr>
                <tr>
                    <td><input type="text" name="route_id"></td><br>
                    <td><input type="text" name="name"></td><br>
                </tr>
                <tr>
                    <td colspan="3"></td>
                </tr>
            </table>

            <select name="command">
                <option value="createTransport"><fmt:message key="CreateCommand" /></option>
                <option value="updateTransport"><fmt:message key="UpdateCommand" /></option>
                <option value="deleteTransport"><fmt:message key="DeleteCommand" /></option>
                <option value="getAllTransport"><fmt:message key="GetAllCommand" /></option>
            </select><br>
            <button class="my_link_smal" type="submit">
                <fmt:message key="ExecuteCommand" />
            </button>
            <br />
            <div style="color: red;">
                <c:out value="${result}" />
            </div>

        </form>

    </div>
    <div id="footer">
    </div>
</fmt:bundle>
</body>
</html>