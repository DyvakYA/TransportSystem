package controller.commands.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandValidator {

    String EMAIL_ATTRIBUTE = "email";
    String ROLE_ATTRIBUTE = "role";
    String ID_ATTRIBUTE = "user_id";
    String TRANSPORT_ID_ATTRIBUTE = "transport_id";
    String DRIVER_ID_ATTRIBUTE = "driver_id";
    String TRANSPORT_DESTINATION_PAGE = "/admin/transports.jsp";
    String USER_DESTINATION_PAGE = "/admin/users.jsp";
    String DRIVER_DESTINATION_PAGE = "/admin/drivers.jsp";

    String PASSWORD_ATTRIBUTE = "password";

    String DRIVER_ERROR_MSG = "DriverNotSelected";

    String PLAN_ERROR_MSG = "PlanNotSelected";

    String ROUTE_ERROR_MSG = "RouteNotSelected";

    String STOP_ERROR_MSG = "StopNotSelected";

    String USER_ERROR_MSG = "UserNotSelected";

    String TRANSPORT_ERROR_MSG = "TransportNotSelected";

    String MSG_NAME = "NotValidEmptyLoginAndPassword";

    boolean validate(HttpServletRequest request, HttpServletResponse response);

}

