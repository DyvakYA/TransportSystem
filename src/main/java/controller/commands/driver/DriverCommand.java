package controller.commands.driver;

import controller.commands.Command;

public interface DriverCommand extends Command {

    String CREATE_DRIVER_SUCCESSFUL_MSG = "CreateDriverSuccessful";
    String UPDATE_DRIVER_SUCCESSFUL_MSG = "UpdateDriverSuccessful";
    String DELETE_DRIVER_SUCCESSFUL_MSG = "DeleteDriverSuccessful";

    String DRIVER_LIST_ATTRIBUTE = "driversList";

    String RESULT_SCHEDULE_LIST = "scheduleList";

    String RESULT_ROUTE_ATTRIBUTE = "resultRoute";

    String RESULT_MAP_ATTRIBUTE = "scheduleMap";

    String RESULT_ROUTE_MAP_ATTRIBUTE = "routeMap";

    String DRIVER_ID_ATTRIBUTE = "driver_id";

    String ADMIN_DESTINATION_PAGE = "admin/drivers.jspx";

    String PLAN_ID_ATTRIBUTE = "planId";

    String STOP_ID_ATTRIBUTE = "stopId";

    String TRANSPORT_ID_ATTRIBUTE = "transportId";

    String RESULT_TRANSPORT_ATTRIBUTE = "transportResult";

    String TRANSPORT_LIST_ATTRIBUTE = "transportList";

    String ARRIVE_TIME_ATTRIBUTE = "arriveTime";

    String LEAVE_TIME_ATTRIBUTE = "leaveTime";

    String INTERVAL_ATTRIBUTE = "interval";

    String INTERVAL_SIGN_ATTRIBUTE = "intervalSign";
}
