package controller.commands.plan;

import controller.commands.Command;

public interface PlanCommand extends Command {

    String CREATE_PLAN_SUCCESSFUL_MSG = "PlanCreatedSuccessful";
    String DELETE_PLAN_SUCCESSFUL_MSG = "DeletePlanSuccessful";

    String RESULT_ROUTES_LIST = "routeList";

    String RESULT_SCHEDULE_LIST = "scheduleList";

    String RESULT_ROUTE_ATTRIBUTE = "resultRoute";

    String RESULT_MAP_ATTRIBUTE = "scheduleMap";

    String RESULT_ROUTE_MAP_ATTRIBUTE = "routeMap";

    String ROUTE_ID_ATTRIBUTE = "routeId";

    String ADMIN_DESTINATION_PAGE = "admin/past_plan.jsp";

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
