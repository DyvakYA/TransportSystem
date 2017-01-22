package controller.commands.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandValidator {

    String DRIVER_ERROR_MSG = "DriverNotSelected";

    String PLAN_ERROR_MSG = "PlanNotSelected";

    String ROUTE_ERROR_MSG = "RouteNotSelected";

    String STOP_ERROR_MSG = "StopNotSelected";

    String TRANSPORT_ERROR_MSG = "TransportNotSelected";

    boolean validate(HttpServletRequest request, HttpServletResponse response);

}

