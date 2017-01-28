package controller.commands.route;

import controller.commands.Command;

public interface RouteCommand extends Command {

    String CREATE_ROUTE_SUCCESSFUL_MSG = "CreateRouteSuccessful";
    String UPDATE_ROUTE_SUCCESSFUL_MSG = "UpdateRouteSuccessful";
    String DELETE_ROUTE_SUCCESSFUL_MSG = "DeleteRouteSuccessful";

    String START_STOP_ATTRIBUTE = "startStop";

    String FINISH_STOP_ATTRIBUTE = "finishStop";

    String TRANSPORT_TYPE_ATTRIBUTE = "transportType";

    String ID_ATTRIBUTE = "stop_id";

    String ROUTE_LIST_ATTRIBUTE = "routesList";

    String RESULT_MAP_ATTRIBUTE = "routeMap";

    String DESTINATION_PAGE = "./index.jsp";

    String ADMIN_DESTINATION_PAGE = "./admin/route.jsp";

    String ROUTE_ID_ATTRIBUTE = "route_id";

    String ROUTE_NAME_ATTRIBUTE = "routeName";

    String STOP_ID_ATTRIBUTE = "stopId";

    String STOP_LIST_ATTRIBUTE = "stopsList";

    String STOP_TO_REMOVE_ATTRIBUTE = "stopToRemoveId";
}
