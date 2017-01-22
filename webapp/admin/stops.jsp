<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="controller.commands.past_stop.StopCommandFactory"%>
<%@page import="controller.commands.past_stop.StopCommand"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/TransportSystem/style.css">
<c:set var="UPDATE_STOP_COMMAND"
	value="<%=StopCommandFactory.UPDATE_STOP_COMMAND%>" />
<c:set var="DELETE_STOP_COMMAND"
	value="<%=StopCommandFactory.DELETE_STOP_COMMAND%>" />
<c:set var="FIND_STOP_BY_ID_COMMAND"
	value="<%=StopCommandFactory.FIND_STOP_BY_ID_COMMAND%>" />
<c:set var="FIND_ALL_STOP_COMMAND"
	value="<%=StopCommandFactory.FIND_ALL_STOP_COMMAND%>" />
<c:set var="CREATE_STOP_COMMAND"
	value="<%=StopCommandFactory.CREATE_STOP_COMMAND%>" />

<c:set var="ID_ATTRIBUTE" value="<%=StopCommand.ID_ATTRIBUTE%>" />
<c:set var="NAME_ATTRIBUTE" value="<%=StopCommand.NAME_ATTRIBUTE%>" />
<c:set var="ADDRESS_ATTRIBUTE"
	value="<%=StopCommand.ADDRESS_ATTRIBUTE%>" />

</head>
<body>
	<fmt:bundle basename="ua.kpi.epam.transport.properties.labels">
		<div class="main">
			<div style="display: block;">
				<div style="display: block; margin: 20px;">
					<a class="my_link" href="/TransportSystem/admin/transport.jsp"><fmt:message
							key="TransportAdminLink" /></a> <a class="my_link"
						href="/TransportSystem/admin/stops.jsp"><fmt:message
							key="StopAdminLink" /></a> <a class="my_link"
						href="/TransportSystem//admin/route.jsp"><fmt:message
							key="RouteAdminLink" /></a> <a class="my_link"
						href="/TransportSystem/admin/schedule.jsp"><fmt:message
							key="ScheduleAdminLink" /></a>
				</div>
				<form method="POST" action="/TransportSystem/TransportServlet">
					<table>
						<tr>
							<td><fmt:message key="Name" /></td>
							<td><fmt:message key="Address" /></td>
						</tr>
						<tr>
							<td><input type="text" name="${NAME_ATTRIBUTE }"></td>
							<td><input type="text" name="${ADDRESS_ATTRIBUTE }"></td>
						</tr>
					</table>

					<select name="command">
						<option value="${CREATE_STOP_COMMAND}"><fmt:message
								key="CreateStopCommand" /></option>
						<option value="${DELETE_STOP_COMMAND}"><fmt:message
								key="DeleteStopCommand" /></option>
						<option value="${FIND_ALL_STOP_COMMAND }"><fmt:message
								key="ShowStopsCommand" /></option>
						<option value="${UPDATE_STOP_COMMAND}"><fmt:message
								key="UpdateStopCommand" /></option>
					</select>
					<button class="my_link_smal" type="submit">
						<fmt:message key="ExecuteStopCommand" />
					</button>
					<br />
					<div style="color: red;">
						<c:out value="${result}" />
					</div>

					<table width="400px" cellspacing="2" border="1" cellpadding="5">
						<tr>
							<td><fmt:message key="ID" /></td>
							<td><fmt:message key="Name" /></td>
							<td><fmt:message key="Address" /></td>
						</tr>
						<c:if test="${stop !=null}">
							<tr>
								<td><input type="radio" name="${ID_ATTRIBUTE}"
									value="${stop.id}">
								<c:out value="${stop.id}" /></td>
								<td><c:out value="${stop.name}" /></td>
								<td><c:out value="${stop.address}" /></td>
							</tr>
						</c:if>
						<c:forEach items="${stopList}" var="item">
							<tr>
								<td><input type="radio" name="${ID_ATTRIBUTE}"
									value="${item.id}">
								<c:out value="${item.id}" /></td>
								<td><c:out value="${item.name}" /></td>
								<td><c:out value="${item.address}" /></td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
		</div>
	</fmt:bundle>
</body>
</html>
