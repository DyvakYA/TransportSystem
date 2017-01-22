<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page
	import="controller.commands.past_plan.PlanCommandFactory"%>
<%@page import="controller.commands.past_plan.PlanCommand"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/TransportSystem/style.css">
<c:set var="FIND_ALL_SCHEDULES_FOR_ROUTE_COMMAND"
	value="<%=PlanCommandFactory.FIND_ALL_SCHEDULES_FOR_ROUTE_COMMAND%>" />
<c:set var="DELETE_SCHEDULE_COMMAND"
	value="<%=PlanCommandFactory.DELETE_SCHEDULE_COMMAND%>" />
<c:set var="SHOW_ADD_SCHEDULE_FORM_COMMAND"
	value="<%=PlanCommandFactory.SHOW_ADD_SCHEDULE_FORM_COMMAND%>" />
<c:set var="ADD_SCHEDULE_TO_STOP_COMMAND"
	value="<%=PlanCommandFactory.ADD_SCHEDULE_TO_STOP_COMMAND%>" />
<c:set var="CREATE_SCHEDULE_COMMAND"
	value="<%=PlanCommandFactory.CREATE_SCHEDULE_COMMAND%>" />
<c:set var="SET_TRANSPORT_ON_SCHEDULE"
	value="<%=PlanCommandFactory.SET_TRANSPORT_ON_SCHEDULE%>" />
<c:set var="SHOW_TRANSPORT_ON_SCHEDULE"
	value="<%=PlanCommandFactory.SHOW_TRANSPORT_ON_SCHEDULE%>" />
<c:set var="CREATE_SCHEDULE_USING_INTERVAL_COMMAND"
	value="<%=PlanCommandFactory.CREATE_SCHEDULE_USING_INTERVAL_COMMAND%>" />


<c:set var="ROUTE_ID_ATTRIBUTE"
	value="<%=PlanCommand.ROUTE_ID_ATTRIBUTE%>" />
<c:set var="SCHEDULE_ID_ATTRIBUTE"
	value="<%=PlanCommand.PLAN_ID_ATTRIBUTE%>" />
<c:set var="STOP_ID_ATTRIBUTE"
	value="<%=PlanCommand.STOP_ID_ATTRIBUTE%>" />
<c:set var="TRANSPORT_ID_ATTRIBUTE"
	value="<%=PlanCommand.TRANSPORT_ID_ATTRIBUTE%>" />
<c:set var="ARRIVE_TIME_ATTRIBUTE"
	value="<%=PlanCommand.ARRIVE_TIME_ATTRIBUTE%>" />
<c:set var="LEAVE_TIME_ATTRIBUTE"
	value="<%=PlanCommand.LEAVE_TIME_ATTRIBUTE%>" />
<c:set var="INTERVAL_ATTRIBUTE"
	value="<%=PlanCommand.INTERVAL_ATTRIBUTE%>" />
<c:set var="INTERVAL_SIGN_ATTRIBUTE"
	value="<%=PlanCommand.INTERVAL_SIGN_ATTRIBUTE%>" />

