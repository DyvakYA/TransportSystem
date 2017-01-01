package ua.kpi.epam.transport.commands.route;

import ua.kpi.epam.transport.commands.Command;

/**
 *
 * @author KIRIL
 */
public interface RouteCommand extends Command {

    /**
     *
     */
    String START_STOP_ATTRIBUTE = "startStop";

    /**
     *
     */
    String FINISH_STOP_ATTRIBUTE = "finishStop";

    /**
     *
     */
    String TRANSPORT_TYPE_ATTRIBUTE = "transportType";

    /**
     *
     */
    String ADDRESS_ATTRIBUTE = "address";

    /**
     *
     */
    String NAME_ATTRIBUTE = "name";

    /**
     *
     */
    String ID_ATTRIBUTE = "stop_id";

    /**
     *
     */
    String RESULT_ATTRIBUTE = "result";

    /**
     *
     */
    String RESULT_ROUTE_ATTRIBUTE = "result";

    /**
     *
     */
    String RESULT_LIST_ATTRIBUTE = "routeList";

    /**
     *
     */
    String RESULT_MAP_ATTRIBUTE = "routeMap";

    /**
     *
     */
    String DESTINATION_PAGE = "./index.jsp";

    /**
     *
     */
    String ADMIN_DESTINATION_PAGE = "./admin/route.jsp";

    /**
     *
     */
    String ROUTE_ID_ATTRIBUTE = "routeId";

    /**
     *
     */
    String ROUTE_NAME_ATTRIBUTE = "routeName";

    /**
     *
     */
    String STOP_ID_ATTRIBUTE = "stopId";

    /**
     *
     */
    String STOP_LIST_ATTRIBUTE = "stopList";

    /**
     *
     */
    String STOP_TO_REMOVE_ATTRIBUTE = "stopToRemoveId";

}
