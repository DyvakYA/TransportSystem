<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page
	import="controller.commands.past_route.RouteCommandFactory"%>
<%@page import="controller.commands.past_route.RouteCommand"%>
<%@ page errorPage="./error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/TransportSystem/style.css">
<c:set var="UPDATE_ROUTE_COMMAND"
	value="<%=RouteCommandFactory.UPDATE_ROUTE_COMMAND%>" />
<c:set var="DELETE_ROUTE_COMMAND"
	value="<%=RouteCommandFactory.DELETE_ROUTE_COMMAND%>" />
<c:set var="FIND_ROUTE_BY_ID_COMMAND"
	value="<%=RouteCommandFactory.FIND_ROUTE_BY_ID_COMMAND%>" />
<c:set var="FIND_ALL_ROUTE_COMMAND"
	value="<%=RouteCommandFactory.FIND_ALL_ROUTE_COMMAND%>" />
<c:set var="CREATE_ROUTE_COMMAND"
	value="<%=RouteCommandFactory.CREATE_ROUTE_COMMAND%>" />
<c:set var="FIND_ALL_STOP_ON_ROUTE_COMMAND"
	value="<%=RouteCommandFactory.FIND_ALL_STOP_ON_ROUTE_COMMAND%>" />
<c:set var="ADD_STOP_ON_ROOT_COMMAND"
	value="<%=RouteCommandFactory.ADD_STOP_ON_ROOT_COMMAND%>" />
<c:set var="REMOVE_STOP_FROM_ROOT_COMMAND"
	value="<%=RouteCommandFactory.REMOVE_STOP_FROM_ROOT_COMMAND%>" />

<c:set var="ROUTE_ID_ATTRIBUTE"
	value="<%=RouteCommand.ROUTE_ID_ATTRIBUTE%>" />
<c:set var="ROUTE_NAME_ATTRIBUTE"
	value="<%=RouteCommand.ROUTE_NAME_ATTRIBUTE%>" />
<c:set var="STOP_ID_ATTRIBUTE"
	value="<%=RouteCommand.STOP_ID_ATTRIBUTE%>" />
<c:set var="STOP_TO_REMOVE_ATTRIBUTE"
	value="<%=RouteCommand.STOP_TO_REMOVE_ATTRIBUTE%>" />
</head>
<body>
	<fmt:bundle basename="ua.kpi.epam.transport.properties.labels">
		<div class="main">
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
			<form method="GET" action="/TransportSystem/TransportServlet">
				<div style="display: inline; float: left;">
					<fieldset>
						<legend>
							<fmt:message key="AdminRouteFormLegend" />
						</legend>
						<fmt:message key="RouteName" />
						<input type="text" name="${ROUTE_NAME_ATTRIBUTE }"><br />
						<select name="command">
							<option value="${CREATE_ROUTE_COMMAND}"><fmt:message
									key="CreateRouteCommand" /></option>
							<option value="${DELETE_ROUTE_COMMAND}"><fmt:message
									key="DeleteRouteCommand" /></option>
							<option value="${FIND_ALL_ROUTE_COMMAND }"><fmt:message
									key="ShowAllRouteCommand" /></option>
							<option value="${UPDATE_ROUTE_COMMAND}"><fmt:message
									key="UpdateRouteCommand" /></option>
							<option value="${ADD_STOP_ON_ROOT_COMMAND}"><fmt:message
									key="AddStopToRouteCommand" /></option>
							<option value="${REMOVE_STOP_FROM_ROOT_COMMAND}"><fmt:message
									key="RemoveStopFromRouteCommand" /></option>
						</select>
						<button class="my_link_smal" type="submit" value="make action">
							<fmt:message key="ExecuteRouteCommand" />
						</button>
						<div style="width: 100px; color: red;">
							<c:out value="${result}" />
						</div>
					</fieldset>

					<c:if test="${routeList != null}">
						<table width="400px" cellspacing="2" border="1" cellpadding="5">
							<tr>
								<td><fmt:message key="ID" /></td>
								<td><fmt:message key="Name" /></td>
							</tr>
							<c:forEach items="${routeList}" var="item">
								<tr>
									<td><input type="radio" name="${ROUTE_ID_ATTRIBUTE }"
										value="${item.id}"> <c:out value="${item.id}" /></td>
									<td><a
										href="TransportServlet?command=findAllStopOnRoute&routeId=${item.id}"><c:out
												value="${item.name}" /></a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>

					<c:if test="${routeMap != null}">
						<table width="400px" cellspacing="2" border="1" cellpadding="5">
							<tr>
								<td><fmt:message key="ID" /></td>
								<td><fmt:message key="Name" /></td>
							</tr>


							<c:forEach items="${routeMap }" var="routeItem">
								<tr>
									<td><input type="radio" name="${ROUTE_ID_ATTRIBUTE }"
										value="${routeItem.key.id}" checked> <c:out
											value="${routeItem.key.id}" /></td>
									<td colspan="3"><c:out value="${routeItem.key.name}" /></td>
								</tr>
								<tr>
									<td><fmt:message key="StopNumber" /></td>
									<td><fmt:message key="ID" /></td>
									<td><fmt:message key="Name" /></td>
									<td><fmt:message key="Address" /></td>
								</tr>
								<c:set var="count" value="1" scope="page" />
								<c:forEach items="${routeItem.value}" var="stopItem">
									<tr>
										<td><input type="radio"
											name="${STOP_TO_REMOVE_ATTRIBUTE}" value="${stopItem.id}">
											<c:out value="${count}" /> <c:set var="count"
												value="${count + 1}" scope="page" /></td>
										<td><c:out value="${stopItem.id}" /></td>
										<td><c:out value="${stopItem.name}" /></td>
										<td><c:out value="${stopItem.address}" /></td>
									</tr>
								</c:forEach>
							</c:forEach>
						</table>
					</c:if>
				</div>

				<div style="display: inline; float: left; margin-left: 30px;">

					<c:if test="${stopList != null}">
						<table width="400px" cellspacing="2" border="1" cellpadding="5">
							<tr>
								<td colspan="3"><fmt:message key="Stops" /></td>
							</tr>
							<tr>
								<td><fmt:message key="ID" /></td>
								<td><fmt:message key="Name" /></td>
								<td><fmt:message key="Address" /></td>
							</tr>
							<c:forEach items="${stopList}" var="item">
								<tr>
									<td><input type="radio" name="${STOP_ID_ATTRIBUTE }"
										value="${item.id}"> <c:out value="${item.id}" /></td>
									<td><c:out value="${item.name}" /></td>
									<td><c:out value="${item.address}" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>

				</div>
			</form>
		</div>
	</fmt:bundle>
</body>
</html>