<c:set var="currentCommand" value="<%=request.getParameter("command")%>" />
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
				<div style="display: inline; float: left; margin-top: 20px;">
					<fieldset>
						<a class="my_link_smal" href="/TransportSystem/admin/schedule.jsp"><fmt:message
								key="ShowAllRouteCommand" /></a>
						<c:if
							test="${currentCommand == FIND_ALL_SCHEDULES_FOR_ROUTE_COMMAND}">
							<input type="hidden" name="${ROUTE_ID_ATTRIBUTE}"
								value="${resultRoute.id}">
							<button class="my_link_smal" name="command"
								value="${CREATE_SCHEDULE_COMMAND}">
								<fmt:message key="CreateNewScheduleCommand" />
							</button>
							</br>
							</br>
							<button class="my_link_smal" name="command"
								value="${CREATE_SCHEDULE_USING_INTERVAL_COMMAND}">
								<fmt:message key="CreateNewScheduleUsingIntervalCommand" />
							</button>
							<fmt:message key="Interval" />
							<input type="text" name="interval">
							<select name="${INTERVAL_SIGN_ATTRIBUTE}"><option>+</option>
								<option>-</option></select>
						</c:if>
						<br />
						<br />
						<br />
						<div style="color: red;">
							<c:out value="${result}" />
						</div>
					</fieldset>
					<c:if test="${routeList != null}">
						<table width="700px" cellspacing="2" border="1" cellpadding="5">
							<tr>
								<td><fmt:message key="ID" /></td>
								<td colspan="3"><fmt:message key="Name" /></td>
							</tr>
							<c:forEach items="${routeList}" var="item">
								<tr>
									<td><c:out value="${item.id}" /></td>
									<td><c:out value="${item.name}" /></td>
									<td><a class="my_link_smal"
										href="/TransportSystem/TransportServlet?${ROUTE_ID_ATTRIBUTE}=${item.id}&command=${FIND_ALL_SCHEDULES_FOR_ROUTE_COMMAND}">
											<fmt:message key="SchowAllSchedulesCommand" />
									</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>

					<c:if
						test="${currentCommand == FIND_ALL_SCHEDULES_FOR_ROUTE_COMMAND}">
						<div style="display: inline; float: left;">
							<ol>
								<c:forEach items="${scheduleMap}" var="schedule_item">
									<li><input type="radio" name="${SCHEDULE_ID_ATTRIBUTE}"
										value="${schedule_item.key.id}">
										<div class="routeItem">
											<table width="400px" cellspacing="2" border="1"
												cellpadding="5">
												<tr>
													<td><fmt:message key="RouteID" /></td>
													<td colspan="5"><fmt:message key="RouteName" /></td>
												</tr>
												<tr>
													<td><c:out value="${resultRoute.id}" /></td>
													<td colspan="5"><c:out value="${resultRoute.name}" /></td>
												</tr>
												<tr>
													<td>Schedule Id</td>
													<td colspan="5"><c:out value="${schedule_item.key.id}" /></td>
												</tr>
												<tr>
													<td><fmt:message key="StopNumber" /></td>
													<td><fmt:message key="ID" /></td>
													<td><fmt:message key="Name" /></td>
													<td><fmt:message key="Address" /></td>
													<td><fmt:message key="ArriveTime" /></td>
													<td><fmt:message key="LeaveTime" /></td>
												</tr>
												<c:set var="count" value="1" scope="page" />
												<c:forEach items="${schedule_item.value}" var="stop_item"
													varStatus="status">
													<tr>
														<td><c:out value="${count}" /> <c:set var="count"
																value="${count + 1}" scope="page" /></td>
														<td><c:out value="${stop_item.key.id}" /></td>
														<td><c:out value="${stop_item.key.name}" /></td>
														<td><c:out value="${stop_item.key.address}" /></td>
														<td><c:out value="${stop_item.value.arriveTime}" /></td>
														<td><c:out value="${stop_item.value.leaveTime}" /></td>
													</tr>
												</c:forEach>
												<tr>
													<td colspan="6" align="right"><a class="my_link_smal"
														href="/TransportSystem/TransportServlet?${SCHEDULE_ID_ATTRIBUTE}=${schedule_item.key.id}&command=${SHOW_TRANSPORT_ON_SCHEDULE}">
															<fmt:message key="ShowTransportForSchedule" />
													</a> <a class="my_link_smal"
														href="/TransportSystem/TransportServlet?${ROUTE_ID_ATTRIBUTE}=${resultRoute.id}&${SCHEDULE_ID_ATTRIBUTE}=${schedule_item.key.id}&command=${SHOW_ADD_SCHEDULE_FORM_COMMAND}">
															<fmt:message key="AddTimeToStopLink" />
													</a> <a class="my_link_smal"
														href="/TransportSystem/TransportServlet?${SCHEDULE_ID_ATTRIBUTE}=${schedule_item.key.id}&command=${DELETE_SCHEDULE_COMMAND}">
															<fmt:message key="DeleteScheduleCommand" />
													</a></td>
												</tr>
											</table>
										</div></li>
								</c:forEach>
							</ol>
						</div>
					</c:if>

					<c:if test="${routeMap != null}">
						<fieldset>
							<legend>
								<fmt:message key="AddTimeForStopLegend" />
							</legend>
							<table>
								<tr>
									<td><fmt:message key="ArriveTime" /></td>
									<td><fmt:message key="LeaveTime" /></td>
									<td></td>
								</tr>
								<tr>
									<td><input type="text" name="${ARRIVE_TIME_ATTRIBUTE}"></td>
									<td><input type="text" name="${LEAVE_TIME_ATTRIBUTE}"></td>
									<td><c:set var="schedule_id"
											value="<%=request.getParameter(PlanCommand.PLAN_ID_ATTRIBUTE)%>" />
										<input type="hidden" name="${SCHEDULE_ID_ATTRIBUTE}"
										value="${schedule_id}">
									<td>
										<button type="submit" name="command"
											value="${ADD_SCHEDULE_TO_STOP_COMMAND}">
											<fmt:message key="AddTimeToStopCommand" />
										</button>
									</td>
								</tr>
							</table>
						</fieldset>
						<table width="400px" cellspacing="2" border="1" cellpadding="5">
							<tr>
								<td><fmt:message key="ID" /></td>
								<td><fmt:message key="Name" /></td>
							</tr>

							<c:forEach items="${routeMap }" var="routeItem">
								<tr>
									<td><c:out value="${routeItem.key.id}" /></td>
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
										<td><input type="radio" name="${STOP_ID_ATTRIBUTE}"
											value="${stopItem.id}"> <c:out value="${count}" /> <c:set
												var="count" value="${count + 1}" scope="page" /></td>
										<td><c:out value="${stopItem.id}" /></td>
										<td><c:out value="${stopItem.name}" /></td>
										<td><c:out value="${stopItem.address}" /></td>
									</tr>
								</c:forEach>
							</c:forEach>
						</table>
					</c:if>

					<c:if test="${currentCommand == SHOW_TRANSPORT_ON_SCHEDULE}">
						<div class="routeItem">
							<c:set var="schedule_id"
								value="<%=request.getParameter(PlanCommand.PLAN_ID_ATTRIBUTE)%>" />
							<input type="hidden" name="${SCHEDULE_ID_ATTRIBUTE}"
								value="${schedule_id}">
							<table width="300px" cellspacing="2" border="1" cellpadding="5">
								<tr>
									<td colspan="3">Schedule Id</td>
									<td><c:out value="${schedule_id}" /></td>
								</tr>
								<tr>
									<td colspan="4"><fmt:message key="Transport" /></td>
								</tr>
								<tr>
									<td><fmt:message key="ID" /></td>
									<td><fmt:message key="Type" /></td>
									<td><fmt:message key="Number" /></td>
									<td><fmt:message key="Model" /></td>
								</tr>
								<tr>
									<td><c:out value="${transportResult.id}" /></td>
									<td><c:out value="${transportResult.type}" /></td>
									<td><c:out value="${transportResult.number}" /></td>
									<td><c:out value="${transportResult.model}" /></td>
								</tr>
								<tr>
									<td colspan="4" align="right">
										<button class="my_link_smal" name="command"
											value="${SET_TRANSPORT_ON_SCHEDULE}">
											<fmt:message key="SetTransportOnScheduleCommand" />
										</button>
									</td>
								</tr>
							</table>
						</div>
					</c:if>

					<c:if test="${transportList!=null}">
						<div
							style="display: inline; float: right; margin-left: 30px; margin-top: 20px">
							<table width="300px" cellspacing="2" border="1" cellpadding="5">
								<tr>
									<td colspan="4"><fmt:message
											key="TransportToSetOnSchedule" /></td>
								</tr>
								<c:forEach items="${transportList}" var="item">
									<tr>
										<td><input type="radio" name="${TRANSPORT_ID_ATTRIBUTE}"
											value="${item.id}"> <c:out value="${item.id}" /></td>
										<td><c:out value="${item.type}" /></td>
										<td><c:out value="${item.number}" /></td>
										<td><c:out value="${item.model}" /></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</c:if>
				</div>
			</form>
		</div>
	</fmt:bundle>
</body>
</html>