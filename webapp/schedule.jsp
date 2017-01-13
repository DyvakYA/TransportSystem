<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page
	import="commands.route.RouteCommandFactory"%>
<%@ page errorPage="error.jsp"%>
<%--<%@ taglib prefix="transport" uri="/WEB-INF/tlds/transportTagLib"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./style.css">
<c:set var="FIND_ALL_ROUTE_AND_STOPS_COMMAND"
	value="<%=RouteCommandFactory.FIND_ALL_ROUTE_AND_STOPS_COMMAND%>" />
</head>
<body>
	<fmt:bundle basename="ua.kpi.epam.transport.properties.labels">
		<div style="margin: 0 auto; width: 1000px">
			<div style="display: block; margin: 20px;">
				<a class="my_link" href="./index.jsp"><fmt:message
						key="RouteSeach" /></a> <a class="my_link"
					href="./TransportServlet?command=${FIND_ALL_ROUTE_AND_STOPS_COMMAND}"><fmt:message
						key="RouteSchemes" /></a> <a class="my_link"
					href="./autentification.jsp"><fmt:message key="EnterSystem" /></a>
			</div>

			<div style="display: block; margin-top: 60px;">
				<ol>
					<c:forEach items="${scheduleMap}" var="schedule_item">
						<li>
							<div class="routeItem">

								<table width="900px" cellspacing="2" border="1" cellpadding="5">
									<tr>
										<td><fmt:message key="RouteID" /></td>
										<td colspan="5"><fmt:message key="RouteName" /></td>
									</tr>
									<tr>
										<td><c:out value="${route.id}" /></td>
										<td colspan="5"><c:out value="${route.name}" /></td>
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
								</table>
							</div>
						</li>
					</c:forEach>
				</ol>
				<%/*
                    <ol>
                        <c:forEach items="${transportMap}" var="schedule_item">
                            <li>
                                <div class="routeItem">
                                    <table width="900px" cellspacing="2" border="1" cellpadding="5">
                                        <tr>
                                            <td colspan="3"><fmt:message key="ScheduleId"/></td>
                                            <td><c:out value="${schedule_item.key.id}" /></td>
                                        </tr>
                                        <tr>
                                            <td colspan="4"><fmt:message key="Transport"/></td>
                                        </tr>
                                        <tr>
                                            <td><fmt:message key="ID"/></td>
                                            <td><fmt:message key="Type"/></td>
                                            <td><fmt:message key="Number"/></td>
                                            <td><fmt:message key="Model"/></td>
                                        </tr>
                                        <tr>
                                            <td><c:out value="${schedule_item.value.id}" /></td>
                                            <td><c:out value="${schedule_item.value.type}" /></td>
                                            <td><c:out value="${schedule_item.value.number}" /></td>
                                            <td><c:out value="${schedule_item.value.model}" /></td>
                                        </tr>
                                    </table>
                                </div>
                            </li>
                        </c:forEach>
                    </ol>
                            */%>
				<transport:ShowTransport map="transportMap" className="routeItem" />
			</div>
		</div>
	</fmt:bundle>
</body>
</html>