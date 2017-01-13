<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page
	import="commands.transport.TransportCommandFactory"%>
<%@page
	import="commands.route.RouteCommandFactory"%>
<%@page
	import="commands.plan_of_stops.PlanOfStopsCommandFactory"%>
<%@ page errorPage="error.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/style.css">
<c:set var="FIND_TRANSPORT_FOR_ROUTE_SCHEDULES"
	value="<%=TransportCommandFactory.FIND_TRANSPORT_FOR_ROUTE_SCHEDULES%>" />
<c:set var="FIND_ALL_ROUTE_AND_STOPS_COMMAND"
	value="<%=RouteCommandFactory.FIND_ALL_ROUTE_AND_STOPS_COMMAND%>" />
<c:set var="FIND_ROUTES_BY_STOPS_COMMAND"
	value="<%=RouteCommandFactory.FIND_ROUTES_BY_STOPS_COMMAND%>" />
<c:set var="FIND_SCHEDULE_OF_STOPS_FOR_ROUTE_COMMAND"
	value="<%=PlanOfStopsCommandFactory.FIND_SCHEDULE_OF_STOPS_FOR_ROUTE_COMMAND%>" />

</head>
<body>
	<fmt:bundle basename="ua.kpi.epam.transport.properties.labels">
		<div class="main">
			<div style="display: block;">
				<div style="display: block; margin: 20px;">
					<a class="my_link" href="./index.jsp"><fmt:message
							key="RouteSeach" /></a> <a class="my_link"
						href="./TransportServlet?command=${FIND_ALL_ROUTE_AND_STOPS_COMMAND}"><fmt:message
							key="RouteSchemes" /></a> <a class="my_link"
						href="./autentification.jsp"><fmt:message key="EnterSystem" /></a>
				</div>
				<form method="get" name="registration" action="./TransportServlet">
					<div style="display: inline; float: left; margin: 20px;">

						<fieldset>
							<legend>
								<fmt:message key="FolowPath" />
							</legend>
							<fmt:message key="InitialStop" />
							<br /> <input type="text" required="required" size="40"
								name="startStop"><br />
							<fmt:message key="EntStop" />
							<br /> <input type="text" size="40" name="finishStop"
								required="required">
							<div style="color: red;">
								<c:out value="${result}" />
							</div>
						</fieldset>

					</div>
					<div class="inlineDiv">
						<fieldset>
							<legend>
								<fmt:message key="ChooseTransport" />
							</legend>
							<input type="checkbox" name="transportType" value="Bus">
							<fmt:message key="Bus" />
							<br /> <input type="checkbox" name="transportType" value="Tram">
							<fmt:message key="Tram" />
							<br /> <input type="checkbox" name="transportType"
								value="Trolleybus">
							<fmt:message key="Trolleybus" />
						</fieldset>

					</div>
					<div class="inlineDiv">
						<button type="submit" class="my_link " name="command"
							value="${FIND_ROUTES_BY_STOPS_COMMAND}">
							<fmt:message key="FindRoute" />
						</button>
					</div>
				</form>
			</div>
			<div style="display: block; margin-top: 200px;">

				<ol>
					<c:forEach items="${routeMap}" var="route_item">
						<li>
							<div class="routeItem">
								<table width="900px" cellspacing="2" border="1" cellpadding="5">
									<tr>
										<td><fmt:message key="RouteID" /></td>
										<td colspan="3"><fmt:message key="RouteName" /></td>
									</tr>
									<tr>
										<td><c:out value="${route_item.key.id}" /></td>
										<td colspan="3"><c:out value="${route_item.key.name}" /></td>
									</tr>
									<c:if test="${route_item.value!= null}">
										<tr>
											<td><fmt:message key="StopNumber" /></td>
											<td><fmt:message key="ID" /></td>
											<td><fmt:message key="Name" /></td>
											<td><fmt:message key="Address" /></td>
										</tr>
									</c:if>
									<c:set var="count" value="1" scope="page" />
									<c:forEach items="${route_item.value}" var="routeStopItem"
										varStatus="status">
										<tr>
											<td><c:out value="${count}" /> <c:set var="count"
													value="${count + 1}" scope="page" /></td>
											<td><c:out value="${routeStopItem.id}" /></td>
											<td><c:out value="${routeStopItem.name}" /></td>
											<td><c:out value="${routeStopItem.address}" /></td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2"><a class="my_link_smal"
											href="./TransportServlet?command=${FIND_SCHEDULE_OF_STOPS_FOR_ROUTE_COMMAND}&route_id=${route_item.key.id}"><fmt:message
													key="ViewSchedule" /></a></td>
										<td colspan="2"><a class="my_link_smal"
											href="./TransportServlet?command=${FIND_TRANSPORT_FOR_ROUTE_SCHEDULES}&route_id=${route_item.key.id}"><fmt:message
													key="TransportInfo" /></a></td>
									</tr>
								</table>
							</div>
						</li>
					</c:forEach>
				</ol>
			</div>
		</div>
	</fmt:bundle>
</body>
</html>