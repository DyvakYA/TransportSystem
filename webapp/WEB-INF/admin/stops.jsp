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
            <li><a class="my_link" href="./rest/route"><fmt:message key="Routes" /></a></li>
            <li><a class="my_link" href="./rest/transport"><fmt:message key="Transports" /></a></li>
            <li><a class="my_link" href="./rest/user"><fmt:message key="Users" /></a></li>
            <li><a class="my_link" href="./rest/driver"><fmt:message key="Drivers" /></a></li>
            <li><a class="my_link" href="./rest/stop"><fmt:message key="Stops" /></a></li>
            <li><a class="my_link" href="./rest/plan"><fmt:message key="Plans" /></a></li>
            <li><a href="./rest/changeLocale"><fmt:message key="ChangeLocale" /></a></li>
        </ul>
    </div>
    <div id="content">
        <div id="colOne">
        </div>
        <div style="clear: both;">&nbsp;</div>
        <table width="900px" cellspacing="2" border="1" cellpadding="5">

            <tr>
                <th><fmt:message key="Id" /></th>
                <th><fmt:message key="Name" /></th>
                <th><fmt:message key="Address" /></th>
            </tr>

            <c:forEach var="stops" items="${stopsList}">
                <tr>
                    <td>${stops.id}</td>
                    <td>${stops.name}</td>
                    <td>${stops.address}</td>
                </tr>
            </c:forEach>
        </table>

        <form method="post" action="./TransportServlet">
            <table>
                <tr>
                    <td><fmt:message key="Id" /></td>
                    <td><fmt:message key="Name" /></td>
                    <td><fmt:message key="Address" /></td>
                </tr>
                <tr>
                    <td><input type="text" name="${ID_ATTRIBUTE }"></input></td>
                    <td><input type="text" name="${NAME_ATTRIBUTE }"></input></td>
                    <td><input type="text" name="${ADDRESS_ATTRIBUTE }"></input></td>
                </tr>
                <tr>
                    <td colspan="3"></td>
                </tr>
            </table>

            <select name="command">
                <option value="createStop"><fmt:message
                        key="CreateCommand" /></option>
                <option value="updateStop"><fmt:message
                        key="UpdateCommand" /></option>
                <option value="deleteStop"><fmt:message
                        key="DeleteCommand" /></option>
                <option value="getAllStops"><fmt:message
                        key="GetAllCommand" /></option>
            </select>
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