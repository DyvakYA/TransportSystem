<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page
        import="model.commands.transport.TransportCommandFactory"%>
<%@page
        import="model.commands.transport.TransportCommand"%>
<%@ page errorPage="WEB-INF/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="/TransportSystem/style.css">
  <c:set var="UPDATE_TRANSPORT_COMMAND"
         value="<%=TransportCommandFactory.UPDATE_TRANSPORT_COMMAND%>" />
  <c:set var="DELETE_TRANSPORT_COMMAND"
         value="<%=TransportCommandFactory.DELETE_TRANSPORT_COMMAND%>" />
  <c:set var="FIND_TRANSPORT_BY_ID_COMMAND"
         value="<%=TransportCommandFactory.FIND_TRANSPORT_BY_ID_COMMAND%>" />
  <c:set var="FIND_ALL_TRANSPORT_COMMAND"
         value="<%=TransportCommandFactory.FIND_ALL_TRANSPORT_COMMAND%>" />
  <c:set var="CREATE_TRANSPORT_COMMAND"
         value="<%=TransportCommandFactory.CREATE_TRANSPORT_COMMAND%>" />

  <c:set var="ID_ATTRIBUTE" value="<%=TransportCommand.ID_ATTRIBUTE%>" />
  <c:set var="MODEL_ATTRIBUTE"
         value="<%=TransportCommand.MODEL_ATTRIBUTE%>" />
  <c:set var="NUMBER_ATTRIBUTE"
         value="<%=TransportCommand.NUMBER_ATTRIBUTE%>" />
  <c:set var="TYPE_ATTRIBUTE" value="<%=TransportCommand.TYPE_ATTRIBUTE%>" />

</head>
<body>
<fmt:bundle basename="labels">
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
            <td><fmt:message key="Type" /></td>
            <td><fmt:message key="Number" /></td>
            <td><fmt:message key="Model" /></td>
          </tr>
          <tr>
            <td><select name="${TYPE_ATTRIBUTE }">
              <option><fmt:message key="Bus" /></option>
              <option><fmt:message key="Tram" /></option>
              <option><fmt:message key="Trolleybus" /></option>
            </select></td>
            <td><input type="number" name="${NUMBER_ATTRIBUTE }"></td>
            <td><input type="text" name="${MODEL_ATTRIBUTE }"></td>
          </tr>
          <tr>
            <td colspan="3"></td>
          </tr>
        </table>

        <select name="command">
          <option value="${CREATE_TRANSPORT_COMMAND}"><fmt:message
                  key="CreateTransportCommand" /></option>
          <option value="${DELETE_TRANSPORT_COMMAND}"><fmt:message
                  key="DeleteTransportCommand" /></option>
          <option value="${FIND_ALL_TRANSPORT_COMMAND }"><fmt:message
                  key="ShowTransportCommand" /></option>
          <option value="${UPDATE_TRANSPORT_COMMAND}"><fmt:message
                  key="UpdateTransportCommand" /></option>
        </select>
        <button class="my_link_smal" type="submit">
          <fmt:message key="ExecuteTransportCommand" />
        </button>
        <br />
        <div style="color: red;">
          <c:out value="${result}" />
        </div>
        <table width="400px" cellspacing="2" border="1" cellpadding="5">
          <tr>
            <td><fmt:message key="ID" /></td>
            <td><fmt:message key="Type" /></td>
            <td><fmt:message key="Number" /></td>
            <td><fmt:message key="Model" /></td>
          </tr>
          <c:if test="${transport !=null}">
            <tr>
              <td><input type="radio" name="${ID_ATTRIBUTE}"
                         value="${transport.id}">
                <c:out value="${transport.id}" /></td>
              <td><c:out value="${transport.type}" /></td>
              <td><c:out value="${transport.number}" /></td>
              <td><c:out value="${transport.model}" /></td>
            </tr>
          </c:if>
          <c:forEach items="${transportList}" var="item">
            <tr>
              <td><input type="radio" name="${ID_ATTRIBUTE}"
                         value="${item.id}">
                <c:out value="${item.id}" /></td>
              <td><c:out value="${item.type}" /></td>
              <td><c:out value="${item.number}" /></td>
              <td><c:out value="${item.model}" /></td>
            </tr>
          </c:forEach>
        </table>
      </form>
    </div>
  </div>
</fmt:bundle>
</body>
</html>
