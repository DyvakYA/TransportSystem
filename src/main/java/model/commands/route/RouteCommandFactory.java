package ua.kpi.epam.transport.commands.route;

/**
 *
 * @author KIRIL
 */
public class RouteCommandFactory {

    /**
     *
     */
    public static final String FIND_ROUTES_BY_STOPS_COMMAND = "findRouteByStops";

    /**
     *
     */
    public static final String FIND_ALL_ROUTE_AND_STOPS_COMMAND = "findAllRouteAndStops";

    /**
     *
     */
    public static final String FIND_ALL_STOP_ON_ROUTE_COMMAND = "findAllStopOnRoute";

    /**
     *
     */
    public static final String UPDATE_ROUTE_COMMAND = "updateRoute";

    /**
     *
     */
    public static final String DELETE_ROUTE_COMMAND = "deleteRoute";

    /**
     *
     */
    public static final String FIND_ROUTE_BY_ID_COMMAND = "findRoute";

    /**
     *
     */
    public static final String FIND_ALL_ROUTE_COMMAND = "findAllRoute";

    /**
     *
     */
    public static final String CREATE_ROUTE_COMMAND = "createRoute";

    /**
     *
     */
    public static final String ADD_STOP_ON_ROOT_COMMAND = "addStopOnRoute";

    /**
     *
     */
    public static final String REMOVE_STOP_FROM_ROOT_COMMAND = "removeStopFromRoute";

	private static final RouteCommandFactory instance = new RouteCommandFactory();

	private RouteCommandFactory() {
		super();
	}

    /**
     *
     * @return
     */
    public static RouteCommandFactory getInstanse() {
		return instance;
	}

    /**
     *
     * @param name
     * @return
     */
    public RouteCommand getRouteCommand(String name) {
		switch (name) {

		case FIND_ROUTES_BY_STOPS_COMMAND: {
			return new FindRoutesByStopsCommand();
		}
		case FIND_ALL_ROUTE_AND_STOPS_COMMAND: {
			return new FindAllRouteAndStopsCommand();
		}
		case CREATE_ROUTE_COMMAND: {
			return new CreateRouteCommand();

		}
		case FIND_ALL_ROUTE_COMMAND: {
			return new FindAllRoutesCommand();

		}
		case FIND_ROUTE_BY_ID_COMMAND: {
			return new FindRouteById();
		}
		case DELETE_ROUTE_COMMAND: {
			return new DeleteRouteCommand();
		}
		case UPDATE_ROUTE_COMMAND: {
			return new UpdateRouteCommand();

		}
		case FIND_ALL_STOP_ON_ROUTE_COMMAND: {
			return new FindAllStopOnRouteCommand();
		}
		case ADD_STOP_ON_ROOT_COMMAND: {
			return new AddStopOnRouteCommand();
		}
		case REMOVE_STOP_FROM_ROOT_COMMAND: {
			return new RemoveStopFromRouteCommand();
		}
		default: {
			return null;
		}
		}
	}
}
